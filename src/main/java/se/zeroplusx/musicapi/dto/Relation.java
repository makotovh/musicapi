package se.zeroplusx.musicapi.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Relation implements Serializable {

    private String type;

    private RelationUrl url;

}
