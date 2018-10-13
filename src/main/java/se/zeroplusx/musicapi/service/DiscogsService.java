package se.zeroplusx.musicapi.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import se.zeroplusx.musicapi.dto.ArtistDto;
import se.zeroplusx.musicapi.dto.RelationDto;
import se.zeroplusx.musicapi.exception.DiscogsServiceException;
import se.zeroplusx.musicapi.exception.ProfileNotFound;

import java.util.Map;

@Service
public class DiscogsService {

    private static final String DISCOGS_TYPE = "discogs";

    private static final String URL_SITE = "https://www.discogs.com/artist/";

    private static final String DOMAIN = "https://api.discogs.com";

    private final WebClient webClient = WebClient.create(DOMAIN);


    @Cacheable("profiles")
    public Mono<String> getProfileFormArtist(ArtistDto artistDto) {
        return webClient.get()
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
                    return (String) map.get("profile");
                })
                .cache();
    }


    private String getDiscogsId(ArtistDto artistDto) {
        RelationDto discogsRelation = artistDto.getRelations()
                .stream()
                .filter(relationDto -> {
                    return DISCOGS_TYPE.equals(relationDto.getType());
                })
                .findFirst()
                .get();

        return discogsRelation.getUrl()
                .getResource()
                .replace(URL_SITE, "");
    }

}
