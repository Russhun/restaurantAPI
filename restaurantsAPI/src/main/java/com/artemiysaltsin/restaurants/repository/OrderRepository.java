package com.artemiysaltsin.restaurants.repository;

import com.artemiysaltsin.restaurants.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<CustomerOrder, Integer> {

    CustomerOrder findByStatusAndBranchSeatId(int status, int branchSeatId);
    CustomerOrder findByUserEmailAndStatus(String userEmail, int status);
    List<CustomerOrder> findAllByStatus(int status);

}
