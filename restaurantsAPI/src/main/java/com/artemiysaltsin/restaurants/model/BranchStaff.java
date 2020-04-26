package com.artemiysaltsin.restaurants.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff")
public class BranchStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int branchId;

    @Column(name = "user_email")
    private String userEmail;

}
