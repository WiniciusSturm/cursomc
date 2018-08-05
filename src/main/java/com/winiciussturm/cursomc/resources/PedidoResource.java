package com.winiciussturm.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.winiciussturm.cursomc.domain.Pedido;
import com.winiciussturm.cursomc.services.PedidoService;

@RestController //Anotação
@RequestMapping(value="/pedidos")
public class PedidoResource 
{
	@Autowired //Instancia automaticamente
	private PedidoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //Endpoint
	public ResponseEntity<?> find(@PathVariable Integer id) //PathVariable indica que o argumento é o Id recebido na URL 
	{ //ResponseEntity = tipo especial do Spring que armazena várias informações de uma reposta HTTP para um serviço REST
		
		Pedido obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
		
	}
}
