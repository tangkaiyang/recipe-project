package guru.springframework.recipeproject.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by tky on 2022/06/01
 */
public interface ImageService {

    void saveImageFile(Long id, MultipartFile file);
}
