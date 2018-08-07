package com.winiciussturm.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winiciussturm.cursomc.domain.Categoria;
import com.winiciussturm.cursomc.repositories.CategoriaRepository;
import com.winiciussturm.cursomc.services.exceptions.ObjectNotFoundException;

@Service // @ = anotação do Spring Boot
public class CategoriaService 
{
	@Autowired //Instancia automaticamente as dependências dentro de uma classe
	private CategoriaRepository repo;
	
	public Categoria find(Integer id)
	{
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException (
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert (Categoria obj)
	{
		obj.setId(null); //Apenas para garantir que o objeto a ser inserido será um novo objeto
		return repo.save(obj);
	}

	public Categoria update (Categoria obj)
	{
		find(obj.getId());
		return repo.save(obj);
	}
	
}
