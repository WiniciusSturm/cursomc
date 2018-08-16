package com.winiciussturm.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.winiciussturm.cursomc.domain.Categoria;
import com.winiciussturm.cursomc.domain.Produto;
import com.winiciussturm.cursomc.repositories.CategoriaRepository;
import com.winiciussturm.cursomc.repositories.ProdutoRepository;
import com.winiciussturm.cursomc.services.exceptions.ObjectNotFoundException;

@Service // @ = anotação do Spring Boot
public class ProdutoService 
{
	@Autowired //Instancia automaticamente as dependências dentro de uma classe
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id)
	{
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException (
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search (String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) //Page: classe do Spring Data, encapsula informações e operações de paginação
	{
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
		
	}
	
}
