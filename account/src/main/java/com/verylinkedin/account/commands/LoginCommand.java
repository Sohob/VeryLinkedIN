package com.verylinkedin.account.commands;

import com.verylinkedin.account.AccountRepository;
import com.verylinkedin.account.ICommand;
import com.verylinkedin.account.classes.Account;
import com.verylinkedin.account.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginCommand implements ICommand {

    public AccountRepository accountRepository;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public LoginCommand(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public String execute(Object object) {

        Map<String, String> account = (Map<String, String>) object;
        String rawPassword = account.get("password")+"";
        String username = account.get("username");

        Account accountDB = accountRepository.findByUsername(username);

        if (accountDB == null) {
            System.out.println("No User Found! ");
            return "Account Doesn't Exist";

        }
        long id = accountDB.getId();

        System.out.println(bCryptPasswordEncoder.encode(rawPassword));
        System.out.println(accountDB.getPassword());
        if (!bCryptPasswordEncoder.matches(rawPassword, accountDB.getPassword())) {
            System.out.println("Password Incorrect! ");
            return "Incorrect Password";
        }

        return jwtUtil.generateToken(id);
    }
}
