package com.artemiysaltsin.restaurants.controller;

import com.artemiysaltsin.restaurants.forms.BranchIdForm;
import com.artemiysaltsin.restaurants.forms.EmailForm;
import com.artemiysaltsin.restaurants.forms.StaffForm;
import com.artemiysaltsin.restaurants.model.BranchSeat;
import com.artemiysaltsin.restaurants.model.BranchStaff;
import com.artemiysaltsin.restaurants.model.Users;
import com.artemiysaltsin.restaurants.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StaffController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    BranchStaffRepository branchStaffRepository;

    @Autowired
    UserAddressRepository userAddressRepository;

    @Autowired
    BranchSeatRepository branchSeatRepository;


    @RequestMapping(value = "/staff", method = RequestMethod.GET)
    public ResponseEntity getStaff(@RequestBody BranchIdForm branchIdForm) {
        Map<Integer, Users> usersMap = new HashMap<>();
        List<BranchStaff> branchStaffList = branchStaffRepository.findAllByBranchId(branchIdForm.getBranchId());
        if (branchStaffList != null)
            if (!branchStaffList.isEmpty()) {
                for (BranchStaff branchStaff : branchStaffList) {
                    usersMap.put(branchStaff.getId(), userRepository.findByEmail(branchStaff.getUserEmail()));
                }
                return ResponseEntity.ok(usersMap);
            }
        return ResponseEntity.ok(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/staff", method = RequestMethod.POST)
    public ResponseEntity postStaff(@RequestBody StaffForm staffForm) {

        Users user = userRepository.findByEmail(staffForm.getEmail());
        if (user != null) return ResponseEntity.ok(HttpStatus.CONFLICT);
        String password = new BCryptPasswordEncoder().encode(staffForm.getPassword());

        userRepository.save(new Users(staffForm.getEmail(), staffForm.getFirstName(), staffForm.getLastName(),
                staffForm.getSureName(), staffForm.getAge(), password,true,true,
                appRoleRepository.findById(2), null));

        branchStaffRepository.save(new BranchStaff(1, staffForm.getBranchId(), staffForm.getEmail(),
                staffForm.getSalary(), staffForm.getHireDate()));

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/staff", method = RequestMethod.DELETE)
    public ResponseEntity deleteStaff(@RequestBody EmailForm staffEmail) {

        BranchStaff staff = branchStaffRepository.findByUserEmail(staffEmail.getEmail());
        if (staff == null) return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        List<BranchSeat> branchSeats = branchSeatRepository.findAllByStaffId(staff.getId());

        for (BranchSeat branchSeat : branchSeats) {
            branchSeat.setStaffId(null);
            branchSeatRepository.save(branchSeat);
        }


        branchStaffRepository.delete(staff);
        Users user = userRepository.findByEmail(staffEmail.getEmail());
        userRepository.delete(user);
        if (user.getUserAddress() != null) userAddressRepository.delete(user.getUserAddress());

        return ResponseEntity.ok(HttpStatus.OK);
    }




}
