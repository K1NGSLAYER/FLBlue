package com.egala.rooftop.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Set;

@Getter
@Setter
@Document("players")
public class Player {
    private String participatingTournamentID;
    @Id
    private String userID;
    private String gamertag;
    private String qrCode;
    private String assignedTeam;
    private String preferredTeam;
    private Set<Match> matches;
    private HashMap<String, String> stats;
    private HashMap<String, Object> statsHistory;

}
