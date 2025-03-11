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
    private AppUser appUser;

    private BigDecimal balance;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt; // Tracks last balance change

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
