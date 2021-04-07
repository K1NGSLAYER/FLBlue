package com.egala.rooftop.DAO.Impl;

import com.egala.rooftop.DAO.TournamentDAO;
import com.egala.rooftop.DTO.AddPlayerToTournamentResponse;
import com.egala.rooftop.DTO.CancelTournamentResponse;
import com.egala.rooftop.DTO.RegisterPlayerRequest;
import com.egala.rooftop.DTO.TournamentResponse;
import com.egala.rooftop.model.Player;
import com.egala.rooftop.model.Tournament;
import com.egala.rooftop.repository.TournamentRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.egala.rooftop.utils.Utils.getNullPropertyNames;

@Component
public class TournamentDAOImpl implements TournamentDAO {
    private Tournament tournamentResponse;
    private TournamentRepository tournamentRepository;

    @Autowired
    public TournamentDAOImpl(Tournament tournamentResponse,
                             TournamentRepository tournamentRepository) {
        this.tournamentResponse = tournamentResponse;
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public Tournament createTournament(Tournament tournament) throws Exception {
        try {
            tournamentResponse = tournamentRepository.insert(tournament);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return tournamentResponse;
    }

    @Override
    public AddPlayerToTournamentResponse registerPlayer(RegisterPlayerRequest registerPlayerRequest) throws Exception {
        Optional<Tournament> tournamentOptional = Optional.of(new Tournament());
        AddPlayerToTournamentResponse addPlayerToTournamentResponse = new AddPlayerToTournamentResponse();
        try{
            tournamentOptional = tournamentRepository.findByTournamentID(registerPlayerRequest.getParticipatingTournamentID());
            if (tournamentOptional.isPresent()){
                Tournament tournament = tournamentOptional.get();
                tournament.getListOfPlayers().add(registerPlayerRequest.getGamertag());
                Integer remainingPlayers = Integer.parseInt(tournament.getNumberOfPlayers()) - 1;
                String playersLeft = String.valueOf(remainingPlayers);
                tournament.setNumberOfPlayers(playersLeft);
                Tournament tournamentSavedResponse = tournamentRepository.save(tournament);

                BeanUtils.copyProperties(tournamentSavedResponse, addPlayerToTournamentResponse);

                return addPlayerToTournamentResponse;
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
      return null;
    }

    @Override
    public TournamentResponse updateTournament(Tournament tournament) throws Exception {

        Optional<Tournament> tournamentOptional;
        try {
            tournamentOptional = tournamentRepository.findByTournamentID(tournament.getTournamentID());
            String id = tournamentOptional.get().getId();
            Tournament updatedTournament;
            if (tournamentOptional.isPresent()) {
                tournament.setId(id);
                Tournament found = tournamentOptional.get();
                String[] emptyFields = getNullPropertyNames(tournament);
                BeanUtils.copyProperties(tournament, found,emptyFields);

                TournamentResponse updatedTournamentResponse = new TournamentResponse();
                updatedTournament = tournamentRepository.save(found);
                BeanUtils.copyProperties(updatedTournament, updatedTournamentResponse);
                return updatedTournamentResponse;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return null;
    }

    @Override
    public CancelTournamentResponse cancelTournament(String tournamentID) {
        int cancelStatus;
        CancelTournamentResponse tournamentResponse = new CancelTournamentResponse();
        try{
            Optional<Integer> tournamentOptional = tournamentRepository.deleteTournamentByTournamentID(tournamentID);
            if(tournamentOptional.isPresent()){
                cancelStatus = tournamentOptional.get();
                tournamentResponse.setStatusCode(cancelStatus);
                tournamentResponse.setTournamentID(tournamentID);
                return tournamentResponse;
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<TournamentResponse> listAll() {
        return getAllTournaments(tournamentRepository.findAll());
    }

    @Override
    public TournamentResponse getTournament(String tournamentID) {
        Tournament tournamentEntity;
        TournamentResponse tournamentResponse = new TournamentResponse();
        try{
            Optional<Tournament> tournamentOptional = tournamentRepository.findByTournamentID(tournamentID);
            if(tournamentOptional.isPresent()){
                tournamentEntity = tournamentOptional.get();
                BeanUtils.copyProperties(tournamentEntity,tournamentResponse);
                return tournamentResponse;
            }
            }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private Player getParticipatingPlayer(String gamerTag, List<Player> players) {
        for (Player player : players) {
            if (gamerTag.equalsIgnoreCase(player.getGamertag()))
                return player;
        }
        return null;
    }

    private List<TournamentResponse> getAllTournaments(List<Tournament> tournamentEntities){
        List<TournamentResponse> tournamentResponseList = new ArrayList<>();

        if (tournamentEntities == null || tournamentEntities.size() == 0){
            return null;
        }
        for (Tournament tournament :
             tournamentEntities) {
            TournamentResponse tournamentResponse = new TournamentResponse();
            BeanUtils.copyProperties(tournament,tournamentResponse);
            tournamentResponseList.add(tournamentResponse);
        }
        return tournamentResponseList;
    }
}
