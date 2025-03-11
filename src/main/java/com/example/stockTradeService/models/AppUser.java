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
