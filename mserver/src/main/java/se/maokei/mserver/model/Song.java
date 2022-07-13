package se.maokei.mserver.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Song {
    private String id;
    private String title;
    private String length;
}
