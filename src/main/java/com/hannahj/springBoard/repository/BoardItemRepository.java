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
import com.hannahj.springBoard.domain.BoardItem;

// CRUD

public interface BoardItemRepository extends JpaRepository<BoardItem, Long>, JpaSpecificationExecutor<BoardItem> {
//	Optional<BoardItem> findOneById(int idx);
	
	Page<BoardItem> findAllByTitle(String title, Pageable pageable);
	
	List<BoardItem> findAllByTitle(String title);
	
	Page<BoardItem> findByParentId(Long parentId, Pageable pageable);
	
	Page<BoardItem> findByBoard(Board board, Pageable pageable);
	
	List<BoardItem> findByBoard(Board board);
	
	List<BoardItem> findAllByBoardAndParentIdIsNull(Board board);
	
	Page<BoardItem> findAllByBoardAndParentIdIsNull(Board board, Pageable pageable);
	
//	@Query(nativeQuery = true, 
//	        value="SELECT * FROM board_item WHERE (title like (:expression) OR content like (:expression))",
//	        countQuery = "SELECT count(*) FROM board_item")
//	@Query("select b from BoardItem b where (b.title like concat('%', :expression, '%') "
//            + "or b.content like concat('%', :expression, '%') and b.parentId is null")
//	Page<BoardItem> findAllPostsWithTitleAndContentLike(String expression, Pageable pageable);

	List<BoardItem> findByParentIdIsNullAndTitleLikeIgnoreCaseOrParentIdIsNullAndContentLikeIgnoreCase(String expression, String expression1);
	
    Page<BoardItem> findByParentId(Specification<BoardItem> search, Pageable pageable);
}
