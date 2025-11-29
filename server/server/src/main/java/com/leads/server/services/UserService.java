package com.leads.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leads.server.entities.User;
import com.leads.server.repositories.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User findByUsername(String string) {
        return userRepo.findByUsername(string);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

}
