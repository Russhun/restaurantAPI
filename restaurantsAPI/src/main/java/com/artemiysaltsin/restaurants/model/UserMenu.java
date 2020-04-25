package com.artemiysaltsin.restaurants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_menu")
public class UserMenu {

    @Id
    private int id;

    @Column(name = "branch_seat_id")
    private int branchSeatId;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "menu_id")
    private int menuId;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "item_count")
    private int itemCount;


}
