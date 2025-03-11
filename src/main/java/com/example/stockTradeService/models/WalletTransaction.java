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
