//package com.verylinkedin.core;
//
//import com.verylinkedin.core.amqp.RabbitMQMessageProducer;
//import com.verylinkedin.core.auth.repository.CacheRepository;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//
//@Slf4j
//@RestController
//@Component
//@RequestMapping("api/v1/auth")
//@Service
//@AllArgsConstructor
//public class UsersController {
//    private final CacheRepository cacheRepository;
//    private final RabbitMQMessageProducer rabbitMQMessageProducer;
//
//
//    @PostMapping("/add-token")
//    public Object Login(@RequestBody LoginRequest loginRequest) {
//        cacheRepository.put(rabbitMQMessageProducer.publishAndReceive(
//                loginRequest,
//                "internal.exchange",
//                "internal.cache.routing.key"
//        ), true);
//        return null;
//    }
//
//    @PostMapping("/remove-token")
//    public Object removeToken(@RequestBody LogoutRequest logoutRequest) {
//        cacheRepository.remove(rabbitMQMessageProducer.publishAndReceive(
//                logoutRequest,
//                "internal.exchange",
//                "internal.cache.routing.key"
//        ));
//        return null;
//    }
//
//}
//
//
