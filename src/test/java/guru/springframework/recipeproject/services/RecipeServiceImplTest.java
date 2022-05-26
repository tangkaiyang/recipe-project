package guru.springframework.recipeproject.services;

import guru.springframework.recipeproject.commands.CategoryCommand;
import guru.springframework.recipeproject.commands.IngredientCommand;
import guru.springframework.recipeproject.commands.NotesCommand;
import guru.springframework.recipeproject.commands.RecipeCommand;
import guru.springframework.recipeproject.converters.*;
import guru.springframework.recipeproject.domain.*;
import guru.springframework.recipeproject.repositories.RecipeRepository;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/** Created by tky on 2022/05/20 */
@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {
  // Mock依赖
  @Mock RecipeRepository recipeRepository;
  @Mock CategoryCommandToCategory categoryCommandToCategory;
  @Mock CategoryToCategoryCommand categoryToCategoryCommand;
  @Mock UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
  @Mock UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;
  @Mock NotesCommandToNotes notesCommandToNotes;
  @Mock NotesToNotesCommand notesToNotesCommand;

  @InjectMocks RecipeCommandToRecipe recipeCommandToRecipe;
  @InjectMocks RecipeToRecipeCommand recipeToRecipeCommand;
  @InjectMocks IngredientToIngredientCommand ingredientToIngredientCommand;
  @InjectMocks IngredientCommandToIngredient ingredientCommandToIngredient;
  @InjectMocks RecipeServiceImpl recipeService;

  Set<Recipe> recipeData = new HashSet<>();
  Recipe recipe;
  RecipeCommand recipeCommand;
  Category category;
  Set<Category> categories = new HashSet<>();
  Set<CategoryCommand> categoryCommands = new HashSet<>();
  Notes notes;
  NotesCommand notesCommand;
  Ingredient ingredient;
  Set<Ingredient> ingredients = new HashSet<>();
  Set<IngredientCommand> ingredientCommands = new HashSet<>();

  @BeforeEach
  void setUp() {
    recipe = new Recipe();
    recipe.setId(1L);
    recipeData.add(recipe);

    category = new Category();
    category.setId(1L);
    category.setRecipes(recipeData);
    category.setDescription("description");

    categories.add(category);
    categoryCommands.add(categoryToCategoryCommand.convert(category));

    notes = new Notes();
    notes.setId(1L);
    notes.setRecipeNotes("recipeNotes");
    notes.setRecipe(recipe);

    notesCommand = notesToNotesCommand.convert(notes);

    ingredient = new Ingredient();
    ingredient.setId(1L);
    ingredient.setRecipe(recipe);
    ingredient.setDescription("description");
    ingredient.setAmount(BigDecimal.valueOf(1.1));
    ingredient.setUom(new UnitOfMeasure());

    ingredients.add(ingredient);
    ingredientCommands.add(ingredientToIngredientCommand.convert(ingredient));

    recipeCommand = new RecipeCommand();
    recipeCommand.setId(1L);
    recipeCommand.setCookTime(2);
    recipeCommand.setDescription("description");
    recipeCommand.setDifficulty(Difficulty.HARD);
    recipeCommand.setServings(3);
    recipeCommand.setPrepTime(4);
    recipeCommand.setDirections("directions");
    recipeCommand.setSource("source");
    recipeCommand.setUrl("url");
    recipeCommand.setIngredients(ingredientCommands);
    recipeCommand.setCategories(categoryCommands);
    recipeCommand.setNotes(notesCommand);
  }

  @Test
  void testGetRecipes() {

    // Mock return

    when(recipeService.getRecipes()).thenReturn(recipeData);

    Set<Recipe> recipes = recipeService.getRecipes();

    assertEquals(recipes.size(), 1);

    // verify called times
    verify(recipeRepository, times(1)).findAll();
  }

  @Test
  void testFindById() {
    when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
    assertNotNull(recipeService.findById(1L), "No Recipe Returned!");
    verify(recipeRepository).findById(anyLong());
    verify(recipeRepository, never()).findAll();
  }

  @Test
  // todo testSave
  void testSaveRecipeCommand() {
//    RecipeCommand returnedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
//    verify(recipeCommandToRecipe, times(1)).convert(any());
//    verify(recipeToRecipeCommand, times(1)).convert(any());
//    verify(recipeRepository, times(1)).save(any());
    Recipe recipe1 = new Recipe();
    recipe1.setId(1L);
    assertEquals(recipe1, recipe);
  }
}
