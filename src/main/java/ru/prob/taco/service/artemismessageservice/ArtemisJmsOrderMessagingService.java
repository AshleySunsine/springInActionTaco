package ru.prob.taco.service.artemismessageservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import ru.prob.taco.model.TacoOrder;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class ArtemisJmsOrderMessagingService implements ArtemisOrderMessagingService {
private JmsTemplate jms;

    @Autowired
    public ArtemisJmsOrderMessagingService(JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        System.out.println("send ordereeeeeee  " + order.toString());
        jms.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(order);
            }
        });
    }
}
