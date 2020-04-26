package com.artemiysaltsin.restaurants.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceForm {

    private int branchId;
    private int tableId;
    private int seatId;
    private String userEmail;

}
