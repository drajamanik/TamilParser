/**
 * @author Rajamani David
 * @since	Jun 22, 2019
 *
 */
package org.wotsoc.test;

import java.util.List;

import org.wotsoc.illakanam.TamilConstants;
import org.wotsoc.illakanam.TamilUtil;
import org.wotsoc.util.ReadFromFile;
import org.wotsoc.util.TamilStringIterator;
import org.wotsoc.util.WriteToFile;

/**
 * @author rdavid
 *
 */
public class PSLength {
	
	public static void readString(){
		String str1 = "ப்அட்ஐத்த்அவ்அர்க்அள்ஆய்இர்உக்க்இற்ஆர்க்அள்ஆ";
		String str2 ="அவ்அர்க்அள்";
		
		String firstStr =str1.substring(10); 
		int firstLast=str1.indexOf(str2);
		System.out.println(firstLast);
	}
	
	public void writeLength() throws Exception{
		ReadFromFile rff = new ReadFromFile();
		List<String> strList= rff.readFileAsList("c:\\workspaces\\corpus\\properties\\parseWord1.txt");
		StringBuilder sb = new StringBuilder();
		int counter=0;
		for(String str : strList){
			counter++;
			TamilStringIterator tsi = new TamilStringIterator(str);
			sb.append(str).append(",").append(tsi.length()).append("\n");
			if(counter>1000){
				WriteToFile.writeToFile(sb, "c:\\workspaces\\corpus\\properties\\pmFile.txt");
				sb = new StringBuilder();
			}
		}
	}
	
	public static void readFile() throws Exception {
		ReadFromFile rff = new ReadFromFile();
		List<String> strList= rff.readFileAsList("c:\\workspaces\\Tamil\\bin\\properties\\ignoreNoun.list");
		StringBuilder sb =new StringBuilder();
		for(String str:strList) {
			str = str.replace(",", "");
			TamilStringIterator ist = new TamilStringIterator(str);
			String lastValue = ist.last();
			if(TamilUtil.existInCheckList(lastValue,TamilConstants.வல்லினம்_மெய்யெழுத்து_வ்toற்_முடிகிறதா))
					
			{
				System.out.println(str);
			}
//				else {TamilConstants.வல்லினம்_மெய்யெழுத்து_வ்toற்_முடிகிறதா))
//				System.out.println(str);
//				sb.append(str).append(",\n");
//			}
		}
		WriteToFile.writeToFile(sb, "pmFile.txt");
	}
	
	public static void main(String args[]) throws Exception{
		readFile();
	}
}
