package guru.springframework.recipeproject.controllers;

import guru.springframework.recipeproject.commands.IngredientCommand;
import guru.springframework.recipeproject.commands.RecipeCommand;
import guru.springframework.recipeproject.commands.UnitOfMeasureCommand;
import guru.springframework.recipeproject.services.IngredientService;
import guru.springframework.recipeproject.services.RecipeService;
import guru.springframework.recipeproject.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** Created by tky on 2022/05/26 */
@Slf4j
@Controller
public class IngredientController {
  private final IngredientService ingredientService;
  private final RecipeService recipeService;
  private final UnitOfMeasureService unitOfMeasureService;

  public IngredientController(
      IngredientService ingredientService,
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
    RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
    ingredientCommand.setRecipeId(Long.valueOf(recipeId));
    model.addAttribute("ingredient", ingredientCommand);
    ingredientCommand.setUom(new UnitOfMeasureCommand());
    model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
    return "recipe/ingredient/ingredientform";
  }

  @PostMapping("/recipe/{recipeId}/ingredient")
  public String saveIngredient(
      @PathVariable String recipeId, @ModelAttribute IngredientCommand ingredientCommand) {
    IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);
    // todo fix recipeId is null
    log.debug("saved recipe id " + savedCommand.getRecipeId());
    log.debug("saved ingredient id " + savedCommand.getId());
    return "redirect:/recipe/"
        + savedCommand.getRecipeId()
        + "/ingredient/"
        + savedCommand.getId()
        + "/show";
  }

  @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
  public String showIngredient(
      @PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
    IngredientCommand returnedIngredientCommand =
        ingredientService.getIngredientCommandById(Long.valueOf(ingredientId));
    model.addAttribute("ingredient", returnedIngredientCommand);
    log.debug(returnedIngredientCommand.toString());
    return "recipe/ingredient/show";
  }

  @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
  public String updateShowIngredient(
      @PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
    IngredientCommand ingredientCommand = ingredientService.getIngredientCommandById(Long.valueOf(ingredientId));
    model.addAttribute("ingredient", ingredientCommand);
    log.debug(ingredientCommand.toString());
    model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
    return "recipe/ingredient/ingredientform";
  }


}
