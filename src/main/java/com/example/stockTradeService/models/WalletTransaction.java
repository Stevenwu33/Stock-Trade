package com.example.stockTradeService.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "app_User_id", nullable = false)
    private AppUser appUser;

    private BigDecimal amount; //Amount of money added or withdrawn

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt; // Tracks when the transaction happened

    public WalletTransaction() {
    }

    public WalletTransaction(Long id, AppUser appUser, BigDecimal amount, LocalDateTime createdAt) {
        this.id = id;
        this.appUser = appUser;
        this.amount = amount;
        this.createdAt = createdAt;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "WalletTransaction{" +
                "id=" + id +
                ", appUser=" + appUser +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                '}';
    }
}
