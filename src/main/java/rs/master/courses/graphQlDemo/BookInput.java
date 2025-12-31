package rs.master.courses.graphQlDemo;

import lombok.*;

@Data
public class BookInput {
    private String title;
    private Integer publicationYear;
    private String language;
    private Integer bPages;
    private Integer idCategory;
    private Integer idAuthor;
}
