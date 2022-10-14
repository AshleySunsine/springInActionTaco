package ru.prob.taco.kitchen.rabbit.activelistener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.prob.taco.model.TacoOrder;

import java.util.Timer;

//@Component
public class RabbitOrderReceiver {
    private RabbitTemplate rabbit;
    private MessageConverter converter;
    @Autowired
    public RabbitOrderReceiver(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
        this.converter = rabbit.getMessageConverter();
    }
    public TacoOrder receiveOrder() {
        Message message = rabbit.receive("taco.order", 3000);

        if (message != null) {
            TacoOrder to = (TacoOrder) converter.fromMessage(message);
            System.out.println("Order receiver --->>>" + to);
            return to;
        } else {
            System.out.println("Order receiver --->>> null");
            return null;
        }
    }
}
