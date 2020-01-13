package com.example.listener.AMQP;

import com.example.listener.Entity.Message;
import com.example.listener.Repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ListenerTest {

    private static final String THIRD_PARAMETER = "Hello!!";
    private static final String SECOND_PARAMETER = "Hello!";
    private static final String FIRST_PARAMETER = "Hello!!!";
    private static final int WANTED_NUMBER_OF_INVOCATIONS = 3;

    @Test
    void processQueue1() {
        MessageRepository messageRepository = mock(MessageRepository.class);
        Listener listener = new Listener(messageRepository);
        listener.processQueue1("Hello!!!");
        ArgumentCaptor<Message> argumentCaptor = ArgumentCaptor.forClass(Message.class);
        verify(messageRepository).save(argumentCaptor.capture());
        Message msg = argumentCaptor.getValue();
        assertEquals(FIRST_PARAMETER, msg.getMessage());
        listener.processQueue1(SECOND_PARAMETER);
        listener.processQueue1(THIRD_PARAMETER);
        verify(messageRepository, times(WANTED_NUMBER_OF_INVOCATIONS)).save(any());
    }
}