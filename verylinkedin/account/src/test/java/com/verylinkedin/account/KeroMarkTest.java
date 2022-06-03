package com.verylinkedin.account;

import com.verylinkedin.account.commands.CreateAccountCommand;
import com.verylinkedin.account.commands.DeleteAccountCommand;
import com.verylinkedin.account.commands.LoginCommand;
import com.verylinkedin.account.commands.RecommendCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AccountApplication.class)
public class KeroMarkTest {

    @Autowired
    ApplicationContext applicationContext;


    public Map<String, Object> createAcc(String userName , int age, String FOI,boolean isCompany){
        Map<String, Object> accountDetails = new HashMap<>();
        accountDetails.put("username",userName);
        accountDetails.put("full_name","full_name");
        accountDetails.put("age",age);
        accountDetails.put("password","123");
        accountDetails.put("profilePicture","123");
        accountDetails.put("fieldOfInterest",FOI);
        accountDetails.put("isCompany",isCompany);
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
        Map<String, Object>  accountDetails = createAcc("testUser1" ,1,"AI",false);
        assertEquals("Account Created" , createAccountCommand.execute(accountDetails));
    }
    @Test
    public void testSignUp2() throws Exception{
        CreateAccountCommand createAccountCommand = applicationContext.getBean(CreateAccountCommand.class);
        createAccountCommand.accountRepository.deleteAll();
        Map<String, Object>  accountDetails = createAcc("testUser1",2,"AI",false);
        createAccountCommand.execute(accountDetails);
        assertEquals("Username Already Taken" , createAccountCommand.execute(accountDetails));
    }
    @Test
    public void testSignUp3() throws Exception{
        CreateAccountCommand createAccountCommand = applicationContext.getBean(CreateAccountCommand.class);

        Map<String, Object>  accountDetails = createAcc("",0,"AI",false);
        assertEquals("Name Is Empty" , createAccountCommand.execute(accountDetails));
    }

    @Test
    public void testSignUp4() throws Exception{
        CreateAccountCommand createAccountCommand = applicationContext.getBean(CreateAccountCommand.class);

        Map<String, Object>  accountDetails = createAcc("testUser3" , 0 ,"AI",false);
        assertEquals("Age Is Not Valid" , createAccountCommand.execute(accountDetails));
    }

    @Test
    public void testLogIn1() throws Exception{
        LoginCommand loginCommand = applicationContext.getBean(LoginCommand.class);
        CreateAccountCommand createAccountCommand = applicationContext.getBean(CreateAccountCommand.class);

        Map<String, Object>  accountCreated = createAcc("testUser2" ,1,"AI",false);
        createAccountCommand.execute(accountCreated);

        Map<String, Object>  accountDetails =  Login("testUser2" ,"1" );
        assertEquals("Incorrect Password" , loginCommand.execute(accountDetails));
    }

    @Test
    public void testLogIn2() throws Exception{
        LoginCommand loginCommand = applicationContext.getBean(LoginCommand.class);

        CreateAccountCommand createAccountCommand = applicationContext.getBean(CreateAccountCommand.class);
        Map<String, Object>  accountCreated = createAcc("testUser4" ,1,"AI",false);
        createAccountCommand.execute(accountCreated);

        Map<String, Object>  accountDetails =  Login("testUser4" ,"123" );

        assertTrue(!loginCommand.execute(accountDetails).equals("Account Doesn't Exist") && !loginCommand.execute(accountDetails).equals("Incorrect Password"));
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


        Map<String, Object>  accountCreated = createAcc("testUser10" ,1,"AI",false);
        createAccountCommand.execute(accountCreated);

        Map<String, Object>  accountDetails =  Login("testUser10" ,"123" );
        String token = loginCommand.execute(accountDetails);

        Long wrongID = 0L;
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
        Map<String, Object>  accountCreated = createAcc("userD" ,1,"AI",false);
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

        Map<String, Object>  accountCreated = createAcc("kero",23,"AI",false); // create new acc
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



    @Test
    public void testRecommend1() throws Exception{
        CreateAccountCommand createAccountCommand = applicationContext.getBean(CreateAccountCommand.class);
        LoginCommand loginCommand = applicationContext.getBean(LoginCommand.class);

        Map<String, Object>  accountCreated = createAcc("RecommendUser1",23,"AI",false); // create new acc
        createAccountCommand.execute(accountCreated);

        Map<String, Object>  accountDetails =  Login("RecommendUser1" ,"123" );// log in with the new acc
        String token = loginCommand.execute(accountDetails);


        Map<String, Object>  comp1Created = createAcc("Company1",23,"AI",true); // create new acc
        createAccountCommand.execute(comp1Created);

        Map<String, Object>  comp3Created = createAcc("Company3",23,"AI",true); // create new acc
        createAccountCommand.execute(comp3Created);


        RecommendCommand recommendCommand = applicationContext.getBean(RecommendCommand.class);

        Map<String, Object>  tokenBody = new HashMap<>();
        tokenBody.put("token" , token);

        String companies = (String) recommendCommand.execute(tokenBody);


        assertEquals("Company1,Company3" , companies);



    }
    @Test
    public void testRecommend2() throws Exception{

        CreateAccountCommand createAccountCommand = applicationContext.getBean(CreateAccountCommand.class);
        LoginCommand loginCommand = applicationContext.getBean(LoginCommand.class);

        Map<String, Object>  accountCreated = createAcc("RecommendUser2",23,"GRAPHIC_DESIGN",false); // create new acc
        createAccountCommand.execute(accountCreated);

        Map<String, Object>  accountDetails =  Login("RecommendUser2" ,"123" );// log in with the new acc
        String token = loginCommand.execute(accountDetails);


        Map<String, Object>  comp1Created = createAcc("Company1",23,"AI",true); // create new acc
        createAccountCommand.execute(comp1Created);


        Map<String, Object>  comp3Created = createAcc("Company3",23,"AI",true); // create new acc
        createAccountCommand.execute(comp3Created);


        RecommendCommand recommendCommand = applicationContext.getBean(RecommendCommand.class);

        Map<String, Object>  tokenBody = new HashMap<>();
        tokenBody.put("token" , token);

        String companies = (String) recommendCommand.execute(tokenBody);


        assertEquals("no match" , companies);

    }




}
