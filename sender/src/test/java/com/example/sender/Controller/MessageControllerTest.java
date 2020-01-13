package com.example.sender.Controller;

import com.example.listener.Entity.Message;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.*;

class MessageControllerTest {

    public static final String MESSAGE = "Anno";
    public static final String QUEUE_1 = "queue1";

    @Test
    void getMessage() {
        RabbitTemplate template = mock(RabbitTemplate.class);
        Queue queue = mock(Queue.class);
        MessageController controller = new MessageController(queue, template);
        when(queue.getName()).thenReturn(QUEUE_1);
        Message message = new Message(MESSAGE);
        controller.getMessage(message);
        verify(template, times(1)).convertAndSend(QUEUE_1, MESSAGE);
    }
}