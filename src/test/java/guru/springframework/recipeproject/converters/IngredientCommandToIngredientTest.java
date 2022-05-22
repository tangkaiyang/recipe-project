package guru.springframework.recipeproject.converters;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import guru.springframework.recipeproject.commands.IngredientCommand;
import guru.springframework.recipeproject.commands.UnitOfMeasureCommand;
import guru.springframework.recipeproject.domain.Ingredient;
import guru.springframework.recipeproject.domain.Recipe;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Created by tky on 2022/05/22 */
class IngredientCommandToIngredientTest {
  private static final Recipe RECIPE = new Recipe();
  private static final Long ID_VALUE = 1L;
  private static final String DESCRIPTION = "description";
  private static final BigDecimal AMOUNT = new BigDecimal(1);
  private static final Long UOM_ID = 2L;
  IngredientCommandToIngredient converter;

  @BeforeEach
  void setUp() {
    converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
  }

  @Test
  void testNullObject() {
    assertNull(converter.convert(null));
  }

  @Test
  void testEmptyObject() {
    assertNotNull(converter.convert(new IngredientCommand()));
  }

  @Test
  void convert() {
    // given
    IngredientCommand ingredientCommand = new IngredientCommand();
    ingredientCommand.setId(ID_VALUE);
    ingredientCommand.setAmount(AMOUNT);
    ingredientCommand.setDescription(DESCRIPTION);
    UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
    unitOfMeasureCommand.setId(UOM_ID);
    unitOfMeasureCommand.setDescription(DESCRIPTION);
    ingredientCommand.setUom(unitOfMeasureCommand);
    // when
    Ingredient ingredient = converter.convert(ingredientCommand);
    // then
    assertNotNull(ingredient);
    assertNotNull(ingredient.getUom());
    assertEquals(AMOUNT, ingredient.getAmount());
    assertEquals(DESCRIPTION, ingredient.getDescription());
    assertEquals(ID_VALUE, ingredient.getId());
    assertEquals(UOM_ID, ingredient.getUom().getId());
    assertEquals(DESCRIPTION, ingredient.getUom().getDescription());
  }

  @org.junit.Test
  public void convertWithNullUOM() throws Exception {
    // given
    IngredientCommand command = new IngredientCommand();
    command.setId(ID_VALUE);
    command.setAmount(AMOUNT);
    command.setDescription(DESCRIPTION);
    UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();

    // when
    Ingredient ingredient = converter.convert(command);

    // then
    assertNotNull(ingredient);
    assertNull(ingredient.getUom());
    assertEquals(ID_VALUE, ingredient.getId());
    assertEquals(AMOUNT, ingredient.getAmount());
    assertEquals(DESCRIPTION, ingredient.getDescription());
  }
}
