package com.winiciussturm.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winiciussturm.cursomc.domain.Categoria;
import com.winiciussturm.cursomc.repositories.CategoriaRepository;

@Service // @ = anotação do Spring Boot
public class CategoriaService 
{
	@Autowired //Instancia automaticamente as dependências dentro de uma classe
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id)
	{
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
}
