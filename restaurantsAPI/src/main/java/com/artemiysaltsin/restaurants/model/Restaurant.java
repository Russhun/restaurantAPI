package com.artemiysaltsin.restaurants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


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
