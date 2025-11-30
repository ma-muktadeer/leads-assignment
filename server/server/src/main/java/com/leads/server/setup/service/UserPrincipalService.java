package com.leads.server.setup.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leads.server.entities.User;
import com.leads.server.repositories.UserRepo;
import com.leads.server.setup.principal.UserPrincipal;

@Service
public class UserPrincipalService implements UserDetailsService {

    private final UserRepo userRepo;

    public UserPrincipalService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }

}
