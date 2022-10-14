package ru.prob.taco.service.artemismessageservice;

import ru.prob.taco.model.TacoOrder;
public interface ArtemisOrderMessagingService {
    void sendOrder(TacoOrder order);
}
