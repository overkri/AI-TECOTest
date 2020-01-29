package com.example.sender.Controller;

import com.example.listener.Entity.Message;
import com.example.sender.DTO.Response;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import static java.util.Objects.requireNonNull;

@RestController
@Transactional
public class MessageController {

    RabbitTemplate template;

    private Queue queue;

    Response response = new Response();

    @Autowired
    public MessageController(@NonNull Queue queue, @NonNull RabbitTemplate template) {
        this.queue = requireNonNull(queue);
        this.template = requireNonNull(template);
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @ResponseBody
    public Response getMessage(@RequestBody Message message) {
        this.template.convertAndSend(queue.getName(), message.getMessage());
        response.setStatusSuccess(true);
        return response;
    }
}
