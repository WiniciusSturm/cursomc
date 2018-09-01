package com.winiciussturm.cursomc.dto;

import java.io.Serializable;

import com.winiciussturm.cursomc.domain.Estado;

public class EstadoDTO implements Serializable //Define os dados que quero trafegar em operações básicas de categoria
{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	
	public EstadoDTO()
	{
		
	}
	
	public EstadoDTO(Estado obj)
	{
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
