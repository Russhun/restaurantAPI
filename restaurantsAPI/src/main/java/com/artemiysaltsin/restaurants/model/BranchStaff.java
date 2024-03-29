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
@Table(name = "staff")
public class BranchStaff {
    /**
     *
     * Класс сотрудника принадлежащего филиалу ресторана
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int branchId;

    @Column(name = "user_email")
    private String userEmail;

    private int salary;

    private Date hireDate;

}
