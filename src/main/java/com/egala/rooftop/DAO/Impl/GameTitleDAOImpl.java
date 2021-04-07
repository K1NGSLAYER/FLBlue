package com.egala.rooftop.DAO.Impl;

import com.egala.rooftop.DAO.GameTitleDAO;
import com.egala.rooftop.model.GameTitle;
import com.egala.rooftop.repository.GameTitleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GameTitleDAOImpl implements GameTitleDAO {
    private GameTitleRepository gameTitleRepository;
    Logger LOGGER = LoggerFactory.getLogger(GameTitleDAOImpl.class);
    @Autowired
    public GameTitleDAOImpl(GameTitleRepository gameTitleRepository){
        this.gameTitleRepository = gameTitleRepository;
    }
    @Override
    public GameTitle addNewGameTitle(GameTitle gameTitle) {
        GameTitle addedTitle;
        try {
            addedTitle = gameTitleRepository.insert(gameTitle);
            LOGGER.info("{} inserted into DB successfully", gameTitle.getOfficialTitle());
            return addedTitle;
        }catch (Exception ex){
            LOGGER.error("Could not insert {} into DB", gameTitle.getOfficialTitle());
            return null;
        }
    }
}
