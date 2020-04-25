package com.artemiysaltsin.restaurants.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurant_legal")
public class RestaurantLegal {

    @Id
    @Column(name = "restaurant_id")
    private int restaurantId;

    private String ogrn;

    @Column(name = "legal_name")
    private String legalName;

    private String inn;

    private String address;


}
