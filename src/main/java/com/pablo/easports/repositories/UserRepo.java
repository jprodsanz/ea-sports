package com.pablo.easports.repositories;

import com.pablo.easports.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Override
    List<User> findAll();
    List<User> findByIdNot(Long id);

    // make a func that returns all matches a user is not a part of

}
