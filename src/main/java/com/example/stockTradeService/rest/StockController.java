package com.example.stockTradeService.rest;

import com.example.stockTradeService.models.Stock;
import com.example.stockTradeService.repository.StockRepository;
import com.example.stockTradeService.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    public final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }


    @GetMapping("/display-all-stocks")
    public ResponseEntity<List<Stock>> getAllStocks(){

        return ResponseEntity.ok(stockService.getAllStocks());
    }


}
