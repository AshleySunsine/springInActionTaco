package ru.prob.taco.kitchen.artemis.activelistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import java.util.Timer;

//@Component
public class JmsOrderReceiverArtemis {
    private JmsTemplate jms;
    private TimerTaskMineArtemis timerTask;
    @Autowired
    private Timer timer;

    public JmsOrderReceiverArtemis(JmsTemplate jms, TimerTaskMineArtemis timerTask, Timer timer) {
        this.jms = jms;
        this.timerTask = timerTask;
        this.timer = timer;
        timer.schedule(timerTask, 1000, 2000);
    }


}
