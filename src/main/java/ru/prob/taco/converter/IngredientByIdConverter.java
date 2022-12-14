package ru.prob.taco.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.prob.taco.data.IngredientRepository;
import ru.prob.taco.model.Ingredient;
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
   private IngredientRepository ingredientRepository;

   @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }
}