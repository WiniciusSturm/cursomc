package com.winiciussturm.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.winiciussturm.cursomc.domain.Cliente;
import com.winiciussturm.cursomc.dto.ClienteDTO;
import com.winiciussturm.cursomc.dto.ClienteNewDTO;
import com.winiciussturm.cursomc.services.ClienteService;

@RestController //Anotação
@RequestMapping(value="/clientes")
public class ClienteResource 
{
	@Autowired //Instancia automaticamente
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //Endpoint
	public ResponseEntity<Cliente> find(@PathVariable Integer id) //PathVariable indica que o argumento é o Id recebido na URL 
	{ //ResponseEntity = tipo especial do Spring que armazena várias informações de uma reposta HTTP para um serviço REST
		
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDTO)//Para gravar nova categoria através do Json
	{ //@Requestbody Converte o Json para o objeto java automaticamente
		Cliente obj = service.fromDTO(objDTO);
		obj = service.insert(obj); //A operação save do repository retorna um objeto
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); //Para definir o path do novo objeto
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update (@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id)
	{
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id)
	{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET) //Endpoint
	public ResponseEntity<List<ClienteDTO>> findAll() 
	{ //ResponseEntity = tipo especial do Spring que armazena várias informações de uma reposta HTTP para um serviço REST
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList()); //Converte uma lista para outra lista
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/page", method=RequestMethod.GET) //Endpoint
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, //RequestParam: para deixar a variável opcional 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) 
	{ //ResponseEntity = tipo especial do Spring que armazena várias informações de uma reposta HTTP para um serviço REST
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj)); //Converte uma lista para outra lista
		return ResponseEntity.ok().body(listDTO);
	}
}
