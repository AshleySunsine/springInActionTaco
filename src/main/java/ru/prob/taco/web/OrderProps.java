package ru.prob.taco.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Кол-во готовых заказов на странице
 */
@Component
@ConfigurationProperties(prefix = "taco.orders")
@Data
public class OrderProps {
    private int pageSize = 20;
}
