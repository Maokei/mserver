package se.maokei.mserver.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import se.maokei.mserver.model.Photo;
import se.maokei.mserver.repository.PhotoRepository;

import java.io.IOException;
import java.util.UUID;

@AllArgsConstructor
@Service
/* *
 * PhotoService
 * */
public class PhotoService { //TODO rename to ImageService
    private PhotoRepository photoRepo;

    public String addPhoto(String title, MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setTitle(title);
        //TODO fix this service
        /*
        photo.setImage(
                new Binary(
                        BsonBinarySubType.BINARY,
                        file.getBytes())
        );*/
        //photo = photoRepo.insert(photo); return photo.getId();
        return "";
    }

    public Photo getPhoto(UUID id) {
        return photoRepo.findById(id).block();
    }
}