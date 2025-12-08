package rs.master.courses.graphQlDemo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBook;

	private String title;
	private int publicationYear;
	private String language;
	private int bPages;

	@ManyToOne
	private Category category;

	@ManyToOne
	private Author author;

}