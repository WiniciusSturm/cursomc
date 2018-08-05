package com.winiciussturm.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winiciussturm.cursomc.domain.Pedido;

@Repository //Para acessar o banco de dados
public interface PedidoRepository extends JpaRepository<Pedido, Integer> //Extends = herda / Integer pq Id (identificador) é desse tipo 
{

}
