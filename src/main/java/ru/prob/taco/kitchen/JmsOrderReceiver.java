package ru.prob.taco.kitchen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import ru.prob.taco.model.TacoOrder;

import java.util.Timer;
import java.util.TimerTask;


@Component
public class JmsOrderReceiver {
    private JmsTemplate jms;
    private TimerTaskMine timerTask;
    @Autowired
    private Timer timer;

    public JmsOrderReceiver(JmsTemplate jms, TimerTaskMine timerTask, Timer timer) {
        this.jms = jms;
        this.timerTask = timerTask;
        this.timer = timer;
        timer.schedule(timerTask, 1000, 2000);
    }


}
