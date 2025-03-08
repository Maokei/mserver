package se.maokei.mserver.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import se.maokei.mserver.model.Media;
import se.maokei.mserver.repository.MediaRepository;

import java.io.IOException;
import java.util.UUID;

@AllArgsConstructor
@Service
/* *
 * PhotoService
 * */
public class PhotoService { //TODO rename to ImageService
    private MediaRepository repo;

    public String addPhoto(String title, MultipartFile file) throws IOException {
        Media media = Media.builder()
                .title(title)
                .filename(file.getName())
                .size(file.getSize())
                .build();
        //TODO fix this service
        /*
        photo.setImage(
                new Binary(
                        BsonBinarySubType.BINARY,
                        file.getBytes())
        );*/
        //photo = photoRepo.insert(photo); return photo.getId();
        media.setContent(file.getBytes());
        repo.save(media);
        return "";
    }

    public Media getPhoto(UUID id) {
        return repo.findById(id).block();
    }
}