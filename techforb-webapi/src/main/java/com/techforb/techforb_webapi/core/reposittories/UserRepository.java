package com.techforb.techforb_webapi.core.reposittories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techforb.techforb_webapi.core.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT u.* from users as u where u.refresh_token = ?1 limit 1")
    Optional<User> findByRefreshToken(String refreshToken);
}
