package se.maokei.mserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class Media extends EntityMetadata {
    private String foreignId;
    private String title;
    private Integer views;
    private String url;
    private String userId;
    @JsonIgnore
    private String fileName;
    @JsonIgnore
    private byte[] content;
    //@JsonIgnore
    private String location;
}
