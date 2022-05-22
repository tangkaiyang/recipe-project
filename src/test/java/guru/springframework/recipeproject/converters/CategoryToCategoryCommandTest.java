package guru.springframework.recipeproject.converters;

import static org.junit.jupiter.api.Assertions.*;

import guru.springframework.recipeproject.commands.CategoryCommand;
import guru.springframework.recipeproject.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Created by tky on 2022/05/22 */
class CategoryToCategoryCommandTest {
  private static final Long ID_VALUE = new Long(1L);
  private static final String DESCRIPTION = "description";
  CategoryToCategoryCommand converter;

  @BeforeEach
  void setUp() {
    converter = new CategoryToCategoryCommand();
  }

  @Test
  void testNullObject() {
    assertNull(converter.convert(null));
  }

  @Test
  void testEmptyObject() {
    assertNotNull(converter.convert(new Category()));
  }

  @Test
  void convert() {
    //given
    Category category = new Category();
    category.setId(ID_VALUE);
    category.setDescription(DESCRIPTION);
    //when
    CategoryCommand categoryCommand = converter.convert(category);
    //then
    assertEquals(ID_VALUE, categoryCommand.getId());
    assertEquals(DESCRIPTION, categoryCommand.getDescription());
  }
}
