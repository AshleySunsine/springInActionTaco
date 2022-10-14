package ru.prob.taco.kitchen.rabbit.passivelistener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.prob.taco.model.TacoOrder;

//@Component
public class RabbitOrderListenerPassive {
    @RabbitListener(queues = "taco.order")
    public void receiveOrder(TacoOrder order) {
        System.out.println("RabbitMq Passive listen --->" + order);
    }
}
