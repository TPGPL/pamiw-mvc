package pl.edu.pw.pamiwmvc.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("endpoints")
@Getter
@Setter
public class APIEndpoints {
    private String baseUrl;
    private ModuleEndpoints authors;
    private ModuleEndpoints books;
    private ModuleEndpoints publishers;
    @Getter
    @Setter
    public static class ModuleEndpoints {
        private String multi;
        private String single;
    }
}
