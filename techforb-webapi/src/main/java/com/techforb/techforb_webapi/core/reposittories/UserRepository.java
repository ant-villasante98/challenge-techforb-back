package com.techforb.techforb_webapi.core.reposittories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techforb.techforb_webapi.core.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
