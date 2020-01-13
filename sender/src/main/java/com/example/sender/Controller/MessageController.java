package com.example.sender.Controller;

import com.example.listener.Entity.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Controller
@Transactional
public class MessageController {

    RabbitTemplate template;

    private Queue queue;

    @Autowired
    public MessageController(@NonNull Queue queue, @NonNull RabbitTemplate template) {
        this.queue = requireNonNull(queue);
        this.template = requireNonNull(template);
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @ResponseBody
    public void getMessage(@RequestBody Message message) {
        this.template.convertAndSend(queue.getName(), message.getMessage());
    }

    @RequestMapping(value = "/index")
    public ModelAndView root() {
        Map<String, Object> model = new HashMap<>();
        return new ModelAndView("index", model);
    }

}
