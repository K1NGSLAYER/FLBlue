package com.egala.rooftop.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Document(collection = "tournaments")
@Component
public class Tournament {
    @Id
    private String id;
    private String tournamentID;
    private String name;
    private String gameTitle;
    private String numberOfPlayers;
    private String platform;
    private Set<String> listOfPlayers;
    private Set<String> listOfOfficials;
    private String tournamentType;
    private HashMap<String, Object> stats;
    private String participant; // Team or player
}
