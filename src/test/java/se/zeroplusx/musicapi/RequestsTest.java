package se.zeroplusx.musicapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.zeroplusx.musicapi.dto.Artist;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RequestsTest {

    private static final String CHARLIE_BROWN_JR_MBID = "29b550f2-04a3-4733-b6f5-4e347fac7e11";
    private static final String INVALID_MBID = "29b550f2-04a3-4733-b6f5-4e347fac7e12";

    @Autowired
    private WebTestClient webClient;

    @Test
    void getArtist() {
        webClient.get()
                .uri("/artists/{mbid}", CHARLIE_BROWN_JR_MBID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Artist.class);
    }

    @Test
    void shouldReturn404WhenDontFoundArtist() {
        webClient.get()
                .uri("/artists/{mbid}", INVALID_MBID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is4xxClientError();
    }
}
