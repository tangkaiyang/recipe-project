package guru.springframework.recipeproject.services;

import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.repositories.RecipeRepository;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/** Created by tky on 2022/06/01 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
  private final RecipeRepository recipeRepository;

  public ImageServiceImpl(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @Override
  @Transactional
  public void saveImageFile(Long recipeId, MultipartFile file) {
    try {
      // get recipe by id
      Recipe recipe = recipeRepository.findById(recipeId).get();
      // new byte array
      Byte[] bytes = new Byte[file.getBytes().length];
      // put image into byte array
      int i = 0;
      for (byte b : file.getBytes()) {
          bytes[i++] = b;
      }
      // set recipe image
      recipe.setImage(bytes);
      // save recipe
      recipeRepository.save(recipe);

    } catch (IOException e) {
      log.error("Error occurred", e);
      e.printStackTrace();
    }
  }
}
