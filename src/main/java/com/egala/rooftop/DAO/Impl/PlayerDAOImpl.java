package com.egala.rooftop.DAO.Impl;

import com.egala.rooftop.DAO.PlayerDAO;
import com.egala.rooftop.model.Player;
import com.egala.rooftop.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class PlayerDAOImpl implements PlayerDAO {
    Logger LOGGER = LoggerFactory.getLogger(PlayerDAOImpl.class);
    private PlayerRepository playerRepository;

    @Autowired
    public PlayerDAOImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player registerNewPlayer(Player player) throws Exception {

        /* Checks PlayerRepository if player is already registered
         * Insert new Player into PlayerRepository
         * */
        Player insertPlayerResponse = new Player();
        try {

            Optional<Player> OptionalPlayer = Optional.of(new Player());
            OptionalPlayer = playerRepository.findById(player.getUserID());
            boolean playerExists = OptionalPlayer.isPresent();
            if (playerExists) {
                throw new Exception();
            }
            insertPlayerResponse = playerRepository.insert(player);
        } catch (Exception ex) {
            LOGGER.error("Player {} is already registered {}", player.getUserID(), ex.getMessage());
        }
        return insertPlayerResponse;
    }
}
