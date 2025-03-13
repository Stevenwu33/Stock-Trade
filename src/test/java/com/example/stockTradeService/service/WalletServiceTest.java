package com.example.stockTradeService.service;

import com.example.stockTradeService.models.AppUser;
import com.example.stockTradeService.models.Wallet;
import com.example.stockTradeService.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")

public class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private WalletService walletService;

    @Test
    void testAddBalance() {
        // Arrange: Set up a user and wallet
        AppUser user = new AppUser();
        user.setId(1L);

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(100.00));

        when(walletRepository.findByAppUserId(user.getId())).thenReturn(Optional.of(wallet));

        // Act: Add balance
        walletService.addBalance(user, BigDecimal.valueOf(50.00));

        // Assert: Verify the balance was updated
        assertEquals(BigDecimal.valueOf(150.00), wallet.getBalance());
        verify(walletRepository).save(wallet); // Ensure the wallet was saved
    }
}


