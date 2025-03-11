package com.example.stockTradeService.repository;

import com.example.stockTradeService.models.Stock;
import com.example.stockTradeService.models.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {


}
