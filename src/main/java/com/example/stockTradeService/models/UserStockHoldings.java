package com.example.stockTradeService.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class UserStockHoldings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "app_User_id", nullable = false)
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @Column(nullable = false)
    private BigDecimal quantity;

    @Column(nullable = false)
    private BigDecimal averageStockPrice;


    @Override
    public String toString() {
        return "UserStockHoldings{" +
                "id=" + id +
                ", appUser=" + appUser +
                ", stock=" + stock +
                ", quantity=" + quantity +
                ", averageStockPrice=" + averageStockPrice +
                '}';
    }
}
