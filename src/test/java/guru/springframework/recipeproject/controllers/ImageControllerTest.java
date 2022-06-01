package guru.springframework.recipeproject.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import guru.springframework.recipeproject.commands.RecipeCommand;
import guru.springframework.recipeproject.services.ImageService;
import guru.springframework.recipeproject.services.RecipeService;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/** Created by tky on 2022/06/01 */
@ExtendWith(MockitoExtension.class)
class ImageControllerTest {
  @Mock ImageService imageService;
  @Mock RecipeService recipeService;
  @InjectMocks ImageController imageController;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
  }

  @Test
  void showUploadForm() throws Exception {
    // given
    RecipeCommand recipeCommand = new RecipeCommand();
    recipeCommand.setId(1L);

    when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

    // when
    mockMvc
        .perform(get("/recipe/1/image"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("recipe"));

    verify(recipeService).findCommandById(anyLong());
  }

  @Test
  void handleImagePost() throws Exception {
    MockMultipartFile mockMultipartFile =
        new MockMultipartFile(
            "imagefile",
            "testing.txt",
            "text/plain",
            "Spring Framework Guru".getBytes(StandardCharsets.UTF_8));
    mockMvc
        .perform(multipart("/recipe/1/image").file(mockMultipartFile))
        .andExpect(status().is3xxRedirection())
        .andExpect(header().string("Location", "/recipe/1/show"));

    verify(imageService).saveImageFile(anyLong(), any());
  }

  @Test
  void renderImageFromDB() throws Exception {
    // given recipe command with id 1
    RecipeCommand recipeCommand = new RecipeCommand();
    recipeCommand.setId(1L);
    // given string
    String s = "fake image text";
    // new byte array
    Byte[] bytes = new Byte[s.getBytes(StandardCharsets.UTF_8).length];
    // put byte of string into byte array
    int i = 0;
    for (byte b : s.getBytes(StandardCharsets.UTF_8)) {
      bytes[i++] = b;
    }
    // recipe command set image
    recipeCommand.setImage(bytes);
    // given when recipe service find command by id return command
    when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
    // when mock http servlet response
    MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage")).andExpect(status().isOk()).andReturn().getResponse();
    // response byte array by get content as byte array
    byte[] responseBytes = response.getContentAsByteArray();
    // assert equal length
    assertEquals(s.getBytes(StandardCharsets.UTF_8).length, responseBytes.length);
  }
}
