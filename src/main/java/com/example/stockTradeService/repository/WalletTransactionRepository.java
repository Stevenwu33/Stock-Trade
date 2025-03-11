package com.example.stockTradeService.repository;


import com.example.stockTradeService.models.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {

}
