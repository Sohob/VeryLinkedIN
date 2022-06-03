package com.verylinkedin.account;

import com.verylinkedin.account.commands.CreateAccountCommand;
import com.verylinkedin.account.commands.DeleteAccountCommand;
import com.verylinkedin.account.commands.LoginCommand;
import com.verylinkedin.account.commands.RecommendCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {

    private AccountService accountService;

    @Autowired
    CreateAccountCommand createAccountCommand;

    @Autowired
    LoginCommand loginCommand;

    @Autowired
    DeleteAccountCommand deleteAccountCommand;

    @Autowired
    RecommendCommand recommendCommand;


    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account")
    public String createAccount(@RequestBody Map<String, Object> account) {
        return createAccountCommand.execute(account);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, Object> account) {
        return loginCommand.execute(account);
    }

    @DeleteMapping("/account/{id}")
    public String deleteAccount(@PathVariable Long id,@RequestHeader("Authorization") String token){
        Map<String, Object> body = new HashMap<>();
        body.put("userID" ,id);
        body.put("token" ,token);
        return deleteAccountCommand.execute(body);
    }

    @GetMapping("/recommend-companies")
    public String recommendCompanies(@RequestHeader("Authorization") String token) {
        Map<String, Object> body = new HashMap<>();
        body.put("token" ,token);
        return recommendCommand.execute(body).toString();

    }



}
