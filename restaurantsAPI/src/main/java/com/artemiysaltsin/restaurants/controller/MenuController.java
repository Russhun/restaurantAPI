package com.artemiysaltsin.restaurants.controller;

import com.artemiysaltsin.restaurants.forms.BranchIdForm;
import com.artemiysaltsin.restaurants.model.BranchMenu;
import com.artemiysaltsin.restaurants.repository.BranchMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {
    /**
     *
     * Контроллер, отвечающий за обработку запросов связанных с меню
     *
     */

    @Autowired
    BranchMenuRepository branchMenuRepository;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public ResponseEntity getMenu(@RequestBody BranchIdForm branchIdForm) {
        //
        // Возвращает List с меню филиала ресторана по его id
        //
        return ResponseEntity.ok(branchMenuRepository.findAllByBranchId(branchIdForm.getBranchId()));
    }

    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    public ResponseEntity postMenu(@RequestBody BranchMenu branchMenu) {
        //
        // Возвращает CREATED после добавления товара в меню
        //
        branchMenuRepository.save(branchMenu);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/menu", method = RequestMethod.DELETE)
    public ResponseEntity deleteMenu(@RequestBody BranchMenu branchMenu) {
        //
        // Возвращает OK после удаления товара из меню
        //
        if (branchMenu.getId() == null) return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        branchMenuRepository.deleteById(branchMenu.getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }


}

