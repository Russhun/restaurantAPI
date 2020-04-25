package com.artemiysaltsin.restaurants.repository;

import com.artemiysaltsin.restaurants.model.UserMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMenuRepository extends JpaRepository<UserMenu, Integer> {

    List<UserMenu> findAllByOrderIdAndBranchSeatId(int orderId, int branchSeatId);


}
