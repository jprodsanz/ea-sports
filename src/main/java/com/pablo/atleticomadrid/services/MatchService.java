package com.pablo.atleticomadrid.services;

import com.pablo.atleticomadrid.models.Match;
import com.pablo.atleticomadrid.repositories.MatchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {
    @Autowired
    private MatchRepo repo;

    public Match createOrUpdate(Match m) {
        return repo.save(m);
    }

    public Match getOne(Long id) {
        // i for instance
        Optional<Match> i = repo.findById(id);

        if(i.isPresent()) {
            return i.get();
        } else {
            return null;
        }
    }
    public List<Match> getAll() {
        return repo.findAll();
    }
//    public List<Match> getAllWithoutUser(User u) {
//        return repo.findByMembersNotContaining(u);
//    }
    public boolean deleteOne(Long id) {
        Optional<Match> i = repo.findById(id);
        if(i.isPresent()) {
            repo.delete(i.get());
            return true;
        } else {
            return false;
        }
    }
//    public void deleteOne(Long id){
//        repo.deleteById(id);
//    }
}
