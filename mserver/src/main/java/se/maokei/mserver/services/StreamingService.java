package se.maokei.mserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StreamingService {
    //logging
    private static final String AUDIO_FORMAT = "classpath:audios/%s.mp3";
    private static final String VIDEO_FORMAT = "classpath:videos/%s.mp4";

    @Autowired
    private ResourceLoader resourceLoader;

    public Mono<Resource> getAudio(String fileName) {
        return Mono.fromSupplier(() -> this.resourceLoader.getResource( String.format(AUDIO_FORMAT, fileName)));
    }

    public Mono<Resource> getVideo(String fileName) {
        return Mono.fromSupplier(() -> this.resourceLoader.getResource( String.format(VIDEO_FORMAT, fileName)));
    }
}