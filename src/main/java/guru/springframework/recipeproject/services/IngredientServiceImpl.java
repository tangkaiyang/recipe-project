package guru.springframework.recipeproject.services;

import guru.springframework.recipeproject.domain.Ingredient;
import guru.springframework.recipeproject.repositories.IngredientRepository;
import java.util.Optional;
import java.util.Set;
import javax.swing.text.html.Option;
import org.springframework.stereotype.Service;

/** Created by tky on 2022/05/26 */
@Service
public class IngredientServiceImpl implements IngredientService {
  private final RecipeService recipeService;
  private final IngredientRepository ingredientRepository;

  public IngredientServiceImpl(RecipeService recipeService,
      IngredientRepository ingredientRepository) {
    this.recipeService = recipeService;
    this.ingredientRepository = ingredientRepository;
  }

  @Override
  public Set<Ingredient> findIngredientsByRecipeId(Long recipeId) {
    return recipeService.findById(recipeId).getIngredients();
  }

  @Override
  public Long getRecipeId(Long ingredientId) {
    Optional<Ingredient> ingredientOptional = ingredientRepository.findById(ingredientId);
    return ingredientOptional.map(ingredient -> ingredient.getRecipe().getId()).orElse(null);
  }
}
