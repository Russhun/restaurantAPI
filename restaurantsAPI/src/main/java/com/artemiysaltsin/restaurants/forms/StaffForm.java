package com.artemiysaltsin.restaurants.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffForm {

    private String email;
    private String firstName;
    private String lastName;
    private String sureName;
    private int age;
    private String password;
    private int branchId;
    private int salary;
    private Date hireDate;


}
