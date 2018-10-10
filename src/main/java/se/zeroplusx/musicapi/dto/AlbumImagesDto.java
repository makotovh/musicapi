package se.zeroplusx.musicapi.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AlbumImagesDto implements Serializable {

    private List<AlbumImageDto> images;
}
