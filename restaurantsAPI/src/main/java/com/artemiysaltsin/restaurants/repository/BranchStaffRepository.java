package com.artemiysaltsin.restaurants.repository;

import com.artemiysaltsin.restaurants.model.BranchStaff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchStaffRepository extends JpaRepository<BranchStaff, Integer> {

    BranchStaff findById(int id);
    BranchStaff findByUserEmail(String email);
    List<BranchStaff> findAllByBranchId(int branchId);

}
