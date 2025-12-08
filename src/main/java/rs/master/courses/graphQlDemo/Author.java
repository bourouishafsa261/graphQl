package rs.master.courses.graphQlDemo;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAuthor;

    private String name;
    private int age;
    private String nationality;


    @OneToMany(mappedBy="author")
    private List<Book> books;
}