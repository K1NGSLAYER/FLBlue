package com.egala.rooftop.service.impl;

import com.egala.rooftop.DAO.TournamentDAO;
import com.egala.rooftop.DTO.*;
import com.egala.rooftop.model.Tournament;
import com.egala.rooftop.service.TournamentService;
import com.egala.rooftop.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class TournamentServiceImpl implements TournamentService{
    private TournamentDAO tournamentDAO;
    private Utils utils;

    @Autowired
    public TournamentServiceImpl(TournamentDAO tournamentDAO, Utils utils) {
        this.tournamentDAO = tournamentDAO;
        this.utils = utils;
    }

    @Override
    public TournamentResponse createNewTournament(CreateTournamentRequest tournamentRequest) throws Exception {
        TournamentResponse tournamentResponse = new TournamentResponse();
        Tournament newTournament = new Tournament();
        try{
            BeanUtils.copyProperties(tournamentRequest, newTournament);
            newTournament.setTournamentID(utils.generateTournamentID(tournamentRequest.getGameTitle()));
            newTournament.setListOfOfficials(new HashSet<>());
            newTournament.setListOfPlayers( new HashSet<>());
            newTournament.setStats(new HashMap<>());
            Tournament tournament = tournamentDAO.createTournament(newTournament);
            BeanUtils.copyProperties(tournament, tournamentResponse);
            return tournamentResponse;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return tournamentResponse;
    }

    @Override
    public TournamentResponse updateTournament(Tournament tournament) {
        TournamentResponse tournamentResponse = new TournamentResponse();
        try{
            tournamentResponse = tournamentDAO.updateTournament(tournament);

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return tournamentResponse;
    }

    @Override
    public CancelTournamentResponse cancelTournament(String tournamentID) {
        CancelTournamentResponse tournamentResponse = new CancelTournamentResponse();
        try{
            tournamentResponse = tournamentDAO.cancelTournament(tournamentID);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return tournamentResponse;
    }

    @Override
    public List<TournamentResponse> listAll() {
        return tournamentDAO.listAll();
    }

    @Override
    public TournamentResponse getTournament(String tournamentID) {
        TournamentResponse tournamentResponse = new TournamentResponse();
        try{
            tournamentResponse = tournamentDAO.getTournament(tournamentID);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return tournamentResponse;
    }

}
