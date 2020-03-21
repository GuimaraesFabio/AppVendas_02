package com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}