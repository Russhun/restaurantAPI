package com.artemiysaltsin.restaurants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class Users {

    @Email(message = "Email should be valid",
            regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    @Id
    private String email;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "surename")
    private String sureName;
    private Integer age;
    private String password;
    private boolean verified;
    private boolean enable;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private AppRole role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_address_id", referencedColumnName = "id")
    private UserAddress userAddress;

    public Users(@Email(message = "Email should be valid",
            regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$") String email,
                 String password,
                 boolean verified,
                 boolean enable,
                 AppRole appRole) {
        this.email = email;
        this.password = password;
        this.verified = verified;
        this.enable = enable;
        this.role = appRole;
    }
}
