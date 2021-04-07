package com.egala.rooftop.service;

import com.egala.rooftop.DTO.CancelTournamentResponse;
import com.egala.rooftop.DTO.CreateTournamentRequest;
import com.egala.rooftop.DTO.TournamentResponse;
import com.egala.rooftop.model.Tournament;

import java.util.List;

public interface TournamentService {
    public TournamentResponse createNewTournament(CreateTournamentRequest newTournament) throws Exception;

    public TournamentResponse updateTournament (Tournament tournament);

    public CancelTournamentResponse cancelTournament(String tournamentIdD);

    public List<TournamentResponse> listAll();


    public TournamentResponse getTournament(String tournamentID);
}
