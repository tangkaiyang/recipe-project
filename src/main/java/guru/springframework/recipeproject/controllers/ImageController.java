package guru.springframework.recipeproject.controllers;

import guru.springframework.recipeproject.commands.RecipeCommand;
import guru.springframework.recipeproject.services.ImageService;
import guru.springframework.recipeproject.services.RecipeService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by tky on 2022/06/01
 */
@Controller
public class ImageController {
    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService,
        RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/imageuploadform";
    }

    @PostMapping("/recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile")MultipartFile file) {
        imageService.saveImageFile(Long.valueOf(id), file);
        return "redirect:/recipe/" + id + "/show";
    }

    @GetMapping("/recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        // new recipe command by find command by id
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));
        // new byte array
        byte[] bytes = new byte[recipeCommand.getImage().length];
        // add byte into byte array
        int i = 0;
        for (Byte b : recipeCommand.getImage()) {
            bytes[i++] = b;
        }
        // response set content type image/jpeg
        response.setContentType("image/jpeg");
        // new input stream
        InputStream is = new ByteArrayInputStream(bytes);
        // copy input steam into output steam by IOUtils
        IOUtils.copy(is, response.getOutputStream());
    }
}
