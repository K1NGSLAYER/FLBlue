package com.egala.rooftop.service.impl;

import com.egala.rooftop.DAO.PlayerDAO;
import com.egala.rooftop.DAO.TournamentDAO;
import com.egala.rooftop.DAO.UserDAO;
import com.egala.rooftop.DTO.*;
import com.egala.rooftop.model.NewUserRegistration;
import com.egala.rooftop.model.Player;
import com.egala.rooftop.model.UserEntity;
import com.egala.rooftop.service.TournamentHubService;
import com.egala.rooftop.service.UserService;
import com.egala.rooftop.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
@Service
public class TournamentServiceHubImpl implements TournamentHubService {
    private Logger LOGGER = LoggerFactory.getLogger(TournamentServiceHubImpl.class);
    private Utils utils;
    private UserDAO userDAO;
    private TournamentDAO tournamentDAO;
    private PlayerDAO playerDAO;
    private UserService userService;

    @Autowired
    public TournamentServiceHubImpl(Utils utils, UserDAO userDAO,
                                    TournamentDAO tournamentDAO,
                                    PlayerDAO playerDAO, UserService userService) {
        this.utils = utils;
        this.userDAO = userDAO;
        this.tournamentDAO = tournamentDAO;
        this.playerDAO = playerDAO;
        this.userService = userService;
    }


    @Override
    public RegisterPlayerResponse registerNewPlayer(RegisterPlayerRequest newPlayer) throws Exception{
        // Prep fields
        NewUserRegistration player = new NewUserRegistration();
        SignUpUserRequest signUpUserRequest = new SignUpUserRequest();

        String userID = utils.generateConfirmationCode(6);
        String gamerTag = utils.generateConfirmationCode(10);
        String qrCode = utils.generateConfirmationCode(16);

        BeanUtils.copyProperties(newPlayer,player);
        player.setUserID(userID);
        player.setGamertag(gamerTag);
        player.setQrCode(qrCode);
        player.setPreferredTeam(newPlayer.getPreferredTeam());
        player.setAssignedTeam("");
        player.setMatches(new HashSet<>());
        player.setStats(new HashMap<>());
        player.setStatsHistory(new HashMap<>());
        player.setEmailVerifcationStatus(false);

        try{
            /*
            * First check if tournament is closed or Registration is full
            * Get new player tournament id and perform check
            * */
            BeanUtils.copyProperties(newPlayer,signUpUserRequest);
            SignUpUserResponse signUpUserResponse = null;
            signUpUserResponse = userService.createUser(signUpUserRequest);
            if (signUpUserResponse != null){
                RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest();
                BeanUtils.copyProperties(player,registerPlayerRequest);
                AddPlayerToTournamentResponse registerPlayer = null;
                registerPlayer = tournamentDAO.registerPlayer(registerPlayerRequest);

                if (registerPlayer != null){
                    registerPlayer.setStatus("Registration Successful");
                    Player addedPlayer = new Player();
                    BeanUtils.copyProperties(player,addedPlayer);
                   Player newAddedPlayer = playerDAO.registerNewPlayer(addedPlayer);
                    return prepareTournamentRegistrationResponse(newAddedPlayer,registerPlayer,signUpUserResponse);
                }
            }else{
                throw new Exception();
            }
        }catch(Exception ex){
            LOGGER.info("Couldn't create user");
        }
        return null;
    }

    private RegisterPlayerResponse prepareTournamentRegistrationResponse(Player addedPlayer,
                                                                         AddPlayerToTournamentResponse response,
                                                                         SignUpUserResponse newUser){
        RegisterPlayerResponse registerPlayerResponse = new RegisterPlayerResponse();

        registerPlayerResponse.setPlayerName(newUser.getFirstName() +" "+ newUser.getLastName());
        registerPlayerResponse.setGameTitle(response.getGameTitle());
        registerPlayerResponse.setTournamentName(response.getName());
        registerPlayerResponse.setPlatform(response.getPlatform());
        registerPlayerResponse.setGamerTag(addedPlayer.getGamertag());
        registerPlayerResponse.setUserID(addedPlayer.getUserID());
        registerPlayerResponse.setRole(newUser.getRole());
        registerPlayerResponse.setQrCode(addedPlayer.getQrCode());
        registerPlayerResponse.setStatus("100");

        return registerPlayerResponse;
    }
}
