package com.example.stockTradeService.repository;

import com.example.stockTradeService.models.Stock;
import com.example.stockTradeService.models.StockTransaction;
import com.example.stockTradeService.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {


    // Get all stock transactions (both BUY and SELL) for a user and a stock
    List<StockTransaction> findByAppUserIdAndStockId(Long userId, Long stockId);

    List<StockTransaction> findByAppUserId(Long userId);

    List<StockTransaction> findByAppUserIdAndStockIdAndTransactionType(Long userId, Long stockId, TransactionType transactionType);

}
