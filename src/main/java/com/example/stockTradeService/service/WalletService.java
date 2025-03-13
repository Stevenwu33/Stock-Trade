package com.example.stockTradeService.service;

import com.example.stockTradeService.models.AppUser;
import com.example.stockTradeService.models.Wallet;
import com.example.stockTradeService.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    // ✅ Checks if the user has enough balance for a transaction
    public boolean hasSufficientBalance(AppUser user, BigDecimal totalCost) {
        Wallet wallet = getWallet(user.getId());
        return wallet.getBalance().compareTo(totalCost) >= 0;
    }

    // ✅ Deducts balance from the user's wallet when they buy stocks
    public void deductBalance(AppUser user, BigDecimal totalCost) {
        Wallet wallet = walletRepository.findByAppUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        // Check balance directly instead of calling another method
        if (wallet.getBalance().compareTo(totalCost) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        // Deduct balance and save
        wallet.setBalance(wallet.getBalance().subtract(totalCost));
        walletRepository.save(wallet);
    }

    // ✅ Adds balance to the user's wallet when they sell stocks
    public void addBalance(AppUser user, BigDecimal amount) {
        Wallet wallet = getWallet(user.getId());
        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);
    }

    // ✅ Retrieves the user's wallet, throws an error if not found
    public Wallet getWallet(Long userId) {
        return walletRepository.findByAppUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    public void updateWallet(Wallet wallet) {
        walletRepository.save(wallet);
    }
}
