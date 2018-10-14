package se.zeroplusx.musicapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Data
public class Artist implements Serializable {

    @JsonAlias("id")
    private String mbid;

    private String description;

    @JsonAlias("release-groups")
    private List<Album> albums;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<Relation> relations;

    @JsonIgnore
    public List<Relation> getRelations() {
        return relations;
    }

    @JsonProperty
    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }
}
