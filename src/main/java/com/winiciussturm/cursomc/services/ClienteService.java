package com.winiciussturm.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winiciussturm.cursomc.domain.Cliente;
import com.winiciussturm.cursomc.repositories.ClienteRepository;
import com.winiciussturm.cursomc.services.exceptions.ObjectNotFoundException;

@Service // @ = anotação do Spring Boot
public class ClienteService 
{
	@Autowired //Instancia automaticamente as dependências dentro de uma classe
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id)
	{
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException (
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
}
