package com.example.stockTradeService.repository;

import com.example.stockTradeService.models.UserStockHoldings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStockHoldingsRepository extends JpaRepository<UserStockHoldings,Long> {

}
