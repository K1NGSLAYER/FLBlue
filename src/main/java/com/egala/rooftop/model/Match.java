package com.egala.rooftop.model;

import java.util.HashMap;
import java.util.List;

public class Match {
    private String id;
    private int numberOfGames;
    private List<Game> games;
    private HashMap<String,HashMap<String,String>> aggregatedStats;
    private List<Official> matchOfficials;
}
