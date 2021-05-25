package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	public List<Order> findAllByUserId(long id);
	
	@Query("SELECT COUNT(o) FROM Order o WHERE o.user.id = ?1")
	public long getToTalWithUserId(long id);
}
