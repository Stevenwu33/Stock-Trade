package com.example.stockTradeService.service;

import com.example.stockTradeService.models.AppUser;
import com.example.stockTradeService.models.StockTransaction;
import com.example.stockTradeService.models.UserStockHoldings;
import com.example.stockTradeService.models.Wallet;
import com.example.stockTradeService.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService{

    @Autowired
    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser createUser(AppUser user) {
        return appUserRepository.save(user);
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
