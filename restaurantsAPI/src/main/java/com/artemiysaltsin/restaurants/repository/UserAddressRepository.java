package com.artemiysaltsin.restaurants.repository;

import com.artemiysaltsin.restaurants.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {
}
