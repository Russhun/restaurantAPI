package com.artemiysaltsin.restaurants.repository;

import com.artemiysaltsin.restaurants.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {

    AppRole findById(int id);

}
