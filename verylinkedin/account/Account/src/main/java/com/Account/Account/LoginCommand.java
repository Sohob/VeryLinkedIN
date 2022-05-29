package com.Account.Account;

import com.Account.ICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginCommand implements ICommand {

    private AccountRepository accountRepository;

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
        String rawPassword = account.get("password");
        String username = account.get("username");

        Account accountDB = accountRepository.findByUsername(username);
        long id = accountDB.getId();

        if (accountDB == null) {
            return "Login Failed";
        }

        System.out.println(bCryptPasswordEncoder.encode(rawPassword));
        System.out.println(accountDB.getPassword());
        if (!bCryptPasswordEncoder.matches(rawPassword, accountDB.getPassword())) {
            return "Login Failed";
        }

        return jwtUtil.generateToken(id);
    }
}
