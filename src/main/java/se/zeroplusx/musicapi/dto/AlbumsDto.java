package se.zeroplusx.musicapi.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlbumsDto implements Serializable {

    private static final String URL_PATTERN = "http://coverartarchive.org/release-group/%s/front";

    private String id;

    private String title;

    //private String image;

    public String getImage() {
        return String.format(URL_PATTERN, this.id);
    }

}
