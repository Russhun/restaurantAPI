package com.artemiysaltsin.restaurants.repository;

import com.artemiysaltsin.restaurants.model.Components;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComponentRepository extends MongoRepository<Components, String> {

    Components findByComponentName(String name);

}
