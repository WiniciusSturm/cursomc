package com.winiciussturm.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException 
{

	private static final long serialVersionUID = 1L;	
	
	public ObjectNotFoundException (String msg)
	{
		super(msg);// Repassa a mensagem para a superclasse
	}
	
	public ObjectNotFoundException (String msg, Throwable cause) //A causa de uma exceção anterior
	{
		super(msg, cause);
	}
	
}
