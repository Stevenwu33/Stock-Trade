package com.example.stockTradeService.controller;

import com.example.stockTradeService.models.AppUser;
import com.example.stockTradeService.service.AppUserService;
import com.example.stockTradeService.service.StockTransactionService;
import com.example.stockTradeService.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private StockTransactionService stockTransactionService;


    @GetMapping("/")
    public String getHome(Model model) {
        Long userId = 1L; // 🔹 Replace with actual logic to get the logged-in user
        Optional<AppUser> user = appUserService.getUserById(userId);
        user.ifPresent(u -> model.addAttribute("user", u));
        return "home-page";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }


    @PostMapping("/login")
    public String loginUser(@RequestParam String name,
                            @RequestParam String email,
                            @RequestParam String password,
                            Model model) {
        // Simulating authentication - Replace this with actual user verification logic
        if ("test@example.com".equals(email) && "password123".equals(password)) {
            model.addAttribute("user", name);
            return "redirect:/"; // Redirect to the home page on successful login
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login"; // Reload login page with an error message
        }



    }

    @GetMapping("/register")
    public String getRegister() {
        return "register"; // This will load register.html
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String referralSource,
                               Model model) {
        // Simulated user registration - replace with actual DB logic
        System.out.println("New User Registered: " + name + " | " + email + " | Referred by: " + referralSource);

        // Redirect to login after successful registration
        return "redirect:/login";
    }





}
