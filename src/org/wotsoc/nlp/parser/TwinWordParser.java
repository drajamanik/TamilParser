package org.wotsoc.nlp.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.wotsoc.nlp.ConfigConstants;
import org.wotsoc.nlp.TamilConstantTable;
import org.wotsoc.util.SplittingUtil;
import org.wotsoc.util.TamilStringIterator;
import org.wotsoc.util.WordClass;

@SuppressWarnings("unchecked")
public class TwinWordParser extends CoreParser implements WordParserInterface
{
	static List<List<List<String>>> globalList= null;
	public static String twinWords[][] = null;
	static List<List<Integer>> twinListOfList = null;
	static Properties	twinParseMapProperty = null;
	static Map<List<Integer>,List<List<String>>> twinParseOrderToValue = new HashMap<List<Integer>,List<List<String>>> ();
	static Map<List<List<String>>,List<Integer>> twinValueToParseOrder = new HashMap<List<List<String>>,List<Integer>> ();
	
	static{
		try {
			staticTct=TamilConstantTable.getInstance();
			Object[] objArr=staticTct.getParseAndMainValue(ConfigConstants.TWIN_CONSTANT_FILE_NAME, ConfigConstants.TWIN_PARSE_ORDER_FILE_NAME,ConfigConstants.MAIN_PARSE_MAP_FILE_NAME);
			twinWords = (String[][])objArr[0];
			twinListOfList = (List<List<Integer>>)objArr[1]; 
			twinParseMapProperty = (Properties)objArr[2];
			spu = new SplittingUtil(staticTct);
			globalList= staticTct.getMainTable(twinWords,twinListOfList,twinParseOrderToValue,twinValueToParseOrder);
			System.out.println("TwinWord Parser Loaded.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<List<List<String>>> getGlobalList() {
		return globalList;
	}
	
	public Properties getParseMapProperty()
	{
		return twinParseMapProperty;
	}

	
	public Map<List<List<String>>,List<Integer>> getValueToParseOrder()
	{
		return twinValueToParseOrder;
	}
	
//	public Map<String,List<String[]>>  parse(List<String> wordList, int passCounter, boolean noDetails, boolean writeToFile,List<String>  wordOfList) 
//			throws Exception{
//		Map<String,List<String[]>> map = new HashMap<String,List<String[]>>();
//		boolean flag = false;
//		for(String word:wordList) {
//			flag = isTwinWord(word);
//			if(!flag)
//				flag = isTwinWord(getTwinWord(word));
//		 
//			System.out.println(word+":"+flag);
//		}
//		
//		return map;
//	}

	public boolean isTwinWord(String word) {
		if(word==null)
			return false;
		TamilStringIterator tsi = new TamilStringIterator(word);
		int length =tsi.length();
		int halfLength= length/2;
		StringBuilder sbFirst = new StringBuilder();
		StringBuilder sbSecond = new StringBuilder();
		 
		String strForward=null;
		List<String> list=tsi.forwardIterator();
		for(int count=0;count<list.size();count++) {
			strForward = list.get(count);
			if(length%2 !=0 && count==halfLength)
				 continue;
			else if(count<halfLength)
				sbFirst.append(strForward);
			else
				sbSecond.append(strForward);
		}
		if(sbFirst.toString().equals(sbSecond.toString()))
			return true;
		return false; 
	}
	
	public List<WordClass> getTwinWord(String word) throws Exception {
		WordClass wc = null;
		List<WordClass> wcList= new ArrayList<WordClass>();
		Map<String,String> mapParseVals = new HashMap<String,String>();
		List<String> wordOfList = new ArrayList<String>();
		Map<String,List<String[]>> map = parse(Arrays.asList(word) ,0,false,false,wordOfList);
		List<String[]> checkValueArrList = map.get(word);
		boolean atleastOneTwin = false;
		wordOfList = new ArrayList<String>();
		if(checkValueArrList!=null) {
			for(String[] strArr:checkValueArrList) {
				wordOfList = Arrays.asList(strArr);
				if(isTwinWord(strArr[0])) {
					mapParseVals.put("Type","Twin");
					wc = new WordClass(0,word,null,null,mapParseVals,new ArrayList(wordOfList));
					atleastOneTwin = true;
					wcList.add(wc);
				}else {
					if(!atleastOneTwin) {
						mapParseVals.put("Type","NA");
						wc = new WordClass(0,word,null,null,mapParseVals,new ArrayList(wordOfList));
						wcList.add(wc);
					}
				}
				
			}
		}
		return wcList;
	}
	
	public boolean isTwinWord(String word1,String word2) throws Exception {
		if(word1.equals(word2))
			return true;
		
		List<WordClass> wcList =getTwinWord(word1+word2);
		for(WordClass wc:wcList) {
			if(wc.getType()!=null && wc.getType().equals("Twin"))
				return true;
		}
		return false;
	}
	
	public List<WordClass> createSingleInstance(String word) throws Exception{
		List<WordClass> wcList= new ArrayList<WordClass>();
		Map<String,String> mapParseVals = new HashMap<String,String>();
		WordClass wc = null;
		boolean flag = isTwinWord(word);
		if(flag) {
			mapParseVals.put("Type","Twin");
			wc = new WordClass(0,word,null,null,mapParseVals,null);
			wcList.add(wc);
			return wcList;
		}else {
			return getTwinWord(word);
		}
	}

	public static void main(String args[]) throws Exception {
		 
//		System.out.println(TamilUtil.எழுத்துகளைபிரி("சாரையாய்"));
//		System.out.println(TamilUtil.எழுத்துகளைபிரி("எதிரே"));
//		System.out.println(TamilUtil.எழுத்துகளைபிரி("எதிராக"));
//		 
//		System.out.println(TamilUtil.எழுத்துகளைபிரி("மொலு"));
//		System.out.println(TamilUtil.எழுத்துகளைபிரி("மொலென்று"));
		TwinWordParser twp = new TwinWordParser();
//		List<String> wordList = new ArrayList<String>();
//		wordList.add("எதிர் எதிராக");
//		if(wordList==null || wordList.size()==0){
//			wordList = staticTct.getWordList();
//		}
//		boolean flag =false;
//		for(String word:wordList) {
//			flag = twp.isTwinWord(word);
//			if(!flag)
//				flag = twp.isTwinWord(twp.getTwinWord(word));
//			if(!flag)
//				
//			System.out.println(word+":"+flag);
//		}
//		
		List<WordClass> wcList= twp.createSingleInstance("எதிர் எதிராக");
		for(WordClass wc:wcList)
			System.out.println(wc.getWord()+":"+wc.getType()+":"+wc.getRawSplitList());
		
//		System.out.println(twp.isTwinWord("திக்குத்திக்கு"));
//		System.out.println(twp.isTwinWord("பக்குப்பக்கு"));
		//System.out.println(twp.isTwinWord("சலசல","சலசல"));
		//System.out.println(twp.isTwinWord("மள","மளன்னு"));
	}
}
