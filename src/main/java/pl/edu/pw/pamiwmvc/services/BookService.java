package pl.edu.pw.pamiwmvc.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.pamiwmvc.dtos.APIEndpoints;
import pl.edu.pw.pamiwmvc.dtos.BookDto;
import pl.edu.pw.pamiwmvc.dtos.ServiceResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class BookService {
    private final HttpClient client;
    private final ObjectMapper mapper;
    private final APIEndpoints endpoints;

    @Autowired
    public BookService(APIEndpoints endpoints) {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
        this.endpoints = endpoints;
    }

    public List<BookDto> getAll() {
        try {
            var uri = endpoints.getBaseUrl() + endpoints.getBooks().getMulti();
            var request = HttpRequest.newBuilder(new URI(uri)).GET().build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.body() == null) return null;

            return mapper.readValue(response.body(), new TypeReference<ServiceResponse<List<BookDto>>>() {
            }).getData();

        } catch (Exception e) {
            return null;
        }
    }

    public BookDto get(int id) {
        try {
            var uri = endpoints.getBaseUrl() + String.format(endpoints.getBooks().getSingle(), id);
            var request = HttpRequest.newBuilder(new URI(uri)).GET().build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.body() == null) return null;

            return mapper.readValue(response.body(), new TypeReference<ServiceResponse<BookDto>>() {
            }).getData();

        } catch (Exception e) {
            return null;
        }
    }

    public void create(BookDto dto) {
        try {
            var uri = endpoints.getBaseUrl() + endpoints.getBooks().getMulti();
            var data = mapper.writeValueAsString(dto);
            var request = HttpRequest.newBuilder(new URI(uri))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(data))
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.body() != null) {
                var serviceResponse = mapper.readValue(response.body(), new TypeReference<ServiceResponse<BookDto>>() {
                });

                if (!serviceResponse.isWasSuccessful()) {
                    throw new Exception(serviceResponse.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: Failed to create book.");

            if (e.getMessage() != null)
                System.out.println("Message: " + e.getMessage());
        }
    }

    public void update(int id, BookDto dto) {
        try {
            var uri = endpoints.getBaseUrl() + String.format(endpoints.getBooks().getSingle(), id);
            var data = mapper.writeValueAsString(dto);
            var request = HttpRequest.newBuilder(new URI(uri))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(data))
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.body() != null) {
                var serviceResponse = mapper.readValue(response.body(), new TypeReference<ServiceResponse<BookDto>>() {
                });

                if (!serviceResponse.isWasSuccessful()) {
                    throw new Exception(serviceResponse.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: Failed to update book.");

            if (e.getMessage() != null)
                System.out.println("Message: " + e.getMessage());
        }
    }

    public void delete(int id) {
        try {
            var uri = endpoints.getBaseUrl() + String.format(endpoints.getBooks().getSingle(), id);
            var request = HttpRequest.newBuilder(new URI(uri))
                    .DELETE()
                    .build();

            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println("ERROR: Failed to delete book.");

            if (e.getMessage() != null)
                System.out.println("Message: " + e.getMessage());
        }
    }
}
