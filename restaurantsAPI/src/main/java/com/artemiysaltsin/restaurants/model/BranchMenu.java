package com.artemiysaltsin.restaurants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu")
public class BranchMenu {
    /**
     *
     * Класс товара из меню ресторана
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer branchId;
    private String name;
    private Float weight;
    private String measure;
    private String composition;
    private Integer price;
    private Boolean enable;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "promo_id", referencedColumnName = "id")
    private Promo promoId;

}
