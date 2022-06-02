package com.Account;

import com.Account.commands.CreateAccountCommand;
import com.Account.commands.DeleteAccountCommand;
import com.Account.commands.LoginCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AccountApplication.class)
public class KeroMarkTest {

    @Autowired
    ApplicationContext applicationContext;



    public Map<String, Object> createAcc(String userName , int age){
        Map<String, Object> accountDetails = new HashMap<>();
        accountDetails.put("username",userName);
        accountDetails.put("full_name","full_name");
        accountDetails.put("age",age);
        accountDetails.put("password","123");
        accountDetails.put("profilePicture","123");
        accountDetails.put("fieldOfInterest","AI");
        accountDetails.put("isCompany",false);
        return accountDetails;
    }

    public Map<String, Object> Login(String userName , String pass){
        Map<String, Object> accountDetails = new HashMap<>();
        accountDetails.put("username",userName);
        accountDetails.put("password",pass);
        return accountDetails;
    }

    @Test
    public void testSignUp1() throws Exception{
        CreateAccountCommand createAccountCommand = applicationContext.getBean(CreateAccountCommand.class);

        createAccountCommand.accountRepository.deleteAll();
        Map<String, Object>  accountDetails = createAcc("user1" ,1);

        assertEquals("Account Created" , createAccountCommand.execute(accountDetails));
    }
    @Test
    public void testSignUp2() throws Exception{
        CreateAccountCommand createAccountCommand = applicationContext.getBean(CreateAccountCommand.class);

        Map<String, Object>  accountDetails = createAcc("user1",2);
        assertEquals("Username Already Taken" , createAccountCommand.execute(accountDetails));
    }
    @Test
    public void testSignUp3() throws Exception{
        CreateAccountCommand createAccountCommand = applicationContext.getBean(CreateAccountCommand.class);

        Map<String, Object>  accountDetails = createAcc("",0);
        assertEquals("Name Is Empty" , createAccountCommand.execute(accountDetails));
    }

    @Test
    public void testSignUp4() throws Exception{
        CreateAccountCommand createAccountCommand = applicationContext.getBean(CreateAccountCommand.class);

        Map<String, Object>  accountDetails = createAcc("user3" , 0 );
        assertEquals("Age Is Not Valid" , createAccountCommand.execute(accountDetails));
    }

    @Test
    public void testLogIn1() throws Exception{
        LoginCommand loginCommand = applicationContext.getBean(LoginCommand.class);
        CreateAccountCommand createAccountCommand = applicationContext.getBean(CreateAccountCommand.class);

        Map<String, Object>  accountCreated = createAcc("user2" ,1);
        createAccountCommand.execute(accountCreated);

        Map<String, Object>  accountDetails =  Login("user2" ,"1" );
        assertEquals("Incorrect Password" , loginCommand.execute(accountDetails));
    }

    @Test
    public void testLogIn2() throws Exception{
        LoginCommand loginCommand = applicationContext.getBean(LoginCommand.class);

        CreateAccountCommand createAccountCommand = applicationContext.getBean(CreateAccountCommand.class);
        Map<String, Object>  accountCreated = createAcc("user3" ,1);
        createAccountCommand.execute(accountCreated);

        Map<String, Object>  accountDetails =  Login("user3" ,"123" );

        if ( !loginCommand.execute(accountDetails).equals("Account Doesn't Exist") && !loginCommand.execute(accountDetails).equals("Incorrect Password") )
            assertTrue(true);
        else
            assertTrue(false);
    }

    @Test
    public void testLogIn3() throws Exception{
        LoginCommand loginCommand = applicationContext.getBean(LoginCommand.class);
        Map<String, Object>  accountDetails =  Login("xKx" ,"1234" );
        assertEquals("Account Doesn't Exist" , loginCommand.execute(accountDetails));
    }




    @Test
    public void testDeleteAcc1() throws Exception{
        DeleteAccountCommand deleteAccountCommand = applicationContext.getBean(DeleteAccountCommand.class);
        LoginCommand loginCommand = applicationContext.getBean(LoginCommand.class);
        CreateAccountCommand createAccountCommand = applicationContext.getBean(CreateAccountCommand.class);


        Map<String, Object>  accountCreated = createAcc("user10" ,1);
        createAccountCommand.execute(accountCreated);

        Map<String, Object>  accountDetails =  Login("user10" ,"123" );
        String token = loginCommand.execute(accountDetails);

        Long wrongID = Long.valueOf(0);
        Map<String, Object>  AccountToDelete = new HashMap<>();
        AccountToDelete.put("token" , token);
        AccountToDelete.put("userID" , wrongID);
        assertEquals("UnAuthorized" , deleteAccountCommand.execute(AccountToDelete));

    }

    @Test
    public void testDeleteAcc2() throws Exception{


        DeleteAccountCommand deleteAccountCommand = applicationContext.getBean(DeleteAccountCommand.class);
        LoginCommand loginCommand = applicationContext.getBean(LoginCommand.class);

        CreateAccountCommand createAccountCommand = applicationContext.getBean(CreateAccountCommand.class);
        Map<String, Object>  accountCreated = createAcc("userD" ,1);
        createAccountCommand.execute(accountCreated);

        Map<String, Object>  accountDetails =  Login("userD" ,"123" );
        String token = loginCommand.execute(accountDetails);


        Long userId = loginCommand.accountRepository.findByUsername("userD").getId();
        Map<String, Object>  AccountToDelete = new HashMap<>();
        AccountToDelete.put("token" , token);
        AccountToDelete.put("userID" , userId);
        assertEquals("Account Deleted" , deleteAccountCommand.execute(AccountToDelete));

    }


    @Test
    public void testUser() throws Exception{
        CreateAccountCommand createAccountCommand = applicationContext.getBean(CreateAccountCommand.class);
        LoginCommand loginCommand = applicationContext.getBean(LoginCommand.class);
        DeleteAccountCommand deleteAccountCommand = applicationContext.getBean(DeleteAccountCommand.class);

        Map<String, Object>  accountCreated = createAcc("kero",23); // create new acc
        createAccountCommand.execute(accountCreated);

        Map<String, Object>  accountDetails =  Login("kero" ,"123" );// log in with the new acc
        String token = loginCommand.execute(accountDetails);
        Long userId = loginCommand.accountRepository.findByUsername("kero").getId();

        Map<String, Object>  AccountToDelete = new HashMap<>();
        AccountToDelete.put("token" , token);
        AccountToDelete.put("userID" , userId);
        deleteAccountCommand.execute(AccountToDelete); // delete it then log in


        assertEquals("Account Doesn't Exist" , loginCommand.execute(accountDetails));



    }





}
