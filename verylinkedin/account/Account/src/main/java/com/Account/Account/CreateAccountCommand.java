package com.Account.Account;

import com.Account.ICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CreateAccountCommand implements ICommand {

    private AccountRepository accountRepository;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CreateAccountCommand(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String execute(Object object) {
        try {
            Map<String, Object> accountDetails = (Map<String, Object>) object;
            System.out.println("Account Details " + accountDetails);

            String username = (String) accountDetails.get("username");
            String fullName = (String) accountDetails.get("full_name");
            int age = (int) accountDetails.get("age");
            String password = (String) accountDetails.get("password");
            String profilePicture = (String) accountDetails.get("profilePicture");
            FieldOfInterest fieldOfInterest = FieldOfInterest.valueOf((String) accountDetails.get("fieldOfInterest"));
            boolean isCompany = (boolean) accountDetails.get("isCompany");
            Account account = new Account(username, fullName, age, password, profilePicture, fieldOfInterest, isCompany);
            System.out.println(account);
            // make sure username is new (no account has this username)
            Account accountFound = accountRepository.findByUsername(account.getUsername());
            if (accountFound != null) {
                return "Username Already Taken";
            }

            String hashedPassword = bCryptPasswordEncoder.encode(account.getPassword());
            account.setPassword(hashedPassword);
            System.out.println(hashedPassword);
            accountRepository.save(account);
            return "done";
        } catch (Exception ex) {
            System.out.println(ex);
            return "Internal Server Error";
        }
    }
}
