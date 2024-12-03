package org.wotsoc.nlp.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.wotsoc.illakanam.TamilConstants;
import org.wotsoc.illakanam.TamilUtil;
import org.wotsoc.nlp.RecursiveAlgorithm;
import org.wotsoc.nlp.TamilConstantTable;
import org.wotsoc.nlp.TamilMultiLoop;
import org.wotsoc.util.SplittingUtil;
import org.wotsoc.util.TamilStringIterator;
import org.wotsoc.util.WordClass;
import org.wotsoc.util.WriteToFile;

/**
 * @author Rajamani David
 * @since Feb/09/2020
 * Abstract class to handle all type of word parsing.
 * */
public abstract class CoreParser implements WordParserInterface{
	
	/**
	 * EXIT LOOP used for as circuit breaker to avoid infinite loop,
	 * * */ 
	 //THIS FIELD IS MODIFED FROM OUTSIDE THIS CLASS.
	static Long	EXIT_LOOP = 20000000l;
	
	static Map<String,List<List<String>>> mapCache =new ConcurrentHashMap<String,List<List<String>>>();
	String verbNoun="";
	
	public static SplittingUtil spu = null;
	public static TamilConstantTable staticTct = null;
	static int totalCounter =0;
	
	public CoreParser(){
		
	}
	
	public String getParserType() {
		return "Core";
	}
	
	public boolean isSpecialCharacter(String word) {
		return staticTct.getSpecialProperty(word)==null?false:true;
	}
	
	public String getSpecialCharacter(String word) {
		return staticTct.getSpecialProperty(word);
	}
	
	public void printMainParseFile(Properties parseMapProperty) {
		staticTct.printMainParseProperty(parseMapProperty);
	}
	
	public Properties getParseMapProperty()
	{
		return null;
	}
	
	public List<List<Integer>> getParseOrderList()
	{
		return null;
	}

	public Map<List<Integer>,List<List<String>>> getParserOrderToValues()
	{
		return null;
	}
	
	public Map<List<List<String>>,List<Integer>> getValueToParseOrder()
	{
		return null;
	}
	
	public List<List<List<String>>> getGlobalList() {
		return null;
	}
	
	public boolean isInNounList(String str) {
		return staticTct.isInIgnoreNounWordList(str);
	}
	
	public boolean isInVerbList(String str) {
		return staticTct.isInIgnoreVerbWordList(str);
	}
	
	public String findVerbOrNounFromSplitResidue(String word[],TamilConstantTable tct,String checkValue) {
		return null;
	}
	
	public Map<String,List<String[]>>  parse(List<String> word, int passCounter, boolean noDetails, boolean writeToFile,List<String>  wordOfList) 
			throws Exception{
		String word1 =null;
		String word2[] =null;
		int counter = passCounter;

		String match="[.,;!\"?]*";
		TamilConstantTable tct=staticTct; 
		if(word==null){
			word=tct.getWordList();
		}

		Map<List<List<String>>,List<Integer>> valueToParseOrderMap= getValueToParseOrder();
		String lastParserOrder=null;
		Map<String,List<String[]>> map = new HashMap<String,List<String[]>>();
		try{
			for(String word0:word){
				if(word0.trim().equals(""))
					return null;
				word2 =null;
				word1 = TamilUtil.எழுத்துகளைபிரி(word0,false,false);
				System.out.println(word1);
		
				Map<List<String>,String[]> lastDeepList =new LinkedHashMap<List<String>,String[]>();
				Map<String,String> lastDeepParseList =new LinkedHashMap<String,String>();
		
				for (List<List<String>> outerList:getGlobalList())
				{
					if(word2!=null && word2[1]!=null && !word2[1].trim().equals(""))
						break;
		
					synchronized (outerList){
						List<List<String>> listOfInnnerList = new ArrayList <List<String>>();
						listOfInnnerList=mapCache.get(outerList+"");
						if(listOfInnnerList==null || outerList.toString().contains("NOUN") || outerList.toString().contains("VERB")){
							RecursiveAlgorithm ra = new RecursiveAlgorithm();
							listOfInnnerList = ra.getAllCombinedValues(outerList);
						}
						mapCache.put(outerList+"",listOfInnnerList);
						String outerKey = getValueToParseOrder().get(outerList).toString(); 
						int depthSize=outerList.size();
						List<List<String>>  listOfInnnerListClone = (List<List<String>>)((ArrayList)(listOfInnnerList)).clone();
						for (List<String> deeperInnerList:listOfInnnerListClone){
							try{
								if(depthSize==deeperInnerList.size() && isEligibile(word1, deeperInnerList)){
//									if(depthSize==4 && deeperInnerList.contains("ப்")  && deeperInnerList.contains("ஆன்")  &&  deeperInnerList.contains("அ") ) {
//										System.out.println("");
//									}
									word2 =கடைஎழுத்து_கொடுக்கபட்வையில்_முடிந்தால்பிரி(word1, deeperInnerList,lastDeepList,outerKey,lastDeepParseList);
									if(word2[1]!=null && !word2[1].trim().equals(""))
									{
										lastParserOrder = valueToParseOrderMap.get(outerList).toString();
										break;
									}
								}
							}catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
				}
				List<String[]> checkValueArrList =new ArrayList<String[]>();				
				if(lastDeepList!=null && lastDeepList.size()>0) {
					Iterator<List<String>> iter = lastDeepList.keySet().iterator();
					List<String> key = null;
					while(iter.hasNext()) {
						key = (List<String>)iter.next();
						checkValueArrList = map.get(word0);
						if(checkValueArrList==null)
							checkValueArrList =new ArrayList<String[]>();
						checkValueArrList.add(lastDeepList.get(key));
						map.put(word0,checkValueArrList);
					}
				}else {
					word2[0] = TamilUtil.எழுத்துகளைசேர்(word2[0]);
					checkValueArrList.add(word2);
					map.put(word0,checkValueArrList);
				}
			  }
		}catch(Exception exp) {
			exp.printStackTrace();	
		}
		return map;
	}
	
	public static String[] கடைஎழுத்து_கொடுக்கபட்வையில்_முடிந்தால்பிரி(String word, List<String> deeperInnerList,Map<List<String>,String[]> lastDeepMap,
			String outerKey,Map<String,String> lastDeepParseList)
	{
		String[] existingWord=lastDeepMap.get(deeperInnerList);

		if(existingWord!=null)
		{
			return new String[]{existingWord[0],null};
		}
		
		/**
		 * Last Index contains in original word then proceed else return it.
		 * */
		if(deeperInnerList.size()>1) {
			int totalSize=deeperInnerList.size();
			String lastLetters= deeperInnerList.get(totalSize-2)+""+deeperInnerList.get(totalSize-1);
			lastLetters= TamilUtil.எழுத்துகளைபிரி(lastLetters, false, false);
			if( ! (lastLetters.contains("NOUN") ||lastLetters.contains("VERB"))) {
				 if( ! (word.endsWith(lastLetters)) ){
					totalCounter++;
					return new String[]{word,null};
				 }
			}
		}else if(deeperInnerList.size()>0){
			int totalSize=deeperInnerList.size();
			String lastLetters= deeperInnerList.get(totalSize-1);
			lastLetters= TamilUtil.எழுத்துகளைபிரி(lastLetters, false, false);
			if( ! (lastLetters.contains("NOUN") ||lastLetters.contains("VERB"))) {
				if(! (word.endsWith(lastLetters))) {
					return new String[]{word,null};
				}
			}
		}
		
		String strArray[]=new String[deeperInnerList.size()+1];
		strArray[0]= word;
		int index[]=new int[2];
		int oldIndex[]=new int[2];
		index[0]=0;index[1]=0;
		oldIndex[0]=0;oldIndex[1]=0;
		
		for(int count=1;count<=deeperInnerList.size();count++)
		{
			strArray[count]="";
			//Later stage split words used to check.
			deeperInnerList.set(count-1, TamilUtil.எழுத்துகளைபிரி(deeperInnerList.get(count-1), false, false));
 		}

		if(கடைஎழுத்து_கொடுக்கபட்வையில்_முடிகிறதா(word,deeperInnerList))
		{
			for(int count=0;count<=deeperInnerList.size();count++){
				try{
					if (count+1==deeperInnerList.size()){
						if(count-1==-1)
							strArray[count]=word.substring(0 ,word.lastIndexOf(deeperInnerList.get(count)));
						else
						{
							int tempCount=word.lastIndexOf(deeperInnerList.get(count));
							String tempWord=word.substring(0,tempCount);
							strArray[count]=word.substring(tempWord.lastIndexOf(deeperInnerList.get(count-1)),word.lastIndexOf(deeperInnerList.get(count)));
						}
					}
					else if (count==deeperInnerList.size() )
						strArray[count]=word.substring(word.lastIndexOf(deeperInnerList.get(count-1)));
					else if(indexMatch(word,deeperInnerList,deeperInnerList.get(count),deeperInnerList.get(count+1),index) )
					{	
						if(count==0)
							strArray[count]=word.substring(0, index[0]);
						else
						{
							indexMatch(word,deeperInnerList,deeperInnerList.get(count-1),deeperInnerList.get(count),oldIndex);
							strArray[count]=word.substring(oldIndex[0],oldIndex[1]);
						}
					}
					else
					{
						if(strArray[count]==null || strArray[count].equals(""))
						{
							indexMatch(word,deeperInnerList,deeperInnerList.get(count-1),deeperInnerList.get(count),oldIndex);
							strArray[count]=word.substring(oldIndex[0],oldIndex[1]);
						}
					}
					oldIndex[0]=index[0];
					oldIndex[1]=index[1];
				}catch(Exception exp)
				{
					exp.printStackTrace();
				}
				//System.out.println(strArray[count]);
			}
			String ignoreString=checkIgnoreList(strArray,deeperInnerList,lastDeepMap,outerKey,lastDeepParseList);
			if(ignoreString!=null)
			{
				strArray[0]=TamilUtil.எழுத்துகளைபிரி(ignoreString);
			}
			else
			{
				strArray[1]="";
			}
			return strArray;	
		}
		return strArray;
	}
	
 	public static String checkIgnoreList(String[] strArray,List<String> deapList,Map<List<String>,String[]> lastDeepMap,String outerKey,
 			Map<String,String> lastDeepParseList)
	{
		List<String> allList =new ArrayList<String>();
		allList .add(TamilUtil.எழுத்துகளைசேர்(strArray[0]));

		for(String str: allList)
		{
			String preFix  = staticTct.getPreFixList(str);
			String endValueModifiedStr = endWithCertianValues(str);
			String conditionalStr = staticTct.getConditionalProperty(str);
			String conditional = conditionalStr!=null && conditionalStr.contains("-") ? conditionalStr.substring(conditionalStr.indexOf("-")+1) : null;
			if(conditional!=null) {
				conditional = conditional.trim();
				outerKey= outerKey.replace("[","");
				outerKey= outerKey.replace("]","");
				if(outerKey.contains(",")) {
					String okSplit[]=outerKey.split(",");
					outerKey = okSplit[0].trim();
				} 
				if(outerKey.equals(conditional)) {
					//System.out.println(conditionalStr+":"+conditional+":"+outerKey+":"+conditionalStr.substring(0,conditionalStr.indexOf("-")));
					return conditionalStr.substring(0,conditionalStr.indexOf("-"));
				}
			} 
			
			if(preFix!=null)
				return str;
			else if(conditionalStr!=null && conditional==null && (staticTct.isInIgnoreVerbWordList(conditionalStr) 
						|| staticTct.isInIgnoreNounWordList(conditionalStr)) ) {
					return conditionalStr;	
			}else if(staticTct.isInIgnoreVerbWordList(str) || staticTct.isInIgnoreNounWordList(str))
				return str; 
			else if(endValueModifiedStr!=null && (staticTct.isInIgnoreVerbWordList(endValueModifiedStr) 
					|| staticTct.isInIgnoreNounWordList(endValueModifiedStr) )) {
				conditionalStr = staticTct.getConditionalProperty(endValueModifiedStr);
				return conditionalStr!=null?conditionalStr:endValueModifiedStr;
			}else if(str!=null && (staticTct.isInIgnorePersonList(str) || staticTct.isInIgnorePlaceList(str))  ){
				return str;
			}else{
				String strNewArray[]=new String[strArray.length];
				System.arraycopy(strArray,0,strNewArray,0,strArray.length);
				lastDeepMap.put(deapList,strNewArray);
				lastDeepParseList.put(deapList.toString(),outerKey);
			}
		}
		return null;
	}

	public static String endWithCertianValues(String orgWord) {
		String word = TamilUtil.எழுத்துகளைபிரி(orgWord);
		String modifiedWord = null;
		TamilStringIterator ist = new TamilStringIterator(orgWord);
		String lastValue = ist.last();
		if(TamilUtil.existInCheckList(lastValue,TamilConstants.வல்லினம்_மெய்யெழுத்து_வ்toற்_முடிகிறதா))
		{
			modifiedWord= TamilUtil.எழுத்துகளைசேர்(word+""+(char)TamilConstants.உ);
		}
		else if(word.endsWith("அ") )
		{
			modifiedWord = TamilUtil.எழுத்துகளைசேர்(word+""+TamilConstants.ம்);
			//If exists in Noun then add ம்
		}
		else if(word.endsWith(TamilConstants.ஞ்) )
		{
			modifiedWord = TamilUtil.எழுத்துகளைசேர்(word.replace(TamilConstants.ஞ், TamilConstants.ம்));
		}
		else if(word.endsWith(TamilConstants.ங்) )
		{
			modifiedWord = TamilUtil.எழுத்துகளைசேர்(word.replace(TamilConstants.ங், TamilConstants.ம்));
		}
		return modifiedWord;
	}

	public Object[] buildNAValues(String word2[], String checkValue,String value,TamilConstantTable tct,Map<List<String>,String[]> lastDeepMap)
	{
		Iterator<List<String>> iter=lastDeepMap.keySet().iterator();
		String[] unParseValue= null;
		List<String> unParseKey= null;
		Map<Integer,List<String[]>> mapOfListOfUnParseValue=new HashMap<Integer,List<String[]>>();
		List<String[]> listOfUnParseValue = new ArrayList<String[]>();
		int prevSize=0;

		while(iter.hasNext())
		{
			unParseKey= iter.next();
			if(unParseKey.size()>=prevSize)
			{
				prevSize=unParseKey.size();
			}
			unParseValue = lastDeepMap.get(unParseKey);
			listOfUnParseValue=mapOfListOfUnParseValue.get(unParseKey.size());
			if(listOfUnParseValue==null)
				listOfUnParseValue = new ArrayList<String[]>();
			listOfUnParseValue.add(unParseValue);
			mapOfListOfUnParseValue.put(unParseKey.size(), listOfUnParseValue);
		}
		List<String[]> checkValueArrList= new ArrayList<String[]>();
		try
		{
			while(prevSize!=0)
			{ 
				listOfUnParseValue=mapOfListOfUnParseValue.get(prevSize);
				if(listOfUnParseValue!=null)
				{
					for(String[] unParseValue1:listOfUnParseValue)
					{
						if(unParseValue1!=null && unParseValue1.length>0)
						{
							word2= new String[unParseValue1.length];
							System.arraycopy(unParseValue1,0,word2,0,unParseValue1.length);
							checkValue = (unParseValue1.length>0?TamilUtil.எழுத்துகளைசேர்(unParseValue1[0]):"");
							checkValueArrList.add(unParseValue1);
							if(unParseValue1[0].endsWith("ட்ட்"))
							{
								word2= new String[unParseValue1.length];
								System.arraycopy(unParseValue1,0,word2,0,unParseValue1.length);
								word2[0]= word2[0].replace("ட்ட்", "ட்");
								checkValue = (unParseValue1.length>0?TamilUtil.எழுத்துகளைசேர்(word2[0]):"");
								checkValueArrList.add(word2);
							}
						}
					}
				}
				prevSize--;
			}
		}catch(Exception exp){
			System.out.println(checkValue+":"+value);
			exp.printStackTrace();
		}
		return new Object[]{checkValue,value,word2,checkValueArrList};
	}

	public static boolean கடைஎழுத்து_கொடுக்கபட்வையில்_முடிகிறதாOld(String word, List<String> deeperInnerList)
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
	
	public static boolean கடைஎழுத்து_கொடுக்கபட்வையில்_முடிகிறதா(String word, List<String> deeperInnerList)
	{
		List<String> verbList = new ArrayList<String>();
		List<String> nounList = new ArrayList<String>();
		List<List<String>> resultListOfList = new ArrayList<List<String>>();
		List<List<String>> finalResultListOfList = new ArrayList<List<String>>();
		List<String>	finalResultList = new ArrayList<String>();
		List<String> resultMatchList = new ArrayList<String>();
		
		if(deeperInnerList.contains("VERB") || deeperInnerList.contains("NOUN")){
			verbList =staticTct.getIgnoreVerbList();
			nounList =staticTct.getIgnoreNounList();
			TamilMultiLoop tml = new TamilMultiLoop();
			resultListOfList = tml.loopMain(deeperInnerList, verbList, nounList);
		}
		
		String result ="";
		if(resultListOfList!=null && resultListOfList.size()>0) {
			for(List<String> resultList: resultListOfList){
				result = String.join("", resultList); 
				if(word.endsWith(result) && !resultMatchList.contains(result)){
					for(int count=0;count < deeperInnerList.size();count++) {
						deeperInnerList.set(count, resultList.get(count));
						finalResultList.add(resultList.get(count));
						//System.out.println("VERB:"+result+":"+deeperInnerList);
					}
					finalResultListOfList.add(finalResultList);
					finalResultList = new ArrayList<String>();
					resultMatchList.add(result);
				}
		  }
		  if(resultMatchList.size()>0) {
			  String temp ="";
			  int 	currIndex =0;
			  for(List<String> finalStrList:finalResultListOfList) {
				  if(temp.length()<finalStrList.toString().length()) {
					  temp = finalStrList.toString();
					  currIndex++;
				  }
			  }
			  deeperInnerList.clear();
			  deeperInnerList.addAll( finalResultListOfList.get(currIndex-1));
			  return true;
		  }
		}else {
			return கடைஎழுத்து_கொடுக்கபட்வையில்_முடிகிறதாOld(word, deeperInnerList);
		}
		return false;
	}
	
	public static boolean indexMatch(String word,List<String> deeperInnerList,String first, String second, int index[])
	{
		String joinRemaining = String.join("",deeperInnerList);
		String totalBegin = word.substring(0,word.indexOf(joinRemaining));
		
		int firstLast=word.indexOf(first,index[1]);
		if(firstLast==-1)
			firstLast= word.indexOf(first,index[0]);
		if(index[0]==0 && index[1] ==0) {
			int wordIndex = totalBegin.length();
			if(firstLast<wordIndex) {
				firstLast=word.indexOf(first,wordIndex);	
			}
		}
		
		int secondLast=0;
		if(first.equals(second))
		  secondLast=word.indexOf(second,firstLast)+second.length(); 
		else {
			if(first.contains(second))
				secondLast=word.indexOf(second,firstLast+first.length());
			else
				secondLast=word.indexOf(second,firstLast);
		}
		while(firstLast>0)
		{
			if(firstLast < secondLast) 
			{
				index[0]=firstLast;
				index[1]=secondLast;
				//System.out.println("***"+index[0]+":"+index[1]+":"+word.substring(index[0], index[1]) );
				return true;
			}
			else if(firstLast == secondLast) {
				int tempFirst = firstLast;
				int tempCount = firstLast+2;
				firstLast=word.lastIndexOf(first,firstLast+2);
				if(tempFirst == firstLast) {
					firstLast = tempCount;
				}
			}
			else
				firstLast=word.lastIndexOf(first,firstLast-1);
		}
		index[0]=0;
		index[1]=0;
		return false;
	}

 	public boolean isEligibile(String word,List<String> deeperInnerList) {
 		String str = String.join("", deeperInnerList);
 		return word.length()>str.length();
 	}

	public List<WordClass> createSingleInstance(String word,boolean writeToFile) throws Exception {
		Map<String,List<String[]>> map = null;
		List<WordClass> wcList = null;
		List<String> wordOfList = new ArrayList<String>();
		map = parse(Arrays.asList(word) ,0,false,false,wordOfList);
		if(map!=null && map.size()>0) {
			wcList = spu.buildWordClass(map, word,wordOfList,getParseMapProperty());
			if(writeToFile) {
				StringBuilder sb= spu.sortAndPrint(wcList);
				WriteToFile.writeToFile(sb,"modfiedResult.txt");
				writeOtherFiles(wcList);
			}
		}
		return  wcList;
	}
	
	public void writeOtherFiles(List<WordClass> mySortList) throws Exception {
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();
		StringBuilder sb4 = new StringBuilder();
		for(WordClass cl: mySortList) {
			if(!cl.getType().equals("NA"))
				sb1.append(cl.getWord()+":"+cl.getRawSplitList()).append("\n");
			//cl.getMapVals().remove("Type");
			//cl.getMapVals().remove("Key");
			if(!cl.getType().equals("NA"))
				sb2.append(cl.getWord()+"/"+cl.getAltValue() +":"+cl.getMapVals()).append("\n");
			if(!cl.getType().equals("NA"))
				sb3.append(cl.getWord()+":"+cl.getSplittedVal()).append("\n");
			if(cl.getType().equals("NA"))
				sb4.append(cl.getWord()+":"+cl.getSplittedVal()).append("\n");
		}
		WriteToFile.writeToFile(sb1,"correctParser.txt");
		WriteToFile.writeToFile(sb2,"grammarWithNo.txt");
		WriteToFile.writeToFile(sb3,"suffixWithGrammar.txt");
		WriteToFile.writeToFile(sb4,"incorrectParser.txt");
	}
	
 	void loopMain(List<String> deeperInnerList){
		List<String> verbList = staticTct.getIgnoreVerbList();
		List<String> nounList = staticTct.getIgnoreNounList();
		TamilMultiLoop tml = new TamilMultiLoop(); 
		tml.loopMain(deeperInnerList, verbList, nounList);
	}
}
