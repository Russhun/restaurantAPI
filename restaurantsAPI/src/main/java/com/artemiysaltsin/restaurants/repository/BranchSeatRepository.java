package com.artemiysaltsin.restaurants.repository;

import com.artemiysaltsin.restaurants.model.BranchSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchSeatRepository extends JpaRepository<BranchSeat, Integer> {

    BranchSeat findByUserEmail(String userEmail);
    BranchSeat findByBranchIdAndTableNumberAndSeatNumber(int branchId, int tableNumber, int seatNumber);
    List<BranchSeat> findAllByStaffId(int staffId);
    List<BranchSeat> findAllByBranchIdAndUserEmailAndEnable(int branchId, String userEmail, boolean enable);
}
