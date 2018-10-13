package se.zeroplusx.musicapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import se.zeroplusx.musicapi.dto.ArtistDto;
import se.zeroplusx.musicapi.service.DiscogsService;
import se.zeroplusx.musicapi.service.MusicBrainzService;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final MusicBrainzService musicBrainzService;
    private final DiscogsService discogsService;

    public ArtistController(MusicBrainzService musicBrainzService, DiscogsService discogsService) {
        this.musicBrainzService = musicBrainzService;
        this.discogsService = discogsService;
    }

    @GetMapping("/{mbid}")
    public Mono<ArtistDto> getInfo(@PathVariable String mbid) {
        return musicBrainzService.getArtist(mbid);
    }

}
