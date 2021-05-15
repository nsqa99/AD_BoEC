package com.example.demo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.Item;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	public Author findByName(String name);

//	public Set<Author> findAllByBook(Book book);
}
