package com.artemiysaltsin.restaurants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_address")
public class UserAddress {

    @Id
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
