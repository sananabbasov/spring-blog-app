package az.edu.compar.dtos.ArticleDtos;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleCreateDto {
    @NotEmpty(message = "Basliq bos ola bilmez.")
    @Size(min = 5,message = "Basliq minimum 5 simvol olmalidir.")
    private String title;
    @NotBlank(message = "Error")
    private String description;
    private Long categoryId;
}



