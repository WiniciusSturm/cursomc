package com.winiciussturm.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException 
{

	private static final long serialVersionUID = 1L;	
	
	public DataIntegrityException (String msg)
	{
		super(msg);// Repassa a mensagem para a superclasse
	}
	
	public DataIntegrityException (String msg, Throwable cause) //A causa de uma exceção anterior
	{
		super(msg, cause);
	}
	
}
