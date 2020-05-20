package com.artemiysaltsin.restaurants.controller;

import com.artemiysaltsin.restaurants.forms.BranchIdForm;
import com.artemiysaltsin.restaurants.forms.PlaceForm;
import com.artemiysaltsin.restaurants.model.BranchSeat;
import com.artemiysaltsin.restaurants.repository.BranchSeatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeatController {
    /**
     *
     * Обработка запросов связанных с местами
     *
     */

    @Autowired
    BranchSeatRepository branchSeatRepository;

    @RequestMapping(value = "/seat", method = RequestMethod.POST)
    public ResponseEntity postSeat(Authentication authentication,
                                   @RequestBody PlaceForm placeForm) {

        // Если пользователь пытается усадить на место другого человека, запрос отклоняется
        // Иначе место фиксируется за посетителем
        if (!authentication.getName().equals(placeForm.getUserEmail())) return ResponseEntity.ok(HttpStatus.FORBIDDEN);

        BranchSeat branchSeat = branchSeatRepository.findByBranchIdAndTableNumberAndSeatNumber(placeForm.getBranchId(),
                placeForm.getTableId(),
                placeForm.getSeatId());

        branchSeat.setUserEmail(placeForm.getUserEmail());
        branchSeatRepository.save(branchSeat);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/seat", method = RequestMethod.GET)
    public ResponseEntity getSeat(@RequestBody BranchIdForm branchIdForm) {
        //
        // Возвращает все свободные столы по id филиала
        //
        return ResponseEntity.ok(branchSeatRepository.findAllByBranchIdAndUserEmailAndEnable(branchIdForm.getBranchId(),
                null,
                true));
    }


    @RequestMapping(value = "/seat", method = RequestMethod.DELETE)
    public ResponseEntity deleteSeat(Authentication authentication,
                                     @RequestBody PlaceForm placeForm) {
        //
        // Открепляет место от пользователя. Если это пытается сделать кто-то другое,
        // то запрос отклоняется
        //
        if (!authentication.getName().equals(placeForm.getUserEmail())) return ResponseEntity.ok(HttpStatus.FORBIDDEN);

        BranchSeat branchSeat = branchSeatRepository.findByBranchIdAndTableNumberAndSeatNumber(placeForm.getBranchId(),
                placeForm.getTableId(),
                placeForm.getSeatId());

        branchSeat.setUserEmail(null);
        branchSeatRepository.save(branchSeat);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
