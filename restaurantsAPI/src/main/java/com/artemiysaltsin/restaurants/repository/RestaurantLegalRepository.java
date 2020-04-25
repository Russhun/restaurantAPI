package com.artemiysaltsin.restaurants.repository;

import com.artemiysaltsin.restaurants.model.RestaurantLegal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantLegalRepository extends JpaRepository<RestaurantLegal, Integer> {

    RestaurantLegal findByRestaurantId(int restaurantId);

}
