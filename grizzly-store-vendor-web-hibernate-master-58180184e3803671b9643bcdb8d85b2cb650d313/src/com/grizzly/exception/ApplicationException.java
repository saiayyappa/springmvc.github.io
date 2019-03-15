package com.grizzly.exception;

public class ApplicationException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	String message;
	
	public ApplicationException(String message)
	{
		this.message = message;
	}
	
	public String getMessage()
	{
		return message;
	}
}

