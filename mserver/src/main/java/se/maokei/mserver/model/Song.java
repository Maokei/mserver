package se.maokei.mserver.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Song extends EntityMetadata {
    private String title;
    private String length;
}
