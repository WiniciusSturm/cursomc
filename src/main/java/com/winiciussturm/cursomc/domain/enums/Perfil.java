package com.winiciussturm.cursomc.domain.enums;

public enum Perfil
{

	ADMIN (1, "ROLE_ADMIN"), //Role: exigência do Spring Security
	CLIENTE (2, "ROLE_CLIENTE");
	
	private int cod;
	public String descricao;
	
	private Perfil (int cod, String descricao)
	{
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod()
	{
		return cod;
	}
	
	public String getDescricao()
	{
		return descricao;
	}
	
	public static Perfil toEnum(Integer cod)//static pq o método pode ser executado mesmo sem instanciar objetos
	{
		
		if (cod == null)
		{
			return null;
		}
		
		for (Perfil x : Perfil.values())//Todo objeto x nos valores possíveis do TipoCliente
		{
			if (cod.equals(x.getCod())) //Se o código que veio como argumento for igual a algum código da lista enumerada
			{
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}
}
