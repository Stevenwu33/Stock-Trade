package com.example.stockTradeService.controller;

import com.example.stockTradeService.models.Stock;
import com.example.stockTradeService.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }


//    @GetMapping
//    public String getAllStocks(@RequestParam Long userId, Model model){
//        List<Stock> stocks = stockService.getAllStocks();
//        model.addAttribute("stocks",stocks);
//        // If userId exists, pass it to the model
//        if (userId != null) {
//            model.addAttribute("userId", userId);
//        }
//        return "stock-list";
//    }

    @GetMapping
    public String getAllStocks( Model model){
        List<Stock> stocks = stockService.getAllStocks();
        Long userId = 1L; // Replace this with actual logic to get the logged-in user

        model.addAttribute("stocks",stocks);
        // If userId exists, pass it to the model

            model.addAttribute("userId", userId);

        return "stock-list";
    }













}
