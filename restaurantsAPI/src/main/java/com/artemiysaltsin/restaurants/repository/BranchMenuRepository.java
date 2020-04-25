package com.artemiysaltsin.restaurants.repository;

import com.artemiysaltsin.restaurants.model.BranchMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchMenuRepository extends JpaRepository<BranchMenu, Integer> {

    List<BranchMenu> findAllByBranchId(int branchId);

}
