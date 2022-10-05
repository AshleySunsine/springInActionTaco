package ru.prob.taco.data;

import org.springframework.data.repository.CrudRepository;
import ru.prob.taco.model.UserU;

public interface UserRepository extends CrudRepository<UserU, Long> {
    UserU findByUsername(String username);
}
