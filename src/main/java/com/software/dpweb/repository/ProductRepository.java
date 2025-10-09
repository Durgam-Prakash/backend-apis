package com.software.dpweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.software.dpweb.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
