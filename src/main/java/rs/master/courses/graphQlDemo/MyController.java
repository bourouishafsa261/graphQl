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
    
    // ---------- PART 1 ----------

    // List books paginated 
    @QueryMapping
    public BookPage books(
            @Argument Integer page,
            @Argument Integer size
    ) {
    	// default page size
        int p = page != null ? page : 0;
        int s = (size != null && size > 0) ? size : 10;

        List<Book> allBooks = brp.findAll();
        int total = allBooks.size();

        List<Book> pageList = allBooks.stream()
                .skip((long) p * s)
                .limit(s)
                .toList();

        return new BookPage(
                pageList,
                makePageInfo(p, s, total)
        );
    }
}



