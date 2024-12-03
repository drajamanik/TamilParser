/**
 * @author Rajamani David
 * @since	Apr 18, 2019
 *
 */
package org.wotsoc.test;

import java.util.List;

import org.wotsoc.illakanam.TamilIllakanam;
import org.wotsoc.illakanam.TamilUtil;
import org.wotsoc.util.ReadFromFile;
import org.wotsoc.util.TamilStringIterator;

/**
 * @author rdavid
 *
 */
public class TamilIllakanamDemo {
	
	public void அனைத்துஉடனிலைமெய்ம்மயக்த்தின்பதிவு(String fileName) throws Exception{
		ReadFromFile rff = new ReadFromFile();
		TamilIllakanam it = new TamilIllakanam();
		List<String> strList = rff.readFileAsList(fileName);
		String retStrValue ="";
		for(String str:strList){
			retStrValue = it.உடனிலைமெய்ம்மயக்மா(str);
			if(retStrValue!=null ){
				System.out.println(retStrValue+":"+str);
			}	
		} 
	}

	public void அனைத்துஇனவெழுத்தின்பதிவு(String fileName) throws Exception{
		ReadFromFile rff = new ReadFromFile();
		TamilIllakanam it = new TamilIllakanam();
		List<String> strList = rff.readFileAsList(fileName);
		String retStrValue ="";
		for(String str:strList){
			retStrValue = it.இனவெழுத்தா(str);
			if(retStrValue!=null ){
				System.out.println(retStrValue+":"+str);
			}	
		} 
	}
	
	public void அனைத்துமெய்யிற்றிப்புணரச்சிபதிவு(String fileName) throws Exception{
		ReadFromFile rff = new ReadFromFile();
		TamilIllakanam it = new TamilIllakanam();
		List<String> strList = rff.readFileAsList(fileName);
		String retStrValue ="";
		for(String strFirst:strList){
			for(String strSecond:strList){
				if(TamilUtil.கடையெழுத்துமெய்யெழுத்தில்முடிகிறதா(strFirst) 
						&& TamilUtil.முதல்லெழுத்துஉயிரெழுதா(strSecond)){
					TamilStringIterator tsi = new TamilStringIterator(strFirst);
					if(tsi.length()>=2){
		 				retStrValue = it.மெய்யிற்றிப்புணரச்சி(strFirst,strSecond);
						if(retStrValue!=null ){
							System.out.println(strFirst+":"+strSecond +":"+ retStrValue);
						}
					}
				}
			}
		} 
	}

	public static void main(String args[]) throws Exception{
		TamilIllakanamDemo it = new TamilIllakanamDemo();
		it.அனைத்துஇனவெழுத்தின்பதிவு("C://Users//rdavid//Downloads//TamilWordNet//mysql4.0//tamil_eng_literal.out");
		//it.அனைத்துஉடனிலைமெய்ம்மயக்த்தின்பதிவு("C://Users//rdavid//Downloads//TamilWordNet//mysql4.0//tamil_eng_literal.out");
		//it.அனைத்துமெய்யிற்றிப்புணரச்சிபதிவு("C://Users//rdavid//Downloads//TamilWordNet//mysql4.0//tamil_eng_literal.out");
		//System.out.println(it.இனவெழுத்தா("பிரதிஹர"));
		
	}
}
