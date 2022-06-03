package com.verylinkedin.account;

import com.verylinkedin.account.classes.Account;
import com.verylinkedin.account.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String createAccount(Account account) {
        System.out.println(account);
        //TODO make sure username is new (no account has this username)
        Account accountFound = accountRepository.findByUsername(account.getUsername());
        if (accountFound != null) {
            return "Username Already Taken";
        }

        String hashedPassword = bCryptPasswordEncoder.encode(account.getPassword());
        account.setPassword(hashedPassword);
        System.out.println(hashedPassword);
        accountRepository.save(account);
        return "done";
    }

    public String login(Account account) {

        String rawPassword = account.getPassword();
        String username = account.getUsername();

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

    public String deleteAccount(long userID , String token) {

        if(jwtUtil.validateToken(token,userID)){
            accountRepository.deleteById(userID);
            System.out.println("del");
            return "delete";
        }
        return "unauth";
    }


}
