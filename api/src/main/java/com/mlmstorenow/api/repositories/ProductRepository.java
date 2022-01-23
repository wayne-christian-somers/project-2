package com.mlmstorenow.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mlmstorenow.api.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
