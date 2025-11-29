package com.leads.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leads.server.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String string);

}
