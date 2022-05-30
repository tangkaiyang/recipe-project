package guru.springframework.recipeproject.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import guru.springframework.recipeproject.commands.IngredientCommand;
import guru.springframework.recipeproject.commands.UnitOfMeasureCommand;
import guru.springframework.recipeproject.converters.IngredientToIngredientCommand;
import guru.springframework.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.recipeproject.domain.Ingredient;
import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.domain.UnitOfMeasure;
import guru.springframework.recipeproject.services.IngredientService;
import guru.springframework.recipeproject.services.RecipeService;
import guru.springframework.recipeproject.services.UnitOfMeasureService;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/** Created by tky on 2022/05/29 */
@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {
  @Mock IngredientService ingredientService;
  @Mock RecipeService recipeService;
  @Mock UnitOfMeasureService unitOfMeasureService;
  @Mock UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
  @InjectMocks IngredientController ingredientController;
  MockMvc mockMvc;
  Recipe recipe;
  Ingredient ingredient;
  Set<Ingredient> ingredients = new HashSet<>();
  IngredientCommand ingredientCommand;
  @Mock IngredientToIngredientCommand ingredientToIngredientCommand;
  UnitOfMeasure unitOfMeasure;
  UnitOfMeasureCommand unitOfMeasureCommand;
  Set<UnitOfMeasureCommand> unitOfMeasureCommands = new HashSet<>();

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    ingredients.add(ingredient);

    recipe = new Recipe();
    recipe.setId(1L);
    recipe.setIngredients(ingredients);

    ingredient = new Ingredient();
    ingredient.setId(1L);
    ingredient.setRecipe(recipe);
    ingredient.setDescription("description");
    ingredient.setUom(unitOfMeasure);
    ingredient.setAmount(BigDecimal.valueOf(1.1D));

    ingredientCommand = ingredientToIngredientCommand.convert(ingredient);

    unitOfMeasure = new UnitOfMeasure();
    unitOfMeasure.setId(1L);
    unitOfMeasure.setDescription("description");

    unitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure);
    unitOfMeasureCommands.add(unitOfMeasureCommand);
  }

  @Test
  void tesGetIngredientsByRecipeId() throws Exception {
    when(ingredientService.findIngredientsByRecipeId(anyLong())).thenReturn(ingredients);
    when(recipeService.findById(anyLong())).thenReturn(recipe);
    mockMvc
        .perform(get("/recipe/1/ingredients"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/ingredient/list"))
        .andExpect(model().attribute("ingredients", ingredients))
        .andExpect(model().attribute("recipe", recipe));
  }

  @Test
  void testNewIngredient() throws Exception {
    when(unitOfMeasureService.listAllUoms()).thenReturn(unitOfMeasureCommands);
    mockMvc
        .perform(get("/recipe/1/ingredient/new"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/ingredient/ingredientform"))
        .andExpect(model().attributeExists("ingredient"))
        .andExpect(model().attribute("uomList", unitOfMeasureCommands));
    verify(recipeService).findCommandById(anyLong());
  }

  @Test
  void testSaveIngredient() throws Exception {
    when(ingredientService.saveIngredientCommand(any())).thenReturn(ingredientCommand);
    mockMvc
        .perform(
            post("/recipe/1/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "description")
                .param("amount", "1.1")
                .param("uom.id", "7"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/recipe/1/ingredient/1/show"));

    verify(ingredientService).saveIngredientCommand(any());
  }

  @Test
  void testShowIngredient() {}

  @Test
  void testUpdateShowIngredient() {}

  @Test
  void testDeleteIngredient() {}
}
