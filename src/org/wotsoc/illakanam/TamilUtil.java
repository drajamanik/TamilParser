/**
 * @author Rajamani David
 * @since	May 2, 2017
 *
 */
package org.wotsoc.illakanam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * @author Rajamani David
 *	Lot of method to parse basic letters added 
 */
public class TamilUtil 
{
	static Map<Character,Character> reverseMapCharacter=new HashMap<Character,Character>();
	static Map<Character,String> mapCharacter=new HashMap<Character,String>();
	static Map<Character,String> mapCharacterForward=new HashMap<Character,String>();
	static Map<String,String> mapMutpleSpecialCharacter=new HashMap<String,String>();
	
	static 
	{
		mapCharacter.put(' '," ");
		mapCharacter.put((char)TamilConstants.அ,"அ"); //  3006 $ா TAMIL VOWEL SIGN AA
		mapCharacter.put((char)TamilConstants.ஆ,"ஆ"); //  3006 $ா TAMIL VOWEL SIGN AA
		mapCharacter.put((char)TamilConstants.இ,"இ"); //	  3007 $ி TAMIL VOWEL SIGN I
		mapCharacter.put((char)TamilConstants.ஈ,"ஈ"); //  3008 $ீ TAMIL VOWEL SIGN II
		mapCharacter.put((char)TamilConstants.உ,"உ");//  3009 $ு TAMIL VOWEL SIGN U
		mapCharacter.put((char)TamilConstants.ஊ,"ஊ");//  3010  ூ$ TAMIL VOWEL SIGN UU
		mapCharacter.put((char)TamilConstants.எ,"எ");//  3014  ெ TAMIL VOWEL SIGN E
		mapCharacter.put((char)TamilConstants.ஏ,"ஏ");//  3015  ே TAMIL VOWEL SIGN EE
		mapCharacter.put((char)TamilConstants.ஐ,"ஐ");//  3016  ை TAMIL VOWEL SIGN AI
		mapCharacter.put((char)TamilConstants.ஒ,"ஒ");//  3018 $ொ TAMIL VOWEL SIGN O
		mapCharacter.put((char)TamilConstants.ஓ,"ஓ");//  3019 $ோ TAMIL VOWEL SIGN OO
		mapCharacter.put((char)TamilConstants.ஃ,"ஃ");
		mapCharacter.put((char)0xBBE,"ஆ்"); //  3006 $ா TAMIL VOWEL SIGN AA
		mapCharacter.put((char)0xBBF,"இ்"); //	  3007 $ி TAMIL VOWEL SIGN I
		mapCharacter.put((char)0xBC0,"ஈ்"); //  3008 $ீ TAMIL VOWEL SIGN II
		mapCharacter.put((char)0xBC1,"உ்");//  3009 $ு TAMIL VOWEL SIGN U
		mapCharacter.put((char)0xBC2,"ஊ்");//  3010  ூ$ TAMIL VOWEL SIGN UU
		mapCharacter.put((char)0xBC6,"எ்");//  3014  ெ TAMIL VOWEL SIGN E
		mapCharacter.put((char)0xBC7,"ஏ்");//  3015  ே TAMIL VOWEL SIGN EE
		mapCharacter.put((char)0xBC8,"ஐ்");//  3016  ை TAMIL VOWEL SIGN AI
		mapCharacter.put((char)0xBCA,"ஒ்");//  3018 $ொ TAMIL VOWEL SIGN O
		mapCharacter.put((char)0xBCB,"ஓ்");//  3019 $ோ TAMIL VOWEL SIGN OO
		mapCharacter.put((char)0xB94,"ஔ");//  3019 $ோ TAMIL VOWEL SIGN OO
		
		mapCharacterForward.put(' '," ");
		mapCharacterForward.put((char)0xBCD,"்"); //  3006 $ா TAMIL VOWEL SIGN AA
		mapCharacterForward.put((char)0xBBE,"்ஆ"); //  3006 $ா TAMIL VOWEL SIGN AA
		mapCharacterForward.put((char)0xBBF,"்இ"); //	  3007 $ி TAMIL VOWEL SIGN I
		mapCharacterForward.put((char)0xBC0,"்ஈ"); //  3008 $ீ TAMIL VOWEL SIGN II
		mapCharacterForward.put((char)0xBC1,"்உ");//  3009 $ு TAMIL VOWEL SIGN U
		mapCharacterForward.put((char)0xBC2,"்ஊ");//  3010  ூ$ TAMIL VOWEL SIGN UU
		mapCharacterForward.put((char)0xBC6,"்எ");//  3014  ெ TAMIL VOWEL SIGN E
		mapCharacterForward.put((char)0xBC7,"்ஏ");//  3015  ே TAMIL VOWEL SIGN EE
		mapCharacterForward.put((char)0xBC8,"்ஐ");//  3016  ை TAMIL VOWEL SIGN AI
		mapCharacterForward.put((char)0xBCA,"்ஒ");//  3018 $ொ TAMIL VOWEL SIGN O
		mapCharacterForward.put((char)0xBCB,"்ஓ");//  3019 $ோ TAMIL VOWEL SIGN OO
		mapCharacterForward.put((char)0x0BCC,"்ஔ");//  2964 $ொ TAMIL VOWEL SIGN O
	
		mapMutpleSpecialCharacter.put("ோ","்ஓ");
		//mapMutpleSpecialCharacter.put("ோ","்ஒ");
		//mapMutpleSpecialCharacter.put("ோ","்ஔ");
		
		mapCharacter.put((char)0xBCD,"்");
		reverseMapCharacter.put((char)TamilConstants.அ,' ');
		reverseMapCharacter.put((char)TamilConstants.ஆ,(char)0xBBE); //  3006 $ா TAMIL VOWEL SIGN AA
		reverseMapCharacter.put((char)TamilConstants.இ,(char)0xBBF); //	  3007 $ி TAMIL VOWEL SIGN I
		reverseMapCharacter.put((char)TamilConstants.ஈ,(char)0xBC0); //  3008 $ீ TAMIL VOWEL SIGN II
		reverseMapCharacter.put((char)TamilConstants.உ,(char)0xBC1);//  3009 $ு TAMIL VOWEL SIGN U
		reverseMapCharacter.put((char)TamilConstants.ஊ,(char)0xBC2);//  3010  ூ$ TAMIL VOWEL SIGN UU
		reverseMapCharacter.put((char)TamilConstants.எ,(char)0xBC6);//  3014  ெ TAMIL VOWEL SIGN E
		reverseMapCharacter.put((char)TamilConstants.ஏ,(char)0xBC7);//  3015  ே TAMIL VOWEL SIGN EE
		reverseMapCharacter.put((char)TamilConstants.ஐ,(char)0xBC8);//  3016  ை TAMIL VOWEL SIGN AI
		reverseMapCharacter.put((char)TamilConstants.ஒ,(char)0xBCA);//  3018 $ொ TAMIL VOWEL SIGN O
		reverseMapCharacter.put((char)TamilConstants.ஓ,(char)0xBCB );//  3019 $ோ TAMIL VOWEL SIGN OO
		reverseMapCharacter.put((char)TamilConstants.ஔ,(char)0xB94 );//  2964 $கௌ TAMIL VOWEL SIGN OO
		//reverseMapCharacter.put((char)0xBCD,"்");
	}
	
	static Set<Character> supportCharacter=new HashSet<Character>();
	public static Map<Integer,Character> supportCharacterMap = new HashMap<Integer,Character>();
	static
	{
		supportCharacter.add((char)0xBBE); //  3006 $ா TAMIL VOWEL SIGN AA
		supportCharacter.add((char)0xBBF); //  3007 $ி TAMIL VOWEL SIGN I
		supportCharacter.add((char)0xBC0); //  3008 $ீ TAMIL VOWEL SIGN II
		supportCharacter.add((char)0xBC1); //  3009 $ு TAMIL VOWEL SIGN U
		supportCharacter.add((char)0xBC2); //  3010  ூ$ TAMIL VOWEL SIGN UU
		supportCharacter.add((char)0xBC6); //  3014  ெ TAMIL VOWEL SIGN E
		supportCharacter.add((char)0xBC7); //  3015  ே TAMIL VOWEL SIGN EE
		supportCharacter.add((char)0xBC8); //  3016  ை TAMIL VOWEL SIGN AI
		supportCharacter.add((char)0xBCA); //  3018 $ொ TAMIL VOWEL SIGN O
		supportCharacter.add((char)0xBCB); //  3019 $ோ TAMIL VOWEL SIGN OO
		supportCharacter.add((char)0xBCD); //  "்" 
		supportCharacterMap.put((char)0xBC6+(char)0xBBE,(char)0xBCA);
		supportCharacterMap.put((char)0xBC7+(char)0xBBE,(char)0xBCB);
		supportCharacterMap.put((char)0xBBE+(char)0xBCD,null);
	}
	
	public TamilUtil() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public static boolean isPulli(Character character)
	{
		return (TamilConstants.ஃ_EXT==character)?true:false;
	}
	
	public static boolean isPulli(int character)
	{
		return (TamilConstants.ஃ_EXT==character)?true:false;
	}
	
	public static boolean isExpectedType(Character character, int constantValue)
	{
		return (constantValue==(int)character)?true:false;
	}
	
	public static boolean isExpectedType(int givenValue, int constantValue)
	{
		return (constantValue==givenValue)?true:false;
	}
	
	public static boolean isExpectedType(int givenValue, Character constantValue)
	{
		return ((int)constantValue==givenValue)?true:false;
	}
	
	/**
	 * Find Dependent chars count and subtract with total length
	 * */
	public static long length(String text)
	{
		long count=text.codePoints().flatMap(dV -> IntStream.of(TamilConstants.dVowels).filter((i->i==dV))).count();
		return text.length()-count;
	}
	
 	
	public static boolean isNull(String word)
	{
		return word==null?true:false;
	}
	
	public static boolean அதுவில்முடிகிறதா(String word)
	{
		if(isNull(word))return false;
		if(word.length()<3) return false;
		int charValue=word.codePointAt(word.length()-3);
		long count=IntStream.of(TamilConstants.உயிர்மெய்யெழுத்து).filter((i->i==charValue)).count();
		if(count>0)
		{
			return (word.codePointAt(word.length()-2)==TamilConstants.த) &&
					(word.codePointAt(word.length()-1)==TamilConstants.உ_EXT);
		}
		return false; 
	}
	
	public static boolean கொடுத்தவில்முடிகிறதா1(int[] givenChars,String word)
	{
		//க,ய,ர,இ,அ,ஆ
		if(isNull(word))return false;
		if(word.length()<2) return false;
		 
		int charValue1=word.codePointAt(word.length()-1);
		int charValue0=word.codePointAt(word.length()-2);
		return (givenChars[0]==charValue0 && givenChars[1]==charValue1);
	}

	public static String கொடுத்தவில்முடிதால்பிரி1(int[] givenChars,String word)
	{
		//க,ய,ர,இ,அ,ஆ
		if(isNull(word))return word;
		if(word.length()<2) return word;

		int charValue1=word.codePointAt(word.length()-1);
		int charValue0=word.codePointAt(word.length()-2);
		if(givenChars[0]==charValue0 && givenChars[1]==charValue1)
		{
			return word.substring(0,word.length()-2);
		}
		return word;
	}

	public static boolean கொடுத்தவில்முடிகிறதா2(int[] givenChars,String word)
	{
		//ஆது,ஆல்,ஆன்,ஒடு,ஓடு
		if(isNull(word))return false;
		if(word.length()<3) return false;
		 
		int charValue=word.codePointAt(word.length()-3);
		if(givenChars[0]==charValue)
		{
			return (word.codePointAt(word.length()-2)==givenChars[1]) &&
					(word.codePointAt(word.length()-1)==givenChars[2]);
		}
		return false;  
	}
	
	public static String கொடுத்தவில்முடிதால்பிரி2(int[] givenChars,String word)
	{
		//ஆது,ஆல்,ஆன்,ஒடு,ஓடு
		if(isNull(word))return word;
		if(word.length()<3) return word;
		 
		if((word.codePointAt(word.length()-3)==givenChars[0]) &&
		   (word.codePointAt(word.length()-2)==givenChars[1]) &&
		   (word.codePointAt(word.length()-1)==givenChars[2]))
		{
			return word.substring(0,word.length()-3);
		}
		return word;  
	}

	public static boolean கொடுத்தவில்முடிகிறதா3(String givenStr,String word)
	{
		//நின்று 
		if(isNull(word))return false;
		if(word.length()<5) return false;
		 
		int charValue=word.codePointAt(word.length()-5);
		if(givenStr.charAt(0)==charValue)
		{
			if(word.lastIndexOf(givenStr)>0) return true;
		}
		return false;  
	}
	
	public static String கொடுத்தவில்முடிதால்பிரி3(String givenStr,String word)
	{
		//நின்று 
		if(isNull(word))return word;
		if(word.length()<5) return word;
		 
		int charValue=word.codePointAt(word.length()-5);
		if(givenStr.charAt(0)==charValue)
		{
			if(word.lastIndexOf(givenStr)>0) 
				return word.substring(0,word.length()-5);
		}
		return word;  
	}

	public static boolean கொடுத்தவில்முடிகிறதா4(String givenStr,String word)
	{
		//இருந்து 
		if(isNull(word))return false;
		if(word.length()<7) return false;
		 
		int charValue=word.codePointAt(word.length()-7);
		if(givenStr.charAt(0)==charValue)
		{
			if(word.lastIndexOf(givenStr)>0) return true;
		}
		return false;  
	}
	
	public static String கொடுத்தவில்முடிதால்பிரி4(String givenStr,String word)
	{
		//இருந்து 
		if(isNull(word))return word;
		if(word.length()<7) return word;
		 
		int charValue=word.codePointAt(word.length()-7);
		if(givenStr.charAt(0)==charValue)
		{
			if(word.lastIndexOf(givenStr)>0) 
				return word.substring(0,word.length()-7);
		}
		return word;  
	}

	public static boolean அவில்முடிகிறதா(String word)
	{
		if(isNull(word))return false;
		
		int charValue=word.codePointAt(word.length()-1);
		long count=IntStream.of(TamilConstants.உயிர்மெய்யெழுத்து).filter((i->i==charValue)).count();
		if(count==0)
			return false;
		return true; 
	}

	public static boolean அவில்முடிகிறதா(int[] charEnds,String word)
	{
		if(isNull(word))return false;
 
		if(!கொடுத்தவில்முடிகிறதா1(charEnds,word))
			return false;

		int charValue=word.codePointAt(word.length()-3);
		long count=IntStream.of(TamilConstants.உயிர்மெய்யெழுத்து).filter((i->i==charValue)).count();
		if(count==0)
			return false;
		
		return true; 
	}

	public static String அவில்முடிந்தால்பிரி(int[] charEnds,String word)
	{
		if(isNull(word))return word;
 
		if(!கொடுத்தவில்முடிகிறதா1(charEnds,word))
			return word;

		int charValue=word.codePointAt(word.length()-3);
		long count=IntStream.of(TamilConstants.உயிர்மெய்யெழுத்து).filter((i->i==charValue)).count();
		if(count==0)
			return word.substring(0,(word.length()-charEnds.length));
		
		return word; 
	}
	
	public static boolean கடையெழுத்துமகரயெழுத்தில்முடிகிறதா(String word)
	{
		String last2Chars= word.substring(word.length()-2,word.length());
		if("ம்".equals(last2Chars))
			return true;
		return false;
	}
	
	public static boolean கடையெழுத்துமெய்யெழுத்தில்முடிகிறதா(String word)
	{
		String last2Chars= word.substring(word.length()-2,word.length());
		
		for(String value:TamilConstants.கடைமெய்யெழுத்து)
		{
			if(value.equals(last2Chars))
				return true;
		}
		return false;
	}
	
	public static boolean முதல்லெழுத்துஉயிரெழுதா(String word)
	{
		char firstChar= (char)word.codePointAt(0);
		return IntStream.of(TamilConstants.உயிர்ரெழுத்தில்முடிகிறதா ).anyMatch((i->i==firstChar));
	}
	
	public static boolean உயிரெழுத்தா(char firstChar)
	{
		return IntStream.of(TamilConstants.முதல்லெழுத்து).anyMatch((i->i==firstChar));
	}
	
	public static boolean கடையெழுத்துஉயிரெழுதா(String word){
		char lastChar= (char)word.codePointAt(word.length()-1);
		return IntStream.of(TamilConstants.உயிர்ரெழுத்தில்முடிகிறதா).anyMatch((i->i==lastChar));
	}


	public static boolean கடையெழுத்துசரியா(String word)
	{
		char lastChar= (char)word.codePointAt(word.length()-1);
		return IntStream.of(TamilConstants.கடையெழுத்தில்முடியாது).anyMatch((i->i==lastChar));
	}
	
	public static String சந்தியைசேர்(String word1,String word2)
	{
		String word11 = எழுத்துகளைபிரி(word1);
		String word22 = எழுத்துகளைபிரி(word2);
		char lastChar= (char)word11.codePointAt(word11.length()-1);
		char firstChar= (char)word22.codePointAt(0);
		
		boolean checkLastChar=IntStream.of(TamilConstants.உயிர்ரெழுத்தில்முடிகிறதா).anyMatch((i->i==lastChar));
		boolean checkFirstChar=IntStream.of(TamilConstants.உயிர்ரெழுத்தில்முடிகிறதா).anyMatch((i->i==firstChar));
		if(checkLastChar && checkFirstChar)
		{
			if(IntStream.of(TamilConstants.யநிலைமொழியின்ஈறு).anyMatch((i->i==lastChar)))
			{
				return எழுத்துகளைசேர்(word11 +"ய்"+word22);
			}
			else if(IntStream.of(TamilConstants.வநிலைமொழியின்ஈறு).anyMatch((i->i==lastChar)))
			{
				return எழுத்துகளைசேர்(word11 +"வ்"+word22);
			}
			else 
			{
				return word1 +" "+word2;
			}
		}
		
		return word1 +" "+word2;
	}
	

	public static String[] சந்தியைபிரி(String word)
	{
		return new String[]{"",""};
	}
	
	public static String மெய்மயங்கிறதா(String word)
	{
		char[] letterArr=word.toCharArray();
		
		for(int i=0;i<letterArr.length;i++)
		{
			char letter1 =letterArr[i];
			char letter2 =0;
			char letter3 =0;
			char letter4 =0;
			if(i+1<letterArr.length)
			  letter2 =letterArr[i+1];
			if(i+2<letterArr.length)
			  letter3 =letterArr[i+2];
			if(i+3<letterArr.length)
			  letter4 =letterArr[i+3];
			
			if(letter2==(char)TamilConstants.ஃ_EXT && letter4==(char)TamilConstants.ஃ_EXT )
			{
				if(letter1==letter3)
					return "உடன்நிலைமெய்மயங்கிறது:"+letter1+""+letter2+""+letter3+""+letter4;
				else
					return "வேற்நிலைமெய்மயங்கிறது:"+letter1+""+letter2+""+letter3+""+letter4;
			}
		}
		return "";
	}
	
	public static String எழுத்துகளைசேர்(String word)
	{
		if(word==null)
			return word;
		
		char[] letterArr=word.toCharArray();
		List<String> sb =new ArrayList<String>(); 
		boolean checkLastChar=false;
		int i= 0;
		char prevLastChar=0;
		while(i<word.length())
		{
			i=i+1;
			char lastChar=letterArr[word.length()-i];
			i=i+1;
			if(i<=word.length())
				prevLastChar=letterArr[word.length()-i];
			else
				prevLastChar=0;
			checkLastChar=IntStream.of(TamilConstants.உயிர்ரெழுத்தில்முடிகிறதா).anyMatch((k->k==lastChar));
			if(lastChar==TamilConstants.ஃ_EXT){
				sb.add(prevLastChar+""+lastChar);
			}
			else if(checkLastChar && prevLastChar==TamilConstants.ஃ_EXT){
				i=i+1;
				sb.add((letterArr[word.length()-i]+""+reverseMapCharacter.get(lastChar)).trim());
				checkLastChar=false;
				//i=i-1;
			}else if(lastChar==' ') {
				sb.add(" ");
				sb.add(prevLastChar+"");
			}else{
				sb.add(lastChar+"");
			}
		}
//		if(i==word.length())
//			sb.add(letterArr[0]+"");
		
		StringBuilder sBuild =new StringBuilder();
		for(int k=sb.size()-1;k>=0;k--)
			sBuild.append(sb.get(k));
		return sBuild.toString();
	}

	public static boolean மெய்யெழுத்தில்முடிகிறதா(String word)
	{
		return Arrays.stream(TamilConstants.மெய்யெழுத்தில்முடிகிறதா).anyMatch((k->k.equals(word)));
	}

	public static void main(String[] args){
		String splitStr = எழுத்துகளைபிரி("அமரா்",false,false);
		System.out.println("அமரா்:"+splitStr+":"+எழுத்துகளைசேர்(splitStr));
		splitStr = எழுத்துகளைபிரி("முத்துக்கா்ப்பம்",false,false);
		System.out.println("முத்துக்கா்ப்பம்:"+splitStr+":"+எழுத்துகளைசேர்(splitStr));
		splitStr = எழுத்துகளைபிரி("அமா்",false,false);
		System.out.println("அமா்:"+splitStr+":"+எழுத்துகளைசேர்(splitStr));
		splitStr = எழுத்துகளைபிரி("யாருக்காக",false,false);
		System.out.println("யாருக்காக:"+splitStr+":"+எழுத்துகளைசேர்(splitStr));
		splitStr = எழுத்துகளைபிரி("பட்ர",false,false);
		System.out.println("பட்ர:"+splitStr+":"+எழுத்துகளைசேர்(splitStr));
	}
	
	public static String எழுத்துகளைபிரி(String word)
	{
		if(word.length()==1)
			return word;
		
		char[] letterArr=word.trim().toCharArray();
		
		StringBuilder charStr = new StringBuilder();
		for(int i=0;i<letterArr.length;i++)
		{
			char letter =letterArr[i];
			String value= mapCharacterForward.get(letter);
			String nextValue= null;
			String nextToNextValue= null;
			if(letterArr.length>i+1){
				nextValue = mapCharacterForward.get(letterArr[i+1]);
			  if(nextValue!=null && letterArr.length>i+2){
				    nextToNextValue = mapCharacterForward.get(letterArr[i+2]);
				  if(nextValue!=null && nextToNextValue!=null){
					  nextValue=mapMutpleSpecialCharacter.get(letterArr[i+1]+""+letterArr[i+2]);
					  i++;
				  }
			  }
			}
			if(value==null && nextValue==null)
			{
				if( i==0 && உயிரெழுத்தா(letter) ) //First letter is உயிரெலுத்தா
					charStr.append(letter);
				else
					charStr.append(letter+""+(char)TamilConstants.ஃ_EXT+""+(char)TamilConstants.அ);
			}
			else if(value==null && nextValue!=null)
			{
				charStr.append(letter+""+nextValue);
				i++;
			}
			else if(value!=null)
			{
				charStr.append(value);
			}
		}
		return charStr.toString();
	}
	
	public static String[] கடைஎழுத்து_த்த்_மற்றும்_அதுவில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_த்த்_மற்றும்_அதுவில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.த்த்தில்முடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.த்த்தில்முடிகிறதா),word.lastIndexOf(TamilConstants.அதுவில்முடிகிறதா)),
				word.substring(word.lastIndexOf(TamilConstants.அதுவில்முடிகிறதா))
				};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_த்த்_மற்றும்_அதுவில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.த்த்தில்முடிகிறதா+TamilConstants.அதுவில்முடிகிறதா))
			return true;
		return false;
	}

	public static String[] கடைஎழுத்து_த்த்_மற்றும்_இல்துவில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_த்த்_மற்றும்_இல்துவில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.த்த்தில்முடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.த்த்தில்முடிகிறதா),word.lastIndexOf(TamilConstants.இல்முடிகிறதா)),
				word.substring(word.lastIndexOf(TamilConstants.இல்முடிகிறதா))
				};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_த்த்_மற்றும்_இல்துவில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.த்த்தில்முடிகிறதா+TamilConstants.இல்முடிகிறதா))
			return true;
		return false;
	}

	public static String[] கடைஎழுத்து_ந்த்_மற்றும்_ஆல்லில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_ந்த்_மற்றும்_ஆல்லில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.ந்த்தில்முடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.ந்த்தில்முடிகிறதா),word.lastIndexOf(TamilConstants.ஆல்லில்முடிகிறதா)),
				word.substring(word.lastIndexOf(TamilConstants.ஆல்லில்முடிகிறதா))
				};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_ந்த்_மற்றும்_ஆல்லில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.ந்த்தில்முடிகிறதா+TamilConstants.ஆல்லில்முடிகிறதா))
			return true;
		return false;
	}
	
	public static String[] கடைஎழுத்து_த்த்_மற்றும்_உவில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_த்த்_மற்றும்_உவில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.த்த்தில்முடிகிறதா)), 
				word.substring(word.lastIndexOf(TamilConstants.த்த்தில்முடிகிறதா),word.lastIndexOf(TamilConstants.உவில்முடிகிறதா)),
				word.substring(word.lastIndexOf(TamilConstants.உவில்முடிகிறதா))
				};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_த்த்_மற்றும்_உவில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.த்த்தில்முடிகிறதா+TamilConstants.உவில்முடிகிறதா ))
			return true;
		return false;
	}
	
	public static String[] கடைஎழுத்து_த்த்_மற்றும்_இல்லில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_த்த்_மற்றும்_உவில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.த்த்தில்முடிகிறதா)), 
				word.substring(word.lastIndexOf(TamilConstants.த்த்தில்முடிகிறதா),word.lastIndexOf(TamilConstants.இல்முடிகிறதா)),
				word.substring(word.lastIndexOf(TamilConstants.இல்முடிகிறதா))
				};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_த்த்_மற்றும்_இல்லில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.த்த்தில்முடிகிறதா+TamilConstants.இல்முடிகிறதா ))
			return true;
		return false;
	}
	
	public static String[] கடைஎழுத்து_ற்_மற்றும்_அதுவில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_ற்_மற்றும்_அதுவில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.ற்வில்முடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.ற்வில்முடிகிறதா),word.lastIndexOf(TamilConstants.அதுவில்முடிகிறதா)),
				word.substring(word.lastIndexOf(TamilConstants.அதுவில்முடிகிறதா))};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_ற்_மற்றும்_அதுவில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.ற்வில்முடிகிறதா+TamilConstants.அதுவில்முடிகிறதா))
			return true;
		return false;
	}

	public static String[] கடைஎழுத்து_1_மற்றும்_2ல்_முடிந்தால்பிரி(String word, String first,String second)
	{
		if(கடைஎழுத்து_1_மற்றும்_2ல்_முடிகிறதா(word,first,second))
		{
			if(word.lastIndexOf(first) < word.lastIndexOf(second))
				return new String[]{word.substring(0, word.lastIndexOf(first)) , 
					word.substring(word.lastIndexOf(first),word.lastIndexOf(second)),
					word.substring(word.lastIndexOf(second))};
			return new String[]{word,"",""};
		}
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_1_மற்றும்_2ல்_முடிகிறதா(String word, String first, String second)
	{
		if(word.endsWith(first+second))
			return true;
		return false;
	}

	public static String[] கடைஎழுத்து_1_மற்றும்_2_மற்றும்_3ல்_முடிந்தால்பிரி(String word, String first,String second, String third)
	{
		if(கடைஎழுத்து_1_மற்றும்_2_மற்றும்_3ல்_முடிகிறதா(word,first,second,third))
		{
			if(word.lastIndexOf(first) < word.lastIndexOf(second) &&
					word.lastIndexOf(second)< word.lastIndexOf(third) )
				return new String[]{word.substring(0, word.lastIndexOf(first)) , 
					word.substring(word.lastIndexOf(first),word.lastIndexOf(second)),
					word.substring(word.lastIndexOf(second),word.lastIndexOf(third)),
					word.substring(word.lastIndexOf(third))};
			return new String[]{word,"","",""};
		}
		return new String[]{word,"","",""};
	}
	
	public static boolean கடைஎழுத்து_கொடுக்கபட்வையில்_முடிகிறதா(String word, List<String> deeperInnerList)
	{
		StringBuilder sb =new StringBuilder();
		for(String inner :deeperInnerList)
		{
			sb.append(TamilUtil.எழுத்துகளைபிரி(inner,false,false));
		}
		if(word.endsWith(sb.toString()))
			return true;
		return false;
	}
	
	public static String[] கடைஎழுத்து_கொடுக்கபட்வையில்_முடிந்தால்பிரி(String word, List<String> deeperInnerList)
	{
		String strArray[]=new String[deeperInnerList.size()+1];
		strArray[0]= word;
		int index[]=new int[2];
		index[0]=0;index[1]=0;
		for(int count=1;count<=deeperInnerList.size();count++)
			strArray[count]="";

		if(கடைஎழுத்து_கொடுக்கபட்வையில்_முடிகிறதா(word,deeperInnerList))
		{
			for(int count=0;count<=deeperInnerList.size();count++)
			{
				if (count+1==deeperInnerList.size())
					strArray[count]=word.substring(word.lastIndexOf(deeperInnerList.get(count-1)),word.lastIndexOf(deeperInnerList.get(count)));
				else if (count==deeperInnerList.size())
					strArray[count]=word.substring(word.lastIndexOf(deeperInnerList.get(count-1)));
				else if(indexMatch(word,deeperInnerList.get(count),deeperInnerList.get(count+1),index) )
				{	
					if(count==0)
						strArray[count]=word.substring(0, index[0]);//word.lastIndexOf(deeperInnerList.get(count)));
					else
					{
						indexMatch(word,deeperInnerList.get(count-1),deeperInnerList.get(count),index);
						strArray[count]=word.substring(index[0],index[1]);
					}
				}
				//System.out.println(strArray[count]);
			}
			return strArray;	
		}
		return strArray;
	}
	
	public static boolean indexMatch(String word,String first, String second, int index[])
	{
		int firstLast=word.lastIndexOf(first);
		int secondLast=word.lastIndexOf(second);
		 
		while(firstLast>0)
		{
			if(firstLast < secondLast) 
			{
				index[0]=firstLast;
				index[1]=secondLast;
				return true;
			}
			else
				firstLast=word.lastIndexOf(first,firstLast-1);
		}
		index[0]=0;
		index[1]=0;
		return false;
	}
	
	public static boolean கடைஎழுத்து_1_மற்றும்_2_மற்றும்_3_மற்றும்_4ல்_முடிகிறதா(String word, String first, String second,String third,String fourth)
	{
		if(word.endsWith(first+second+third+fourth))
			return true;
		return false;
	}

	public static String[] கடைஎழுத்து_1_மற்றும்_2_மற்றும்_3_மற்றும்_4ல்_முடிந்தால்பிரி(String word, String first,String second, String third,String fourth)
	{
		if(கடைஎழுத்து_1_மற்றும்_2_மற்றும்_3_மற்றும்_4ல்_முடிகிறதா(word,first,second,third,fourth))
		{
			if(word.lastIndexOf(first) < word.lastIndexOf(second) &&
					word.lastIndexOf(second)< word.lastIndexOf(third) &&
					word.lastIndexOf(third)< word.lastIndexOf(fourth) )
			return new String[]{word.substring(0, word.lastIndexOf(first)) , 
				word.substring(word.lastIndexOf(first),word.lastIndexOf(second)),
				word.substring(word.lastIndexOf(second),word.lastIndexOf(third)),
				word.substring(word.lastIndexOf(third),word.lastIndexOf(fourth)),
				word.substring(word.lastIndexOf(fourth))};
			return new String[]{word,"","","",""};	
		}
		return new String[]{word,"","","",""};
	}
	
	public static boolean கடைஎழுத்து_1_மற்றும்_2_மற்றும்_3ல்_முடிகிறதா(String word, String first, String second,String third)
	{
		if(word.endsWith(first+second+third))
			return true;
		return false;
	}

	public static String[] கடைஎழுத்து_ஆக்இற்_மற்றும்_அதுவில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_ஆக்இற்_மற்றும்_அதுவில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.ஆக்இற்வில்முடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.ஆக்இற்வில்முடிகிறதா),word.lastIndexOf(TamilConstants.அதுவில்முடிகிறதா)),
				word.substring(word.lastIndexOf(TamilConstants.அதுவில்முடிகிறதா))};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_ஆக்இற்_மற்றும்_அதுவில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.ஆக்இற்வில்முடிகிறதா+TamilConstants.அதுவில்முடிகிறதா))
			return true;
		return false;
	}
	
	public static String[] கடைஎழுத்து_ய்_மற்றும்_இல்வில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_ய்_மற்றும்_இல்வில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.ய்லில்முடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.ய்லில்முடிகிறதா),word.lastIndexOf(TamilConstants.இல்முடிகிறதா)),
				word.substring(word.lastIndexOf(TamilConstants.இல்முடிகிறதா))};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_ய்_மற்றும்_இல்வில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.ய்லில்முடிகிறதா+TamilConstants.இல்முடிகிறதா))
			return true;
		return false;
	}
	
	public static String[] கடைஎழுத்து_அன்_மற்றும்_அர்ரில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_அன்_மற்றும்_அர்ரில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.அன்வில்முடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.அன்வில்முடிகிறதா),word.lastIndexOf(TamilConstants.அர்ரில்முடிகிறதா)),
				word.substring(word.lastIndexOf(TamilConstants.அர்ரில்முடிகிறதா))};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_அன்_மற்றும்_அர்ரில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.அன்வில்முடிகிறதா+TamilConstants.அர்ரில்முடிகிறதா))
			return true;
		return false;
	}

	public static String[] கடைஎழுத்து_அ_மற்றும்_இறந்தகாலஉறுபுபில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_அ_மற்றும்_இறந்தகாலஉறுபுபில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.length()-5) , 
				word.substring(word.length()-5,word.length()-1),
				word.substring(word.length()-1)
				};
		return new String[]{word,"",""};
	}
	
	public static String[] கடைஎழுத்து_இன்லில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_இன்லில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.இன்லில்முடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.இன்லில்முடிகிறதா)),
				""};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_இன்லில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.இன்லில்முடிகிறதா))
			return true;
		return false;
	}
	public static String[] கடைஎழுத்து_ஆல்லில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_ஆல்லில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.ஆல்லில்முடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.ஆல்லில்முடிகிறதா)),
				""};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_ஆல்லில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.ஆல்லில்முடிகிறதா))
			return true;
		return false;
	}
	
	public static String[] கடைஎழுத்து_ஒல்லில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_ஒல்லில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.ஒல்இலில்முடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.ஒல்இலில்முடிகிறதா)),
				""};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_ஒல்லில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.ஒல்இலில்முடிகிறதா))
			return true;
		return false;
	}

	public static String[] கடைஎழுத்து_இய்_மற்றும்_இன்வில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_த்த்_மற்றும்_அதுவில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.இய்லில்முடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.இய்லில்முடிகிறதா),word.lastIndexOf(TamilConstants.இன்லில்முடிகிறதா)),
				word.substring(word.lastIndexOf(TamilConstants.இன்லில்முடிகிறதா))
				};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_இய்_மற்றும்_இன்வில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.இய்லில்முடிகிறதா+TamilConstants.இன்லில்முடிகிறதா))
			return true;
		return false;
	}

	//இய்லில்முடிகிறதா
	//இன்லில்முடிகிறதா
	public static String[] கடைஎழுத்து_வ்இலில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_வ்இலில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.வ்இலில்முடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.வ்),word.lastIndexOf(TamilConstants.இவில்முடிகிறதா)),
				word.substring(word.lastIndexOf(TamilConstants.இவில்முடிகிறதா))};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_வ்இலில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.வ்இலில்முடிகிறதா))
			return true;
		return false;
	}
	public static String[] கடைஎழுத்து_க்இலில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_க்இலில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.க்இலில்முடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.க்),word.lastIndexOf(TamilConstants.இவில்முடிகிறதா)),
				word.substring(word.lastIndexOf(TamilConstants.இவில்முடிகிறதா))};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_க்இலில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.க்இலில்முடிகிறதா))
			return true;
		return false;
	}
	
	public static String[] கடைஎழுத்து_கள்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_கள்லில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.க்அள்லில்முடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.க்அள்லில்முடிகிறதா)),
				""};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_கள்லில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.க்அள்லில்முடிகிறதா))
			return true;
		return false;
	}

	public static String[] கடைஎழுத்து_உல்லில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_உல்லில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.உல்லில்முடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.உல்லில்முடிகிறதா)),
				""};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_உல்லில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.உல்லில்முடிகிறதா))
			return true;
		return false;
	}
	
	public static String[] கடைஎழுத்து_அல்லில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_அல்லில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.அல்லில்முடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.அல்லில்முடிகிறதா)),
				""};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_அல்லில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.அல்லில்முடிகிறதா))
			return true;
		return false;
	}
	
	public static String[] கடைஎழுத்து_த்ஆன்லில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_த்ஆன்லில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.த்ஆன்முடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.த்ஆன்முடிகிறதா)),
				""};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_த்ஆன்லில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.த்ஆன்முடிகிறதா))
			return true;
		return false;
	}
	
	public static String[] கடைஎழுத்து_இர்உந்த்உலில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_இர்உந்த்உலில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.இர்உந்த்உமுடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.இர்உந்த்உமுடிகிறதா)),
				""};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_இர்உந்த்உலில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.இர்உந்த்உமுடிகிறதா))
			return true;
		return false;
	}

	public static String[] கடைஎழுத்து_உம்மில்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_உம்லில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.lastIndexOf(TamilConstants.உம்மில்முடிகிறதா)) , 
				word.substring(word.lastIndexOf(TamilConstants.உம்மில்முடிகிறதா)),
				""};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_உம்லில்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.உம்மில்முடிகிறதா))
			return true;
		return false;
	}

	public static String[] கடைஎழுத்து_1ல்_முடிந்தால்பிரி(String word,String first)
	{
		if(கடைஎழுத்து_1ல்_முடிகிறதா(word,first))
			return new String[]{word.substring(0, word.lastIndexOf(first)) , 
				word.substring(word.lastIndexOf(first)),
				""};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_1ல்_முடிகிறதா(String word,String first)
	{
		if(word.endsWith(first))
			return true;
		return false;
	}

	public static String[] கடைஎழுத்து_என்ற்உல்_முடிந்தால்பிரி(String word)
	{
		if(கடைஎழுத்து_என்ற்உல்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.length()-6) , word.substring(word.length()-6),""};
		return new String[]{word,"",""};
	}
	
	public static boolean கடைஎழுத்து_என்ற்உல்_முடிகிறதா(String word)
	{
		if(word.endsWith(TamilConstants.என்ற்உல்முடிகிறதா))
			return true;
		return false;
	}

	public static String[] ஒற்றில்_முடிந்தால்பிரி(String word)
	{
		if(ஒற்றில்_முடிகிறதா(word))
			return new String[]{word.substring(0, word.length()-2) , 
				word.substring(word.length()-2),
				""};
		return new String[]{word,"",""};
	}
	
	public static boolean ஒற்றில்_முடிகிறதா(String word)
	{
		long count = TamilConstants.ஒற்று_மெய்யெழுத்துமுடிகிறதா.stream()
				.filter(item -> item.equals(word.substring(word.length()-2))).count();
		return count==0?false:true;
	}
	
	public static boolean வல்லினம்_மெய்யெழுத்துமுடிகிறதா(String word)
	{
		long count =0;
		try
		{
			if(word.length()>1)
				count = TamilConstants.வல்லினம்_மெய்யெழுத்துமுடிகிறதா.stream()
					.filter(item -> item.equals(word.substring(word.length()-2))).count();
		}catch(Exception exp)
		{
			System.out.println(word);
			exp.printStackTrace();
		}
		return count==0?false:true;
	}
	
	public static boolean existInCheckList(String word,String[] checkList)
	{
		return existInCheckList(word,Arrays.asList(checkList));
	}
	
	private static Map<String,String> mapஎழுத்தின்மெய்வல்லினம்இணை = new HashMap<String,String>();
	
	public static String எழுத்தின்மெய்யாடுவல்லினம்இணை(String firstLetter){
		String value = mapஎழுத்தின்மெய்வல்லினம்இணை.get(firstLetter);
		if(value!=null)
			return value;
		
		if(existInCheckList(firstLetter,TamilConstants.ககரவரிசை)){
			mapஎழுத்தின்மெய்வல்லினம்இணை.put(firstLetter,"க்");
		}else if(existInCheckList(firstLetter,TamilConstants.சகரவரிசை)){
			mapஎழுத்தின்மெய்வல்லினம்இணை.put(firstLetter,"ச்");
		}else if(existInCheckList(firstLetter,TamilConstants.டகரவரிசை)){
			mapஎழுத்தின்மெய்வல்லினம்இணை.put(firstLetter,"ட்");
		}else if(existInCheckList(firstLetter,TamilConstants.தகரவரிசை)){
			mapஎழுத்தின்மெய்வல்லினம்இணை.put(firstLetter,"ந்");
		}else if(existInCheckList(firstLetter,TamilConstants.பகரவரிசை)){
			mapஎழுத்தின்மெய்வல்லினம்இணை.put(firstLetter,"ப்");
		}else if(existInCheckList(firstLetter,TamilConstants.றகரவரிசை)){
			mapஎழுத்தின்மெய்வல்லினம்இணை.put(firstLetter,"ற்");
		}else if(existInCheckList(firstLetter,TamilConstants.யகரவரிசை)){
			mapஎழுத்தின்மெய்வல்லினம்இணை.put(firstLetter,"ய்");
		}else if(existInCheckList(firstLetter,TamilConstants.ரகரவரிசை)){
			mapஎழுத்தின்மெய்வல்லினம்இணை.put(firstLetter,"ர்");
		}else if(existInCheckList(firstLetter,TamilConstants.லகரவரிசை)){
			mapஎழுத்தின்மெய்வல்லினம்இணை.put(firstLetter,"ற்");
		}else if(existInCheckList(firstLetter,TamilConstants.வகரவரிசை)){
			mapஎழுத்தின்மெய்வல்லினம்இணை.put(firstLetter,"வ்");
		}else if(existInCheckList(firstLetter,TamilConstants.ழகரவரிசை)){
			mapஎழுத்தின்மெய்வல்லினம்இணை.put(firstLetter,"ழ்");
		}else if(existInCheckList(firstLetter,TamilConstants.ளகரவரிசை)){
			mapஎழுத்தின்மெய்வல்லினம்இணை.put(firstLetter,"ட்");
		}else if(existInCheckList(firstLetter,TamilConstants.ஙகரவரிசை)){
			mapஎழுத்தின்மெய்வல்லினம்இணை.put(firstLetter,"ங்");
		}else if(existInCheckList(firstLetter,TamilConstants.ஞகரவரிசை)){
			mapஎழுத்தின்மெய்வல்லினம்இணை.put(firstLetter,"ஞ்");
		}else if(existInCheckList(firstLetter,TamilConstants.ணகரவரிசை)){
			mapஎழுத்தின்மெய்வல்லினம்இணை.put(firstLetter,"ட்");
		}else if(existInCheckList(firstLetter,TamilConstants.நகரவரிசை)){
			mapஎழுத்தின்மெய்வல்லினம்இணை.put(firstLetter,"ந்");
		}else if(existInCheckList(firstLetter,TamilConstants.மதகரவரிசை)){
			mapஎழுத்தின்மெய்வல்லினம்இணை.put(firstLetter,"ம்");
		}else if(existInCheckList(firstLetter,TamilConstants.னகரவரிசை)){
			mapஎழுத்தின்மெய்வல்லினம்இணை.put(firstLetter,"ற்");
		}
		return mapஎழுத்தின்மெய்வல்லினம்இணை.get(firstLetter);
	}
	
	private static Map<String,String> mapஎழுத்தின்மெய்மெல்லினம்இணை = new HashMap<String,String>();
	
	public static String எழுத்தின்மெய்யாடுமெல்லினம்இணை(String firstLetter){
		String value = mapஎழுத்தின்மெய்மெல்லினம்இணை.get(firstLetter);
		if(value!=null)
			return value;
		
		if(existInCheckList(firstLetter,TamilConstants.ககரவரிசை)){
			mapஎழுத்தின்மெய்மெல்லினம்இணை.put(firstLetter,"க்");
		}else if(existInCheckList(firstLetter,TamilConstants.சகரவரிசை)){
			mapஎழுத்தின்மெய்மெல்லினம்இணை.put(firstLetter,"ச்");
		}else if(existInCheckList(firstLetter,TamilConstants.டகரவரிசை)){
			mapஎழுத்தின்மெய்மெல்லினம்இணை.put(firstLetter,"ட்");
		}else if(existInCheckList(firstLetter,TamilConstants.தகரவரிசை)){
			mapஎழுத்தின்மெய்மெல்லினம்இணை.put(firstLetter,"ந்");
		}else if(existInCheckList(firstLetter,TamilConstants.பகரவரிசை)){
			mapஎழுத்தின்மெய்மெல்லினம்இணை.put(firstLetter,"ப்");
		}else if(existInCheckList(firstLetter,TamilConstants.றகரவரிசை)){
			mapஎழுத்தின்மெய்மெல்லினம்இணை.put(firstLetter,"ற்");
		}else if(existInCheckList(firstLetter,TamilConstants.யகரவரிசை)){
			mapஎழுத்தின்மெய்மெல்லினம்இணை.put(firstLetter,"ய்");
		}else if(existInCheckList(firstLetter,TamilConstants.ரகரவரிசை)){
			mapஎழுத்தின்மெய்மெல்லினம்இணை.put(firstLetter,"ர்");
		}else if(existInCheckList(firstLetter,TamilConstants.லகரவரிசை)){
			mapஎழுத்தின்மெய்மெல்லினம்இணை.put(firstLetter,"ன்");
		}else if(existInCheckList(firstLetter,TamilConstants.வகரவரிசை)){
			mapஎழுத்தின்மெய்மெல்லினம்இணை.put(firstLetter,"வ்");
		}else if(existInCheckList(firstLetter,TamilConstants.ழகரவரிசை)){
			mapஎழுத்தின்மெய்மெல்லினம்இணை.put(firstLetter,"ழ்");
		}else if(existInCheckList(firstLetter,TamilConstants.ளகரவரிசை)){
			mapஎழுத்தின்மெய்மெல்லினம்இணை.put(firstLetter,"ன்");
		}else if(existInCheckList(firstLetter,TamilConstants.ஙகரவரிசை)){
			mapஎழுத்தின்மெய்மெல்லினம்இணை.put(firstLetter,"ங்");
		}else if(existInCheckList(firstLetter,TamilConstants.ஞகரவரிசை)){
			mapஎழுத்தின்மெய்மெல்லினம்இணை.put(firstLetter,"ஞ்");
		}else if(existInCheckList(firstLetter,TamilConstants.ணகரவரிசை)){
			mapஎழுத்தின்மெய்மெல்லினம்இணை.put(firstLetter,"ட்");
		}else if(existInCheckList(firstLetter,TamilConstants.நகரவரிசை)){
			mapஎழுத்தின்மெய்மெல்லினம்இணை.put(firstLetter,"ந்");
		}else if(existInCheckList(firstLetter,TamilConstants.மதகரவரிசை)){
			mapஎழுத்தின்மெய்மெல்லினம்இணை.put(firstLetter,"ம்");
		}else if(existInCheckList(firstLetter,TamilConstants.னகரவரிசை)){
			mapஎழுத்தின்மெய்மெல்லினம்இணை.put(firstLetter,"ற்");
		}
		return mapஎழுத்தின்மெய்மெல்லினம்இணை.get(firstLetter);
	}

	
	public static boolean existInCheckList(String word,List<String> checkList)
	{
		long count =0;
		try
		{
				count = checkList.stream()
					.filter(item -> item.equals(word)).count();
		}catch(Exception exp)
		{
			System.out.println(word);
			exp.printStackTrace();
		}
		return count==0?false:true;
	}
	
	public static boolean existInCheckListOLD(String word,List<String> checkList)
	{
		long count =0;
		try
		{
			if(word.length() > 1)
				count = checkList.stream()
					.filter(item -> item.equals(word.substring(word.length()-2))).count();
		}catch(Exception exp)
		{
			System.out.println(word);
			exp.printStackTrace();
		}
		return count==0?false:true;
	}
	
	public static boolean கடைஎழுத்து_அ_மற்றும்_இறந்தகாலஉறுபுபில்_முடிகிறதா(String word)
	{
		if(word.length()<6)return false;
		
		char[] letterArr=word.trim().toCharArray();
		if(letterArr[letterArr.length-1]==TamilConstants.அ )
		{
			if(
					(String.valueOf(letterArr[letterArr.length-3]+""+letterArr[letterArr.length-2]).equals(TamilConstants.த்) 
					&& String.valueOf(letterArr[letterArr.length-5]+""+letterArr[letterArr.length-4]).equals(TamilConstants.ந்))
					||
					(String.valueOf(letterArr[letterArr.length-3]+""+letterArr[letterArr.length-2]).equals(TamilConstants.த்) 
					&& String.valueOf(letterArr[letterArr.length-5]+""+letterArr[letterArr.length-4]).equals(TamilConstants.த்))
			 )
				return true;
		}
		return false;
	}
	
	
	public static String toUnicode(char ch) {
	    return String.format("\\u%04x", (int) ch);
	}
	
	public static String எழுத்துகளைபிரி(String word,boolean needBrackets,boolean needDelimitor)
	{
		if(word.contains("VERB") || word.contains("NOUN"))
			return word;

		word = word.replace("\ufeff", "");
		char[] letterArr=word.trim().toCharArray();
		
		Stack<Character> stack =new Stack<>();
		char ch1 = 0;
		char ch2 = 0;
		for(int i=0;i<letterArr.length;i++) {
			ch1 = letterArr[i];
			if(i+1<letterArr.length)
				ch2 = letterArr[i+1];
			else
				ch2=0;
			if(supportCharacter.contains(ch1) && supportCharacter.contains(ch2)){
				if(supportCharacterMap.get(ch1+ch2)==null) {
					stack.add('ர');
					stack.add(ch2);
				}else
					stack.add(supportCharacterMap.get(ch1+ch2));
				i=i+1;
			}else {
				stack.add(ch1);
			}
		}
		
		StringBuilder sb =new StringBuilder();
		boolean extension=false;

		char letter=0;
		while(!stack.empty())
		{
			letter=stack.pop();
			String value= mapCharacter.get(letter);
			if(value==null)
			{
				if(extension)
				{
					sb.append(letter);
					if(needBrackets)
						sb.append("(");
					extension=false;
				}
				else
				{
					if(needDelimitor)
						if(sb.length()>0)
							sb.append("+");
					if(needBrackets)
						sb.append(")");
					sb.append((char)TamilConstants.அ+ (needDelimitor?"+":"")+(char)TamilConstants.ஃ_EXT+""+letter);
					if(needBrackets)
						sb.append("(");
				}
			}
			else
			{
				if(needDelimitor)
				{
					value=value.replace("+", "");
					if(sb.length()>0)
						sb.append("+");
				}
				if(needBrackets)
					sb.append(")");
				sb.append(value);
				extension =true;
			}
		}
		//System.out.println(word+":"+sb.reverse());
		return sb.reverse().toString();
	}
	
	public static String பகுதிகாட்டு(String word)
	{
		return "";
	}
	
	public static String எண்கள்காட்டு(String word)//Singular/Prural
	{
		return "";
	}
	
	public static String படர்க்கைகாட்டு(String word)//place-First,Second,Third Person -I,he,She,They
	{
		return "";
	}
	
	public static String காலம்காட்டு(String word)//present, future,
	{
		return "";
	}
	
	public static String பால்காட்டு(String word)
	{
		if(கொடுத்தவில்முடிகிறதா2(TamilConstants.ஆன்வில்முடிகிறதா,word)||அவில்முடிகிறதா(new int[]{TamilConstants.ன,TamilConstants.ஃ_EXT},word))
			return "ஆண்பால்";
		else if(கொடுத்தவில்முடிகிறதா2(TamilConstants.ஆள்வில்முடிகிறதா,word))
			return "பெண்பால்";
		else if(கொடுத்தவில்முடிகிறதா2(TamilConstants.ஆர்ரில்முடிகிறதா,word)||கொடுத்தவில்முடிகிறதா2(TamilConstants.மார்ரில்முடிகிறதா,word)
				||கொடுத்தவில்முடிகிறதா1(TamilConstants.யவில்முடிகிறதா,word))
			return "பலர்பால்";
		//ஒன்றுபால்
		else if(கொடுத்தவில்முடிகிறதா1(TamilConstants.ஆவில்முடிகிறதா,word) || அவில்முடிகிறதா(word))
				return "பலவின்பால்";
		return "";
	
	}
	
	public static void mainOld(String args[])
	{
	//			System.out.println(அவில்முடிகிறதா(new int[]{TamilConstants.ன,TamilConstants.ஃ_EXT},"குடையன்"));
//		System.out.println(கொடுத்தவில்முடிகிறதா1(new int[]{TamilConstants.ய}, "வாழிய"));
//		System.out.println(கொடுத்தவில்முடிகிறதா1(new int[]{TamilConstants.இ_EXT}, "படுத்தி"));
		//System.out.println(கொடுத்தவில்முடிதால்பிரி2(TamilConstants.ஆல்வில்முடிகிறதா,"அரிவாளால்"));
//		//எழுத்துகளைபிரி("டாக்டர்",true,true);
		//System.out.println("நடந்தனன்:"+பால்காட்டு("நடந்தனன்"));
//		System.out.println(TamilUtil.நான்காம்வேற்றுமையா("பொன்னுக்குப்"));
//		System.out.println(TamilUtil.நான்காம்வேற்றுமையா("பொன்னுக்கு"));
		System.out.println(கடையெழுத்துமெய்யெழுத்தில்முடிகிறதா("பால்"));
//
		System.out.println("	புலியாகிறது	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	புலியாகிறது	"));
		System.out.println("	ஒரு 	:"+	TamilIllakanam.வேற்றுமையைபிரி("	ஒரு 	"));
		System.out.println("	பூனை	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	பூனை	"));
		System.out.println("	புலியாகிறது	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	புலியாகிறது	"));
		System.out.println("	டாக்டர்	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	டாக்டர்	"));
		System.out.println("	பூவண்ணன்	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	பூவண்ணன்	"));
		System.out.println("	காலைப்	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	காலைப்	"));
		System.out.println("	பிடித்த	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	பிடித்த	"));
		System.out.println("	கை	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	கை	"));
		System.out.println("	தடதடவென்று	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	தடதடவென்று	"));
		System.out.println("	மாணவர்கள்	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	மாணவர்கள்	"));
		System.out.println("	மகிழ்ச்சியுடன்	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	மகிழ்ச்சியுடன்	"));
		System.out.println("	கைதட்டிய	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	கைதட்டிய	"));
		System.out.println("	ஒலி	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	ஒலி	"));
		System.out.println("	இடிமுழக்கம்	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	இடிமுழக்கம்	"));
		System.out.println("	போல	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	போல	"));
		System.out.println("	எங்கும்	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	எங்கும்	"));
		System.out.println("	பரவி	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	பரவி	"));
		System.out.println("	எதிரொலித்தது	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	எதிரொலித்தது	"));
		System.out.println("	சம்பிரதாயத்துக்காகக்	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	சம்பிரதாயத்துக்காகக்	"));
		System.out.println("	கைதட்டுவதாக	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	கைதட்டுவதாக	"));
		System.out.println("	இருந்தால்	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	இருந்தால்	"));
		System.out.println("	போய்	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	போய்	"));
		System.out.println("	அமர்ந்த	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	அமர்ந்த	"));
		System.out.println("	பிறகும்	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	பிறகும்	"));
		System.out.println("	கைதட்டல்	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	கைதட்டல்	"));
		System.out.println("	நீடித்தது	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	நீடித்தது	"));
		System.out.println("	இலக்கிய	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	இலக்கிய	"));
		System.out.println("	மன்றத்துச்	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	மன்றத்துச்	"));
		System.out.println("	செயலாளன்	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	செயலாளன்	"));
		System.out.println("	நன்றி	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	நன்றி	"));
		System.out.println("	கூற	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	கூற	"));
		System.out.println("	வந்த	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	வந்த	"));
		System.out.println("	பிறகுதான்	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	பிறகுதான்	"));
		System.out.println("	கைதட்டல்	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	கைதட்டல்	"));
		System.out.println("	நின்றது	:"+	TamilIllakanam.வேற்றுமையைபிரி	("	நின்றது	"));


//		System.out.println("புலமையை"+":"+இரண்டாம்வேற்றுமையைபிரி("புலமையை"));
//		System.out.println("புகழை"+":"+இரண்டாம்வேற்றுமையைபிரி("புகழை"));
//		System.out.println("பாடலை"+":"+இரண்டாம்வேற்றுமையைபிரி("பாடலை"));                        
//		System.out.println("இரயிலை"+":"+இரண்டாம்வேற்றுமையைபிரி("இரயிலை"));                        
//		System.out.println("சாப்பாட்டு"+":"+இரண்டாம்வேற்றுமையைபிரி("சாப்பாட்டு"));                        
//		System.out.println("சாப்பாட்டை"+":"+இரண்டாம்வேற்றுமையைபிரி("சாப்பாட்டை"));                        
//		System.out.println("மனைவியை"+":"+இரண்டாம்வேற்றுமையைபிரி("மனைவியை"));                        
//		System.out.println("மாநிலத்தை"+":"+இரண்டாம்வேற்றுமையைபிரி("மாநிலத்தை"));
//		System.out.println("கண்ணை"+":"+இரண்டாம்வேற்றுமையைபிரி("கண்ணை"));
//		System.out.println("வழக்கை"+":"+இரண்டாம்வேற்றுமையைபிரி("வழக்கை"));
//		System.out.println(மூன்றாம்வேற்றுமையா("அரிவாளால்"));
//		System.out.println(மூன்றாம்வேற்றுமையா("அறத்தான்"));
//		System.out.println(மூன்றாம்வேற்றுமையா("வள்ளியோடு"));
//		System.out.println(மூன்றாம்வேற்றுமையா("தாயொடு"));
//		System.out.println(நான்காம்வேற்றுமையா("பொன்னுக்குப்"));
//		System.out.println(நான்காம்வேற்றுமையா("பொன்னுக்கு"));
//		System.out.println(ஐந்தாம்வேற்றுமையா("மலையின்"));
//		System.out.println(ஐந்தாம்வேற்றுமையா("பாலின்"));
//		System.out.println(ஐந்தாம்வேற்றுமையா("கல்வியில்"));
//		System.out.println(ஐந்தாம்வேற்றுமையா("செல்வத்தில்"));
//		System.out.println(ஐந்தாம்வேற்றுமையா("வீட்டிலிருந்து"));
//		System.out.println(ஐந்தாம்வேற்றுமையா("ஊரிலிருந்து"));
//		System.out.println(ஐந்தாம்வேற்றுமையா("ஊரினின்று"));
//		System.out.println(ஐந்தாம்வேற்றுமையா("காட்டினின்று"));		
//		System.out.println(ஆறாம்வேற்றுமையா("என"));
//		System.out.println(ஆறாம்வேற்றுமையா("எனது"));
//		System.out.println(ஆறாம்வேற்றுமையா("எனாது"));
//		System.out.println(ஏழாம்வேற்றுமையா("அரைநொடியில்"));
//		System.out.println(ஏழாம்வேற்றுமையா("வேனிற்கண்"));
	}
}
