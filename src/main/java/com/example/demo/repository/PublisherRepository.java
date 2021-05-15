package com.example.demo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
	public Publisher findByName(String name);

//	public Set<Publisher> findAllByBook(Book book);
}
