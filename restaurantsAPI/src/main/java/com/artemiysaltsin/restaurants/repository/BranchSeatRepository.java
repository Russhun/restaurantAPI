package com.artemiysaltsin.restaurants.repository;

import com.artemiysaltsin.restaurants.model.BranchSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchSeatRepository extends JpaRepository<BranchSeat, Integer> {

    List<BranchSeat> findAllByTableNumber(int tableNum);
    BranchSeat findByUserEmail(String userEmail);
    List<BranchSeat> findAllByStaffId(int staffId);
    List<BranchSeat> findAllByBranchId(int branchId);
    List<BranchSeat> findAllByEnableAndStaffId(boolean enable, int staffId);

}
