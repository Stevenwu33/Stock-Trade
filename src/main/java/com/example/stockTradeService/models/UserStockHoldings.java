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

    public UserStockHoldings() {
    }

    public UserStockHoldings(Long id, AppUser appUser, Stock stock, BigDecimal quantity, BigDecimal averageStockPrice) {
        this.id = id;
        this.appUser = appUser;
        this.stock = stock;
        this.quantity = quantity;
        this.averageStockPrice = averageStockPrice;
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

    public BigDecimal getAverageStockPrice() {
        return averageStockPrice;
    }

    public void setAverageStockPrice(BigDecimal averageStockPrice) {
        this.averageStockPrice = averageStockPrice;
    }

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
