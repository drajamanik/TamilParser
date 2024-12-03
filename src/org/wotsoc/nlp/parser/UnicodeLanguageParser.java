package org.wotsoc.nlp.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.wotsoc.nlp.ConfigConstants;
import org.wotsoc.nlp.ReadConfig;
import org.wotsoc.nlp.TamilConstantTable;
import org.wotsoc.util.SplittingUtil;

public class UnicodeLanguageParser extends CoreParser implements WordParserInterface{
	
	static final Map<Integer[],String> languageMap = new HashMap<Integer[],String>();
	static Map<String,String> parseMapProperty  = null; 
	static {
		try {
			init();
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
	
	public Set<String> findLanguage(String word) {
		Set<String> lanList = new HashSet<String>();
		if(word== null) 
			return lanList;
		char[] charArray = word.toCharArray();
		for(char c : charArray) {
			lanList.add(whichLanguage(c));
		}
		return lanList;
	}
	
	public String whichLanguage(char ch) {
		Iterator<Integer[]> iter = languageMap.keySet().iterator();
		String value = null;
		Integer[] range = null;
		while(iter.hasNext()) {
			range = iter.next();
			value = languageMap.get(range);
			if(ch >= range[0] && ch<= range[1]) {
				return value;
			}
		}
		return null;
	}

	public static void init() throws Exception {
		ReadConfig rc = ReadConfig.getInstance();
		Map<String,String> map =rc.getProperties();
		String current = new java.io.File( "." ).getCanonicalPath();
		current = "";//rc.getCurrentRoot();
		
		Map<String,String> unicodeMap = rc.readFileAsMap(current+map.get(ConfigConstants.UNICODE_MAP_FILE_NAME));
		Iterator<String> iter = unicodeMap.keySet().iterator();
		String key = null;
		Integer[] range = null;
		while(iter.hasNext()) {
			key = iter.next();
			range= range(key," — ");
			languageMap.put(range,unicodeMap.get(key).trim());
		}
	}

	public static Integer[] range(String range, String delimiter) {
		String rangeArr[]= range.split(delimiter);
		Integer begin = Integer.parseInt(rangeArr[0].trim(),16);
		Integer end = Integer.parseInt(rangeArr[1].trim(),16);
		return new Integer[] {begin,end};
	}
	
 	public static void main(String args[]) throws Exception {
		UnicodeLanguageParser ulf = new UnicodeLanguageParser();
		System.out.println("Wordஅ?1:"+ulf.findLanguage("Wordஅ?1")); 
		System.out.println("அம்மா:"+ulf.findLanguage("அம்மா"));
	}

	@Override
	public boolean isInNounList(String str) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInVerbList(String str) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, List<String[]>> parse(List<String> word, int passCounter, boolean noDetails, boolean writeToFile,
			List<String> wordOfList) throws Exception {
		Map<String,List<String[]>> mapOfList = new ConcurrentHashMap<String,List<String[]>>();
		try{
			for(String word0:word){
				Set<String> langSet= findLanguage(word0);
				if(langSet.size()>1){
					String value[] = new String[]{passCounter+"" ,":",":(NW) IgnoreList",":",word0.trim(),":"+word0.trim()};
					if(noDetails)
						value[0] = word0.trim();
					
					List<String[]> listOfString= mapOfList.get(word0);
					if(listOfString==null)
						listOfString = new ArrayList<String[]>();
					listOfString.add(value);
					mapOfList.put(word0,listOfString);
				}
			}
		}catch(Exception exp) {
			exp.printStackTrace();
		}
		return mapOfList;
	}

	@Override
	public String getParserType() {
		return "Unicode";
	}
}
