package guru.springframework.recipeproject.services;

import guru.springframework.recipeproject.commands.RecipeCommand;
import guru.springframework.recipeproject.converters.RecipeCommandToRecipe;
import guru.springframework.recipeproject.converters.RecipeToRecipeCommand;
import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.repositories.RecipeRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.transaction.annotation.Transactional;

/** Created by tky on 2022/05/18 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

  private final RecipeRepository recipeRepository;
  private final RecipeCommandToRecipe recipeConverter;
  private final RecipeToRecipeCommand recipeCommandConverter;

  public RecipeServiceImpl(
      RecipeRepository recipeRepository,
      RecipeCommandToRecipe recipeConverter,
      RecipeToRecipeCommand recipeCommandConverter) {
    this.recipeRepository = recipeRepository;
    this.recipeConverter = recipeConverter;
    this.recipeCommandConverter = recipeCommandConverter;
  }

  @Override
  public Set<Recipe> getRecipes() {
    log.debug("I'm in the Service!!");
    Set<Recipe> recipes = new HashSet<>();
    recipeRepository.findAll().forEach(recipes::add);
    return recipes;
  }

  @Override
  public Recipe findById(Long id) {
    Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
    if (!optionalRecipe.isPresent()) {
      throw new RuntimeException("Recipe Not Found!");
    }
    return optionalRecipe.get();
  }

  @Override
  public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
    Recipe detachedRecipe = recipeConverter.convert(recipeCommand);
    Recipe savedRecipe = recipeRepository.save(detachedRecipe);
    log.debug("Saved RecipeId:" + savedRecipe.getId());
    return recipeCommandConverter.convert(savedRecipe);
  }

  @Override
  @Transactional
  public void deleteById(Long idToDelete) {
    recipeRepository.deleteById(idToDelete);
  }

  @Override
  public RecipeCommand findCommandById(Long id) {
    Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
    return optionalRecipe.map(recipeCommandConverter::convert).orElse(null);
  }
}
