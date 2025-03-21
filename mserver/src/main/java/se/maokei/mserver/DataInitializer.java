package se.maokei.mserver;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Media;
import se.maokei.mserver.model.Playlist;
import se.maokei.mserver.model.Role;
import se.maokei.mserver.model.User;
import se.maokei.mserver.repository.CommentRepository;
import se.maokei.mserver.repository.MediaRepository;
import se.maokei.mserver.repository.PlaylistRepository;
import se.maokei.mserver.repository.UserRepository;
import se.maokei.mserver.services.FileService;

import java.util.List;

//@Profile("!test")
@AllArgsConstructor
@Slf4j
@Component
class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MediaRepository mediaRepository;
    private final CommentRepository commentRepository;
    private final FileService fileService;
    private final PlaylistRepository playlistRepository;
    private final ResourceLoader resourceLoader;

    private static final String R_VIDEO_FORMAT = "classpath:videos/%s.mp4";
    private static final String R_AUDIO_FORMAT = "classpath:audios/%s.mp3";


    @Override
    public void run(String[] args) throws Exception {
        log.info("Starting data initialization  ...");

        User userOne = User.builder().password(passwordEncoder.encode("password")).username("cat").email("cat@gmail.com").roles(List.of(Role.ROLE_ADMIN)).enabled(true).build();
        User userTwo = new User("user1", passwordEncoder.encode("password"), "user1@gmail.com", List.of(Role.ROLE_USER), true, true);
        addInitUser(userOne).subscribe();
        addInitUser(userTwo).subscribe();
        //addInitUser(new User("user", passwordEncoder.encode("password"), "user@gmail.com", true, List.of(Role.ROLE_USER))).subscribe();
        //username:password-> admin:admin
        //addInitUser(new User("admin", passwordEncoder.encode("password"), "admin@gmail.com", true, List.of(Role.ROLE_ADMIN))).subscribe();

        userRepository.findAll().subscribe(System.out::println);
        log.info("Initialization of testing data ...");

        // Video
        Media netflixCyberpunkMedia = Media.builder()
                .location("file:test_files/netflix_cyberpunk.mp4")
                .filename("netflix_cyberpunk.mp4")
                .title("Cyberpunk: Edgerunners trailer")
                .foreignId("c7192772-0c1c-11ed-861d-0242ac120001")
                .userId(userOne.getId())
                .type(Media.MediaType.VIDEO)
                .build();
        Media videoMedia = Media.builder()
                .location("file:test_files/video.mp4")
                .filename("video.mp4")
                .title("Toy story trailer")
                .foreignId("c7192772-0c1c-11ed-861d-0242ac120002")
                .userId(userOne.getId())
                .type(Media.MediaType.VIDEO)
                .build();
        addInitMedia(netflixCyberpunkMedia).subscribe();
        addInitMedia(videoMedia).subscribe();

        // Audio
        Media audio = Media.builder()
                .location("file:test_files/audio.mp3")
                .filename("song.mp3").title("HxH Piano")
                .foreignId("c7192772-0c1c-11ed-861d-0242ac120003")
                .userId(userOne.getId())
                .type(Media.MediaType.AUDIO)
                .build();
        Media neverFade = Media.builder()
                .location("file:test_files/never_fade_away.mp3")
                .filename("never_fade_away.mp3")
                .title("Never fade away")
                .foreignId("c7192772-0c1c-11ed-861d-0242ac120004")
                .userId(userOne.getId())
                .type(Media.MediaType.AUDIO)
                .build();
        Media duvet = Media.builder()
                .location("file:test_files/synthwave_duvet.mp3")
                .filename("synthwave_duvet.mp3")
                .title("Synthwave duvet")
                .foreignId("c7192772-0c1c-11ed-861d-0242ac120005")
                .userId(userOne.getId())
                .type(Media.MediaType.AUDIO)
                .build();
        Media daycore = Media.builder()
                .location("file:test_files/Mr.Kitty - Destroy me (Daycore).mp3")
                .filename("Mr.Kitty - Destroy me (Daycore).mp3")
                .title("Mr.Kitty - Destroy me (Daycore)")
                .foreignId("c7192772-0c1c-11ed-861d-0242ac120006")
                .userId(userOne.getId())
                .type(Media.MediaType.AUDIO)
                .build();


        addInitMedia(audio).subscribe();
        addInitMedia(neverFade).subscribe();
        addInitMedia(duvet).subscribe();
        addInitMedia(daycore).subscribe();
        mediaRepository.findAll(Sort.unsorted()).subscribe(res -> System.out.println("Media: " + res.getTitle()));

        //Playlist p = new Playlist();
        //p.setPlaylistName("Fun list");
        //addInitPlaylist(p).subscribe();
    }

    /*private Mono<Playlist> addInitPlaylist(Playlist playlist) {
        return mediaRepository.findByForeignId("c7192772-0c1c-11ed-861d-0242ac120005").map(t-> {
            playlist.addMediaId(t);
            return t;
        }).then(this.playlistRepository.save(playlist));
    }
    }*/

    private Mono<Media> addInitMedia(Media media) {
        return mediaRepository.findByForeignId(media.getForeignId())
        .switchIfEmpty(mediaRepository.save(media).doFinally(c -> {
            log.info("Init media added: " +  media.getTitle());
        }));
    }


    private Mono<User> addInitUser(User user) {
        return userRepository.findByEmail(user.getEmail())
        .switchIfEmpty(userRepository.save(user).doFinally(c -> {
            log.info("Init user added: " +  user.getUsername());
        }));
    }
}