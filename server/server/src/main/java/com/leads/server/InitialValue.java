package com.leads.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.leads.server.entities.User;
import com.leads.server.services.UserService;

@Component
public class InitialValue implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(InitialValue.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        logger.info("InitialValue.run() called");
        if (userService.findByUsername("leads") != null) {
            return;
        }
        userService.saveUser(new User(null, "leads", "leads@leads.com", passwordEncoder.encode("123")));
    }
}
