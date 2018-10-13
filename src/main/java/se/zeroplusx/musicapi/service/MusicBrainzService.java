package se.zeroplusx.musicapi.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import se.zeroplusx.musicapi.dto.ArtistDto;
import se.zeroplusx.musicapi.dto.RelationDto;
import se.zeroplusx.musicapi.exception.ArtistNotFoundException;
import se.zeroplusx.musicapi.exception.DiscogsServiceException;
import se.zeroplusx.musicapi.exception.MusicBrainzServiceException;
import se.zeroplusx.musicapi.exception.ProfileNotFound;

import java.util.Map;


@Service
public class MusicBrainzService {

    private static final String MUSIC_BRAINZ_URL = "https://musicbrainz.org";

    private static final String DISCOGS_URL = "https://api.discogs.com";

    private final WebClient wcMusicBrainz = WebClient.create(MUSIC_BRAINZ_URL);

    private final WebClient wcDiscogs = WebClient.create(DISCOGS_URL);

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
                .flatMap(artistDto -> {
                    return wcDiscogs.get()
                            .uri("/artists/{discogsId}", getDiscogsId(artistDto))
                            .retrieve()
                            .onStatus(HttpStatus::isError, clientResponse -> {
                                if (clientResponse.statusCode().value() == 404) {
                                    return Mono.error(new ProfileNotFound("Artist Profile not found"));
                                } else {
                                    return Mono.error(new DiscogsServiceException("Error on connect to MusicBrainz api"));
                                }
                            })
                            .bodyToMono(Map.class)
                            .map(map -> {
                                artistDto.setDescription((String) map.get("profile"));
                                return artistDto;
                            })
                            .cache();
                })
                .cache();
    }

    private String getDiscogsId(ArtistDto artistDto) {
        RelationDto discogsRelation = artistDto.getRelations()
                .stream()
                .filter(relationDto -> {
                    return "discogs".equals(relationDto.getType());
                })
                .findFirst()
                .get();

        return discogsRelation.getUrl()
                .getResource()
                .replace("https://www.discogs.com/artist/", "");
    }
}
