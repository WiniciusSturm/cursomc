package com.winiciussturm.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.winiciussturm.cursomc.domain.Estado;

@Repository //Para acessar o banco de dados
public interface EstadoRepository extends JpaRepository<Estado, Integer> //Extends = herda / Integer pq Id (identificador) é desse tipo 
{

	@Transactional(readOnly=true)
	public List<Estado> findAllByOrderByNome();
	
}
