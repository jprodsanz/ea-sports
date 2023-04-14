package com.pablo.easports.services;

import com.pablo.easports.models.LoginUser;
import com.pablo.easports.models.User;
import com.pablo.easports.repositories.UserRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    // REGISTER METHOD
    public User register(User newUser, BindingResult result) {
        // check for pre-existing errors
        if (result.hasErrors()) {
            return null;
        }
        // is email already in use?
        Optional<User> user = repo.findByEmail(newUser.getEmail());

        if (user.isPresent()) {
            result.rejectValue("email", "matches", "Email already in use");
            return null;
        }
        // Check if password and confirmPass are the same
        if (!newUser.getPassword().equals(newUser.getConfirmPass())) {
            result.rejectValue("confirmPass", "matches", "Your passwords do not match");
            return null;
        }
        // salt bea
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);

        return repo.save(newUser);
    }
    // LOGIN METHOD
    public User login(LoginUser loginUser, BindingResult result) {
        // checks if user exists and entry form errors
        if(result.hasErrors()) {
            return null;
        }
        // check if user logging in exists
        Optional<User> user = repo.findByEmail(loginUser.getEmail());
        if(user.isEmpty()) {
            result.rejectValue("email", "matches", "User email is not found");
            return null;
        }
        User u = user.get();
        // check if the password given by the user matches the one that's stored in db
        if(!BCrypt.checkpw(loginUser.getPassword(), u.getPassword())) {
            result.rejectValue("password", "matches", "Password is incorrect");
            return null;
        }
        return u;
    }


    public User createOrUpdate(User u) {
        return repo.save(u);
    }

    public User getOne(Long id) {
        // i for instance
        Optional<User> i = repo.findById(id);

        if (i.isPresent()) {
            return i.get();
        } else {

            return null;
        }
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public List<User> getAllExcept(Long id){
        return repo.findByIdNot(id);
    }

    public boolean deleteOne(Long id) {
        Optional<User> i = repo.findById(id);
        if (i.isPresent()) {
            repo.delete(i.get());
            return true;
        } else {
            return false;
        }
    }
}

