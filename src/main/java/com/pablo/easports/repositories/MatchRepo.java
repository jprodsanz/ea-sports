package com.pablo.easports.repositories;

import com.pablo.easports.models.Match;
import com.pablo.easports.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepo extends CrudRepository<Match, Long> {
    List<Match> findAll();

    // make a func that returns all matches a user is not a part of

    List<Match>findByPlayersNotContaining(User u);
}
