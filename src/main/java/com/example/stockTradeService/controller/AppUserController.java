package com.example.stockTradeService.controller;

import com.example.stockTradeService.models.AppUser;
import com.example.stockTradeService.models.StockTransaction;
import com.example.stockTradeService.models.Wallet;
import com.example.stockTradeService.repository.AppUserRepository;
import com.example.stockTradeService.service.AppUserService;
import com.example.stockTradeService.service.StockTransactionService;
import com.example.stockTradeService.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private StockTransactionService stockTransactionService;

    @GetMapping("/{userId}")
    public String showProfile(@PathVariable Long userId, Model model) {
        AppUser user = appUserService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wallet wallet = walletService.getWallet(user.getId());
        List<StockTransaction> transactions = stockTransactionService.getUserTransactions(userId);

        model.addAttribute("user", user);
        model.addAttribute("wallet", wallet);
        model.addAttribute("transactions", transactions);

        return "user-profile";
    }

    @PostMapping("/{userId}/update-email")
    public String updateEmail(@PathVariable Long userId, @RequestParam String email) {
        appUserService.updateEmail(userId, email);
        return "redirect:/profile/" + userId; // Redirect back to profile after update
    }

}
