package ru.prob.taco.data;

import org.springframework.data.repository.CrudRepository;
import ru.prob.taco.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
