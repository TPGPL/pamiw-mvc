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
public class ModuleEndpoints {
    @JsonProperty("multi")
    private String multi;
    @JsonProperty("single")
    private String single;
}
