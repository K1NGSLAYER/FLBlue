package com.egala.rooftop.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddPlayerToTournamentResponse {
    private String id;
    private String tournamentID;
    private String name;
    private String gameTitle;
    private String numberOfPlayers;
    private String platform;
    private String status;
}
