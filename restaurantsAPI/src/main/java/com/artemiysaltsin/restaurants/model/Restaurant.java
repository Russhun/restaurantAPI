package com.artemiysaltsin.restaurants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    private int id;

    private String name;

    @Column(name = "tiny_description")
    private String tinyDescription;

    private String description;

    private String phone;


}
