package com.egala.rooftop.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterPlayerRequest extends SignUpUserRequest{

    private static final long serialVersionUID = 624126894478650109L;
    private String gamertag;
    private String userID;
    private String preferredTeam;
    private String participatingTournamentID;
}
