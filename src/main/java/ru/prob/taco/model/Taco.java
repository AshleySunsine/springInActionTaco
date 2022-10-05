package ru.prob.taco.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createdAt = new Date();
    @SuppressWarnings("checkstyle:WhitespaceAround")
    @NotNull
    @Size(min = 2, message = "Name must be at least 2 characters long")
    private String name;

    @Size(min = 1, message = "You must choose at least 1 ingredient")
    @ManyToMany()
    private List<Ingredient> ingredients;

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

}