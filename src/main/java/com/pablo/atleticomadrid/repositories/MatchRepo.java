package com.pablo.atleticomadrid.repositories;

import com.pablo.atleticomadrid.models.Match;
import com.pablo.atleticomadrid.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepo extends CrudRepository<Match, Long> {
    List<Match> findAll();

    // this function returns all matches a user is not a part of

//    List<Match>findByMembersNotContaining(User u);
}
