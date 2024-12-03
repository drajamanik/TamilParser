/**
 * @author Rajamani David
 * @since	Apr 30, 2019
 *
 */
package org.wotsoc.illakanam;

import java.util.List;

import org.wotsoc.util.TamilStringIterator;

/**
 * @author Rajamani David
 * @since 30/Apr/2019
 *
 */ 
public class TamilPunarchi {

	public static void main(String args[]){
		TamilPunarchi tp = new TamilPunarchi();
		System.out.println(tp.மெய்யிற்றிப்புணரச்சி ("மரம்","இலை"));
		System.out.println(tp.மெய்யிற்றிப்புணரச்சி ("மரம்","கிளை"));
	}
	
	public String findReplacementLetter(String firstWordLastLetter,String secondWordFirstLetter){
		
		if( TamilUtil.existInCheckList(firstWordLastLetter, TamilConstants.ணகரவரிசை)
		||  TamilUtil.existInCheckList(firstWordLastLetter, TamilConstants.ளகரவரிசை))
		{
			
		}
		return "";
	}
	
	
	public String மகரமெய்யிற்றிப்புணரச்சி (String firstWord, String secondWord){
		return null;
	}
	
	public String குற்றியலுகரப்புணரச்சி (String firstWord, String secondWord){
		return null;
	}
	
	public String உயிரீற்றிப்புணரச்சி (String firstWord, String secondWord){
		String firstWordRemainingLetters  = "";
		String secondWordRemainingLetters = "";
		
		TamilStringIterator itFirst = new TamilStringIterator(TamilUtil.எழுத்துகளைபிரி(firstWord));
		List<String> tfList=itFirst.forwardIterator();
		String firstWordLastLetter = tfList.get(tfList.size()-1);
		
		boolean firstWordஉயிரெழுதா = TamilUtil.முதல்லெழுத்துஉயிரெழுதா(firstWordLastLetter);
		boolean secondWordஉயிரெழுதா = TamilUtil.முதல்லெழுத்துஉயிரெழுதா(secondWord);
		/**
		 * முதல் வார்த்தின் கடையெழுத்து  உயிரெழுத்து மற்றும் இரண்டாவது வார்த்தை முதலெழுத்து மெய்யெழுத்து இல்லையானால் ஒன்றும் செய்ய வேண்டாம்.
		 * */
		if( !(firstWordஉயிரெழுதா && secondWordஉயிரெழுதா)) 
			return null;
		
		TamilStringIterator itSecond = new TamilStringIterator(TamilUtil.எழுத்துகளைபிரி(secondWord));
		List<String> tsList=itSecond.forwardIterator();
		
		for(int x=0; x<tfList.size(); x++)
			firstWordRemainingLetters = firstWordRemainingLetters + tfList.get(x);

		for(int x=0; x<tsList.size(); x++)
			secondWordRemainingLetters = secondWordRemainingLetters + tsList.get(x);

		if(firstWordLastLetter.equals(TamilConstants.இகரம்) 
				|| firstWordLastLetter.equals(TamilConstants.ஈகரம்)
				|| firstWordLastLetter.equals(TamilConstants.ஐகரம்))
			return (TamilUtil.எழுத்துகளைசேர்(firstWordRemainingLetters+"ய்"+secondWordRemainingLetters)); 
		if(firstWordLastLetter.equals(TamilConstants.அகரம்) 
				|| firstWordLastLetter.equals(TamilConstants.ஆகரம்))
			return (TamilUtil.எழுத்துகளைசேர்(firstWordRemainingLetters+"வ்"+secondWordRemainingLetters)); 
		if(firstWordLastLetter.equals(TamilConstants.ஏகரம்))
			return (TamilUtil.எழுத்துகளைசேர்(firstWordRemainingLetters+"ய்"+secondWordRemainingLetters)); 
		return null;
	}
	
	/**
	 * Join two words together and see they change their form by taking last letter of first word
	 * and first letter of second word
	 * */
	public String மெய்யிற்றிப்புணரச்சி (String firstWord, String secondWord){
		String firstWordRemainingLetters  = "";
		String secondWordRemainingLetters = "";
		String joinedLetter = "";
		boolean மெல்லினவரிசையா = false;
		boolean இடையினவரிசையா = false;
		boolean வல்லினவரிசையா = false;
		
		TamilStringIterator itFirst = new TamilStringIterator(firstWord);
		List<String> tfList=itFirst.forwardIterator();
		String firstWordLastLetter = tfList.get(tfList.size()-1);
		
		/**
		 * கடையெழுத்து  மெய்யெழுத்து இல்லையானால் ஒன்றும் செய்ய வேண்டாம்.
		 * */
		if(!TamilUtil.existInCheckList(firstWordLastLetter,TamilConstants.மெய்யெழுத்து))
			return null;
		
		TamilStringIterator itSecond = new TamilStringIterator(secondWord);
		List<String> tsList=itSecond.forwardIterator();
		String secondWordFirstLetter = tsList.get(0);
		
		boolean firstWordLastLetterமகரமெய்யா = firstWordLastLetter.equals(TamilConstants.மகரமெய்);
		boolean secondWordஉயிரெழுதா = TamilUtil.முதல்லெழுத்துஉயிரெழுதா(secondWord);
		
		if(firstWordLastLetterமகரமெய்யா && !secondWordஉயிரெழுதா){
			மெல்லினவரிசையா = TamilUtil.existInCheckList(secondWordFirstLetter,TamilConstants.மெல்லினவரிசைகொடு());
			if(!மெல்லினவரிசையா)
				இடையினவரிசையா = TamilUtil.existInCheckList(secondWordFirstLetter,TamilConstants.இடையினவரிசைகொடு());

			for(int x=0; x<tfList.size()-1; x++)
				firstWordRemainingLetters = firstWordRemainingLetters + tfList.get(x);

			for(int x=0; x<tsList.size(); x++)
				secondWordRemainingLetters = secondWordRemainingLetters + tsList.get(x);

			if(மெல்லினவரிசையா || இடையினவரிசையா ){
				return firstWordRemainingLetters+secondWordRemainingLetters;
			}
			if( !(மெல்லினவரிசையா && இடையினவரிசையா)){
				வல்லினவரிசையா = true;
				joinedLetter = TamilUtil.எழுத்தின்மெய்யாடுவல்லினம்இணை(secondWordFirstLetter);
				return firstWordRemainingLetters+joinedLetter+secondWordRemainingLetters;
			}
		}
		
		boolean firstWordLastLetterணகரமெய்யா = firstWordLastLetter.equals(TamilConstants.ண்கரவரிசை);
		boolean firstWordLastLetterனகரமெய்யா = firstWordLastLetter.equals(TamilConstants.ன்கரவரிசை);
		boolean firstWordLastLetterளகரமெய்யா = firstWordLastLetter.equals(TamilConstants.ள்கரவரிசை);
		boolean firstWordLastLetterலகரமெய்யா = firstWordLastLetter.equals(TamilConstants.ல்கரவரிசை);
		
		if(!வல்லினவரிசையா)
			வல்லினவரிசையா = TamilUtil.existInCheckList(secondWordFirstLetter,TamilConstants.வல்லினவரிசைகொடு());
		if(!மெல்லினவரிசையா)
			மெல்லினவரிசையா = TamilUtil.existInCheckList(secondWordFirstLetter,TamilConstants.மெல்லினவரிசைகொடு());
		
		/**
		 * இரண்டாம் மொழி கடைசி எழுத்து தகரவரிசை அல்லது நகரவரிசை
		 * */
		boolean secondWordFirstLetterதகரமா = TamilUtil.existInCheckList(secondWordFirstLetter,TamilConstants.தகரவரிசை);
		boolean secondWordFirstLetterநகரமா = TamilUtil.existInCheckList(secondWordFirstLetter,TamilConstants.நகரவரிசை);
		if((firstWordLastLetterனகரமெய்யா || firstWordLastLetterலகரமெய்யா || firstWordLastLetterணகரமெய்யா || firstWordLastLetterளகரமெய்யா) 
				&& (secondWordFirstLetterதகரமா || secondWordFirstLetterநகரமா)){
			for(int x=1; x<tsList.size(); x++)
				secondWordRemainingLetters = secondWordRemainingLetters + tsList.get(x);
			
			if(firstWordLastLetterளகரமெய்யா && secondWordFirstLetterதகரமா )
				return firstWord +"டீ"+secondWordRemainingLetters;
			if(firstWordLastLetterணகரமெய்யா && secondWordFirstLetterநகரமா )
				return firstWord +"ணீ"+secondWordRemainingLetters;
			if(firstWordLastLetterணகரமெய்யா && secondWordFirstLetterதகரமா )
				return firstWord +"டீ"+secondWordRemainingLetters;
			if(firstWordLastLetterனகரமெய்யா && secondWordFirstLetterதகரமா)
				return firstWord +"றீ"+secondWordRemainingLetters;
			if(secondWordFirstLetterநகரமா)
				return firstWord +"ன"+secondWordRemainingLetters;
		}
		/**
		 * முதல் மொழி கடைசி எழுத்து ணகரமெய்யா அல்லது னகரமெய்யா அல்லது லகரமெய்யா அல்லது ளகரமெய்யா
		 * */
		if((firstWordLastLetterணகரமெய்யா || firstWordLastLetterனகரமெய்யா
				|| firstWordLastLetterளகரமெய்யா || firstWordLastLetterலகரமெய்யா) ){
			for(int x=0; x<tfList.size()-1; x++)
				firstWordRemainingLetters = firstWordRemainingLetters + tfList.get(x);

			for(int x=0; x<tsList.size(); x++)
				secondWordRemainingLetters = secondWordRemainingLetters + tsList.get(x);

			if(வல்லினவரிசையா){
				joinedLetter = TamilUtil.எழுத்தின்மெய்யாடுவல்லினம்இணை(firstWordLastLetter);
				return firstWordRemainingLetters + joinedLetter + secondWordRemainingLetters;
			}else if(மெல்லினவரிசையா && (firstWordLastLetterளகரமெய்யா || firstWordLastLetterலகரமெய்யா) ){
				joinedLetter = TamilUtil.எழுத்தின்மெய்யாடுமெல்லினம்இணை(firstWordLastLetter);
				return firstWordRemainingLetters + joinedLetter + secondWordRemainingLetters;
			}
		}
		
		if(secondWordFirstLetter.length()>1)
			secondWordFirstLetter= TamilUtil.எழுத்துகளைபிரி(secondWordFirstLetter);

		for(int x=0; x<tfList.size()-1; x++)
			firstWordRemainingLetters = firstWordRemainingLetters + tfList.get(x);

		for(int x=1; x<tsList.size(); x++)
			secondWordRemainingLetters = secondWordRemainingLetters + tsList.get(x);
		
		if(TamilUtil.கடையெழுத்துமகரயெழுத்தில்முடிகிறதா(firstWord) 
			&& secondWordஉயிரெழுதா){
			joinedLetter = TamilUtil.எழுத்துகளைசேர்("வ்"+secondWordFirstLetter);
			return firstWordRemainingLetters + joinedLetter + secondWordRemainingLetters;
		}
		else if(TamilUtil.கடையெழுத்துமெய்யெழுத்தில்முடிகிறதா(firstWord) 
				&& secondWordஉயிரெழுதா){
			joinedLetter = TamilUtil.எழுத்துகளைசேர்(firstWordLastLetter+secondWordFirstLetter);
			if(tfList.size()>=3){
				return firstWordRemainingLetters + joinedLetter + secondWordRemainingLetters;
			}else if(tfList.size()<3){
				return firstWordRemainingLetters + firstWordLastLetter + joinedLetter + secondWordRemainingLetters;
			}
		}
		return null; 
	}

}
