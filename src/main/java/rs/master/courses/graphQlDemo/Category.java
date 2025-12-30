package rs.master.courses.graphQlDemo;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategory;

	private String categoryName;

	// parent category
	@ManyToOne
	private Category parentCategory;

	@OneToMany(mappedBy = "category")
	private List<Book> books;
}

