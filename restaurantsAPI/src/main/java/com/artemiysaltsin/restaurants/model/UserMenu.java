package com.artemiysaltsin.restaurants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_product")
public class UserMenu {
    /**
     *
     * Класс товара из корзины пользователя
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "menu_id")
    private int menuId;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "item_count")
    private int itemCount;

    @Column(name = "product_status_id")
    private int productStatusId;


}
