package com.techforb.techforb_webapi.webapi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techforb.techforb_webapi.core.dtos.SuccessResponse;
import com.techforb.techforb_webapi.core.dtos.Response.UserDetailsResponse;
import com.techforb.techforb_webapi.core.models.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @GetMapping("me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<SuccessResponse<UserDetailsResponse>> getMethodName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        UserDetailsResponse userDetail = new UserDetailsResponse(user.getId(), user.getEmail(), user.getFirstName(),
                user.getLastName());
        SuccessResponse<UserDetailsResponse> response = SuccessResponse.create(userDetail, HttpStatus.OK.value());
        return ResponseEntity.ok().body(response);
    }

}
