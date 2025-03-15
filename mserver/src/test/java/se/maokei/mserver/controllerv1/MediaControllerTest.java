package se.maokei.mserver.controllerv1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.maokei.mserver.TestcontainersConfiguration;
import se.maokei.mserver.model.Media;

import java.util.List;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class MediaControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    private static final String URL = "/api/v1/media";

    @Test
    public void getMediaTest() {
        ObjectMapper mapper = new ObjectMapper();
        webTestClient.get().uri(URL)
            .exchange().returnResult(Object.class).getResponseBody()
                .collectList().subscribe(res -> {
                    try {
                        System.out.println(res.toString());
                        String json = mapper.writeValueAsString(res);
                        List<Media> mlist = mapper.readValue(json, List.class);
                        Assertions.assertEquals(6, mlist.size(), "Should be 6 media");
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
            });
    }
}
