package se.zeroplusx.musicapi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.zeroplusx.musicapi.dto.ArtistDto;


@Service
public class MusicBrainzService {

    private static final String URL_PATTERN = "https://musicbrainz.org/ws/2/%s/%s?fmt=json&inc=%s";

    public ArtistDto getArtist(String entityId, String includes) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(URL_PATTERN, "artist", entityId, includes);
        ResponseEntity<ArtistDto> response = restTemplate.getForEntity(url, ArtistDto.class);
        return response.getBody();
    }
}
