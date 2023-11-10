package pl.edu.pw.pamiwmvc.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Endpoints {
    @JsonProperty("publisher")
    private ModuleEndpoints publisher;
    @JsonProperty("author")
    private ModuleEndpoints author;
    @JsonProperty("book")
    private ModuleEndpoints book;
}
