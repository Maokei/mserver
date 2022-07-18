package se.maokei.mserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class Media extends EntityMetadata {
    private String title;
    private Integer views;
    private String url;
    private String userId;
}
