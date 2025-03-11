package com.example.stockTradeService.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class StockTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "app_User_id", nullable = false)
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    private BigDecimal quantity;

    private BigDecimal priceAtTransaction;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate transactionDate;

    @Override
    public String toString() {
        return "StockTransaction{" +
                "id=" + id +
                ", appUser=" + appUser +
                ", stock=" + stock +
                ", quantity=" + quantity +
                ", priceAtTransaction=" + priceAtTransaction +
                ", transactionType=" + transactionType +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
