package guru.springframework.recipeproject.services;

import guru.springframework.recipeproject.domain.Recipe;

import java.util.Set;

/**
 * Created by tky on 2022/05/18
 */
public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe getById(Long id);
}
