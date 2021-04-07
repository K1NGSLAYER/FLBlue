package com.egala.rooftop.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.Set;

@Getter
@Setter
public class NewUserRegistration extends UserEntity{
    private String participatingTournamentID;
    private String gamertag;
    private String assignedTeam;
    private String preferredTeam;
    private String qrCode;
    private Set<Match> matches;
    private HashMap<String, String> stats;
    private HashMap<String, Object> statsHistory;
}
