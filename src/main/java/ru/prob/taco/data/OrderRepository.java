package ru.prob.taco.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.prob.taco.model.TacoOrder;
import ru.prob.taco.model.UserU;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByDeliveryZip(String deliveryZip);
    List<TacoOrder> findByUserUOrderByPlaceAtDesc(UserU user, Pageable pageable);

    List<TacoOrder> findAll(Pageable page);
}
