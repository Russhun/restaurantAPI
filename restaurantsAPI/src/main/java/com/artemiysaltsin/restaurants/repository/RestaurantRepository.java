package com.artemiysaltsin.restaurants.repository;

import com.artemiysaltsin.restaurants.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Restaurant findById(int id);
    List<Restaurant> findAll();

}
