package com.hannahj.springBoard.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hannahj.springBoard.domain.Board;
import com.hannahj.springBoard.domain.Category;

// CRUD
@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, JpaSpecificationExecutor<Board> {
//	Optional<Board> findOneByIdx(int idx);
	
	Page<Board> findAllByTitle(String title, Pageable pageable);
	
	List<Board> findAllByTitle(String title);

    Page<Board> findAllByCategory(Category category, Pageable pageable);
}
