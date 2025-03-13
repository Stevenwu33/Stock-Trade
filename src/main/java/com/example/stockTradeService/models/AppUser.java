package com.example.stockTradeService.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class AppUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL) //The mappedBy = "user" tells Hibernate that this entity (AppUser) is not the owner of the relationship.
    private Wallet wallet;                                          // the owner belongs to the other side(wallet)

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<StockTransaction> stockTransactions;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<UserStockHoldings> userStockHoldings;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<WalletTransaction> walletTransactions;

    public AppUser() {
    }

    public AppUser(Long id, String username, String email, Wallet wallet, List<StockTransaction> stockTransactions, List<UserStockHoldings> userStockHoldings, List<WalletTransaction> walletTransactions) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.wallet = wallet;
        this.stockTransactions = stockTransactions;
        this.userStockHoldings = userStockHoldings;
        this.walletTransactions = walletTransactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public List<StockTransaction> getStockTransactions() {
        return stockTransactions;
    }

    public void setStockTransactions(List<StockTransaction> stockTransactions) {
        this.stockTransactions = stockTransactions;
    }

    public List<UserStockHoldings> getUserStockHoldings() {
        return userStockHoldings;
    }

    public void setUserStockHoldings(List<UserStockHoldings> userStockHoldings) {
        this.userStockHoldings = userStockHoldings;
    }

    public List<WalletTransaction> getWalletTransactions() {
        return walletTransactions;
    }

    public void setWalletTransactions(List<WalletTransaction> walletTransactions) {
        this.walletTransactions = walletTransactions;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", wallet=" + wallet +
                ", stockTransactions=" + stockTransactions +
                ", userStockHoldings=" + userStockHoldings +
                ", walletTransactions=" + walletTransactions +
                '}';
    }
}
