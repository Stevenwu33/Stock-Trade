package com.example.stockTradeService.controller;

import com.example.stockTradeService.models.AppUser;
import com.example.stockTradeService.service.AppUserService;
import com.example.stockTradeService.service.StockTransactionService;
import com.example.stockTradeService.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private StockTransactionService stockTransactionService;


    @GetMapping("/")
    public String getHome(Model model) {
        Long userId = 1L; // 🔹 Replace with actual logic to get the logged-in user
        Optional<AppUser> user = appUserService.getUserById(userId);
        user.ifPresent(u -> model.addAttribute("user", u));
        return "home-page";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }








}
