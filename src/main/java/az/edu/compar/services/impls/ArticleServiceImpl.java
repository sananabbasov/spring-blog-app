package az.edu.compar.services.impls;

import az.edu.compar.dtos.ArticleDtos.ArticleCreateDto;
import az.edu.compar.dtos.ArticleDtos.ArticleHomeDto;
import az.edu.compar.entities.Article;
import az.edu.compar.repositories.ArticleRepository;
import az.edu.compar.services.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ArticleServiceImpl implements ArticleService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<ArticleHomeDto> getAll() {
        List<ArticleHomeDto> articles = articleRepository.findAll().stream().map(x->modelMapper.map(x,ArticleHomeDto.class)).collect(Collectors.toList());
        return articles;
    }

    @Override
    public void add(ArticleCreateDto article) {

        Article mapper = modelMapper.map(article,Article.class);
        articleRepository.save(mapper);
    }

    @Override
    public ArticleHomeDto getById(Long articleId) {
        Optional<Article> article = articleRepository.findById(articleId);
        ArticleHomeDto result = modelMapper.map(article, ArticleHomeDto.class);
        article.get().getCategory().getName();
        return result;
    }
}
