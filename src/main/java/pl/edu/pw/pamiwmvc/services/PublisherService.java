package pl.edu.pw.pamiwmvc.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.pamiwmvc.dtos.APIEndpoints;
import pl.edu.pw.pamiwmvc.dtos.PublisherDto;
import pl.edu.pw.pamiwmvc.dtos.ServiceResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class PublisherService {
    private final HttpClient client;
    private final ObjectMapper mapper;
    private final APIEndpoints endpoints;

    @Autowired
    public PublisherService(APIEndpoints endpoints) {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
        this.endpoints = endpoints;
    }

    public List<PublisherDto> getAll() {
        try {
            var uri = endpoints.getBaseUrl() + endpoints.getPublishers().getMulti();
            var request = HttpRequest.newBuilder(new URI(uri)).GET().build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.body() == null) return null;

            return mapper.readValue(response.body(), new TypeReference<ServiceResponse<List<PublisherDto>>>() {
            }).getData();

        } catch (Exception e) {
            return null;
        }
    }

    public PublisherDto get(int id) {
        try {
            var uri = endpoints.getBaseUrl() + String.format(endpoints.getPublishers().getSingle(), id);
            var request = HttpRequest.newBuilder(new URI(uri)).GET().build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.body() == null) return null;

            return mapper.readValue(response.body(), new TypeReference<ServiceResponse<PublisherDto>>() {
            }).getData();

        } catch (Exception e) {
            return null;
        }
    }

    public void create(PublisherDto dto) {
        try {
            var uri = endpoints.getBaseUrl() + endpoints.getPublishers().getMulti();
            var data = mapper.writeValueAsString(dto);
            var request = HttpRequest.newBuilder(new URI(uri))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(data))
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.body() != null) {
                var serviceResponse = mapper.readValue(response.body(), new TypeReference<ServiceResponse<PublisherDto>>() {
                });

                if (!serviceResponse.isWasSuccessful()) {
                    throw new Exception(serviceResponse.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: Failed to create publisher.");

            if (e.getMessage() != null)
                System.out.println("Message: " + e.getMessage());
        }
    }

    public void update(int id, PublisherDto dto) {
        try {
            var uri = endpoints.getBaseUrl() + String.format(endpoints.getPublishers().getSingle(), id);
            var data = mapper.writeValueAsString(dto);
            var request = HttpRequest.newBuilder(new URI(uri))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(data))
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.body() != null) {
                var serviceResponse = mapper.readValue(response.body(), new TypeReference<ServiceResponse<PublisherDto>>() {
                });

                if (!serviceResponse.isWasSuccessful()) {
                    throw new Exception(serviceResponse.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: Failed to update publisher.");

            if (e.getMessage() != null)
                System.out.println("Message: " + e.getMessage());
        }
    }

    public void delete(int id) {
        try {
            var uri = endpoints.getBaseUrl() + String.format(endpoints.getPublishers().getSingle(), id);
            var request = HttpRequest.newBuilder(new URI(uri))
                    .DELETE()
                    .build();

            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println("ERROR: Failed to delete publisher.");

            if (e.getMessage() != null)
                System.out.println("Message: " + e.getMessage());
        }
    }
}
