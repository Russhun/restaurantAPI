package com.artemiysaltsin.restaurants.controller;

import com.artemiysaltsin.restaurants.model.Branch;
import com.artemiysaltsin.restaurants.model.BranchMenu;
import com.artemiysaltsin.restaurants.model.Restaurant;
import com.artemiysaltsin.restaurants.model.Users;
import com.artemiysaltsin.restaurants.repository.BranchMenuRepository;
import com.artemiysaltsin.restaurants.repository.BranchRepository;
import com.artemiysaltsin.restaurants.repository.RestaurantRepository;
import com.artemiysaltsin.restaurants.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;


@RestController
public class UsersController {


    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    BranchMenuRepository branchMenuRepository;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public List<BranchMenu> index() {
        return branchMenuRepository.findAllByBranchId(1);
    }


}
