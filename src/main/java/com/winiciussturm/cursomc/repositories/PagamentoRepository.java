package com.winiciussturm.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winiciussturm.cursomc.domain.Pagamento;

@Repository //Para acessar o banco de dados
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> //Extends = herda / Integer pq Id (identificador) é desse tipo 
{

}
