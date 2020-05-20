package com.artemiysaltsin.restaurants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class CustomerOrder {
    /**
     *
     * Заказ пользователя
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer price;

    @Column(name = "order_date")
    private Date dateTime;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "branch_seat_id")
    private int branchSeatId;

    @Column(name = "order_status_id")
    private Integer status;


}
