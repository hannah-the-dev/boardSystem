package com.hannahj.springBoard.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hannahj.springBoard.domain.Board;
import com.hannahj.springBoard.domain.Post;

// CRUD

public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
//	Optional<Post> findOneById(int idx);
	
	Page<Post> findAllByTitle(String title, Pageable pageable);
	
	List<Post> findAllByTitle(String title);
	
	Page<Post> findByParentId(Long parentId, Pageable pageable);
	
	Page<Post> findByBoard(Board board, Pageable pageable);
	
	List<Post> findByBoard(Board board);
	
	List<Post> findAllByBoardAndParentIdIsNull(Board board);
	
	Page<Post> findAllByBoardAndParentIdIsNull(Board board, Pageable pageable);
	
//	@Query(nativeQuery = true, 
//	        value="SELECT * FROM board_item WHERE (title like (:expression) OR content like (:expression))",
//	        countQuery = "SELECT count(*) FROM board_item")
//	@Query("select b from Post b where (b.title like concat('%', :expression, '%') "
//            + "or b.content like concat('%', :expression, '%') and b.parentId is null")
//	Page<Post> findAllPostsWithTitleAndContentLike(String expression, Pageable pageable);

	List<Post> findByParentIdIsNullAndTitleLikeIgnoreCaseOrParentIdIsNullAndContentLikeIgnoreCase(String expression, String expression1);
	
    Page<Post> findByParentId(Specification<Post> search, Pageable pageable);
}
