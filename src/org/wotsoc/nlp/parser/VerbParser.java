package org.wotsoc.nlp.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.wotsoc.illakanam.TamilUtil;
import org.wotsoc.nlp.ConfigConstants;
import org.wotsoc.nlp.TamilConstantTable;
import org.wotsoc.util.SplittingUtil;

@SuppressWarnings("unchecked")
public class VerbParser extends TamilRootWordParser implements WordParserInterface
{
	public VerbParser() throws Exception {
		
	}

	static List<List<List<String>>> globalList= null;
	public static String verbWords[][] = null;
	static List<List<Integer>> verbListOfList = null;
	static Properties verbParseMapProperty  = null;
	static Map<List<Integer>,List<List<String>>> verbParseOrderToValue = new HashMap<List<Integer>,List<List<String>>> ();
	static Map<List<List<String>>,List<Integer>> verbValueToParseOrder = new HashMap<List<List<String>>,List<Integer>> ();
	
	static{
		try {
			staticTct=TamilConstantTable.getInstance();
			Object[] objArr=staticTct.getParseAndMainValue(ConfigConstants.VERB_CONSTANT_FILE_NAME, ConfigConstants.VERB_PARSE_ORDER_FILE_NAME,ConfigConstants.VERB_PARSE_MAP_FILE_NAME);
			verbWords = (String[][])objArr[0];
			verbListOfList = (List<List<Integer>>)objArr[1];
			verbParseMapProperty = (Properties)objArr[2];
			spu = new SplittingUtil(staticTct);
			globalList= staticTct.getMainTable(verbWords,verbListOfList,verbParseOrderToValue,verbValueToParseOrder);
			System.out.println("Verb Parser Loaded.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getParserType() {
		return "Verb";
	}

	public List<List<List<String>>> getGlobalList() {
		return globalList;
	}
	
	public Properties getParseMapProperty()
	{
		return verbParseMapProperty;
	}
	
	public Map<List<List<String>>,List<Integer>> getValueToParseOrder()
	{
		return verbValueToParseOrder;
	}
	
	public Map<List<Integer>,List<List<String>>> getParseOrderToValue()
	{
		return verbParseOrderToValue;
	}
	
	public String findVerbOrNounFromSplitResidue(String word[],TamilConstantTable tct,String checkValue)
	{
 		if(word.length>1 && word[1]!=null && verbResidueList.contains(TamilUtil.எழுத்துகளைசேர்(word[1]))){
 			if(tct.isInIgnoreVerbWordList(checkValue))
 				return "V";
 			return "NA (V)";
 		}
// 		if(word.length>1 && word[1]!=null && nounResidueList.contains(TamilUtil.எழுத்துகளைசேர்(word[1]))) {
// 			if(tct.isInIgnoreNounWordList(checkValue))
// 				return "N";
// 			return "NA (N)";
// 		}

 		if(tct.isInIgnoreVerbWordList(checkValue))
 			return "V";
 		if(tct.isInPreFixList(checkValue))
 			return "PRE";
 		if(tct.isInIgnorePersonList(checkValue))
 			return "PR";
 		if(tct.isInIgnorePlaceList(checkValue))
 			return "PL";

 		return "NA";
	}

}
