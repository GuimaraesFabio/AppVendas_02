package com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.models.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
