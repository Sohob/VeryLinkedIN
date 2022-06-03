package com.verylinkedin.core;


import com.verylinkedin.core.amqp.RabbitMQMessageProducer;
//import com.verylinkedin.core.auth.JwtUtil;
import com.verylinkedin.core.auth.repository.CacheRepository;
import com.verylinkedin.core.auth.JWToken;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Slf4j
@RestController
@Component
@RequestMapping("api/v1/accountSubApp")
@Service
@AllArgsConstructor

public class AccountController {
    public final CacheRepository cacheRepository;
//    @Autowired
//    public final JwtUtil jwtUtil;
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
        cacheRepository.put(reply, true);
        return "AccountLogged with token:   " + reply; //
    }
    @PostMapping("/logout")
    public ResponseEntity<Object> logout(@RequestHeader JWToken token) {
        Optional<String> s = cacheRepository.get(token.getToken());
        if (s.isPresent()) {
            cacheRepository.remove(token.getToken());
            return ResponseEntity.ok("Token removed & logged out!" );
        }
        return ResponseEntity.badRequest().body("Token not in Cache, no one to log out!");
    }
    @DeleteMapping("/account/{id}")
    public String deleteAccount(@PathVariable Long id, @RequestHeader("Authorization") JWToken token){

        Map<String, Object> body = new HashMap<>();
        body.put("userID" ,id);
        body.put("token" ,token.getToken());

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
        JWToken tokenSer = new JWToken();
        tokenSer.setToken(token);
        Optional<String> s = cacheRepository.get(tokenSer.getToken());
        //&& jwtUtil.validateToken(token, userid)
        if (s.isPresent() ) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("Command", "recommendCommand");

            Map<String, Object> body = new HashMap<>();
            body.put("token", token);

            tmp.put("data", body);

            String reply = (String) rabbitMQMessageProducer.publishAndReceive(
                    tmp,
                    TOPIC_NAME,
                    ROUTING_KEY
            );

            return reply.toString();

        }
        return "Unauthorized";
    }
    }
