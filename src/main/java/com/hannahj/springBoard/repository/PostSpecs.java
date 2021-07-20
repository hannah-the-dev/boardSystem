package com.hannahj.springBoard.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.hannahj.springBoard.domain.Post;

public class PostSpecs {
    public enum SearchKey {
        TITLE("title"),
        CONTENT("content"),
        WRITER("username"),
        HIT("hit");

        private final String value;

        SearchKey(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    
	public static Specification<Post> search(Map<SearchKey, Object> filter){
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			filter.forEach((key, value) -> {
				switch (key) {
				case TITLE:
				case CONTENT:
				case WRITER:
					predicates.add(
					        builder.equal(
					                root.get(key.value), 
					                filter.get(key)
					                )
					        );
					break;
				case HIT:
				    predicates.add(
				            builder.greaterThanOrEqualTo(
				                    root.get(key.value), 
				                    Integer.valueOf(filter.get(key).toString())
				                    )
				            );
				    break;
				}
			});
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

    public static String getExpression (String string) {
        String[] expArr = string.split(" ");
        String regexp = String.join("|", expArr);
        return regexp;
    }
}
