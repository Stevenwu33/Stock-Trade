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

    public StockTransaction() {
    }

    public StockTransaction(Long id, AppUser appUser, Stock stock, BigDecimal quantity, BigDecimal priceAtTransaction, TransactionType transactionType, LocalDate transactionDate) {
        this.id = id;
        this.appUser = appUser;
        this.stock = stock;
        this.quantity = quantity;
        this.priceAtTransaction = priceAtTransaction;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceAtTransaction() {
        return priceAtTransaction;
    }

    public void setPriceAtTransaction(BigDecimal priceAtTransaction) {
        this.priceAtTransaction = priceAtTransaction;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

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
