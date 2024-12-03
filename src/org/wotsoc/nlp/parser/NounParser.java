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
public class NounParser extends TamilRootWordParser implements WordParserInterface
{
	public NounParser() throws Exception {
		 
	}

	static List<List<List<String>>> globalList= null;
	public static String nounWords[][] = null;
	static List<List<Integer>> nounListOfList = null;
	static Properties nounParseMapProperty  = null;
	static Map<List<Integer>,List<List<String>>> nounParseOrderToValue = new HashMap<List<Integer>,List<List<String>>> ();
	static Map<List<List<String>>,List<Integer>> nounValueToParseOrder = new HashMap<List<List<String>>,List<Integer>> ();
	
	static{
		try {
			staticTct=TamilConstantTable.getInstance();
			Object[] objArr=staticTct.getParseAndMainValue(ConfigConstants.NOUN_CONSTANT_FILE_NAME, ConfigConstants.NOUN_PARSE_ORDER_FILE_NAME,ConfigConstants.NOUN_PARSE_MAP_FILE_NAME);
			nounWords = (String[][])objArr[0];
			nounListOfList = (List<List<Integer>>)objArr[1];
			nounParseMapProperty = (Properties)objArr[2];
			spu = new SplittingUtil(staticTct);
			globalList= staticTct.getMainTable(nounWords,nounListOfList,nounParseOrderToValue,nounValueToParseOrder);
			System.out.println("Noun Parser Loaded.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getParserType() {
		return "Noun";
	}
	
	public List<List<List<String>>> getGlobalList() {
		return globalList;
	}
	
	public Properties getParseMapProperty()
	{
		return nounParseMapProperty;
	}
	
	public Map<List<List<String>>,List<Integer>> getValueToParseOrder()
	{
		return nounValueToParseOrder;
	}

	public String findVerbOrNounFromSplitResidue(String word[],TamilConstantTable tct,String checkValue)
	{
 		if(word.length>1 && word[1]!=null && nounResidueList.contains(TamilUtil.எழுத்துகளைசேர்(word[1]))) {
 			if(tct.isInIgnoreNounWordList(checkValue))
 				return "N";
 			return "NA (N)";
 		}
// 		if(word.length>1 && word[1]!=null && verbResidueList.contains(TamilUtil.எழுத்துகளைசேர்(word[1]))){
// 			if(tct.isInIgnoreVerbWordList(checkValue))
// 				return "V";
// 			return "NA (V)";
// 		}

 		if(tct.isInIgnoreNounWordList(checkValue))
 			return "N";
 		if(tct.isInPreFixList(checkValue))
 			return "PRE";
 		if(tct.isInIgnorePersonList(checkValue))
 			return "PR";
 		if(tct.isInIgnorePlaceList(checkValue))
 			return "PL";

 		return "NA";
	}
}
