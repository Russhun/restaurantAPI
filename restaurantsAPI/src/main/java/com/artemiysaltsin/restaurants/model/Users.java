package com.artemiysaltsin.restaurants.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    private ObjectId _id;

    private String username;
    private String password;
    private String role;




}
