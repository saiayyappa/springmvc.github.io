package com.grizzly.validation;
public class  CheckValidation {

//                                              TO CHECK WHETHER THE INPUT IS AN INTEGER
	
    public static boolean checkDigit(String integer)
    {
        boolean bool=false;

        for(int i=0;i<integer.length();i++)
        {
        	
            if(Character.isDigit(integer.charAt(i))==false)
            {
            	bool=false;
                break;
            }
            else 
            {
                bool=true;
            }
        }
        return bool;
    }
    
    public static boolean checkDecimal(String integer)
    {
    	boolean bool = false;
    	
    	String[] digits = integer.split(".");
    			
    	if(digits.length <=2)
    	{
    		for(int i=0;i< digits.length;i++)
            {
            	
                if(Character.isDigit(integer.charAt(i))==false)
                {
                	bool=false;
                    break;
                }
                else 
                {
                    bool=true;
                }
            }
    	}
    	else
    	{
    		bool = false;
    	}
    	
    	return bool;
    }
  
//                                               TO CHECK WHETHER ENTERED INPUT IS A STRING
       
    public static boolean checkCharacter(String strings)
    {
        boolean bool=false;
   
        for(int i=0;i<strings.length()-1;i++)
        {
        	String string=strings.replaceAll(" ","");

            if(Character.isLetter(string.charAt(i))==true)
            {
                bool=true;
                
            }
            else
            {
            	bool=false;
                break;
            }
        }
        return bool;
    }
  
}