package com.Account.Account;

import com.Account.ICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DeleteAccountCommand implements ICommand {

    private AccountRepository accountRepository;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DeleteAccountCommand(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public String execute(Object object) {

        System.out.println("HERE");
        Map<String, Object> body = (Map<String, Object>) object;
        String token = (String) body.get("token");
        Long userID = Long.valueOf((int) body.get("userID"));
        if (jwtUtil.validateToken(token, userID)) {
            System.out.println("DEL");
            accountRepository.deleteById(userID);
            System.out.println("del");
            return "delete";
        }
        System.out.println("UNAUTH");
        return "unauth";
    }
}
