package se.zeroplusx.musicapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.zeroplusx.musicapi.dto.ArtistDto;
import se.zeroplusx.musicapi.dto.RelationDto;
import se.zeroplusx.musicapi.dto.RelationUrlDto;

import java.util.Map;

@Service
public class DiscogsService {

    private static final String URL_API = "https://api.discogs.com/artists/";

    private static final String DISCOGS_TYPE = "discogs";

    private static final String URL_SITE = "https://www.discogs.com/artist/";

    public String getProfileFormArtist(ArtistDto artist) {
        RelationDto relationDiscogs = artist.getRelations()
                .stream()
                .filter(relationDto -> {
                    return DISCOGS_TYPE.equals(relationDto.getType());
                })
                .findFirst()
                .get();
        String url = resolsveApiUrl(relationDiscogs.getUrl());

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> discogsDto = restTemplate.getForObject(url, Map.class);
        return discogsDto.get("profile");
    }

    private String resolsveApiUrl(RelationUrlDto url) {
        return url.getResource().replace(URL_SITE, URL_API);
    }


}
