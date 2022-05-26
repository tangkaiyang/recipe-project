package guru.springframework.recipeproject.controllers;

import guru.springframework.recipeproject.commands.IngredientCommand;
import guru.springframework.recipeproject.services.IngredientService;
import guru.springframework.recipeproject.services.RecipeService;
import guru.springframework.recipeproject.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/** Created by tky on 2022/05/26 */
@Controller
public class IngredientController {
  private final IngredientService ingredientService;
  private final RecipeService recipeService;
  private final UnitOfMeasureService unitOfMeasureService;

  public IngredientController(IngredientService ingredientService,
      RecipeService recipeService,
      UnitOfMeasureService unitOfMeasureService) {
    this.ingredientService = ingredientService;
    this.recipeService = recipeService;
    this.unitOfMeasureService = unitOfMeasureService;
  }

  @RequestMapping("/recipe/{recipeId}/ingredients")
  public String getIngredientsByReipeId(@PathVariable String recipeId, Model model) {
    model.addAttribute(
        "ingredients", ingredientService.findIngredientsByRecipeId(Long.valueOf(recipeId)));
    model.addAttribute("recipe", recipeService.findById(Long.valueOf(recipeId)));
    return "recipe/ingredient/list";
  }

  @RequestMapping("/recipe/{recipeId}/ingredient/new")
  public String newIngredient(@PathVariable String recipeId, Model model) {
    IngredientCommand ingredientCommand = new IngredientCommand();
    model.addAttribute("ingredient", ingredientCommand);
    model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
    return "recipe/ingredient/ingredientform";
  }
}
