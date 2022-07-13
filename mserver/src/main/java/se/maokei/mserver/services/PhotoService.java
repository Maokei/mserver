package se.maokei.mserver.services;

import lombok.AllArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import se.maokei.mserver.model.Photo;
import se.maokei.mserver.repository.PhotoRepository;

import java.io.IOException;

@AllArgsConstructor
@Service
/**
 * PhotoService
 * */
public class PhotoService {
    private PhotoRepository photoRepo;

    public String addPhoto(String title, MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setTitle(title);
        photo.setImage(
                new Binary(
                        BsonBinarySubType.BINARY,
                        file.getBytes())
        );
        //photo = photoRepo.insert(photo); return photo.getId();
        return "";
    }

    public Photo getPhoto(String id) {
        return photoRepo.findById(id).block();
    }
}