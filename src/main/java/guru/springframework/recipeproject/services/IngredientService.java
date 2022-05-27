package guru.springframework.recipeproject.services;

import guru.springframework.recipeproject.commands.IngredientCommand;
import guru.springframework.recipeproject.domain.Ingredient;
import guru.springframework.recipeproject.domain.Recipe;
import java.util.Set;

/**
 * Created by tky on 2022/05/26
 */
public interface IngredientService {
    Set<Ingredient> findIngredientsByRecipeId(Long recipeId);
    Long getRecipeId(Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
}
