package se.maokei.mserver.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Image {
    @Id
    private String id;
    private byte[] content;
    private String name;
    private String location;
}
