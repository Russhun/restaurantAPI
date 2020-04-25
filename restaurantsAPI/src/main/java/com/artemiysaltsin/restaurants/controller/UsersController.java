package com.artemiysaltsin.restaurants.controller;
import com.artemiysaltsin.restaurants.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UsersController {


    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public ResponseEntity index(HttpServletRequest request,
                                Authentication authentication) {
        if (authentication.isAuthenticated()) return ResponseEntity.ok(userRepository.findByEmail(authentication.getName()));
        else return ResponseEntity.ok(HttpStatus.FORBIDDEN);
    }


}
