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
	private  AuthorRepo arp;
    private  BookRepo brp;
    private  CategoryRepo crp;

    public MyController(AuthorRepo arp, BookRepo brp, CategoryRepo crp){
        this.arp = arp;
        this.brp = brp;
        this.crp = crp;
    }
   
    
    
    //helpers
    private PageInfo makePageInfo(int page, int size, int total) {
        int from = page * size;
        int to = Math.min(from + size, total);
        int itemsLeft = Math.max(total - to, 0);
        boolean hasMore = itemsLeft > 0;
        return new PageInfo(total, itemsLeft, hasMore, page, size);
    }
    private boolean isInCategory(Book b, int catId, boolean recursive) {
        if (b.getCategory() == null) return false;
        Category c = b.getCategory();
        if (c.getIdCategory() == catId) return true;
        if (!recursive) return false;

        Category parent = c.getParentCategory();
        while (parent != null) {
            if (parent.getIdCategory() == catId) return true;
            parent = parent.getParentCategory();
        }
        return false;
    }
    
    // ---------- PART 1 ----------

    // List books paginated + filters + recursive category
    @QueryMapping
    public BookPage books(
        @Argument Integer page,
        @Argument Integer size,
        @Argument Integer publicationYear,
        @Argument String language,
        @Argument Integer idCategory,
        @Argument Boolean recursive
    ) {
        int p = page != null ? page : 0;
        int s = (size != null && size > 0) ? size : 10;
        boolean rec = recursive != null && recursive;

        List<Book> filtered = brp.findAll().stream()
            .filter(b -> publicationYear == null || b.getPublicationYear() == publicationYear)
            .filter(b -> language == null || b.getLanguage().equalsIgnoreCase(language))
            .filter(b -> idCategory == null || isInCategory(b, idCategory, rec))
            .toList();

        int total = filtered.size();
        List<Book> pageList = filtered.stream()
            .skip(p * s).limit(s)
            .toList();

        return new BookPage(pageList, makePageInfo(p, s, total));
    }
    
 // Books of selected author
    @QueryMapping
    public List<Book> booksByAuthor(@Argument int idAuthor){
        return brp.findAll().stream()
            .filter(b -> b.getAuthor()!=null && b.getAuthor().getIdAuthor()==idAuthor)
            .toList();
    }
}



