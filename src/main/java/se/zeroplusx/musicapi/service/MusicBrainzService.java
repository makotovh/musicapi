package se.zeroplusx.musicapi.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import se.zeroplusx.musicapi.dto.ArtistDto;
import se.zeroplusx.musicapi.exception.ArtistNotFoundException;
import se.zeroplusx.musicapi.exception.MusicBrainzServiceException;


@Service
public class MusicBrainzService {

    private static final String MUSIC_BRAINZ_URL = "https://musicbrainz.org";

    private final WebClient wcMusicBrainz = WebClient.create(MUSIC_BRAINZ_URL);

    @Cacheable("artistDtos")
    public Mono<ArtistDto> getArtist(String mbid) {
       return wcMusicBrainz.get()
                .uri("/ws/2/artist/{mbid}?fmt=json&inc=url-rels+release-groups", mbid)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    if (clientResponse.statusCode().value() == 404) {
                        return Mono.error(new ArtistNotFoundException("Artist not found"));
                    } else {
                        return Mono.error(new MusicBrainzServiceException("Error on connect to MusicBrainz api"));
                    }
                })
                .bodyToMono(ArtistDto.class)
                .cache();
    }

}
