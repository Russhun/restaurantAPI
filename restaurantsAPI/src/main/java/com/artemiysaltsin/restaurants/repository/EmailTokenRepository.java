package com.artemiysaltsin.restaurants.repository;

import com.artemiysaltsin.restaurants.model.EmailToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailTokenRepository extends JpaRepository<EmailToken, String> {


}
