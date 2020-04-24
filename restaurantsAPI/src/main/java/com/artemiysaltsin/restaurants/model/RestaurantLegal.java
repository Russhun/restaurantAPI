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
    private int id;

    private String ogrn;

    @Column(name = "legal_name")
    private String legalName;

    private String inn;

    private String address;

//    @OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL)
//    private RestaurantLegal restaurantLegal;

}
