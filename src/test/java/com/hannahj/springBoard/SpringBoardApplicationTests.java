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
import com.hannahj.springBoard.domain.Post;
import com.hannahj.springBoard.repository.PostRepository;
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
    private PostRepository postRepo;
    
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

	    Post post1 = Post.builder()
	            .board(board)
	            .title("첫번째 공지")
	            .username("관리자1")
	            .content("게시판을 개설했습니다.")
	            .build();
	    Post post2 = Post.builder()
	            .board(board)
	            .title("두번째 공지")
                .username("관리자1")
                .content("이벤트는 뭘로 하면 좋을까요?")
	            .build();
	    
	    
	    List<Post> list = new ArrayList<>();
	    list.add(post1);
	    list.add(post2);
	    
	    board.setPosts(list);
	    boardRepo.save(board);
//	    return board.getId();
	}
	
//	@Test
    void getPosts() {
	    List<Post> posts = postRepo.findAll();
	    for (Post  post: posts) {
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
//	        Hibernate.initialize(board.getPosts());
	        for (int i = 0; i < 20; i++) {
                
	            Post post = Post.builder()
	                    .board(board)
	                    .title("TestInsertPostOnly")
	                    .build();
	            postRepo.save(post);
            }
	    }
    }
	
//	@Test @Order(1)
//	@Transactional
	void delete() {
	    long id = 1L;
	    Optional<Board> boardOptional;
	    Board board;
	    if (boardRepo.existsById(id)) {
	        boardOptional = boardRepo.findById(id);
	        if (!boardOptional.isPresent()) {
	            return;
	        } else {
	            board = boardOptional.get();
	            Hibernate.initialize(board.getPosts());
	            board.getTitle();
	            board.getPosts();
	            boardRepo.deleteById(id);
	        }
	        List<Post> deleted = postRepo.findByBoard(board);
	        assertEquals(0, deleted.size());
	    }
	}
	
//	@Test
	void findByID() {
	    Optional<Board> boardOptional = boardRepo.findById(3L);
	    Board board = boardOptional.get();
	    List<Post> posts = board.getPosts();

	}
//	@Test
//	@Transactional
	void findByIDWithChild() {
//	    LocalDateTime now = LocalDateTime.now();
	    Optional<Board> boardOptional = boardRepo.findById(3L);
	    Board board = boardOptional.get();
	    Hibernate.initialize(board.getPosts());
	    List<Post> posts = board.getPosts();
	    assertNotNull(posts);
	}
	
	
}
