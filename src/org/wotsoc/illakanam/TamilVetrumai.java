
package org.wotsoc.illakanam;


/**
 * @author Rajamani David
 * @since	May 15, 2019
 *	
 */
public class TamilVetrumai {

	public static boolean இரண்டாம்வேற்றுமையா(String word)
	{
		if(word.length()>3)
		{
			char lastChars[]=word.substring(word.length()-3).toCharArray();
			for(char lastChar:lastChars)
			{
				if(lastChar==(char)TamilConstants.ஐ_EXT)
					return true;
			}
		}
		return false;
	}
	
	public static String இரண்டாம்வேற்றுமையைபிரி(String word)
	{
		if(word.length()>3)
		{
			char lastChars[]=word.substring(word.length()-3).toCharArray();
			for(char lastChar:lastChars)
			{
				if(lastChar==(char)TamilConstants.ஐ_EXT)
					return word.substring(0,word.length()-2);
			}
		}
		return word;
	}


	public static boolean மூன்றாம்வேற்றுமையா(String word)
	{
		return TamilUtil.கொடுத்தவில்முடிகிறதா2(TamilConstants.ஆல்வில்முடிகிறதா,word)
				||TamilUtil.கொடுத்தவில்முடிகிறதா2(TamilConstants.ஆன்வில்முடிகிறதா,word)
				||TamilUtil.கொடுத்தவில்முடிகிறதா2(TamilConstants.ஒடுவில்முடிகிறதா, word)
				||TamilUtil.கொடுத்தவில்முடிகிறதா2(TamilConstants.ஓடுவில்முடிகிறதா, word);
	}

	public static String மூன்றாம்வேற்றுமையாபிரி(String word)
	{
		if(TamilUtil.கொடுத்தவில்முடிகிறதா2(TamilConstants.ஆல்வில்முடிகிறதா,word))
			return TamilUtil.கொடுத்தவில்முடிதால்பிரி2(TamilConstants.ஆல்வில்முடிகிறதா,word);
		else if (TamilUtil.கொடுத்தவில்முடிகிறதா2(TamilConstants.ஆன்வில்முடிகிறதா,word))
			return TamilUtil.கொடுத்தவில்முடிதால்பிரி2(TamilConstants.ஆன்வில்முடிகிறதா,word);
		else if (TamilUtil.கொடுத்தவில்முடிகிறதா2(TamilConstants.ஒடுவில்முடிகிறதா, word))
			return TamilUtil.கொடுத்தவில்முடிதால்பிரி2(TamilConstants.ஒடுவில்முடிகிறதா, word);
		else if (TamilUtil.கொடுத்தவில்முடிகிறதா2(TamilConstants.ஓடுவில்முடிகிறதா, word))
			return TamilUtil.கொடுத்தவில்முடிதால்பிரி2(TamilConstants.ஓடுவில்முடிகிறதா, word);
		return word;
	}
	
	public static String நான்காம்வேற்றுமையைபிரி(String word)
	{
		if(word.length()>3)
		{
			if(word.codePointAt(word.length()-1)==TamilConstants.உ_EXT)
				return word.substring(0,word.length()-2);
			else if(word.codePointAt(word.length()-2)==TamilConstants.உ_EXT)
				return word.substring(0,word.length()-3);
			else if(word.codePointAt(word.length()-3)==TamilConstants.உ_EXT)
				return word.substring(0,word.length()-4);
		}
		return word;
	}
	
	public static boolean நான்காம்வேற்றுமையா(String word)
	{
		if(word.length()>3)
		{
			char lastChars[]=word.substring(word.length()-3).toCharArray();
			for(char lastChar:lastChars)
			{
				if(lastChar==(char)TamilConstants.உ_EXT)
					return true;
			}
		}
		return false;
	}
	
	public static boolean ஐந்தாம்வேற்றுமையா(String word)
	{
		return TamilUtil.கொடுத்தவில்முடிகிறதா2(TamilConstants.இல்லில்முடிகிறதா, word) || TamilUtil.கொடுத்தவில்முடிகிறதா2(TamilConstants.இன்னில்முடிகிறதா, word)
				|| TamilUtil.கொடுத்தவில்முடிகிறதா4(TamilConstants.இருந்து,word) || TamilUtil.கொடுத்தவில்முடிகிறதா3(TamilConstants.நின்று,word);
	}
	
	public static String ஐந்தாம்வேற்றுமையைபிரி(String word)
	{
		if(TamilUtil.கொடுத்தவில்முடிகிறதா2(TamilConstants.இல்லில்முடிகிறதா, word))
			return TamilUtil.கொடுத்தவில்முடிதால்பிரி2(TamilConstants.இல்லில்முடிகிறதா, word);
		else if(TamilUtil.கொடுத்தவில்முடிகிறதா2(TamilConstants.இன்னில்முடிகிறதா, word))
			return TamilUtil.கொடுத்தவில்முடிதால்பிரி2(TamilConstants.இன்னில்முடிகிறதா, word);
		else if(TamilUtil.கொடுத்தவில்முடிகிறதா4(TamilConstants.இருந்து,word))
			return TamilUtil.கொடுத்தவில்முடிதால்பிரி4(TamilConstants.இருந்து, word);
		else if(TamilUtil.கொடுத்தவில்முடிகிறதா3(TamilConstants.நின்று,word))
			return TamilUtil.கொடுத்தவில்முடிதால்பிரி3(TamilConstants.நின்று, word);
		return word;	
	}
	
	public static boolean ஆறாம்வேற்றுமையா(String word)
	{
		return TamilUtil.அவில்முடிகிறதா(word)
				||TamilUtil.அதுவில்முடிகிறதா(word)
				||TamilUtil.கொடுத்தவில்முடிகிறதா2(TamilConstants.ஆதுவில்முடிகிறதா, word);
	}
	
	public static String ஆறாம்வேற்றுமையைபிரி(String word)
	{
		if(TamilUtil.அவில்முடிகிறதா(word))
			return	word;
		else if(TamilUtil.அதுவில்முடிகிறதா(word))
			return	TamilUtil.அவில்முடிந்தால்பிரி(TamilConstants.ஆதுவில்முடிகிறதா , word);
		else if(TamilUtil.கொடுத்தவில்முடிகிறதா2(TamilConstants.ஆதுவில்முடிகிறதா, word))
			return	TamilUtil.கொடுத்தவில்முடிதால்பிரி2(TamilConstants.ஆதுவில்முடிகிறதா, word);
		return word;
	}
	
	public static boolean ஏழாம்வேற்றுமையா(String word)
	{
		return TamilUtil.கொடுத்தவில்முடிகிறதா2(TamilConstants.கண்ணில்முடிகிறதா, word) 
				|| TamilUtil.கொடுத்தவில்முடிகிறதா2(TamilConstants.இல்லில்முடிகிறதா, word)
				|| TamilUtil.கொடுத்தவில்முடிகிறதா3(TamilConstants.இடம்,word);
	}

	public static String ஏழாம்வேற்றுமையைபிரி(String word)
	{
		if(TamilUtil.கொடுத்தவில்முடிகிறதா2(TamilConstants.கண்ணில்முடிகிறதா, word)) 
			return TamilUtil.கொடுத்தவில்முடிதால்பிரி2(TamilConstants.கண்ணில்முடிகிறதா, word);
		else if(TamilUtil.கொடுத்தவில்முடிகிறதா2(TamilConstants.இல்லில்முடிகிறதா, word))
			return TamilUtil.கொடுத்தவில்முடிதால்பிரி2(TamilConstants.இல்லில்முடிகிறதா, word);
		else if(TamilUtil.கொடுத்தவில்முடிகிறதா3(TamilConstants.இடம்,word))
			return TamilUtil.கொடுத்தவில்முடிதால்பிரி3(TamilConstants.இடம், word);
		return word;
	}

	public static boolean வேற்றுமையா(String word)
	{
		word=word.trim();
		return இரண்டாம்வேற்றுமையா(word) || மூன்றாம்வேற்றுமையா(word) || நான்காம்வேற்றுமையா(word) || ஐந்தாம்வேற்றுமையா(word) 
				|| ஆறாம்வேற்றுமையா(word) || ஏழாம்வேற்றுமையா(word);
	}
	
	public static String வேற்றுமையைபிரி(String word)
	{
		word=word.trim();
		
		if(இரண்டாம்வேற்றுமையா(word)) 
			return இரண்டாம்வேற்றுமையைபிரி(word);
		else if (மூன்றாம்வேற்றுமையா(word))
			return மூன்றாம்வேற்றுமையாபிரி(word);
		else if (நான்காம்வேற்றுமையா(word))
			return நான்காம்வேற்றுமையைபிரி(word);
		else if (ஐந்தாம்வேற்றுமையா(word))
			return ஐந்தாம்வேற்றுமையைபிரி(word);
		else if(ஆறாம்வேற்றுமையா(word))
			return ஆறாம்வேற்றுமையைபிரி(word);
		else if(ஏழாம்வேற்றுமையா(word))
			return ஏழாம்வேற்றுமையைபிரி(word);
		return word;
	}

}
