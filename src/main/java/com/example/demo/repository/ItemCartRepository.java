package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ItemCart;
@Repository
public interface ItemCartRepository extends JpaRepository<ItemCart, Long> {

}
