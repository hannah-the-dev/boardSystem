package com.hannahj.springBoard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hannahj.springBoard.domain.Sample;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Long>, JpaSpecificationExecutor<Sample>{
	Optional<Sample> findOneByTitle(String title);
	
	Page<Sample> findAllById(Long id, Pageable pageable);
	
	List<Sample> findAllById(Long id);

    List<Sample> findAllAop();
    List<Sample> findByIdAop(Long id);
}
