package com.egala.rooftop.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterPlayerResponse {
    private String playerName;
    private String gameTitle;
    private String tournamentName;
    private String platform;
    private String gamerTag;
    private String userID;
    private String qrCode;
    private String role;
    private String status;
}
