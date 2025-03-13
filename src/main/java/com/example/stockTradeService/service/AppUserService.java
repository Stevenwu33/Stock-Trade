package com.example.stockTradeService.service;

import com.example.stockTradeService.models.AppUser;
import com.example.stockTradeService.models.StockTransaction;
import com.example.stockTradeService.models.UserStockHoldings;
import com.example.stockTradeService.models.Wallet;
import com.example.stockTradeService.repository.AppUserRepository;
import com.example.stockTradeService.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService{

    @Autowired
    private final AppUserRepository appUserRepository;

    @Autowired
    private WalletRepository walletRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser createUser(String username, String email) {

        AppUser user = new AppUser();       // when will we use this??
        user.setUsername("Test");
        user.setEmail("Test@email.com");

        // Save the user first
        user = appUserRepository.save(user);

        // Create a wallet for the user
        Wallet wallet = new Wallet();
        wallet.setAppUser(user);
        wallet.setBalance(BigDecimal.valueOf(10000.00)); // Initial balance
        walletRepository.save(wallet);

        return user;
    }

    public Optional<AppUser> getUserById(Long id){
        return appUserRepository.findById(id);
    }
    public Optional<AppUser> getUserByEmail(String email){
        return appUserRepository.findByEmail(email);
    }
//    public List<StockTransaction> getUserStockTransactions(Long userId){
//
//
//    }
//
//    public List<UserStockHoldings> getUserStockHoldings(Long userId){
//
//    }

    //add these two to the appUSer serivve becuase its easiet to track for fast personal
    // information instead of gping t the actial model fo rit










}
