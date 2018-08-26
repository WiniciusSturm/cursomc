package com.winiciussturm.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.winiciussturm.cursomc.domain.Pedido;
import com.winiciussturm.cursomc.services.PedidoService;

@RestController //Anotação
@RequestMapping(value="/pedidos")
public class PedidoResource 
{
	@Autowired //Instancia automaticamente
	private PedidoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //Endpoint
	public ResponseEntity<Pedido> find(@PathVariable Integer id) //PathVariable indica que o argumento é o Id recebido na URL 
	{ //ResponseEntity = tipo especial do Spring que armazena várias informações de uma reposta HTTP para um serviço REST
		
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj)//Para gravar nova categoria através do Json
	{ //@Requestbody Converte o Json para o objeto java automaticamente
		obj = service.insert(obj); //A operação save do repository retorna um objeto
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); //Para definir o path do novo objeto
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.GET) //Endpoint
	public ResponseEntity<Page<Pedido>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, //RequestParam: para deixar a variável opcional 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="instante") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) 
	{ //ResponseEntity = tipo especial do Spring que armazena várias informações de uma reposta HTTP para um serviço REST
		Page<Pedido> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
}
