package com.artemiysaltsin.restaurants.repository;

import com.artemiysaltsin.restaurants.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoRepository extends JpaRepository<Promo, Integer> {

    Promo findById(int id);

}
