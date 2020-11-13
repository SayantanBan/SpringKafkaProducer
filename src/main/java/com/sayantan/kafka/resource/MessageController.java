package com.sayantan.kafka.resource;

import com.sayantan.kafka.model.Greeting;
import com.sayantan.kafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private KafkaProducerService producerService;

    @GetMapping("/send/message")
    public void onReceivedMessage() {
        producerService.sendMessage("Hello");
    }

    @GetMapping("/send/partitioned")
    public void onReceivedPartitionedMessage() {
        for (int i = 0; i < 5; i++) {
            producerService.sendMessageToPartition("Hello To Partitioned Topic!", i);
        }
    }

    @GetMapping("/send/filtered")
    public void onFilterMessage() {
        producerService.sendMessageToFiltered("Hello Baeldung!");
        producerService.sendMessageToFiltered("Hello World!");
    }

    @GetMapping("/send/greeting")
    public void onGreetingMessage() {
        producerService.sendGreetingMessage(new Greeting("Greetings", "World!"));
    }
}