package com.example.stockTradeService.repository;

import com.example.stockTradeService.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock,Long> {

//    Stock findByTicker(String ticker);
//    List<Stock> getAllByCurrentPriceAsc();
//    List<Stock> getAllByCurrentPriceDesc();

}


// make repos, service the n controller

