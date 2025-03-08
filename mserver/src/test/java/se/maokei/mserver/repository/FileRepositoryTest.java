package se.maokei.mserver.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;
import se.maokei.mserver.TestcontainersConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
public class FileRepositoryTest {
    @Autowired
    FileRepository fileRepository;
    @Autowired
    private Environment env;

    @Test
    public void saveTest() throws IOException {
        Path tempDirPath = null;
        File file = new File("file:test_files/audio.mp3");
        System.out.println(file.getAbsolutePath());
        //byte[] fileBytes = Files.readAllBytes(file.toPath());
        //Mono<File> mFile = Mono.just(file);
        //FilePart filePart = Part
        try {
            tempDirPath = Files.createTempDirectory("tempDir");
            System.out.printf("Temporary folder created at %s%n", tempDirPath);
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
        //fileRepository.saveFile();
    }
}
