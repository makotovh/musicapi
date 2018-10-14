package se.zeroplusx.musicapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import se.zeroplusx.musicapi.dto.Artist;
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
    public Mono<Artist> getInfo(@PathVariable String mbid) {
        return musicBrainzService.getArtist(mbid)
                .flatMap(artist -> {
                    return discogsService.getProfileFormArtist(artist)
                            .map(s -> {
                                artist.setDescription(s);
                                return artist;
                            });
                }).cache();
    }
}
