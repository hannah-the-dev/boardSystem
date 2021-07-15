package com.hannahj.springBoard.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.data.jpa.domain.Specification;

import com.hannahj.springBoard.domain.Post;

public class PostSpecs {
	public static Specification<Post> search(Map<String, Object> filter){
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			filter.forEach((key, value) -> {
				switch (key) {
				case "title":
					predicates.add(builder.equal(root.get(key).as(String.class), value));
				}
			});
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	public static Specification<Post> searchByTitleAndContent(Map<String, Object> filter){
	    return (root, query, builder) -> {
	        List<Predicate> predicates = new ArrayList<>();
	        filter.forEach((key, value) -> {
	            switch (key) {
	            case "title":
	            case "content":
	                predicates.add(builder.equal(root.get(key).as(String.class), value));
	            }
	        });
	        return builder.and(predicates.toArray(new Predicate[0]));
	    };
	}
	
	//service로 돌림
    public static String getExpression (String string) {
        String[] expArr = string.split(" ");
        String regexp = String.join("|", expArr);
        return regexp;
    }
    
//    public static Predicate searchPred(String keywords) {
//        String[] searches = keywords.split(" ");
//        EntityManager entityManager = null;
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        
//        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
//        Root<Post> itemRoot = criteriaQuery.from(Post.class);
//        Predicate predicateForTitleLike = null;
//        Predicate predicateForContentLike = null;
//        for (String search : searches) {
//            predicateForTitleLike
//            = criteriaBuilder.like(itemRoot.get("title"), search.toLowerCase());
//            predicateForContentLike
//            = criteriaBuilder.like(itemRoot.get("content"), search.toLowerCase());
//        }
//        Predicate predicateForTitleAndContentLike
//          = criteriaBuilder.or(predicateForTitleLike, predicateForContentLike);
//    
//        Predicate predicateForPidNull
//          = criteriaBuilder.equal(itemRoot.get("parentId"), null);
//        
//        Predicate finalPredicate
//          = criteriaBuilder
//          .or(predicateForTitleAndContentLike, predicateForPidNull);
//        criteriaQuery.where(finalPredicate);
//        List<Post> items = entityManager.createQuery(criteriaQuery).getResultList();
//        return finalPredicate;
//    }
}
