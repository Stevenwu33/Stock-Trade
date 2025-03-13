package com.example.stockTradeService.controller;

import com.example.stockTradeService.models.AppUser;
import com.example.stockTradeService.models.StockTransaction;
import com.example.stockTradeService.repository.StockTransactionRepository;
import com.example.stockTradeService.service.AppUserService;
import com.example.stockTradeService.service.StockTransactionService;
import org.springframework.ui.Model;


import com.example.stockTradeService.models.Stock;
import com.example.stockTradeService.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/transaction")
public class TransactionController {


    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockTransactionService stockTransactionService;

    @GetMapping("/new")
    public String showTransactionPage(@RequestParam Long stockId,@RequestParam Long userId ,Model model) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
        model.addAttribute("stock", stock);
        model.addAttribute("userId", userId);
        return "transaction-page";
    }


    @GetMapping("/checkout")
    public String showCheckout(@RequestParam Long stockId,
                               @RequestParam BigDecimal quantity,
                               @RequestParam Long userId,
                               Model model) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        BigDecimal totalCost = stock.getCurrentPrice().multiply(quantity);

        model.addAttribute("stock", stock);
        model.addAttribute("quantity", quantity);
        model.addAttribute("totalCost", totalCost);
        model.addAttribute("userId", userId);

        StockTransaction transaction = new StockTransaction();
        transaction.setStock(stock);
        transaction.setQuantity(quantity);
        model.addAttribute("transactions", List.of(transaction)); // Ensures Thymeleaf sees it



        return "checkout";
    }



    // Update stock quantity in checkout
//    @PostMapping("/update")
//    public String updateTransaction(@RequestParam Long transactionId, @RequestParam BigDecimal newQuantity, @RequestParam Long userId) {
//        stockTransactionService.updateTransaction(transactionId, newQuantity);
//        return "redirect:/transaction/checkout?userId=" + userId; // Correct reload after update
//    }
//    // Delete a stock from checkout
//    @PostMapping("/delete")
//    public String deleteTransaction(@RequestParam Long transactionId, @RequestParam Long userId) {
//        stockTransactionService.deleteTransaction(transactionId);
//        return "redirect:/transaction/checkout?userId=" + userId;
//    }

    // Confirm purchase (Only saves transaction now)
    @PostMapping("/confirm")
    public String confirmPurchase(@RequestParam Long userId,
                                  @RequestParam Long stockId,
                                  @RequestParam BigDecimal quantity) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        stockTransactionService.buyStock(userId, stockId, quantity); // Now saves transaction

        return "redirect:/profile/" + userId;  // Redirect to profile after confirming purchase
    }






}
