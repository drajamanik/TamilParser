package org.wotsoc.nlp.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.wotsoc.nlp.ConfigConstants;
import org.wotsoc.nlp.TamilConstantTable;
import org.wotsoc.util.SplittingUtil;

public class NumberParser extends CoreParser implements WordParserInterface
{
	static Map<String,String> parseMapProperty  = null;
	static List<List<List<String>>> globalList= null;
	public static String twinWords[][] = null;
	static List<List<Integer>> twinListOfList = null;
	static Map<List<Integer>,List<List<String>>> twinParseOrderToValue = new HashMap<List<Integer>,List<List<String>>> ();
	static Map<List<List<String>>,List<Integer>> twinValueToParseOrder = new HashMap<List<List<String>>,List<Integer>> ();
	
	static{
		try {
			staticTct=TamilConstantTable.getInstance();
			parseMapProperty  = staticTct.getParseAndMainValue(ConfigConstants.SPECIAL_CHARACTER_FILE_NAME);
			spu = new SplittingUtil(staticTct);
			System.out.println("Number Parser Loaded.");
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
		try{
			for(String word0:word){
				boolean hasAlphaNumeric = word0.matches(".*\\d+.*"); 
				if(!word0.trim().equals("") && ( StringUtils.isNumeric(word0) || hasAlphaNumeric)){
					String value[] = new String[]{passCounter+"" ,":",":(NU) IgnoreList",":",word0.trim(),":"+word0.trim()};
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

	
	public List<List<List<String>>> getGlobalList() {
		return globalList;
	}
	
	public Map<List<List<String>>,List<Integer>> getValueToParseOrder()
	{
		return twinValueToParseOrder;
	}

}
