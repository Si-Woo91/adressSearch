package com.example.adress.util;

import org.springframework.util.ObjectUtils;

public class Utiles {

	// null or empty 체크
	static public boolean isNullOrEmpty(String str) 
	{
		if(str == null || str.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	static public boolean isNullOrEmpty(Object obj) 
	{
		if(obj == null || ObjectUtils.isEmpty(obj))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
