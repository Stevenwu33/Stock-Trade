package com.example.stockTradeService.service;

import com.example.stockTradeService.models.*;
import com.example.stockTradeService.repository.AppUserRepository;
import com.example.stockTradeService.repository.StockRepository;
import com.example.stockTradeService.repository.StockTransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class StockTransactionService {


    @Autowired
    private StockTransactionRepository stockTransactionRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private WalletService walletService;





    // ✅ Handles stock purchase, deducts funds, and records transaction
    @Transactional
    public void buyStock(Long userId, Long stockId, BigDecimal quantity) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        BigDecimal totalCost = stock.getCurrentPrice().multiply(quantity);

        // Check if user has enough balance
        if (!walletService.hasSufficientBalance(user, totalCost)) {
            throw new RuntimeException("Insufficient funds");
        }

        // Deduct balance
        walletService.deductBalance(user, totalCost);

        // Create a stock transaction
        StockTransaction transaction = new StockTransaction();
        transaction.setAppUser(user);
        transaction.setStock(stock);
        transaction.setQuantity(quantity);
        transaction.setPriceAtTransaction(stock.getCurrentPrice());
        transaction.setTransactionType(TransactionType.BUY);
        transaction.setTransactionDate(LocalDate.now());

        stockTransactionRepository.save(transaction);
    }

    // ✅ Handles stock sale, credits funds, and records transaction
    @Transactional
    public void sellStock(Long userId, Long stockId, BigDecimal quantity) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        // Check if user has enough stocks to sell
        BigDecimal userStockQuantity = getTotalStockQuantity(userId, stockId);
        if (userStockQuantity.compareTo(quantity) < 0) {
            throw new RuntimeException("Not enough stock to sell");
        }

        // Calculate the earnings
        BigDecimal totalEarnings = stock.getCurrentPrice().multiply(quantity);

        // Credit balance to wallet
        walletService.addBalance(user, totalEarnings);

        // Create a stock transaction
        StockTransaction transaction = new StockTransaction();
        transaction.setAppUser(user);
        transaction.setStock(stock);
        transaction.setQuantity(quantity);
        transaction.setPriceAtTransaction(stock.getCurrentPrice());
        transaction.setTransactionType(TransactionType.SELL);
        transaction.setTransactionDate(LocalDate.now());

        stockTransactionRepository.save(transaction);
    }

    // 📄 Get all transactions for a user
    public List<StockTransaction> getUserTransactions(Long userId) {
        return stockTransactionRepository.findByAppUserId(userId);
    }

    public BigDecimal getTotalStockQuantity(Long userId, Long stockId) {
        List<StockTransaction> transactions = stockTransactionRepository.findByAppUserIdAndStockId(userId, stockId);

        BigDecimal totalQuantity = BigDecimal.ZERO;

        for (StockTransaction transaction : transactions) {
            if (transaction.getTransactionType() == TransactionType.BUY) {
                totalQuantity = totalQuantity.add(transaction.getQuantity());
            } else if (transaction.getTransactionType() == TransactionType.SELL) {
                totalQuantity = totalQuantity.subtract(transaction.getQuantity());
            }
        }

        return totalQuantity.max(BigDecimal.ZERO);

    }



    //FOR CHECKOUT PAGE

    // Calculate total checkout cost
    public BigDecimal calculateTotalCost(Long userId) {
        return getUserTransactions(userId).stream()
                .map(t -> t.getStock().getCurrentPrice().multiply(t.getQuantity())) // No need for valueOf()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    // FOr Transaction history Delete



    public void deleteTransaction(Long transactionId) {
        // Check if the transaction exists
        if (!stockTransactionRepository.existsById(transactionId)) {
            throw new RuntimeException("Transaction not found");
        }
        // Delete the transaction
        stockTransactionRepository.deleteById(transactionId);
    }




    // Update the quantity of a stock in checkout --- not a crud methdd
//    public void updateTransaction(Long transactionId, BigDecimal newQuantity) {
//        StockTransaction transaction = stockTransactionRepository.findById(transactionId)
//                .orElseThrow(() -> new RuntimeException("Transaction not found"));
//
//        // Update the quantity
//        transaction.setQuantity(newQuantity);
//
//        // Optionally, update the priceAtTransaction if needed (though this is not necessary if you just want the current price)
//        transaction.setPriceAtTransaction(transaction.getStock().getCurrentPrice());
//
//        // Save the updated transaction
//        stockTransactionRepository.save(transaction);
//    }

    // Delete a stock from checkout - not a crud method
//    public void deleteTransaction(Long transactionId) {
//        stockTransactionRepository.deleteById(transactionId);
//    }
//
//    // Get user ID from transaction (for redirects)
//    public Long getUserIdFromTransaction(Long transactionId) {
//        StockTransaction transaction = stockTransactionRepository.findById(transactionId)
//                .orElseThrow(() -> new RuntimeException("Transaction not found"));
//
//        return transaction.getAppUser().getId(); // Make sure 'getAppUser()' is correctly named in your entity
//    }
//
//    // Confirm purchase: Charge wallet and finalize transactions
//    public void confirmPurchase(Long userId) {
//        List<StockTransaction> transactions = stockTransactionRepository.findByAppUserId(userId);
//        BigDecimal totalCost = calculateTotalCost(userId);
//
//        Wallet wallet = walletService.getWallet(userId);
//        if (wallet.getBalance().compareTo(totalCost) < 0) {
//            throw new RuntimeException("Insufficient funds");
//        }
//
//        wallet.setBalance(wallet.getBalance().subtract(totalCost));
//        walletService.updateWallet(wallet);
//
//        stockTransactionRepository.saveAll(transactions); // Save finalized transactions
//    }







}
