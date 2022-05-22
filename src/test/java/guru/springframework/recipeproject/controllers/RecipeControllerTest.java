package guru.springframework.recipeproject.controllers;

import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.services.RecipeService;
import guru.springframework.recipeproject.services.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/** Created by tky on 2022/05/22 */
@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

  @Mock RecipeService recipeService;
  @InjectMocks RecipeController controller;

  Recipe returnedRecipe;

  @BeforeEach
  void setUp() {
    returnedRecipe = new Recipe();
    returnedRecipe.setId(1L);
  }

  @Test
  void showById() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    when(recipeService.findById(anyLong())).thenReturn(returnedRecipe);
    mockMvc
        .perform(get("/recipe/show/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/show"))
        .andExpect(model().attribute("recipe", returnedRecipe))
        .andExpect(model().attributeExists("recipe"));
  }
}
