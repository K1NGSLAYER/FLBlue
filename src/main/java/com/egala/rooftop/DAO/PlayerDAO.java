package com.egala.rooftop.DAO;

import com.egala.rooftop.model.Player;

public interface PlayerDAO {
    public Player registerNewPlayer(Player player) throws Exception;
}
