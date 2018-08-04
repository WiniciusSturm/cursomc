package com.winiciussturm.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winiciussturm.cursomc.domain.Cliente;

@Repository //Para acessar o banco de dados
public interface ClienteRepository extends JpaRepository<Cliente, Integer> //Extends = herda / Integer pq Id (identificador) é desse tipo 
{

}
