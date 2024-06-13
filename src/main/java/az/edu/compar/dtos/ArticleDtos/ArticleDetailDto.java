package az.edu.compar.dtos.ArticleDtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class ArticleDetailDto {
    private Long id;
    private String title;
    private String description;
    private String categoryName;
    private List<String> tags = new ArrayList<>();
}
