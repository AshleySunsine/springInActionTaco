package ru.prob.taco.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.prob.taco.model.Taco;

import java.util.Collection;

public interface TacoRepository extends CrudRepository<Taco, Long> {
    Page<Taco> findAll(Pageable page);
}
