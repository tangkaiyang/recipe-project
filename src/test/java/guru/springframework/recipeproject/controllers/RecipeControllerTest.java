package guru.springframework.recipeproject.controllers;

import guru.springframework.recipeproject.commands.RecipeCommand;
import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/** Created by tky on 2022/05/22 */
@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

  @Mock RecipeService recipeService;
  @InjectMocks RecipeController controller;

  Recipe returnedRecipe;
  RecipeCommand recipeCommand;
  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    returnedRecipe = new Recipe();
    returnedRecipe.setId(1L);
    recipeCommand = new RecipeCommand();
    recipeCommand.setId(1L);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  void testShowById() throws Exception {
    when(recipeService.findById(anyLong())).thenReturn(returnedRecipe);
    mockMvc
        .perform(get("/recipe/1/show"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/show"))
        .andExpect(model().attribute("recipe", returnedRecipe))
        .andExpect(model().attributeExists("recipe"));
  }

  @Test
  void testNewRecipe() throws Exception {
    mockMvc
        .perform(get("/recipe/new"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("recipe"))
        .andExpect(view().name("recipe/recipeform"));
  }

  @Test
  void testUpdateRecipe() throws Exception {

    when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
    mockMvc
        .perform(get("/recipe/1/update"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/recipeform"))
        .andExpect(model().attribute("recipe", recipeCommand));
  }

  @Test
  void testSaveOrUpdate() throws Exception {
    when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);
    mockMvc
        .perform(
            post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "wow"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/recipe/1/show"));
  }

  @Test
  void testDeleteById() throws Exception {
    mockMvc
        .perform(get("/recipe/1/delete"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/"));
    verify(recipeService, times(1)).deleteById(anyLong());
  }
}
