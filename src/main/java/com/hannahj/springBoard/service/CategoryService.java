package com.hannahj.springBoard.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hannahj.springBoard.domain.Category;

/**
 * @author HannahJ
 * @param categoryList get the category list that contains board list
 * @return mapped by grouped ...
 * @see Category
 */
@Service
public class CategoryService {
    public Map<Long, List<Object>> getCategoryMap(List<Category> categoryList) {
        Map<Long, List<Object>> categoryMap = 
                categoryList.stream()
                .collect(
                        Collectors.groupingBy(p -> (Long)p.getId(), 
                                Collectors.mapping(
                                        p -> p.getBoards(), 
                                        Collectors.toList()
                                )
                        )
                );
        return categoryMap;
    }
}
