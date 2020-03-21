package com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.models.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
