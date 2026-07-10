package se.maokei.mserver.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table("photos")
public class Photo extends EntityMetadata {
    @Schema(name = "photoId", description = "The generated ID when saved into the database")
    @Id
    private UUID photoId;
    private String title;

    @Override
    public void generateId() {
        if (photoId == null)
            this.photoId = UUID.randomUUID();
    }
}
