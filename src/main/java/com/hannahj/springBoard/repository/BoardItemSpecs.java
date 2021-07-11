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

import com.hannahj.springBoard.domain.BoardItem;

public class BoardItemSpecs {
	public static Specification<BoardItem> search(Map<String, Object> filter){
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
	public static Specification<BoardItem> searchByTitleAndContent(Map<String, Object> filter){
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
        String[] expArr = string.split("%20");
        String regexp = String.join("|", expArr);
        return regexp;
    }
    
    public static Predicate searchPred(String keywords) {
        String[] searches = keywords.split(" ");
        EntityManager entityManager = null;
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<BoardItem> criteriaQuery = criteriaBuilder.createQuery(BoardItem.class);
        Root<BoardItem> itemRoot = criteriaQuery.from(BoardItem.class);
        Predicate predicateForTitleLike = null;
        Predicate predicateForContentLike = null;
        for (String search : searches) {
            predicateForTitleLike
            = criteriaBuilder.like(itemRoot.get("title"), search.toLowerCase());
            predicateForContentLike
            = criteriaBuilder.like(itemRoot.get("content"), search.toLowerCase());
        }
        Predicate predicateForTitleAndContentLike
          = criteriaBuilder.or(predicateForTitleLike, predicateForContentLike);
    
        Predicate predicateForPidNull
          = criteriaBuilder.equal(itemRoot.get("parentId"), null);
        
        Predicate finalPredicate
          = criteriaBuilder
          .or(predicateForTitleAndContentLike, predicateForPidNull);
        criteriaQuery.where(finalPredicate);
        List<BoardItem> items = entityManager.createQuery(criteriaQuery).getResultList();
        return finalPredicate;
    }
}
