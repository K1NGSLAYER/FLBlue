package com.egala.rooftop.DAO;

import com.egala.rooftop.DTO.AddPlayerToTournamentResponse;
import com.egala.rooftop.DTO.CancelTournamentResponse;
import com.egala.rooftop.DTO.RegisterPlayerRequest;
import com.egala.rooftop.DTO.TournamentResponse;
import com.egala.rooftop.model.Tournament;

import java.util.List;

public interface TournamentDAO {
    public Tournament createTournament(Tournament tournament) throws Exception;

    public AddPlayerToTournamentResponse registerPlayer(RegisterPlayerRequest registerPlayerRequest) throws Exception;

    public TournamentResponse updateTournament (Tournament tournament) throws Exception;

    public CancelTournamentResponse cancelTournament(String tournamentID);

    public List<TournamentResponse> listAll();

    public TournamentResponse getTournament(String tournamentID);
}
