package az.edu.compar.services;

import az.edu.compar.dtos.ArticleDtos.ArticleCreateDto;
import az.edu.compar.dtos.ArticleDtos.ArticleHomeDto;
import az.edu.compar.entities.Article;
import az.edu.compar.services.impls.ArticleServiceImpl;

import java.util.List;

public interface ArticleService {
    List<ArticleHomeDto> getAll();
    void add(ArticleCreateDto article);
    ArticleHomeDto getById(Long articleId);
}