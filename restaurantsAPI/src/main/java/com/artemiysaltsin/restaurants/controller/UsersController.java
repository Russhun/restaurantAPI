package com.artemiysaltsin.restaurants.controller;

import com.artemiysaltsin.restaurants.forms.UserForm;
import com.artemiysaltsin.restaurants.model.Components;
import com.artemiysaltsin.restaurants.model.Users;
import com.artemiysaltsin.restaurants.services.ComponentService;
import com.artemiysaltsin.restaurants.services.MongoUserDetailsService;
import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.stream.Collectors;


@RestController
public class UsersController {

    @Autowired
    ComponentService componentService;



    @RequestMapping(value = "/components")
    public Components index(@RequestParam String componentName) {


        return componentService.findComponentByName(componentName);

    }


    @RequestMapping(value = "/check")
    public UserForm testS(@RequestBody UserForm userForm) {


        return userForm;

    }






}
