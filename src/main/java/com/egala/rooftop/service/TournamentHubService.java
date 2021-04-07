package com.egala.rooftop.service;

import com.egala.rooftop.DTO.RegisterPlayerRequest;
import com.egala.rooftop.DTO.RegisterPlayerResponse;

public interface TournamentHubService {
    public RegisterPlayerResponse registerNewPlayer (RegisterPlayerRequest newPlayer) throws Exception;

}
