package com.winiciussturm.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity//Fazer mapeamento da classe Produto
public class Produto implements Serializable //Os objetos da classe podem ser convertidos para uma sequência de bytes
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;
	
	//Anotação jpa
	@JsonBackReference //Informa que do outro lado da aplicação já foram buscados os objetos, omitindo a lista de categorias para cada produto.
	@ManyToMany
	@JoinTable (name="PRODUTO_CATEGORIA",
		joinColumns = @JoinColumn (name="produto_id"),//Chave estrangeira que referencia produtos
		inverseJoinColumns = @JoinColumn (name="categoria_id")//Chave estrangeira que referencia categorias
			)//Cria uma tabela entre Categoria e Produto, para relacionar ambos (de acordo com modelagem relacional de dados)
	private List<Categoria> categorias = new ArrayList<>();//Associações: um produto pode ter uma ou mais categorias, segundo diagrama de classes
	
	@JsonIgnore
	@OneToMany(mappedBy="id.produto")
	private Set<ItemPedido> itens = new HashSet<>();
	
	//A classe tem que ter o construtor vazio
	public Produto()
	{
		
	}

	public Produto(Integer id, String nome, Double preco) { //Gerado automaticamente, sem selecionar as coleções (categorias)
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	@JsonIgnore
	public List<Pedido> getPedidos()
	{
		List<Pedido> lista = new ArrayList<>();
		for (ItemPedido x : itens)
		{
			lista.add(x.getPedido());
		}
		return lista;
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
