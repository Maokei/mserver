package se.maokei.mserver.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.EntityMetadata;
import se.maokei.mserver.model.Image;
import se.maokei.mserver.repository.FileRepository;
import se.maokei.mserver.repository.ImageDbRepository;

@AllArgsConstructor
@Service
public class FileService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private FileRepository fileRepository;
    private ImageDbRepository imageDbRepository;

    public Mono<String> save(Mono<FilePart> fileMono) throws Exception {
        Mono<String> location = fileRepository.saveFile(fileMono);
        return location.flatMap(locRes -> {
            Image img = new Image();
            img.setLocation(locRes);
            img.setName("test");
            return imageDbRepository.save(img);
        }).flatMap(res -> Mono.just(res.getId()));
    }

    public String save(byte[] bytes, String imageName) throws Exception {
        String location = fileRepository.saveFile(bytes, imageName);
        Image img = new Image();
        img.setLocation(location);
        img.setName("test");
        return imageDbRepository.save(img).subscribe(EntityMetadata::getId).toString();
    }

    public FileSystemResource find(String imageId) {
        /*Image image = imageDbRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));*/
        //return fileRepository.findFile(image.getLocation());
        return (FileSystemResource) imageDbRepository.findById(imageId).subscribe(res -> fileRepository.findFile(res.getLocation()));
    }

    public Mono<FileSystemResource> monoFind(String imageId) {
        return imageDbRepository.findById(imageId).flatMap(
            metadata -> fileRepository.findFileMono(metadata.getLocation()
            )
        );
    }
}
