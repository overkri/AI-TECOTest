package com.example.listener.AMQP;

import com.example.listener.Entity.Message;
import com.example.listener.Repository.MessageRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class Listener {

    private MessageRepository messageRepository;

    @Autowired
    public Listener(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @RabbitListener(queues = "queue1")
    public void processQueue1(String message) {
        Message msg = new Message(message);
        messageRepository.save(msg);
    }
}
