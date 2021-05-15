package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Comment;
import com.example.demo.entity.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	public List<Comment> findAllByUser(User user);
	
}
