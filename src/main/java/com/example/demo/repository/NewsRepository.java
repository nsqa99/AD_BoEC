package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.NewsEntity;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
	List<NewsEntity> findByTitleContaining(Pageable pageable, String keyword);
	List<NewsEntity> findByCategory(Pageable pageable, String keyword);
	List<NewsEntity> findAllNewByCategoryId(String category);
}
