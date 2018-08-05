package com.winiciussturm.cursomc.domain.enums;

public enum EstadoPagamento
{

	PENDENTE (1, "Pendente"),
	QUITADO (2, "Quitado"),
	CANCELADO (3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	private EstadoPagamento (int cod, String descricao)
	{
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod()
	{
		return cod;
	}
	
	public String gedDescricao()
	{
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod)//static pq o método pode ser executado mesmo sem instanciar objetos
	{
		
		if (cod == null)
		{
			return null;
		}
		
		for (EstadoPagamento x : EstadoPagamento.values())//Todo objeto x nos valores possíveis do TipoCliente
		{
			if (cod.equals(x.getCod())) //Se o código que veio como argumento for igual a algum código da lista enumerada
			{
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}
}
