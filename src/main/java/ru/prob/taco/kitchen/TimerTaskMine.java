package ru.prob.taco.kitchen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import ru.prob.taco.model.TacoOrder;

import java.util.TimerTask;

@Component
public class TimerTaskMine extends TimerTask {
    @Autowired
    private JmsTemplate jms;

    @Override
    public void run() {
        System.out.println("Таймер создан/запущен");
        try {
            Thread.sleep(1000 * 10);
            TacoOrder to = (TacoOrder) jms.receiveAndConvert("tacocloud.order.queue");
            System.out.println("---->  " + to);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
