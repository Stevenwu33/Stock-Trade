package com.example.stockTradeService.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity

public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticker")
    private String ticker; //stock symbol TSLA
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "current_price")
    private BigDecimal currentPrice;


    public Stock() {
    }

    public Stock(Long id, String ticker, String companyName, BigDecimal currentPrice) {
        this.id = id;
        this.ticker = ticker;
        this.companyName = companyName;
        this.currentPrice = currentPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", ticker='" + ticker + '\'' +
                ", companyName='" + companyName + '\'' +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
