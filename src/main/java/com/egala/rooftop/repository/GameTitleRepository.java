package com.egala.rooftop.repository;

import com.egala.rooftop.model.GameTitle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameTitleRepository extends MongoRepository<GameTitle, String> {
}
