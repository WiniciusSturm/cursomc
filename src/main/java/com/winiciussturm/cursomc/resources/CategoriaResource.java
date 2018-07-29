package com.winiciussturm.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.winiciussturm.cursomc.domain.Categoria;
import com.winiciussturm.cursomc.services.CategoriaService;

@RestController //Anotação
@RequestMapping(value="/categorias")
public class CategoriaResource 
{
	@Autowired //Instancia automaticamente
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //Endpoint
	public ResponseEntity<?> find(@PathVariable Integer id) //PathVariable indica que o argumento é o Id recebido na URL 
	{ //ResponseEntity = tipo especial do Spring que armazena várias informações de uma reposta HTTP para um serviço REST
		
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
		
	}
}
