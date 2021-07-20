package com.hannahj.springBoard.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hannahj.springBoard.domain.Board;
import com.hannahj.springBoard.domain.Category;
import com.hannahj.springBoard.repository.CategoryRepository;

@SpringBootTest
class CategoryServiceTest {
    @Autowired
    CategoryRepository cateRepo;

//    @Test
    void test() {
        CategoryService testService = new CategoryService();
        Category cate1 = Category.builder()
                .id(1L)
                .title("Admin")
                .boards(List.of(
                        Board.builder().title("공지게시판").build(),
                        Board.builder().title("문의게시판").build()
                        )
                )
                .build();
        Category cate2 = Category.builder()
                .id(2L)
                .title("Finance")
                .boards(List.of(
                        Board.builder().title("주식게시판").build(),
                        Board.builder().title("펀드게시판").build()
                        )
                )
                .build();
        List<Category> list = Stream.of(cate1, cate2)
                .collect(Collectors.toList());
        for (Category category : list) {
            System.out.println(category.getId() + ": " + category.getBoards().get(0).getTitle());
            
        }
        Map<Long, List<Object>> categories = testService.getCategoryMap(list);
        
        assertNotNull(categories);
    }
    
    @Test
    public void saveTest() {
    Category admin = Category.builder()
        .title("Admin")
        .build();
    
    Board board1 = Board.builder()
            .title("공지게시판")
            .category(admin)
            .build();
    Board board2 = Board.builder()
            .title("문의게시판")
            .category(admin)
            .build();
    
    admin.setBoards(List.of(board1, board2));
    cateRepo.save(admin);
    
    }
}
