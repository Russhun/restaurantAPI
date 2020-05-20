package com.artemiysaltsin.restaurants.controller;

import com.artemiysaltsin.restaurants.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @RequestMapping(value = "/restaurant", method = RequestMethod.GET)
    public ResponseEntity getRestaurant() {
        // Возвращает ресторан по id
        return ResponseEntity.ok(restaurantRepository.findById(1));
    }


}
