package rs.master.courses.graphQlDemo;

import lombok.*;
import java.util.List;

@Data @AllArgsConstructor
public class SearchPage {
    private List<Object> list;
    private PageInfo pageInfo;
}
