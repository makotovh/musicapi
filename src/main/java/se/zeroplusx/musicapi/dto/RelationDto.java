package se.zeroplusx.musicapi.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RelationDto implements Serializable {

    private String type;

    private RelationUrlDto url;
}
