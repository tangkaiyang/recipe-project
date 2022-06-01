package guru.springframework.recipeproject.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import guru.springframework.recipeproject.commands.RecipeCommand;
import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.repositories.RecipeRepository;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;

/** Created by tky on 2022/06/01 */
@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {
  @Mock RecipeRepository recipeRepository;
  @InjectMocks ImageServiceImpl imageService;

  @BeforeEach
  void setUp() {}

  @Test
  void saveImageFile() throws IOException {
    // given a multipart file
    Long id = 1L;
    MockMultipartFile mockMultipartFile =
        new MockMultipartFile(
            "imagefile", "testing.txt", "text/plain", "Spring Framework Guru".getBytes());
    // given a recipe set id = 1
    Recipe recipe = new Recipe();
    recipe.setId(id);
    Optional<Recipe> recipeOptional = Optional.of(recipe);
    // given when recipe repository find by id return optional recipe
    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
    // new argument captor
    ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);
    // image service save image
    imageService.saveImageFile(id, mockMultipartFile);
    // then assert equal
    verify(recipeRepository).save(argumentCaptor.capture());
    Recipe savedRecipe = argumentCaptor.getValue();
    assertEquals(savedRecipe.getImage().length, mockMultipartFile.getBytes().length);
  }
}
