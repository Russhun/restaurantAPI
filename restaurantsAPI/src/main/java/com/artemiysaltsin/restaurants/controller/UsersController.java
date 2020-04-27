package com.artemiysaltsin.restaurants.controller;

import com.artemiysaltsin.restaurants.forms.UserForm;
import com.artemiysaltsin.restaurants.model.BranchSeat;
import com.artemiysaltsin.restaurants.model.CustomerOrder;
import com.artemiysaltsin.restaurants.model.Users;
import com.artemiysaltsin.restaurants.repository.AppRoleRepository;
import com.artemiysaltsin.restaurants.repository.BranchSeatRepository;
import com.artemiysaltsin.restaurants.repository.OrderRepository;
import com.artemiysaltsin.restaurants.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UsersController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BranchSeatRepository branchSeatRepository;


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity getUser(Authentication authentication) {
        return ResponseEntity.ok(userRepository.findByEmail(authentication.getName()));
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public ResponseEntity postUser(@RequestBody UserForm userForm) {

        Users user = userRepository.findByEmail(userForm.getEmail());
        if (user != null) return ResponseEntity.ok(HttpStatus.CONFLICT);
        String password = new BCryptPasswordEncoder().encode(userForm.getPassword());
        Users newUser = new Users(userForm.getEmail(), password, true, true,
                appRoleRepository.findById(1));
        userRepository.save(newUser);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public ResponseEntity postPay(Authentication authentication) {

        CustomerOrder customerOrder = orderRepository.findByUserEmailAndStatus(authentication.getName(), 1);
        if (customerOrder == null) return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        customerOrder.setStatus(2);
        orderRepository.save(customerOrder);
        BranchSeat branchSeat = branchSeatRepository.findByUserEmail(authentication.getName());
        branchSeat.setUserEmail(null);
        branchSeatRepository.save(branchSeat);


        return ResponseEntity.ok(HttpStatus.OK);
    }
}
