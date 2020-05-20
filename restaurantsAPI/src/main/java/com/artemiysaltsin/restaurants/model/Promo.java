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
@Table(name = "promo")
public class Promo {
    /**
     *
     * Класс акции
     *
     */

    @Id
    private int id;
    private int discount;

    @OneToOne(mappedBy = "promoId")
    @JsonIgnore
    private BranchMenu branchMenu;

}
