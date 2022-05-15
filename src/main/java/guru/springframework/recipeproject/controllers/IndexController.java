package guru.springframework.recipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tky on 2022/05/15
 */
@Controller
public class IndexController {
    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage() {
        return "index";
    }
}
