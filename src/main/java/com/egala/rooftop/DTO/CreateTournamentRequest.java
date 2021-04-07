package com.egala.rooftop.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CreateTournamentRequest implements Serializable {
    private static final long serialVersionUID = 1416679889504248080L;
    private String name;
    private String gameTitle;
    private String numberOfPlayers;
    private String platform;
    private String tournamentType;
    private String participant;

}
