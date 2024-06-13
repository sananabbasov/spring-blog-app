package az.edu.compar.entities;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private int viewCount;
    @ManyToOne
    private Category category;
    private Date createdDate;
    private Date updatedDate;

    @ManyToOne
    private Comment comment;
    @ManyToMany
    private List<Tag> tag;

    @ManyToOne
    private User user;
}
