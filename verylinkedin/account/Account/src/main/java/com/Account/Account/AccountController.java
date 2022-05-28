package com.Account.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account")
    public String createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PostMapping("/login")
    public String login(@RequestBody Account account) {
        return accountService.login(account);
    }

    @DeleteMapping("/account/{id}")
    public String deleteAccount(@PathVariable Long id,@RequestHeader("Authorization") String token){
        System.out.println(token.substring(7));
        return accountService.deleteAccount(id , token.substring(7));
    }
}
