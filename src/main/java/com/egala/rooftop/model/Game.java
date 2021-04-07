package com.egala.rooftop.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Getter
@Setter
public class Game {
    private String id;
    private HashMap<String, HashMap<String,String>> game;
}
