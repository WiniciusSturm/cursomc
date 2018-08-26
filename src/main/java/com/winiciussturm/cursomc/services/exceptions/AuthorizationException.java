package com.winiciussturm.cursomc.services.exceptions;

public class AuthorizationException extends RuntimeException 
{

	private static final long serialVersionUID = 1L;	
	
	public AuthorizationException (String msg)
	{
		super(msg);// Repassa a mensagem para a superclasse
	}
	
	public AuthorizationException (String msg, Throwable cause) //A causa de uma exceção anterior
	{
		super(msg, cause);
	}
	
}
