package se.maokei.mserver;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.maokei.mserver.model.Media;
import se.maokei.mserver.model.Role;
import se.maokei.mserver.model.User;
import se.maokei.mserver.repository.CommentRepository;
import se.maokei.mserver.repository.MediaRepository;
import se.maokei.mserver.repository.UserRepository;
import se.maokei.mserver.services.FileService;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Component
class DataInitializer implements CommandLineRunner {
  private final UserRepository userRepository;
  private final CommentRepository commentRepository;
  private final FileService fileService;
  private final MediaRepository mediaRepository;
  private final PasswordEncoder passwordEncoder;
  private ResourceLoader resourceLoader;
  private static final String VIDEO_FORMAT = "classpath:videos/%s.mp4";
  private static final String AUDIO_FORMAT = "classpath:audios/%s.mp3";


  @Override
  public void run(String[] args) throws Exception {
    log.info("Starting data initialization  ...");
    User userOne = User.builder()
            .password(passwordEncoder.encode("password"))
            .username("cat")
            .email("cat@gmail.com")
            .roles(List.of(Role.ROLE_ADMIN))
            .enabled(true)
            .build();
    userRepository.save(userOne).subscribe();
    userRepository.save(new User("user", passwordEncoder.encode("password"), "user@gmail.com",true, List.of(Role.ROLE_USER)))
        .subscribe();
    //username:password-> admin:admin
    userRepository.save(new User("admin", passwordEncoder.encode("password"), "admin@gmail.com",true, List.of(Role.ROLE_ADMIN)))
            .subscribe();
    userRepository.findAll().subscribe(System.out::println);

    Media netflixCyberpunkMedia = Media.builder()
        .location("classpath:videos/netflix_cyberpunk.mp4")
        .fileName("netflix_cyberpunk.mp4")
        .title("Cyberpunk trailer")
        .foreignId("c7192772-0c1c-11ed-861d-0242ac120001").build();
    Media videoMedia = Media.builder()
        .location("classpath:videos/video.mp4")
        .fileName("video.mp4")
        .title("Toy story trailer")
        .foreignId("c7192772-0c1c-11ed-861d-0242ac120002").build();
    Media audio = Media.builder()
        .location("classpath:audios/song.mp3")
        .fileName("song.mp3")
        .title("Piano")
        .foreignId("c7192772-0c1c-11ed-861d-0242ac120003").build();
    Media neverFade = Media.builder()
        .location("classpath:audios/never_fade_away.mp3")
        .fileName("never_fade_away.mp3")
        .title("Never fade away")
        .foreignId("c7192772-0c1c-11ed-861d-0242ac120004").build();
    Media duvet = Media.builder()
        .location("classpath:audios/synthwave_duvet.mp3")
        .fileName("synthwave_duvet.mp3")
        .title("Cyberpunk trailer")
        .foreignId("c7192772-0c1c-11ed-861d-0242ac120005").build();
    Media plasticLove = Media.builder()
        .location("classpath:audios/synthwave_plastic_love.webp")
        .fileName("synthwave_plastic_love.webp")
        .title("Plastic Love - synthwave remix")
        .foreignId("c7192772-0c1c-11ed-861d-0242ac120006").build();
    mediaRepository.save(netflixCyberpunkMedia).subscribe();
    mediaRepository.save(videoMedia).subscribe();
    mediaRepository.save(audio).subscribe();
    mediaRepository.save(neverFade).subscribe();
    mediaRepository.save(duvet).subscribe();
    mediaRepository.save(plasticLove).subscribe();

  }

}