package com.leads.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.leads.server.classes.LoginRequest;
import com.leads.server.entities.User;
import com.leads.server.repositories.UserRepo;
import com.leads.server.setup.principal.UserPrincipal;
import com.leads.server.setup.service.AuthService;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthService authService;

    public User findByUsername(String string) {
        return userRepo.findByUsername(string);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public String login(LoginRequest loginRequest) {
        UserPrincipal userPrincipal = doAuthenticate(loginRequest);
        String token = authService.generateToken(userPrincipal);
        return token;
    }

    private UserPrincipal doAuthenticate(LoginRequest loginRequest) {
        Authentication authentication = authService.authenticateUser(loginRequest.getUsername(),
                loginRequest.getPassword());
        return (UserPrincipal) authentication.getPrincipal();
    }

}
