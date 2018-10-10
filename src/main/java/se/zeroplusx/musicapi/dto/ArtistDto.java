package se.zeroplusx.musicapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ArtistDto implements Serializable {

    @JsonAlias("id")
    private String mbid;

    private String description;

    @JsonAlias("release-groups")
    private List<AlbumsDto> albums;

    //@JsonFilter
    private List<RelationDto> relations;
}
