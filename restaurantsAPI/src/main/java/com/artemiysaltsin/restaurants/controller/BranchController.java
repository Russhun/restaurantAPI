package com.artemiysaltsin.restaurants.controller;

import com.artemiysaltsin.restaurants.forms.BranchIdForm;
import com.artemiysaltsin.restaurants.model.Branch;
import com.artemiysaltsin.restaurants.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BranchController {

    @Autowired
    BranchRepository branchRepository;

    @RequestMapping(value = "/branch", method = RequestMethod.GET)
    public ResponseEntity getBranch() {
        return ResponseEntity.ok(branchRepository.findAllByRestaurantIdAndEnable(1, true));
    }

    @RequestMapping(value = "/branch", method = RequestMethod.POST)
    public ResponseEntity postBranch(@RequestBody Branch branch) {
        branchRepository.save(branch);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/branch", method = RequestMethod.DELETE)
    public ResponseEntity postBranch(@RequestBody BranchIdForm branchIdForm) {
        Branch branch = branchRepository.findBranchById(branchIdForm.getBranchId());
        if (branch == null) return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        branch.setEnable(false);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
