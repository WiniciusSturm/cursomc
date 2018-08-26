package com.winiciussturm.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.winiciussturm.cursomc.domain.Cliente;
import com.winiciussturm.cursomc.domain.Pedido;

@Repository //Para acessar o banco de dados
public interface PedidoRepository extends JpaRepository<Pedido, Integer> //Extends = herda / Integer pq Id (identificador) é desse tipo 
{

	@Transactional(readOnly=true)
	Page <Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
	
}