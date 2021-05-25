package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	public Category findOneByCode(String code);
	@Query("select count(sc) from SubCategory sc where sc.category.id = ?1")
	public long countSubCategory(long id);
}
