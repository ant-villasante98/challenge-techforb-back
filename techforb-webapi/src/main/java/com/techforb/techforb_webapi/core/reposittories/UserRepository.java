package com.techforb.techforb_webapi.core.reposittories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techforb.techforb_webapi.core.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
