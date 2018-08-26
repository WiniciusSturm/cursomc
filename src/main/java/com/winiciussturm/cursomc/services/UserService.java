package com.winiciussturm.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.winiciussturm.cursomc.security.UserSS;

public class UserService 
{

	public static UserSS authenticated() 
	{
		try
		{
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//Casting de UserSS
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
}
