package com.winiciussturm.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winiciussturm.cursomc.domain.Categoria;

@Repository //Para acessar o banco de dados
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> //Extends = herda / Integer pq Id (identificador) é desse tipo 
{

}
