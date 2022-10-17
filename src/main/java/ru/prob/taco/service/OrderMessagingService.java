package ru.prob.taco.service;

import ru.prob.taco.model.TacoOrder;

public interface OrderMessagingService {
    public void sendOrder(TacoOrder order);
}
