package se.zeroplusx.musicapi.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Album implements Serializable {

    private static final String URL_PATTERN = "http://coverartarchive.org/release-group/%s/front";

    private String id;

    private String title;

    public String getImage() {
        return String.format(URL_PATTERN, this.id);
    }

}
