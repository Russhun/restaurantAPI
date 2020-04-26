package com.artemiysaltsin.restaurants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Set<Users> user;

    public AppRole(int id, String title) {
        this.title = title;
    }
}
