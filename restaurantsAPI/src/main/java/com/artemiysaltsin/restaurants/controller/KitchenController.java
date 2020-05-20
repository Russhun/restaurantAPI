package com.artemiysaltsin.restaurants.controller;


import com.artemiysaltsin.restaurants.forms.KitchenForm;
import com.artemiysaltsin.restaurants.model.BranchMenu;
import com.artemiysaltsin.restaurants.model.UserMenu;
import com.artemiysaltsin.restaurants.repository.BranchMenuRepository;
import com.artemiysaltsin.restaurants.repository.UserMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class KitchenController {
    /**
     * Контроллер, отвечающий за обработку запросов связанных с кухней
     */

    @Autowired
    UserMenuRepository userMenuRepository;

    @Autowired
    BranchMenuRepository branchMenuRepository;

    @RequestMapping(value = "/kitchen", method = RequestMethod.GET)
    public ResponseEntity getProducts() {
        //
        // Возвращает все заказы со статусом 2 (готовится)
        // Если таких нет, то возвращает NOT_FOUND
        //
        List<BranchMenu> userMenuItems = new ArrayList<BranchMenu>();
        List<UserMenu> userMenus = userMenuRepository.findAllByProductStatusId(2);
        if (userMenus != null)
            if (!userMenus.isEmpty())
            {
                for (UserMenu userMenu : userMenus) {
                    userMenuItems.add(branchMenuRepository.findById(userMenu.getMenuId()));
                }
                return ResponseEntity.ok(userMenuItems);
            }

        return ResponseEntity.ok(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(value = "/kitchen", method = RequestMethod.POST)
    public ResponseEntity makeProduct(@RequestBody KitchenForm kitchenForm) {
        //
        // Возвращает OK, если статус заказа был обновлён на 3 (приготовлен)
        //
        UserMenu userMenu = userMenuRepository.findById(kitchenForm.getUserMenuId());
        userMenu.setProductStatusId(3);
        userMenuRepository.save(userMenu);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
