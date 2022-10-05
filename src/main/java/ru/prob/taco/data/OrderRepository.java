package ru.prob.taco.data;

import org.springframework.data.repository.CrudRepository;
import ru.prob.taco.model.TacoOrder;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByDeliveryZip(String deliveryZip);
}
