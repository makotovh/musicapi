package se.zeroplusx.musicapi.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlbumImageDto implements Serializable {

    private boolean front;

    private boolean back;

    private String image;
}
