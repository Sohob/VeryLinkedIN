package com.verylinkedin.core;


import com.verylinkedin.core.amqp.RabbitMQMessageProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@Component
@RequestMapping("api/v1/accountSubApp")
@Service
@AllArgsConstructor

public class AccountController {

    private final RabbitMQMessageProducer rabbitMQMessageProducer;


    public final static String QUEUE_NAME = "Kero.accountApp";
    public final static String TOPIC_NAME = "Kero.exchange";
    public final static String ROUTING_KEY = "Kero.accountKey";



    @PostMapping("/account")
    public String createAccount(@RequestBody Map<String, Object> account) {

        Map<String, Object> tmp = new HashMap<>();
        tmp.put("Command" ,"createAccountCommand");
        tmp.put("data" ,account);
        String reply = (String)rabbitMQMessageProducer.publishAndReceive(
                tmp,
                TOPIC_NAME,
                ROUTING_KEY
        );


        return reply;
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, Object> account) {

        Map<String, Object> tmp = new HashMap<>();
        tmp.put("Command" ,"loginCommand");
        tmp.put("data" ,account);

        String reply = (String) rabbitMQMessageProducer.publishAndReceive(
                tmp,
                TOPIC_NAME,
                ROUTING_KEY
        );

        if(reply.equals("Account Doesn't Exist") || reply.equals("Incorrect Password") )
            return reply;

        // use the reply as the token
        return "AccountLogged with token:   " + reply; //
    }

    @DeleteMapping("/account/{id}")
    public String deleteAccount(@PathVariable Long id, @RequestHeader("Authorization") String token){

        Map<String, Object> body = new HashMap<>();
        body.put("userID" ,id);
        body.put("token" ,token);

        Map<String, Object> tmp = new HashMap<>();
        tmp.put("Command" ,"deleteAccountCommand");
        tmp.put("data" ,body);

        String reply = (String) rabbitMQMessageProducer.publishAndReceive(
                tmp,
                TOPIC_NAME,
                ROUTING_KEY
        );
        return reply;

    }
    @GetMapping("/recommend-companies")
    public String recommendCompanies(@RequestHeader("Authorization") String token) {

        Map<String, Object> tmp = new HashMap<>();
        tmp.put("Command" ,"recommendCommand");

        Map<String, Object> body = new HashMap<>();
        body.put("token" ,token);

        tmp.put("data" ,body);

        String reply = (String)rabbitMQMessageProducer.publishAndReceive(
                tmp,
                TOPIC_NAME,
                ROUTING_KEY
        );

        return  reply.toString();

    }

//    @GetMapping("/recommend-companies")
//    public String recommendCompanies(@RequestHeader("Authorization") String token) {
//        //parse jwt to get userid
//        String userid = token.getToken().parse();
//        //get the user's field of interest from postgresDB
//        Fields userInterest = Db.get(userid)
//        //get companies with same field of interest from postgresDB
//        ArrayList<User> companies = Db.get(isCompany == true, fieldOfInterest == userInterest);
//        String ret = "";
//        for (User user : companies) {
//            ret += user.name();
//        }
//        return new ResponseEntity<String>(ret,
//                HttpStatus.OK);
//        ;
//    }

    }
