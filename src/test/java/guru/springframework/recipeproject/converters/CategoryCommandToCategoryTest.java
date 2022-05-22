package guru.springframework.recipeproject.converters;

import static org.junit.jupiter.api.Assertions.*;

import guru.springframework.recipeproject.commands.CategoryCommand;
import guru.springframework.recipeproject.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/** Created by tky on 2022/05/22 */
// @ExtendWith(MockitoExtension.class)
class CategoryCommandToCategoryTest {
  private CategoryCommandToCategory categoryConverter;

  @BeforeEach
  void setUp() {
    categoryConverter = new CategoryCommandToCategory();
  }

  @Test
  void testNullObject() {
    assertNull(categoryConverter.convert(null));
  }

  @Test
  void testEmptyObject() {
    assertNotNull(categoryConverter.convert(new CategoryCommand()));
  }

  @Test
  void convert() {
    CategoryCommand categoryCommand = new CategoryCommand();
    categoryCommand.setId(1L);
    categoryCommand.setDescription("wowow");
    Category returnedCategory = categoryConverter.convert(categoryCommand);
    assertEquals(returnedCategory.getId(), categoryCommand.getId());
    assertEquals(returnedCategory.getDescription(), categoryCommand.getDescription());
  }
}
