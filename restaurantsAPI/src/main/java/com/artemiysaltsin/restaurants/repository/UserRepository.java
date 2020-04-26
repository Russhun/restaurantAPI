package com.artemiysaltsin.restaurants.repository;

import com.artemiysaltsin.restaurants.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<Users, String> {

    Users findByEmail(String email);

}
