package com.egala.rooftop.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelTournamentResponse {
    private String tournamentID;
    private int statusCode;
}
