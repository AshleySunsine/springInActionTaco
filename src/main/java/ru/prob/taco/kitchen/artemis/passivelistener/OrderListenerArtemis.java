package ru.prob.taco.kitchen.artemis.passivelistener;

import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ru.prob.taco.model.TacoOrder;

@Profile("jms-listener")
//@Component
public class OrderListenerArtemis {
    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(TacoOrder order) {
        System.out.println("Passive listener----> " + order);
    }
}
