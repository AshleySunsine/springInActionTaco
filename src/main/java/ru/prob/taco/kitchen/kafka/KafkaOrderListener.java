package ru.prob.taco.kitchen.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.prob.taco.model.TacoOrder;

@Component
public class KafkaOrderListener {
    @KafkaListener(topics = "tacocloud.orders.topic")
    public void handle(TacoOrder order, ConsumerRecord<String, TacoOrder> record) {
        System.out.println("Kafka listener --->>> " + order + "  " + record.timestamp());
    }
}
