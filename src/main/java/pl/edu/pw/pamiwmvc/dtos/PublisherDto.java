package pl.edu.pw.pamiwmvc.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublisherDto {
    private int id;
    private String name;
    private List<BookDto> books;
}
