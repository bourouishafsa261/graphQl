package rs.master.courses.graphQlDemo;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
public class MyController {
	private AuthorRepo arp;
	private BookRepo brp;
	private CategoryRepo crp;

	public MyController(AuthorRepo arp, BookRepo brp, CategoryRepo crp) {
		this.arp = arp;
		this.brp = brp;
		this.crp = crp;
	}

	// helpers
	private PageInfo makePageInfo(int page, int size, int total) {
		int from = page * size;
		int to = Math.min(from + size, total);
		int itemsLeft = Math.max(total - to, 0);
		boolean hasMore = itemsLeft > 0;
		return new PageInfo(total, itemsLeft, hasMore, page, size);
	}

	private boolean isInCategory(Book b, int catId, boolean recursive) {
		if (b.getCategory() == null)
			return false;
		Category c = b.getCategory();
		if (c.getIdCategory() == catId)
			return true;
		if (!recursive)
			return false;

		Category parent = c.getParentCategory();
		while (parent != null) {
			if (parent.getIdCategory() == catId)
				return true;
			parent = parent.getParentCategory();
		}
		return false;
	}

	// ---------- PART 1 ----------

	// List books paginated + filters + recursive category
	@QueryMapping
	public BookPage books(@Argument Integer page, @Argument Integer size, @Argument Integer publicationYear,
			@Argument String language, @Argument Integer idCategory, @Argument Boolean recursive) {
		int p = page != null ? page : 0;
		int s = (size != null && size > 0) ? size : 10;
		boolean rec = recursive != null && recursive;

		List<Book> filtered = brp.findAll().stream()
				.filter(b -> publicationYear == null || b.getPublicationYear() == publicationYear)
				.filter(b -> language == null || b.getLanguage().equalsIgnoreCase(language))
				.filter(b -> idCategory == null || isInCategory(b, idCategory, rec)).toList();

		int total = filtered.size();
		List<Book> pageList = filtered.stream().skip(p * s).limit(s).toList();

		return new BookPage(pageList, makePageInfo(p, s, total));
	}

	// Books of selected author by ID or name
	@QueryMapping
	public List<Book> booksByAuthor(@Argument String nameOrId) {

		if (nameOrId.matches("\\d+")) { // If it's all digits, treat as ID
			int id = Integer.parseInt(nameOrId);
			return brp.findAll().stream().filter(b -> b.getAuthor() != null && b.getAuthor().getIdAuthor() == id)
					.toList();
		}

		// Otherwise, treat as name
		String lowerName = nameOrId.toLowerCase();
		return brp.findAll().stream()
				.filter(b -> b.getAuthor() != null && b.getAuthor().getName().toLowerCase().equals(lowerName)).toList();

	}

	// Search keyword in authors/books/categories, paginated + filter by type
	@QueryMapping
	public SearchPage search(@Argument String keyword, @Argument String type, // BOOK/AUTHOR/CATEGORY/ALL
			@Argument Integer page, @Argument Integer size) {
		String kw = keyword.toLowerCase();
		String t = type == null ? "ALL" : type.toUpperCase();

		int p = page != null ? page : 0;
		int s = (size != null && size > 0) ? size : 10;

		List<Object> combinedResults = new ArrayList<>();

		if (t.equals("ALL") || t.equals("BOOK")) {
			combinedResults
					.addAll(brp.findAll().stream().filter(b -> b.getTitle().toLowerCase().contains(kw)).toList());
		}

		if (t.equals("ALL") || t.equals("AUTHOR")) {
			combinedResults.addAll(arp.findAll().stream().filter(a -> a.getName().toLowerCase().contains(kw)).toList());
		}

		if (t.equals("ALL") || t.equals("CATEGORY")) {
			combinedResults.addAll(
					crp.findAll().stream().filter(c -> c.getCategoryName().toLowerCase().contains(kw)).toList());
		}

		int total = combinedResults.size();
		List<Object> pageList = combinedResults.stream().skip(p * s).limit(s).toList();

		return new SearchPage(pageList, makePageInfo(p, s, total));
	}

}
