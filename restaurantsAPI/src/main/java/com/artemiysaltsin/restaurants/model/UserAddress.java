package com.artemiysaltsin.restaurants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_address")
public class UserAddress {
    /**
     *
     * Класс адреса пользователя
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String city;
    private String street;
    private String house;
    private String apartment;
    private String description;

    @OneToOne(mappedBy = "userAddress")
    @JsonIgnore
    private Users user;

}
