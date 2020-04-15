package com.artemiysaltsin.restaurants.services;

import com.artemiysaltsin.restaurants.model.Components;
import com.artemiysaltsin.restaurants.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComponentService {

    @Autowired
    ComponentRepository componentRepository;

    public Components findComponentByName(String componentName) {
        return componentRepository.findByComponentName(componentName);
    }

}
