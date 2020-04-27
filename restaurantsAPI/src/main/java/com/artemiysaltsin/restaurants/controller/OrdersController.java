package com.artemiysaltsin.restaurants.controller;


import com.artemiysaltsin.restaurants.forms.CartItemForm;

import com.artemiysaltsin.restaurants.model.BranchMenu;
import com.artemiysaltsin.restaurants.model.BranchSeat;
import com.artemiysaltsin.restaurants.model.CustomerOrder;
import com.artemiysaltsin.restaurants.model.UserMenu;
import com.artemiysaltsin.restaurants.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
public class OrdersController {

    @Autowired
    BranchStaffRepository branchStaffRepository;

    @Autowired
    BranchSeatRepository branchSeatRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserMenuRepository userMenuRepository;

    @Autowired
    BranchMenuRepository branchMenuRepository;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ResponseEntity getOrders() {
        List<CustomerOrder> orders = orderRepository.findAllByStatus(1);

        return ResponseEntity.ok(orders);

    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity createOrder(Authentication authentication) {
        CustomerOrder order = orderRepository.findByUserEmailAndStatus(authentication.getName(), 1);
        if (order == null) {
            BranchSeat branchSeat = branchSeatRepository.findByUserEmail(authentication.getName());
            orderRepository.save(new CustomerOrder(0, 0,
                    Date.valueOf(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toLocalDate()),
                    authentication.getName(), branchSeat.getId(), 1));
            return ResponseEntity.ok(HttpStatus.CREATED);
        }
        return ResponseEntity.ok(HttpStatus.IM_USED);
    }

    @RequestMapping(value = "/cart", method = RequestMethod.PUT)
    public ResponseEntity putCartItem(Authentication authentication,
                                       @RequestBody CartItemForm cartItem) {

        CustomerOrder customerOrder = orderRepository.findByUserEmailAndStatus(authentication.getName(), 1);
        if (customerOrder == null) return ResponseEntity.ok(HttpStatus.NOT_FOUND);

        UserMenu userMenu = new UserMenu(0, cartItem.getMenuId(), customerOrder.getId(),
                cartItem.getCount(), 1);

        BranchMenu branchMenu = branchMenuRepository.findById(cartItem.getMenuId());
        if (branchMenu == null) return ResponseEntity.ok(HttpStatus.BAD_REQUEST);

        customerOrder.setPrice(customerOrder.getPrice() + branchMenu.getPrice());

        orderRepository.save(customerOrder);

        userMenuRepository.save(userMenu);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public ResponseEntity postCartItem(Authentication authentication) {

        CustomerOrder customerOrder = orderRepository.findByUserEmailAndStatus(authentication.getName(), 1);
        if (customerOrder == null) return ResponseEntity.ok(HttpStatus.NOT_FOUND);

        List<UserMenu> userMenus = userMenuRepository.findAllByOrderId(customerOrder.getId());
        if (userMenus == null) return ResponseEntity.ok(HttpStatus.NO_CONTENT);

        userMenus.forEach(userMenu -> userMenu.setProductStatusId(2));

        userMenuRepository.saveAll(userMenus);

        return ResponseEntity.ok(HttpStatus.OK);
    }


}
