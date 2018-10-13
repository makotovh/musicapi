package se.zeroplusx.musicapi.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import se.zeroplusx.musicapi.dto.ArtistDto;
import se.zeroplusx.musicapi.dto.RelationDto;
import se.zeroplusx.musicapi.dto.RelationUrlDto;

import java.util.Map;

@Service
public class DiscogsService {

    private static final String URL_API = "https://api.discogs.com/artists/";

    private static final String DISCOGS_TYPE = "discogs";

    private static final String URL_SITE = "https://www.discogs.com/artist/";

    private static final String DOMAIN = "https://api.discogs.com";

    private final WebClient webClient = WebClient.create(DOMAIN);

    @Cacheable("profiles2")
    public String getProfileFormArtist2(ArtistDto artist) {
        RelationDto relationDiscogs = getRelation(artist);
        String url = resolsveApiUrl(relationDiscogs.getUrl());

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> discogsDto = restTemplate.getForObject(url, Map.class);
        return discogsDto.get("profile");
    }

    @Cacheable("profiles")
    public Mono<String> getProfileFormArtist(ArtistDto artist) {
        String discogsId = getDiscogsId(artist);
       return webClient.get().uri("/artists/{discogsId}", discogsId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(clientResponse -> clientResponse.toEntity(Map.class))
                //.block()
                //.getBody().get("profile");
                .map(mapResponseEntity -> (String) mapResponseEntity.getBody().get("profile"));
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

    private RelationDto getRelation(ArtistDto artist) {
        return artist.getRelations()
                .stream()
                .filter(relationDto -> {
                    return DISCOGS_TYPE.equals(relationDto.getType());
                })
                .findFirst()
                .get();
    }

    private String resolsveApiUrl(RelationUrlDto url) {
        return url.getResource().replace(URL_SITE, URL_API);
    }



    @Cacheable("artists4")
    public Mono<ArtistDto> fillDescription(ArtistDto artistDto) {
        return webClient.get()
                .uri("/artists/{discogsId}", getDiscogsId(artistDto))
                .retrieve()
                .bodyToMono(Map.class)
                .map(map -> {
                    artistDto.setDescription((String) map.get("profile"));
                    return artistDto;
                }).cache();
    }

}
