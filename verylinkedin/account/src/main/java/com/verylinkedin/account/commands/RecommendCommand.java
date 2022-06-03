package com.verylinkedin.account.commands;

import com.verylinkedin.account.AccountRepository;
import com.verylinkedin.account.ICommand;
import com.verylinkedin.account.classes.Account;
import com.verylinkedin.account.classes.FieldOfInterest;
import com.verylinkedin.account.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecommendCommand implements ICommand {

    public AccountRepository accountRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    public RecommendCommand(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Object execute(Object object) {

        Map<String, Object> body = (Map<String, Object>) object;
        String token = (String) body.get("token");
        if(token.startsWith("Bearer "))
            token =token.substring(7);

        Long userID = ((Number)Integer.parseInt(jwtUtil.extractUsername(token))).longValue();

        Account accountFound= accountRepository.getById(userID) ;

        if(accountFound ==null)
            return "Account Not Found";

        FieldOfInterest userInterest = accountFound.getFieldOfInterest();

        List<Account> relatedCompanies= accountRepository.findByFieldOfInterestAndIsCompany(userInterest,true);

        if(relatedCompanies.isEmpty())
            return "no match";
        String companiesNames = "" ;
        for(Account account : relatedCompanies)
            companiesNames += account.getUsername()+",";

        return companiesNames.substring(0,companiesNames.length()-1);

    }
}
