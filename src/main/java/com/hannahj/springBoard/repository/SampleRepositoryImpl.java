package com.hannahj.springBoard.repository;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.hannahj.springBoard.domain.Sample;

@Repository
public class SampleRepositoryImpl implements SampleRepository{
    private static final Logger logger = LoggerFactory.getLogger(SampleRepositoryImpl.class);
    

    @Override
    public List<Sample> findAllById(Iterable<Long> ids) {
        return null;
    }

    @Override
    public <S extends Sample> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public void flush() {
    }

    @Override
    public <S extends Sample> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Sample> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Sample> entities) {
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
    }

    @Override
    public void deleteAllInBatch() {
    }

    @Override
    public Sample getOne(Long id) {
        return null;
    }

    @Override
    public Sample getById(Long id) {
        return null;
    }

    @Override
    public <S extends Sample> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Sample> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public Page<Sample> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Sample> S save(S entity) {
        return null;
    }

    @Override
    public Optional<Sample> findById(Long id) {
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public void delete(Sample entity) {
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
    }

    @Override
    public void deleteAll(Iterable<? extends Sample> entities) {
    }

    @Override
    public void deleteAll() {
    }

    @Override
    public <S extends Sample> Optional<S> findOne(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Sample> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Sample> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Sample> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public Optional<Sample> findOne(Specification<Sample> spec) {
        return null;
    }

    @Override
    public List<Sample> findAll(Specification<Sample> spec) {
        return null;
    }

    @Override
    public Page<Sample> findAll(Specification<Sample> spec, Pageable pageable) {
        return null;
    }

    @Override
    public List<Sample> findAll(Specification<Sample> spec, Sort sort) {
        return null;
    }

    @Override
    public long count(Specification<Sample> spec) {
        return 0;
    }

    @Override
    public Optional<Sample> findOneByTitle(String title) {
        return null;
    }

    @Override
    public Page<Sample> findAllById(Long id, Pageable pageable) {
        return null;
    }

    @Override
    public List<Sample> findAllById(Long id) {
        return null;
    }

    @Override
    public List<Sample> findAll() {
        return findAll();
    }

    @Override
    public List<Sample> findByIdAop(Long id) {
        logger.info("********** on impl ***********");
        return findByIdAop(id);

    }
	
    @Override
    public List<Sample> findAllAop() {
        logger.info("********** on impl ***********");
        return findAll();
    }

    @Override
    public List<Sample> findAll(Sort sort) {
        return findAll();
    }

}
