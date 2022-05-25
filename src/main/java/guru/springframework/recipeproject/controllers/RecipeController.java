package guru.springframework.recipeproject.controllers;


import com.sun.org.apache.xpath.internal.operations.Mod;
import guru.springframework.recipeproject.commands.RecipeCommand;
import guru.springframework.recipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** Created by tky on 2022/05/22 */
@Controller
public class RecipeController {
  private final RecipeService recipeService;

  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @RequestMapping("/recipe/{id}/show")
  public String showById(@PathVariable String id, Model model) {
    model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
    return "recipe/show";
  }

  @RequestMapping("/recipe/new")
  public String newRecipe(Model model) {
    model.addAttribute("recipe", new RecipeCommand());
    return "recipe/recipeform";
  }

  @RequestMapping("/recipe/{id}/update")
  public String updateRecipe(@PathVariable String id, Model model) {
    RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));
    model.addAttribute("recipe", recipeCommand);
    return "recipe/recipeform";
  }

  @PostMapping(path = "/recipe")
  public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
    RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);
    return "redirect:/recipe/" + savedCommand.getId() + "/show";
  }

  @RequestMapping("/recipe/{id}/delete")
  public String deleteById(@PathVariable String id) {
    recipeService.deleteById(Long.valueOf(id));
    return "redirect:/";
  }
}
