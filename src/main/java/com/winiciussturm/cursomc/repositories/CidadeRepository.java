package com.winiciussturm.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.winiciussturm.cursomc.domain.Cidade;

@Repository //Para acessar o banco de dados
public interface CidadeRepository extends JpaRepository<Cidade, Integer> //Extends = herda / Integer pq Id (identificador) é desse tipo 
{

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Cidade obj WHERE obj.estado.id = :estadoId ORDER BY obj.nome")
	public List<Cidade> findCidades(@Param("estadoId") Integer estado_id);
	
}
