package com.example.stockTradeService.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "app_user_id", nullable = false)
    private AppUser appUser;

    private BigDecimal balance = BigDecimal.valueOf(10000.00);

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt; // Tracks last balance change

    public Wallet() {
    }

    public Wallet(Long id, AppUser appUser, BigDecimal balance, LocalDateTime updatedAt) {
        this.id = id;
        this.appUser = appUser;
        this.balance = balance;
        this.updatedAt = updatedAt;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", appUser=" + appUser +
                ", balance=" + balance +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
