package com.artemiysaltsin.restaurants.controller;

import com.artemiysaltsin.restaurants.model.Users;
import com.artemiysaltsin.restaurants.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UsersController {


    @Autowired
    UserRepository repository;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public Users index() {
        return repository.findByEmail("artemy.saltsin@gmail.com");
    }


}
