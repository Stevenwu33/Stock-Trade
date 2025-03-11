package com.example.stockTradeService.repository;

import com.example.stockTradeService.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
}
