package com.winiciussturm.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winiciussturm.cursomc.domain.Pedido;
import com.winiciussturm.cursomc.repositories.PedidoRepository;
import com.winiciussturm.cursomc.services.exceptions.ObjectNotFoundException;

@Service // @ = anotação do Spring Boot
public class PedidoService 
{
	@Autowired //Instancia automaticamente as dependências dentro de uma classe
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id)
	{
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException (
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
}