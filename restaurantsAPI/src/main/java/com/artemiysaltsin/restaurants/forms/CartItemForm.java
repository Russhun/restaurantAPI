package com.artemiysaltsin.restaurants.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemForm {

    private int menuId;
    private int count;
    private int branchSeatId;

}
