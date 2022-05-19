package guru.springframework.recipeproject.domain;

import lombok.*;
import sun.security.provider.PolicyParser;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tky on 2022/05/17
 */
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes = new HashSet<>();

}
