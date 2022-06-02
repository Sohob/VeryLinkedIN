package com.verylinkedin.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.verylinkedin.core.amqp.RabbitMQMessageProducer;
import com.verylinkedin.core.requests.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@Component
@RequestMapping("api/v1/reports")
@Service
@AllArgsConstructor
public class ReportingController {
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    @PostMapping("/add-report")
    public long notification(@RequestBody AddReportRequest request) {
        rabbitMQMessageProducer.publish(
                request,
                "internal.exchange",
                "internal.reports.routing.key"
        );
        return 1;
    }

    @GetMapping("/get-report/{reportId}")
    public Object getReport(@PathVariable Long reportId) {
        Object response = rabbitMQMessageProducer.publishAndReceive(
                new GetReportRequest(reportId),
                "internal.exchange",
                "internal.reports.routing.key"
        );
//        new String(res)
        return new ResponseEntity<String>("Report details: " +  new String((byte[]) response),
                HttpStatus.OK);

    }

    // TODO Replace userId from path variable when authentication is finished
//    @GetMapping("/view/{userId}/{groupId}")
//    public ResponseEntity<String> viewChat(@PathVariable String userId, @PathVariable String groupId) throws JSONException, JsonProcessingException {
//        log.info("viewing messages in group {} as user {}", groupId, userId);
//        Object viewChatResponse = rabbitMQMessageProducer.publishAndReceive(
//                new ViewChatRequest(userId,groupId),
//                "internal.exchange",
//                "internal.groups.routing.key"
//        );
//        return new ResponseEntity<String>("Chat details: " + viewChatResponse.toString(),
//                HttpStatus.OK);
//
//                /*
//        try {
//            ViewChatResponse res = listenableFuture.get();
//            return new ResponseEntity<String>("Chat details: " + res,
//                    HttpStatus.OK);
//        } catch (InterruptedException | ExecutionException e) {
//            return new ResponseEntity<String>("Invalid chat",
//                    HttpStatus.BAD_REQUEST);
//        }*/
//
//        /*
//        AsyncRabbitTemplate.RabbitConverterFuture<ViewChatResponse> rabbitConverterFuture =  rabbitMQMessageProducer.publishAndReceive(
//                new ViewChatRequest(userId,groupId),
//                "internal.exchange",
//                "internal.groups.routing.key"
//        );
//        rabbitConverterFuture.addCallback(new ListenableFutureCallback<>() {
//            @Override
//            public void onFailure(Throwable ex) {
//                // ...
//            }
//
//            @Override
//            public void onSuccess(ViewChatResponse viewChatResponse) {
//                log.info("Response received {}", viewChatResponse);
//
//            }
//        });*/
//    }

}
