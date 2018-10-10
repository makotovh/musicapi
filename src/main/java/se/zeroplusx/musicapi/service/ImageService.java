package se.zeroplusx.musicapi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.zeroplusx.musicapi.dto.AlbumImageDto;
import se.zeroplusx.musicapi.dto.AlbumImagesDto;

@Service
public class ImageService {

    private static final String URL_PATTERN = "http://coverartarchive.org/release-group/%s";

    public AlbumImageDto getAlbumImage(String albumId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(URL_PATTERN, albumId);
        ResponseEntity<AlbumImagesDto> entity = restTemplate.getForEntity(url, AlbumImagesDto.class);
        AlbumImagesDto albumImagesDto = entity.getBody();
        return albumImagesDto.getImages()
                .stream()
                .filter(AlbumImageDto::isFront)
                .findFirst()
                .get();
    }
}
