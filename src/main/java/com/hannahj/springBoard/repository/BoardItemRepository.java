package com.hannahj.springBoard.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hannahj.springBoard.domain.Board;
import com.hannahj.springBoard.domain.BoardItem;

// CRUD
@Repository
public interface BoardItemRepository extends JpaRepository<BoardItem, Long>, JpaSpecificationExecutor<BoardItem> {
//	Optional<BoardItem> findOneById(int idx);
	
	Page<BoardItem> findAllByTitle(String title, Pageable pageable);
	
	List<BoardItem> findAllByTitle(String title);
	
	Page<BoardItem> findByParentId(Long parentId, Pageable pageable);
	
	Page<BoardItem> findByBoard(Board board, Pageable pageable);
	
	List<BoardItem> findByBoard(Board board);
	
	List<BoardItem> findAllByBoardAndParentIdIsNull(Board board);
	
	Page<BoardItem> findAllByBoardAndParentIdIsNull(Board board, Pageable pageable);
	
	@Query(nativeQuery=true, value="SELECT * FROM board_item WHERE parent_id is null AND (title regexp ?1 OR content regexp ?1);")
	Page<BoardItem> findAllByParentIdIsNullAndRegex(String expression, Pageable pageable);
}
