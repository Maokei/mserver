package se.maokei.mserver.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.maokei.mserver.model.Media;
import se.maokei.mserver.model.MediaUtils;
import se.maokei.mserver.services.MediaMetadataService;

import java.io.File;

@SpringBootTest
public class MediaMetadataServiceTest {
    @Autowired
    private MediaMetadataService mediaMetadataService;

    //@Test
    public void readMetadataFromMp3TitleTest() {
        Media media = new Media();
        media.setLocation("test_files/audio.mp3");
        File testFile = MediaUtils.mediaToFile.apply(media);
        if(!testFile.exists())
            Assertions.fail(String.format("Test file is missing: %s", media.getLocation()));
        mediaMetadataService.getMetadata(media);
    }

    //@Test
    public void writeMetadataFromMp3TitleTest() {

    }
}
