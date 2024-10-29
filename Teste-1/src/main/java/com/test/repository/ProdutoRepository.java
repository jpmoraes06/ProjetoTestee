package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entity.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
