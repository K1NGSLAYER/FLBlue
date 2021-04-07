package com.egala.rooftop.repository;

import com.egala.rooftop.model.Tournament;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;
@CrossOrigin("*")
@Repository
public interface TournamentRepository extends MongoRepository<Tournament, String>{

    @Override
    public Tournament insert(Tournament tournament);

    @Override
    public Tournament save(Tournament tournament);
    public Optional<Tournament> findByTournamentID(String tournamentID);

    @Override
    public Optional<Tournament> findById(String id);

    public Optional<Integer> deleteTournamentByTournamentID(String tournamentID);
}
