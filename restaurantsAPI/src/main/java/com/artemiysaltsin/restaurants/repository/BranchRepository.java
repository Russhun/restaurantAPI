package com.artemiysaltsin.restaurants.repository;

import com.artemiysaltsin.restaurants.model.Branch;
import com.artemiysaltsin.restaurants.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

    List<Branch> findAllByRestaurantId(int restaurantId);
    Branch findBranchById(Integer id);

}
