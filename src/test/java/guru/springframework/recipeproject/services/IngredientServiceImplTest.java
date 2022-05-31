package guru.springframework.recipeproject.services;

import guru.springframework.recipeproject.commands.IngredientCommand;
import guru.springframework.recipeproject.converters.IngredientCommandToIngredient;
import guru.springframework.recipeproject.converters.IngredientToIngredientCommand;
import guru.springframework.recipeproject.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.recipeproject.domain.Ingredient;
import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.domain.UnitOfMeasure;
import guru.springframework.recipeproject.repositories.IngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.ignoreStubs;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {
  @Mock RecipeService recipeService;
  @Mock IngredientRepository ingredientRepository;
  @InjectMocks IngredientServiceImpl ingredientService;
  IngredientCommandToIngredient ingredientCommandToIngredient;
  IngredientToIngredientCommand ingredientToIngredientCommand;
  UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;
  UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
  Recipe recipe;
  Ingredient ingredient;
  UnitOfMeasure unitOfMeasure;
  Set<Ingredient> ingredientSet = new HashSet<>();

  @BeforeEach
  void setUp() {
    unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
    ingredientCommandToIngredient =
        new IngredientCommandToIngredient(unitOfMeasureCommandToUnitOfMeasure);
    unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    ingredientToIngredientCommand =
        new IngredientToIngredientCommand(unitOfMeasureToUnitOfMeasureCommand);

    unitOfMeasure = new UnitOfMeasure();
    unitOfMeasure.setDescription("description");
    unitOfMeasure.setId(1L);

    recipe = new Recipe();
    recipe.setId(1L);
    recipe.setIngredients(ingredientSet);

    ingredient = new Ingredient();
    ingredient.setRecipe(recipe);
    ingredient.setAmount(BigDecimal.valueOf(1.1));
    ingredient.setUom(unitOfMeasure);
    ingredient.setDescription("description");
    ingredient.setId(1L);

    ingredientSet.add(ingredient);
  }

  @Test
  void testFindIngredientsByRecipeId() {
    when(recipeService.findById(anyLong())).thenReturn(recipe);
    Set<Ingredient> returnIngredients = ingredientService.findIngredientsByRecipeId(1L);
    assertEquals(returnIngredients, recipe.getIngredients());
    verify(recipeService).findById(anyLong());
  }
  // todo test when recipe id not exist
  @Test
  void testGetRecipeId() {
    when(ingredientRepository.findById(anyLong())).thenReturn(Optional.of(ingredient));
    assertNotNull(ingredientService.getRecipeId(1L));
    verify(ingredientRepository).findById(anyLong());
  }

  @Test
  void testSaveIngredientCommand() {
  }

  @Test
  void testGetIngredientCommandById() {
  }

  @Test
  void testDeleteIngredientCommandById() {}
}
