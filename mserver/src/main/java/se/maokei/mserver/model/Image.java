package se.maokei.mserver.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Image extends EntityMetadata {
    private byte[] content;
    private String name;
    private String location;
}
