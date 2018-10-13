package se.zeroplusx.musicapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class ArtistDto implements Serializable {

    @JsonAlias("id")
    private String mbid;

    private String description;

    @JsonAlias("release-groups")
    private List<AlbumsDto> albums;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<RelationDto> relations;

    @JsonIgnore
    public List<RelationDto> getRelations() {
        return relations;
    }

    @JsonProperty
    public void setRelations(List<RelationDto> relations) {
        this.relations = relations;
    }
}
