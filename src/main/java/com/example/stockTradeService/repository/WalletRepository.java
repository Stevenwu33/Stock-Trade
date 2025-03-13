package com.example.stockTradeService.repository;

import com.example.stockTradeService.models.AppUser;
import com.example.stockTradeService.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,Long> {


    Optional<Wallet> findByAppUserId(Long appUserId);


}
