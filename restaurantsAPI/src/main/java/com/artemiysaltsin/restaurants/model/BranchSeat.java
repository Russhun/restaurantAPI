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
@Table(name = "branch_seat")
public class BranchSeat {
    /**
     *
     * Класс места в филиале ресторана
     *
     */

    @Id
    private int id;

    @Column(name = "branch_id")
    private int branchId;

    @Column(name = "num")
    private int seatNumber;

    @Column(name = "table_num")
    private int tableNumber;

    private boolean enable;

    @Column(name = "user_email")
    private String userEmail;

    private Integer staffId;



}
