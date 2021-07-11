package com.hannahj.springBoard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.MockMvc.*;
import static org.springframework.util.LinkedMultiValueMap.*;
import static org.springframework.util.MultiValueMap.*;
import com.hannahj.springBoard.domain.Board;
import com.hannahj.springBoard.domain.BoardItem;
import com.hannahj.springBoard.repository.BoardItemRepository;
import com.hannahj.springBoard.repository.BoardRepository;
import com.hannahj.springBoard.web.BoardController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@Transactional
@WebMvcTest(BoardController.class)
class SpringBoardApplicationTests {

    @Autowired
    private BoardRepository boardRepo;
    @Autowired
    private BoardItemRepository boardItemRepo;
    
    @Autowired
    private  MockMvc mockMvc;
    
   
    
//	@Test
	void contextLoads() {
	}
	
//	@Test
	void create() {

	    Board board = Board.builder()
	            .title("공지게시판")
	            .build();

	    BoardItem boardItem1 = BoardItem.builder()
	            .board(board)
	            .title("첫번째 공지")
	            .username("관리자1")
	            .content("게시판을 개설했습니다.")
	            .build();
	    BoardItem boardItem2 = BoardItem.builder()
	            .board(board)
	            .title("두번째 공지")
                .username("관리자1")
                .content("이벤트는 뭘로 하면 좋을까요?")
	            .build();
	    
	    
	    List<BoardItem> list = new ArrayList<>();
	    list.add(boardItem1);
	    list.add(boardItem2);
	    
	    board.setBoardItems(list);
	    boardRepo.save(board);
//	    return board.getId();
	}
	
//	@Test
    void getBoardItems() {
	    List<BoardItem> posts = boardItemRepo.findAll();
	    for (BoardItem  post: posts) {
	        System.out.println("->");
//	        System.out.println(post.getId());
	        System.out.println(post.getTitle());
	        System.out.println("<-");
	    }
	}
    
//    @Test
    void createPost() {
	    Optional<Board> boardOptional = boardRepo.findById(3L);
	    if (!boardOptional.isPresent()) {
	        return;
	    } else {
	        Board board = boardOptional.get();
//	        Hibernate.initialize(board.getBoardItems());
	        for (int i = 0; i < 20; i++) {
                
	            BoardItem boardItem = BoardItem.builder()
	                    .board(board)
	                    .title("TestInsertPostOnly")
	                    .build();
	            boardItemRepo.save(boardItem);
            }
	    }
    }
	
//	@Test @Order(1)
//	@Transactional
	void delete() {
	    long id = create();
	    Optional<Board> boardOptional;
	    Board board;
	    if (boardRepo.existsById(id)) {
	        boardOptional = boardRepo.findById(id);
	        if (!boardOptional.isPresent()) {
	            return;
	        } else {
	            board = boardOptional.get();
	            Hibernate.initialize(board.getBoardItems());
	            board.getTitle();
	            board.getBoardItems();
	            boardRepo.deleteById(id);
	        }
	        List<BoardItem> deleted = boardItemRepo.findByBoard(board);
	        assertEquals(0, deleted.size());
	    }
	}
	
//	@Test
	void findByID() {
	    Optional<Board> boardOptional = boardRepo.findById(3L);
	    Board board = boardOptional.get();
	    List<BoardItem> posts = board.getBoardItems();

	}
//	@Test
//	@Transactional
	void findByIDWithChild() {
//	    LocalDateTime now = LocalDateTime.now();
	    Optional<Board> boardOptional = boardRepo.findById(3L);
	    Board board = boardOptional.get();
	    Hibernate.initialize(board.getBoardItems());
	    List<BoardItem> boardItems = board.getBoardItems();
	    assertNotNull(boardItems);
	}
	
	
}
