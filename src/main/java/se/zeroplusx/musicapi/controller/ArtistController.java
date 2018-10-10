package se.zeroplusx.musicapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.zeroplusx.musicapi.dto.ArtistDto;
import se.zeroplusx.musicapi.service.DiscogsService;
import se.zeroplusx.musicapi.service.MusicBrainzService;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private MusicBrainzService musicBrainzService;
    private DiscogsService discogsService;

    public ArtistController(MusicBrainzService musicBrainzService, DiscogsService discogsService) {
        this.musicBrainzService = musicBrainzService;
        this.discogsService = discogsService;
    }

    @GetMapping("/{mbid}")
    public ArtistDto test(@PathVariable String mbid) {

        ArtistDto artist = musicBrainzService.getArtist(mbid, "url-rels+release-groups");
        artist.setDescription(discogsService.getProfileFormArtist(artist));
        return artist;
    }
}
