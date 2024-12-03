package org.wotsoc.nlp.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.wotsoc.nlp.ConfigConstants;
import org.wotsoc.nlp.TamilConstantTable;
import org.wotsoc.util.SplittingUtil;
import org.wotsoc.util.TamilStringIterator;

/**
 * Removes Special Characters and Symbols
 * */

public class SymbolParser extends CoreParser implements WordParserInterface{
	static Map<String,String> parseMapProperty  = null; 

	static {
		try {
			staticTct=TamilConstantTable.getInstance();
 			parseMapProperty  = staticTct.getParseAndMainValue(ConfigConstants.SPECIAL_CHARACTER_FILE_NAME);
			spu = new SplittingUtil(staticTct);
			System.out.println("Symbol Parser Loaded.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Properties getParseMapProperty(){
		Properties prop = new Properties();
		prop.putAll(parseMapProperty);
		return prop;
	}
	
	public Map<String,List<String[]>> parse(List<String> word, int passCounter, boolean noDetails, boolean writeToFile,List<String>  wordOfList) throws Exception{
		Map<String,List<String[]>> mapOfList = new ConcurrentHashMap<String,List<String[]>>();
		String match="[.,;!\"?]*";
		TamilConstantTable tct=null; 
		tct =staticTct;
		if(word==null){
			word=tct.getWordList();
		}
		try{
			for(String word0:word){
				/**
				 * Remove Special Characters
				 * */
				String orgWord = word0;
				word0 =word0.replaceAll(match, "");
				if(word0==null||word0.trim().equals("")){
					String value[] = new String[]{passCounter+"" ,":",":(Symbol) IgnoreList",":",parseMapProperty.get(orgWord.trim()),":",":"+parseMapProperty.get(orgWord.trim())};
					List<String[]> tempList= new ArrayList<String[]>();
					tempList.add(value);
					mapOfList.put(orgWord.trim(),tempList);
				}
			}
		}catch(Exception exp) {
			exp.printStackTrace();
		}
		return mapOfList;
	}
	
	public static void main(String args[]) throws Exception {
		SymbolParser sp = new SymbolParser();
		List<String> strList = new ArrayList<String>();
		strList.add("வின்னர்!");
		System.out.println(sp.parse(strList, 0, true, false, null));
		strList = new ArrayList<String>();
		strList.add("!");
		System.out.println(sp.parse(strList, 0, true, false, null));
		strList = new ArrayList<String>();
		strList.add("...");
		System.out.println(sp.parse(strList, 0, true, false, null));
		strList = new ArrayList<String>();
		strList.add("வின்னர்...");
		System.out.println(sp.parse(strList, 0, true, false, null));
		TamilStringIterator tsi = new TamilStringIterator("வின்னர்...");
		Iterator<String> iter=tsi.forwardIterator().iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());	
		}
	}
}
