package az.edu.compar.entities;


import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;

    @ManyToOne
    private User user;

    @OneToMany
    @JoinColumn(name = "articles", nullable = true)
    private List<Article> articles;
}
