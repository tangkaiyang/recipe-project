package guru.springframework.recipeproject.services;

import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Created by tky on 2022/05/20
 */
@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {
    @InjectMocks
    RecipeServiceImpl recipeService;
    // Mock依赖
    @Mock
    RecipeRepository recipeRepository;
    Set<Recipe> recipeData = new HashSet<>();
    Recipe recipe;

    @BeforeEach
    void setUp() {
        recipe = new Recipe();
        recipe.setId(1L);
        recipeData.add(recipe);
    }

    @Test
    void getRecipes() {

        // Mock return

        when(recipeService.getRecipes()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);

        // verify called times
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void getById() {
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        Recipe returnRecipe = recipeService.getById(1L);
        assertNotNull(returnRecipe,"No Recipe Returned!");
        verify(recipeRepository).findById(anyLong());
        verify(recipeRepository, never()).findAll();

    }
}
