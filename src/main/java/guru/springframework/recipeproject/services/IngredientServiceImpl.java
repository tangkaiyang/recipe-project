package guru.springframework.recipeproject.services;

import guru.springframework.recipeproject.commands.IngredientCommand;
import guru.springframework.recipeproject.converters.IngredientCommandToIngredient;
import guru.springframework.recipeproject.converters.IngredientToIngredientCommand;
import guru.springframework.recipeproject.domain.Ingredient;
import guru.springframework.recipeproject.repositories.IngredientRepository;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** Created by tky on 2022/05/26 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
  private final RecipeService recipeService;
  private final IngredientRepository ingredientRepository;
  private final IngredientCommandToIngredient ingredientCommandToIngredient;
  private final IngredientToIngredientCommand ingredientToIngredientCommand;

  public IngredientServiceImpl(RecipeService recipeService,
      IngredientRepository ingredientRepository,
      IngredientCommandToIngredient ingredientCommandToIngredient,
      IngredientToIngredientCommand ingredientToIngredientCommand) {
    this.recipeService = recipeService;
    this.ingredientRepository = ingredientRepository;
    this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    this.ingredientToIngredientCommand = ingredientToIngredientCommand;
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

  @Override
  public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
   Ingredient savedIngredient = ingredientRepository.save(ingredientCommandToIngredient.convert(ingredientCommand));
   log.debug(savedIngredient.toString());
   return ingredientToIngredientCommand.convert(savedIngredient);
  }

  @Override
  public IngredientCommand getIngredientCommandById(Long ingredientId) {
    Optional<Ingredient> ingredientOptional = ingredientRepository.findById(ingredientId);
    return ingredientOptional.map(ingredientToIngredientCommand::convert).orElse(null);
  }
}
