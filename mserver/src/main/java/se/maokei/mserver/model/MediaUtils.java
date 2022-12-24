package se.maokei.mserver.model;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.function.Function;

public interface MediaUtils {
    Function<Media, File> mediaToFile = media -> new File(media.getLocation());
    Function<Media, Resource> mediaToResource = media ->  new FileSystemResourceLoader().getResource(media.getLocation());
}
