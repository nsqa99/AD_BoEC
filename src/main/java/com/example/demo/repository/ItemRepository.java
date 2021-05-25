package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	@Query("SELECT i FROM Item i WHERE i.category.code = ?1")
	public List<Item> findAllByCategory(String category);
	
	@Query("SELECT i FROM Item i WHERE i.subcategory.code = ?1")
	public List<Item> findAllBySubcategory(String sub);
	
	@Query("SELECT COUNT(i) FROM Item i WHERE i.category.code = ?1")
	public long getToTalByCategory(String category);
	
	@Query("SELECT COUNT(i) FROM Item i WHERE i.subcategory.code = ?1")
	public long getToTalBySubcategory(String sub);
}
