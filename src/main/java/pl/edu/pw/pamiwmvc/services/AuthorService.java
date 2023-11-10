package pl.edu.pw.pamiwmvc.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.edu.pw.pamiwmvc.dtos.AuthorDto;
import pl.edu.pw.pamiwmvc.dtos.Endpoints;
import pl.edu.pw.pamiwmvc.dtos.ServiceResponse;
import pl.edu.pw.pamiwmvc.utils.ConfigHandler;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class AuthorService {
    private final HttpClient client;
    private final ObjectMapper mapper;
    private final String BASE_URL = "http://localhost:8080/api";
    private final Endpoints endpoints;

    public AuthorService() {
        this.client = HttpClient.newHttpClient();
        this.endpoints = ConfigHandler.getConfig();
        this.mapper = new ObjectMapper();
    }

    public List<AuthorDto> getAll() {
        try {
            var uri = BASE_URL + endpoints.getAuthor().getMulti();
            var request = HttpRequest.newBuilder(new URI(uri)).GET().build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.body() == null) return null;

            return mapper.readValue(response.body(), new TypeReference<ServiceResponse<List<AuthorDto>>>() {
            }).getData();

        } catch (Exception e) {
            return null;
        }
    }

    public void create(AuthorDto dto) {
        try {
            var uri = BASE_URL + endpoints.getAuthor().getMulti();
            var data = mapper.writeValueAsString(dto);
            var request = HttpRequest.newBuilder(new URI(uri))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(data))
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.body() != null) {
                var serviceResponse = mapper.readValue(response.body(), new TypeReference<ServiceResponse<AuthorDto>>() {
                });

                if (!serviceResponse.isWasSuccessful()) {
                    throw new Exception(serviceResponse.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: Failed to create author.");

            if (e.getMessage() != null)
                System.out.println("Message: " + e.getMessage());
        }
    }

    public void update(int id, AuthorDto dto) {
        try {
            var uri = BASE_URL + String.format(endpoints.getAuthor().getSingle(), id);
            var data = mapper.writeValueAsString(dto);
            var request = HttpRequest.newBuilder(new URI(uri))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(data))
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.body() != null) {
                var serviceResponse = mapper.readValue(response.body(), new TypeReference<ServiceResponse<AuthorDto>>() {
                });

                if (!serviceResponse.isWasSuccessful()) {
                    throw new Exception(serviceResponse.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: Failed to update author.");

            if (e.getMessage() != null)
                System.out.println("Message: " + e.getMessage());
        }
    }

    public void delete(int id) {
        try {
            var uri = BASE_URL + String.format(endpoints.getAuthor().getSingle(), id);
            var request = HttpRequest.newBuilder(new URI(uri))
                    .DELETE()
                    .build();

            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println("ERROR: Failed to delete author.");

            if (e.getMessage() != null)
                System.out.println("Message: " + e.getMessage());
        }
    }
}
