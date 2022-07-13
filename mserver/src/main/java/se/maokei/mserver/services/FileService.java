package se.maokei.mserver.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Image;
import se.maokei.mserver.repository.FileRepository;
import se.maokei.mserver.repository.ImageDbRepository;

import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ImageDbRepository imageDbRepository;

    //@Autowired
    // private FileDbRepository fileDbRepository;

    public String save(Mono<FilePart> fileMono) throws Exception {
        String location = fileRepository.saveFile(fileMono);
        System.out.println(location);
        /*return imageDbRepository.save(new Image(imageName, location))
                .getId();*/
        Image img = new Image();
        img.setLocation(location);
        img.setName("test");
        Mono<Image> mono = imageDbRepository.save(img);
        return mono.block().getId();
    }

    public String save(byte[] bytes, String imageName) throws Exception {
        String location = fileRepository.saveFile(bytes, imageName);
        System.out.println(location);
        /*return imageDbRepository.save(new Image(imageName, location))
                .getId();*/
        Image img = new Image();
        img.setLocation(location);
        img.setName(imageName);
        Mono<Image> mono = imageDbRepository.save(img);
        return mono.block().getId();
    }

    public FileSystemResource find(String imageId) {
        /*Image image = imageDbRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));*/
        Mono<Image> image = imageDbRepository.findById(imageId);
        //return fileRepository.findFile(image.getLocation());
        return fileRepository.findFile(image.block().getLocation());
    }
}
