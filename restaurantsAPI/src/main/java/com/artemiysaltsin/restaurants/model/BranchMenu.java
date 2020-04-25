package com.artemiysaltsin.restaurants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu")
public class BranchMenu {

    @Id
    private int id;
    private int branchId;
    private String name;
    private Float weight;
    private String composition;
    private int price;
    private boolean enable;
    private Integer promoId;

}
