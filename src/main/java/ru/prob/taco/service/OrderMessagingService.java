package ru.prob.taco.service;

import ru.prob.taco.model.TacoOrder;

public interface OrderMessagingService {
    void sendOrder(TacoOrder order);
}
