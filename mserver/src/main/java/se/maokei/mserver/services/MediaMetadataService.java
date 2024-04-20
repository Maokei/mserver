package se.maokei.mserver.services;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.images.Artwork;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Media;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class MediaMetadataService {

  public void setMetadata() throws FieldDataInvalidException {
    /*File mp3File = new File("test_files/synthwave_duvet.mp3");
    AudioFile f = AudioFileIO.read(testFile);
    Tag tag = f.getTag();
    tag.setField(FieldKey.ARTIST,"Kings of Leon");*/
    //f.commit();
  }
  public Mono<Tag> getMetadata(Media media ) {
    //AudioFile f = AudioFileIO.read(mp3File);

    return Mono.empty();
  }

  public void getArtwork(Media media) {

  }

  public void setArtwork(Artwork artwork, Long mediaId) {

  }
  public static void main(String[] args) throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
    File mp3File = new File("test_files/audio.mp3");
    Path path = Path.of("test_files/never_fade_away.mp3");
    System.out.println(Files.exists(path));
    AudioFile f = AudioFileIO.read(mp3File);
    Tag tag = f.getTag();
    AudioHeader header = f.getAudioHeader();

    System.out.println(tag.getFirst(FieldKey.TITLE));
    System.out.println(tag.getFirst(FieldKey.ARTIST));
    System.out.println(tag.getFirst(FieldKey.ALBUM));
    System.out.println(f.getAudioHeader().getTrackLength());
    System.out.println(f.getAudioHeader().getSampleRateAsNumber());
    //System.out.println(f);
    /*
    tag.getFirst(FieldKey.ARTIST);
    tag.getFirst(FieldKey.ALBUM);
    tag.getFirst(FieldKey.TITLE);
    tag.getFirst(FieldKey.COMMENT);
    tag.getFirst(FieldKey.YEAR);
    tag.getFirst(FieldKey.TRACK);
    tag.getFirst(FieldKey.DISC_NO);
    tag.getFirst(FieldKey.COMPOSER);
    tag.getFirst(FieldKey.ARTIST_SORT)
     */
  }
}
