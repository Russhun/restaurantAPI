package com.artemiysaltsin.restaurants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order")
public class CustomerOrder {

    @Id
    private int id;
    private int price;

    @Column(name = "order_date")
    private Date dateTime;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "order_status_id")
    private int status;


}
