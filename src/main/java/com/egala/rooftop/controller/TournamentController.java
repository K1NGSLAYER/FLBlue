package com.egala.rooftop.controller;

import com.egala.rooftop.DTO.*;
import com.egala.rooftop.model.Tournament;
import com.egala.rooftop.service.TournamentHubService;
import com.egala.rooftop.service.TournamentService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournament")
public class TournamentController {
    private TournamentService tournamentService;
    private TournamentHubService tournamentHub;

    public TournamentController(TournamentService tournamentService, TournamentHubService tournamentHub) {
        this.tournamentService = tournamentService;
        this.tournamentHub = tournamentHub;
    }

    @PostMapping("/create")
    public TournamentResponse createTournament(@NonNull @RequestBody CreateTournamentRequest tournament) throws Exception {
        TournamentResponse tournamentResponse = new TournamentResponse();
        try{
            tournamentResponse = tournamentService.createNewTournament(tournament);
            return tournamentResponse;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return tournamentResponse;
    }

    @PatchMapping("/update/{tournamentID}")
    public TournamentResponse updateTournament(@NonNull @RequestBody Tournament tournament,
                                               @PathVariable(required = true) String tournamentID) throws Exception{
        TournamentResponse tournamentResponse = new TournamentResponse();
        try{
            tournament.setTournamentID(tournamentID);
            tournamentResponse = tournamentService.updateTournament(tournament);
            return tournamentResponse;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return tournamentResponse;
        }
    }

    // Return a tournament given the tournamentID
    @GetMapping("/get/{tournamentID}")
    public TournamentResponse getTournament(@PathVariable(required = true) String tournamentID) throws Exception{
        TournamentResponse tournamentResponse = new TournamentResponse();
        try{
            tournamentResponse = tournamentService.getTournament(tournamentID);
            return tournamentResponse;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return tournamentResponse;
        }
    }

    @GetMapping("/all")
    public List<TournamentResponse> listAllTournaments(){

        return tournamentService.listAll();
    }
    @DeleteMapping("/cancel/{tournamentID}")
    public CancelTournamentResponse cancelTournament(@PathVariable(required = true) String tournamentID) throws Exception{
        CancelTournamentResponse tournamentResponse = new CancelTournamentResponse();
        try{
            tournamentResponse = tournamentService.cancelTournament(tournamentID);
            return tournamentResponse;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return tournamentResponse;
        }
    }

    @PostMapping("/register{tournamentID}/")
    public RegisterPlayerResponse registerPlayer(@RequestBody RegisterPlayerRequest registerPlayerRequest,
                                                        @PathVariable(required = true) String tournamentID ) throws Exception{
        RegisterPlayerResponse registerPlayerResponse = new RegisterPlayerResponse();
        try {
            registerPlayerRequest.setParticipatingTournamentID(tournamentID);
            registerPlayerResponse= tournamentHub.registerNewPlayer(registerPlayerRequest);
            return registerPlayerResponse;

        }catch(Exception ex){
            registerPlayerResponse.setStatus("Failed");
            System.out.println(ex.getMessage());
        }
        return registerPlayerResponse;
    }
}
