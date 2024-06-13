package az.edu.compar.controllers;


import az.edu.compar.dtos.ArticleDtos.ArticleCreateDto;
import az.edu.compar.dtos.ArticleDtos.ArticleHomeDto;
import az.edu.compar.entities.Article;
import az.edu.compar.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {


    @Autowired
    private ArticleService articleService;



    @GetMapping("/getall")
    public ResponseEntity<List<ArticleHomeDto>> getAll()
    {
        List<ArticleHomeDto> result = articleService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity create(@RequestBody ArticleCreateDto article)
    {
        try {
            articleService.add(article);
            return ResponseEntity.ok(HttpStatus.CREATED);
        }  catch (Exception e){
            return ResponseEntity.ok(HttpStatus.BAD_GATEWAY);
        }
    }


}
