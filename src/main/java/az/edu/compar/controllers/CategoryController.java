package az.edu.compar.controllers;


import az.edu.compar.dtos.CategoryDtos.CategoryCreateDto;
import az.edu.compar.dtos.CategoryDtos.CategoryDto;
import az.edu.compar.dtos.CategoryDtos.CategoryUpdateDto;
import az.edu.compar.services.CategoryService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/create")
    public ResponseEntity add( @RequestBody CategoryCreateDto categoryCreateDto)
    {
        categoryService.createCategory(categoryCreateDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody CategoryUpdateDto categoryUpdateDto)
    {
        categoryService.updateCategory(id,categoryUpdateDto);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/getall")
    public ResponseEntity<List<CategoryDto>> getAll()
    {
        rabbitTemplate.convertAndSend("","spring-boot","salam");
       List<CategoryDto> categories = categoryService.getAllCategories();
       return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
