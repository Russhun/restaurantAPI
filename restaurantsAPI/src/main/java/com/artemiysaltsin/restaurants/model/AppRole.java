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
@Table(name = "role")
public class AppRole {

    @Id
    @GeneratedValue
    private int id;
    private String title;

    @OneToOne(mappedBy = "role")
    @JsonIgnore
    private Users user;

}
