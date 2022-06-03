package com.Account.commands;

import com.Account.AccountRepository;
import com.Account.ICommand;
import com.Account.classes.Account;
import com.Account.security.JwtUtil;
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
        Map<String, Object> body = (Map<String, Object>) object;
        String token = (String) body.get("token");

        if(token.startsWith("Bearer "))
            token =token.substring(7);

        Long userID = ((Number)body.get("userID")).longValue();
       Account accountFound= accountRepository.getById(userID) ;

       if(accountFound ==null)
           return "Not Found";

        System.out.println("token and id " + token +""+userID);
        if (jwtUtil.validateToken(token, userID)) {
            accountRepository.deleteById(userID);
            System.out.println("account with id : " + userID + " has been deleted");
            return "Account Deleted";
        }
        System.out.println("UnAuthorized");
        return "UnAuthorized";
    }
}
