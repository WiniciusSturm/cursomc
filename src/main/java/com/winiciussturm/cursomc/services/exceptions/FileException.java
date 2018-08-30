package com.winiciussturm.cursomc.services.exceptions;

public class FileException extends RuntimeException 
{

	private static final long serialVersionUID = 1L;	
	
	public FileException (String msg)
	{
		super(msg);// Repassa a mensagem para a superclasse
	}
	
	public FileException (String msg, Throwable cause) //A causa de uma exceção anterior
	{
		super(msg, cause);
	}
	
}
