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

import org.apache.commons.lang.StringUtils;
import org.wotsoc.illakanam.TamilConstants;
import org.wotsoc.illakanam.TamilUtil;
import org.wotsoc.nlp.ConfigConstants;
import org.wotsoc.nlp.RecursiveAlgorithm;
import org.wotsoc.nlp.TamilConstantTable;
import org.wotsoc.nlp.TamilMultiLoop;
import org.wotsoc.util.SplittingUtil;
import org.wotsoc.util.TamilStringIterator;
import org.wotsoc.util.WordClass;
import org.wotsoc.util.WriteToFile;

/**
 * @author Rajamani David, Kamatchi
 * @since	Oct 21, 2017
 *
 */

@SuppressWarnings("unchecked")
public class TamilRootWordParser  extends CoreParser implements WordParserInterface
{
	static List<List<List<String>>> globalList =null;
	
	public static String mainWords[][]=null;
	static String mainConstantFileName =null;
	static List<List<Integer>> mainListOfList= null;
	static Properties parseMapProperty  = null; 
	static Map<List<Integer>,List<List<String>>> mainParseOrderToValue =new HashMap<> ();
	static Map<List<List<String>>,List<Integer>> mainValueToParseOrder =new HashMap<> ();


	static {
		try {
			staticTct=TamilConstantTable.getInstance();
			Object objArr[]= staticTct.getParseAndMainValue(ConfigConstants.MAIN_CONSTANT_FILE_NAME,ConfigConstants.PARSE_ORDER_FILE_NAME,ConfigConstants.MAIN_PARSE_MAP_FILE_NAME);
			mainWords  = (String[][])objArr[0];
			mainListOfList = (List<List<Integer>>)objArr[1];
			parseMapProperty  = (Properties)objArr[2];
			spu = new SplittingUtil(staticTct);
			globalList= staticTct.getMainTable(mainWords,mainListOfList,mainParseOrderToValue,mainValueToParseOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<List<List<String>>> splittedGlobalList(List<List<List<String>>> globalList, String splitWord,Map<List<List<String>>,List<Integer>>	splitValueToParseOrder){
		List<List<List<String>>> splittedOuterList 	= new ArrayList<>();
		List<List<String>> splittedInner1 			= null;
		List<String> splittedInner2 				= null;
		String splitStr 							= null;
		List<Integer>	valInteger 					= null;
		
		for(List<List<String>> listOfList:globalList){
			valInteger = mainValueToParseOrder.get(listOfList);
			splittedInner1 = new ArrayList<>();
			for(List<String> list:listOfList) {
				splittedInner2 = new ArrayList<>();
				for(String globalStr:list) {
					splitStr = TamilUtil.எழுத்துகளைபிரி(globalStr,false,false);
					if(splitWord.contains(splitStr) || globalStr.contains("VERB") || globalStr.contains("NOUN")) {
						splittedInner2.add(globalStr);
						
					}
				}
				if(!splittedInner2.isEmpty())
					splittedInner1.add(splittedInner2);
			}
			if(!splittedInner1.isEmpty() && !splittedInner2.isEmpty()) {
				splittedOuterList.add(splittedInner1);
				splitValueToParseOrder.put(splittedInner1,valInteger);	
			}
		}
		return splittedOuterList;
	}

	public Properties getParseMapProperty()
	{
		return parseMapProperty;
	}
	
	public Map<List<List<String>>,List<Integer>> getValueToParseOrder()
	{
		return mainValueToParseOrder;
	}
	
	public Map<List<Integer>,List<List<String>>> getParseOrderToValue()
	{
		return mainParseOrderToValue;
	}
	
	public TamilRootWordParser() throws Exception 
	{
//		if(staticTct==null){
//			staticTct=TamilConstantTable.getInstance();
//			spu = new SplittingUtil(staticTct);
//			globalList= staticTct.getMainTable();
//		}
	}

	public List<List<List<String>>> getGlobalList() {
		return globalList;
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
		List<String> verbList = new ArrayList<>();
		List<String> nounList = new ArrayList<>();
		List<List<String>> resultListOfList = new ArrayList<>();
		List<List<String>> finalResultListOfList = new ArrayList<>();
		List<String>	finalResultList = new ArrayList<>();
		List<String> resultMatchList = new ArrayList<>();
		
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
	
 	void loopMain(List<String> deeperInnerList){
		List<String> verbList = staticTct.getIgnoreVerbList();
		List<String> nounList = staticTct.getIgnoreNounList();
		TamilMultiLoop tml = new TamilMultiLoop(); 
		tml.loopMain(deeperInnerList, verbList, nounList);
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

	/**
	 * 
	 * Passed Word is checked against possible combination from mainConstant.list 
	 */
	static int totalCounter =0;
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
			for(int count=0;count<=deeperInnerList.size();count++)
			{
				try
				{
					if (count+1==deeperInnerList.size())
					{
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
	
	/**
	 * TODO Very complex method --Need Refactoring
	 * Find Start and End index numbers for given deeperInner String 
	 * */
	public static boolean indexMatch(String word,List<String> deeperInnerList,String first, String second, int index[])
	{
		String joinRemaining = String.join("",deeperInnerList);
		String totalBegin = word.substring(0,word.indexOf(joinRemaining));
		
		int firstLast=word.indexOf(first,index[0]);
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

	static Map<String,List<List<String>>> mapCache =new ConcurrentHashMap<String,List<List<String>>>();
	String verbNoun="";
	
	public boolean isSpecialCharacter(String word) {
		return staticTct.getSpecialProperty(word)==null?false:true;
	}
	
	public String getSpecialCharacter(String word) {
		return staticTct.getSpecialProperty(word);
	}
	
	public void printMainParseFile(Properties parseMapProperty) {
		staticTct.printMainParseProperty(parseMapProperty);
	}
	
 	public Map<String,List<String[]>> parse(List<String> word, int passCounter, boolean noDetails, boolean writeToFile,List<String>  wordOfList) throws Exception{
		String word1 =null;
		String word2[] =null;
		int counter=passCounter;
		verbNoun ="";
		String match="[.,;!\"?]*";
		TamilConstantTable tct=null; 
		tct =staticTct;
		if(word==null){
			word=tct.getWordList();
		}
		//Map<List<List<String>>,List<Integer>> valueToParseOrderMap= mainValueToParseOrder;
		String lastParserOrder=null;
		try{
			long endTime=System.currentTimeMillis();
			for(String word0:word){
				totalCounter = 0;
				/**
				 * Remove Special Characters
				 * */
				String orgWord = word0;
				word0 =word0.replaceAll(match, "");
				if(word0==null||word0.trim().equals("")){
					String value[] = new String[]{counter+"" ,":",":(Symbol) IgnoreList",":",orgWord,":",":"+orgWord};
					List<String[]> tempList= new ArrayList<>();
					tempList.add(value);
					mapOfList.put(word0,tempList);
					continue;
				}
				
				if(passCounter==0)
					counter++; //For Each word increments
				
				boolean hasAlphaNumeric = word0.matches(".*\\d+.*"); 
				if(StringUtils.isNumeric(word0) || hasAlphaNumeric){
					verbNoun = "NU";
					addIgnoreWords(word2, counter, word0, word0, verbNoun,noDetails,"IgnoreList");
					List<String> keyList = buildKeyValuesList(new String[] {word0,""});
					wordOfList.addAll(keyList);
					wordOfMap.put(word0, word0 +" " + keyList.toString());
					continue;
				}

				lastParserOrder=null;
				/**
				 * If word is in ignore list then ignore them to parse
				 * */
				boolean verbIgnore=false;
				boolean nounIgnore=false;
				boolean personIgnore= false;
				boolean placeIgnore= false;
				boolean otherIgnore= false;

				verbIgnore=tct.isInIgnoreVerbWordList(word0);
				nounIgnore=tct.isInIgnoreNounWordList(word0);
				personIgnore=tct.isInIgnorePersonList(word0);
				placeIgnore=tct.isInIgnorePlaceList(word0);
				otherIgnore=tct.isInIgnoreWordList(word0);
				
				word1 = word0;
				if (!( verbIgnore || nounIgnore || otherIgnore || personIgnore || placeIgnore)) {
					//First words Ends with அசோக,அசுர,அங்குல
					String tempWord = endWithCertianValues(word0);
					if(tempWord!=null) {
						word1 = tempWord;
						if(getParserType().equals("Verb")) {	
							verbIgnore=tct.isInIgnoreVerbWordList(word1);//போக,:போகம்,காண:காணம்,அழ:அழம்,சாக	:சாகர்
							if(tct.isInIgnoreNounWordList(word1)) {
								//Performance Reason
								EXIT_LOOP=2000000l;
							}
						}
						else if(getParserType().equals("Noun")) {
							nounIgnore=tct.isInIgnoreNounWordList(word1);//Think about Noun and Verb parser as separate call.
							if(tct.isInIgnoreVerbWordList(word1)) {
								//Performance Reason
								EXIT_LOOP=2000000l;
							}
						}
					}
				}
				
				if(verbIgnore)
					verbNoun="(V)";
				if(nounIgnore)
					verbNoun=verbNoun+"(N)";
				if(personIgnore)
					verbNoun=verbNoun+"(PR)";
				if(placeIgnore)
					verbNoun=verbNoun+"(PL)";
				if(otherIgnore)
					verbNoun=verbNoun+"(OTHER)";
				
				if(verbIgnore || nounIgnore || otherIgnore || personIgnore || placeIgnore){
					addIgnoreWords(word2, counter, word0, word1, verbNoun,noDetails,"IgnoreList");
					List<String> keyList = buildKeyValuesList(new String[] {word1,""});
					wordOfList.addAll(keyList);
					wordOfMap.put(word0, word1 +" " + keyList.toString());
					continue;
				}

				/*****Comment this If you dont need for tomorrow*/
				  /**
				   * Before going deeper check we can do anything with original word
				   * */
				
//				Object objArr[]=buildRegularValues(word0,tct);
//				if(objArr!=null && (boolean)objArr[1]==true)
//				{
//					addIgnoreWords(word2, counter, word0, (String)objArr[0],(String)objArr[2],noDetails);
//					wordOfList.add((String)objArr[0]);
//					wordOfMap.put(word0,(String)objArr[0]);
//					continue;
//				}
				/**END**/
				try
				{
					word2 =null;
					word1=TamilUtil.எழுத்துகளைபிரி(word0,false,false);
					System.out.println(word0+":"+word1);

					Map<List<String>,String[]> lastDeepList =new LinkedHashMap<>();
					Map<String,String> lastDeepParseList =new LinkedHashMap<>();

					List<String> splitList = getSplitWordList(word1);
					
					List<List<List<String>>> globalList=(ArrayList<List<List<String>>>) ((ArrayList<List<List<String>>>)getGlobalList()).clone();

					/**
					 * Performance reason - if word's split value contains in global list then include- It help RecursiveAlgorithm.
					 * */
					//Map<List<List<String>>,List<Integer>>	splitValueToParseOrder = new HashMap<List<List<String>>,List<Integer>>(); 
					//List<List<List<String>>> splittedGlobalList = splittedGlobalList(globalList,word1,splitValueToParseOrder);
					
					for (List<List<String>> outerList:globalList)
					{
						/**
						 * Performance Reason, Last chars of outerList should matched with splitted word then go inside.
						 * */
						if(!containsEnding(outerList, splitList)){
							continue;
						}
						if(word2!=null && word2[1]!=null && !word2[1].trim().equals(""))
							break;

						synchronized (outerList){
							List<List<String>> listOfInnnerList = new ArrayList <>();
							listOfInnnerList=mapCache.get(outerList+"");
//							if(listOfInnnerList==null || outerList.toString().contains("NOUN") || outerList.toString().contains("VERB")){
//								if(outerList.size()>4) {
//									String outerKey= staticTct.getValueToParseOrder().get(outerList).toString();
//									ReadAndWriteParseOrderList rwp = new ReadAndWriteParseOrderList();
//									listOfInnnerList = rwp.read(outerKey+".txt");
//								}
//							}
							if(listOfInnnerList==null || outerList.toString().contains("NOUN") || outerList.toString().contains("VERB")){
								RecursiveAlgorithm ra = new RecursiveAlgorithm();
								listOfInnnerList = ra.getAllCombinedValues(outerList);
								//listOfInnnerList=StoreRecursiveConstants.getFromCacheByKey(valueToParseOrderMap.get(outerList).toString());
							}
							mapCache.put(outerList+"",listOfInnnerList);
							String outerKey = getValueToParseOrder().get(outerList).toString();
							//String outerKey = splitValueToParseOrder.get(outerList).toString();
							
							int depthSize=outerList.size();
							System.out.println(outerKey+":"+"OuterList Size:"+listOfInnnerList.size()+":depthSize:"+depthSize+",TotalCount:"+totalCounter);
							try{
								List<List<String>>  listOfInnnerListClone = (List<List<String>>)((ArrayList)(listOfInnnerList)).clone();
								for (List<String> deeperInnerList:listOfInnnerListClone){
										if(deeperInnerList!=null && depthSize==deeperInnerList.size() && isEligibile(word1, deeperInnerList)){
											word2 =கடைஎழுத்து_கொடுக்கபட்வையில்_முடிந்தால்பிரி(word1, deeperInnerList,lastDeepList,outerKey,lastDeepParseList);
											if(word2[1]!=null && !word2[1].trim().equals(""))
											{
												lastParserOrder=getValueToParseOrder().get(outerList).toString();
												//lastParserOrder=splitValueToParseOrder.get(outerList).toString();
												break;
											}
										}
									if(totalCounter>EXIT_LOOP) {//Break inner loop later this will break outer too.
										break;
									}
								}
							}catch(Exception e)
							{
								e.printStackTrace();
							}
						} 
						if(totalCounter>EXIT_LOOP) {
							System.out.println(word+":T Break totalCounter:"+totalCounter);
							totalCounter =0;
							break;
						}
						if(word2!=null && word2[1]!=null && !word2[1].trim().equals(""))
							break;
					}
					if(word2==null) {
						addIgnoreWords(word2, counter, word0, word1, verbNoun,noDetails,"Parse Order Not Found");
					}else {
						List<String[]> checkValueArrList =new ArrayList<>();
						groupWords(word2, counter,word0, word1,tct,lastDeepList,lastDeepParseList,lastParserOrder,checkValueArrList,wordOfList);
					}
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
				totalCounter =0;
			}
			endTime = System.currentTimeMillis()-endTime;
			System.out.println(endTime);
		}catch(Exception exp)
		{
			exp.printStackTrace();
		}

		if(writeToFile)
			WriteToFile.writeToFile(spu.printMap(mapOfList));
		spu.printMapValues(mapOfList);
		return mapOfList;
	}
 	
 	public List<String> getSplitWordList(String splitWord){
 		TamilStringIterator tsi = new TamilStringIterator(splitWord);
		return  tsi.backwardIterator();
 	}
 	
 	
 	public boolean containsEnding(List<List<String>> outerList, List<String> splitList) {
 		if(splitList.size() >= outerList.size()) {
	 		if(outerList.size() >= 1 && containsEnding(outerList, splitList,1)) {
	 			if(outerList.size() >= 2 && containsEnding(outerList, splitList,2)) {
	 				if(outerList.size() >= 3 && containsEnding(outerList, splitList,3)) {
	 					return true;
	 				}
	 			}
	 			return true;
	 		}
 		}
 		return false;
 	}
 	
 	/**
 	 * Performance reason check given splitted string existing in parseOrder before 
 	 * recursive call.
 	 * */
 	public boolean containsEnding(List<List<String>> outerList, List<String> splitList, int size) {
 		List<String> lastList = outerList.get(outerList.size()-size);
 		//Don't know what is inside noun or Verb so ingore it - Performance
 		if(lastList.contains("NOUN") || lastList.contains("VERB"))
 			return true;
 					
 		String temp = "";
 		String tempJoin = "";
 		int counter =0;
 		/**
 		 * Length of biggest Parse Order is 5 இல்லாமல், so exit after that.
 		 * */
 		for(String split:splitList) {
 			counter++;
 			temp = split + temp;
 			tempJoin = TamilUtil.எழுத்துகளைசேர்(temp);
			if(lastList.contains(tempJoin))
	 			return true;
			//Ignore the very first letter as it should be noun or verb -Performance
			if(splitList.size()-1<counter)
				return false;
			//Check Depth upto 4 then quit -Performance
			if(counter>7)
				return false;
		}
 		return false;
 	}
 	
	Map<String,List<String[]>> mapOfList = new ConcurrentHashMap<String,List<String[]>>();
	Map<String,String>  wordOfMap = new HashMap<String,String>();

	public void addIgnoreWords(String[] word2, int counter, String word0, String word1, String verbOrNoun, boolean noDetails,String hint)
	{
		String value[] = new String[]{counter+"" ,":",":(", verbOrNoun, ") "+hint,":",word0.trim(),":"+word1.trim()};
		if(noDetails)
			value[0] = word0.trim();
		
		List<String[]> listOfString= mapOfList.get(word0);
		if(listOfString==null)
			listOfString = new ArrayList<String[]>();
		listOfString.add(value);
		mapOfList.put(word0,listOfString);
	}

	public boolean groupWords(String[] word2, int counter, String word0, String word1,TamilConstantTable tct,
			Map<List<String>,String[]> lastDeepMap,Map<String,String> lastDeepParseList,String lastParserOrder,
			List<String[]> checkValueArrList,List<String>  wordOfList) throws Exception 
	{
		List<String> keyList =new ArrayList<String>();
		String value ="";
		List<String> valueList =new ArrayList<String>();
		String addValue ="";
		Map<String,String> addValueMap =new HashMap<String,String>();

		String verbOrNoun="";
		value = (":"+word0.trim()+":"+word1.trim()+":");
		String checkValue =null;

		if(word2[0]!=null)
			checkValue = (word2.length>0?TamilUtil.எழுத்துகளைசேர்(word2[0]):"");

		List<String[]> strArrList=new ArrayList<String[]>();
		Object arr[]=null;
		if(word2[1]==null || word2[1].trim().equals(""))
		{
			arr=buildNAValues(word2, checkValue, value,tct,lastDeepMap);
			checkValue =(String)arr[0];
			value=(String)arr[1];
			word2 = (String[])arr[2];
			checkValueArrList.addAll((List<String[]>)arr[3]);
			addValue=(tct.isInIgnoreWordList(checkValue)?"(IgnList)":"")+"(NA)";
			String strArrJoin[] = null;
			if( checkValueArrList!=null && checkValueArrList.size()>0)
			{
				for(String[] strArr:checkValueArrList)
				{
					strArrJoin=buildRegularValues(strArr,tct, TamilUtil.எழுத்துகளைசேர்(strArr[0]), value,":","");
					strArrList.add(strArrJoin);
					if(tct.isInIgnoreNounWordList(strArrJoin[0])) {
						addValueMap.put(strArrJoin[0],"(N)");
						strArr[0] = TamilUtil.எழுத்துகளைபிரி(strArrJoin[0]);
					}else if(tct.isInIgnoreVerbWordList(strArrJoin[0])) {
						addValueMap.put(strArrJoin[0],"(V)");
						strArr[0] = TamilUtil.எழுத்துகளைபிரி(strArrJoin[0]);
					}else if(tct.isInIgnorePersonList(strArrJoin[0])) {
						addValueMap.put(strArrJoin[0],"(PR)");
						strArr[0] = TamilUtil.எழுத்துகளைபிரி(strArrJoin[0]);
					}else if(tct.isInIgnorePlaceList(strArrJoin[0])) {
						addValueMap.put(strArrJoin[0],"(PR)");
						strArr[0] = TamilUtil.எழுத்துகளைபிரி(strArrJoin[0]);
					}else if(tct.isInIgnoreWordList(strArrJoin[0])) {
						addValueMap.put(strArr[0],"(UNKN)(NA)");
					}else {
						addValueMap.put(strArr[0],"(NA)");
					}
					
					keyList=buildKeyValuesList(strArr,strArrJoin[0],value);
					valueList.add(value+keyList.toString());
					wordOfList.addAll(keyList);
					wordOfMap.put(word0,keyList.toString());
				}
			}
			else
			{
				checkValue =(String)arr[0];
				value=(String)arr[1];
				keyList=buildKeyValuesList(word2,"",value);
				value= value+ keyList;
				if(keyList.toString().trim().equals(""))
				{
					arr[0]=TamilUtil.எழுத்துகளைபிரி((String)arr[0]);
					arr = buildRegularValues(arr,tct, checkValue, value,":","");
					checkValue =(String)arr[0];
					value= value.trim() + checkValue;
				}
 				wordOfList.addAll(keyList);
				wordOfMap.put(word0,checkValue +" " +keyList.toString().trim());
			}
		}
		else
		{
			arr=buildRegularValues(word2, tct,checkValue, value,"",":");
			checkValue =(String)arr[0];
			value=(String)arr[1];
			keyList = buildKeyValuesList(word2,"",value);
			value= value + keyList.toString();
			if(keyList.toString().equals(""))
				value= value.trim() + checkValue;
			wordOfList.addAll(keyList);
			wordOfMap.put(word0,checkValue +" " +keyList.toString());
		}

		List<String[]> listOfString= mapOfList.get(word0);
		if(listOfString==null)
			listOfString = new ArrayList<String[]>();

		String finalValue[] = new String[8];
		if( checkValueArrList!=null && checkValueArrList.size()>0)
		{
			String[] strArr=null;
			for(int i=0;i<checkValueArrList.size();i++)
			{
				strArr=checkValueArrList.get(i);
				finalValue = new String[8];
				verbOrNoun=findVerbOrNounFromSplitResidue(strArr,tct,strArr[0]);
				finalValue[0]=counter+"";
				finalValue[1]=":";
				if(lastParserOrder!=null)
					finalValue[2]=lastParserOrder;
				else {
					if(strArr.length>0) {
						StringBuilder sb = new StringBuilder();
						sb.append("[");
						for(int count=1;count<strArr.length;count++) {
							sb.append(strArr[count]);
							if(count+1<strArr.length)
								sb.append(", "); 
						}
						sb.append("]");
						finalValue[2]=lastDeepParseList.get(sb.toString());
					}
				}
				finalValue[3]=":"; 
				finalValue[4]=addValueMap.get(strArr[0]);
				finalValue[5]="";
				finalValue[6]=verbOrNoun;
				finalValue[7]=valueList.get(i);
				listOfString.add(finalValue);
			}
		}
		else
		{
			verbOrNoun=findVerbOrNounFromSplitResidue(word2,tct,checkValue);
			finalValue[0]=counter+"";
			finalValue[1]=":";
			finalValue[2]=lastParserOrder;
			finalValue[3]=":";
			finalValue[4]=addValue;
			finalValue[5]="";
			finalValue[6]=verbOrNoun;
			finalValue[7]=value;
			listOfString.add(finalValue);
		}
		
		mapOfList.put(word0,listOfString);
		return String.join("",finalValue).contains("(NA)");
	}

	public Object[] buildRegularValues(String value,TamilConstantTable tct)
	{
		String checkValue = TamilUtil.எழுத்துகளைபிரி(value); 
		Boolean flag = true;
		
		if(checkValue!=null && checkValue.endsWith("அ") )
		{
			checkValue= TamilUtil.எழுத்துகளைசேர்(checkValue+""+TamilConstants.ம்);
		}else{
			return new Object[]{checkValue,false,""};	
		}
		boolean verbIgnore=false;
		boolean nounIgnore=false;
		verbIgnore=tct.isInIgnoreVerbWordList(checkValue);
		nounIgnore=tct.isInIgnoreNounWordList(checkValue);
		boolean otherIgnore=tct.isInIgnoreWordList(checkValue);
		String verbOrNoun="";
		if(verbIgnore || nounIgnore)
		{
			flag=true;
			if(verbIgnore)
				verbOrNoun="(V)";
			if(nounIgnore)
				verbOrNoun=verbOrNoun+"(N)";
			if(otherIgnore)
				verbOrNoun=verbOrNoun+"(UNK)";
		}
		else
		{
			checkValue=value;
			flag =false;
		}
		return new Object[]{checkValue,flag,verbOrNoun};
	}

	public String buildKeyValues(String[] word2,String key,String value){
		key = key + " "; 
 		key = key + (word2.length>1?TamilUtil.எழுத்துகளைசேர்(word2[1]):"")+(" ");
		key = key + (word2.length>2?TamilUtil.எழுத்துகளைசேர்(word2[2]):"")+(" ");
		key = key + (word2.length>3?TamilUtil.எழுத்துகளைசேர்(word2[3]):"")+(" ");
		key = key + (word2.length>4?TamilUtil.எழுத்துகளைசேர்(word2[4]):"")+(" ");
		key = key + (word2.length>5?TamilUtil.எழுத்துகளைசேர்(word2[5]):"")+(" ");
		key = key + (word2.length>6?TamilUtil.எழுத்துகளைசேர்(word2[6]):"")+(" ");
		key = key + (word2.length>7?TamilUtil.எழுத்துகளைசேர்(word2[7]):"")+(" ");
		key = key + (word2.length>8?TamilUtil.எழுத்துகளைசேர்(word2[8]):"")+(" ");
		key = key + (word2.length>9?TamilUtil.எழுத்துகளைசேர்(word2[9]):"")+(" ");
		key = key + (word2.length>10?TamilUtil.எழுத்துகளைசேர்(word2[10]):"")+(" ");
		//value=value+key;
		return key;
	}
	
	public List<String> buildKeyValuesList(String[] word2){
		List<String> keyList = new ArrayList<String>();
		if(word2.length>1) {
			String tempStr= word2[0];
			keyList.add(tempStr); 
			word2[0] = tempStr;
		}
		keyList.add(word2.length>1?word2[1]:"");
 		keyList.add(word2.length>2?word2[2]:"");
		keyList.add(word2.length>3?word2[3]:"");
		keyList.add(word2.length>4?word2[4]:"");
		keyList.add(word2.length>5?word2[5]:"");
		keyList.add(word2.length>6?word2[6]:"");
		keyList.add(word2.length>7?word2[7]:"");
		keyList.add(word2.length>8?word2[8]:"");
		keyList.add(word2.length>9?word2[9]:"");
		keyList.add(word2.length>10?word2[10]:"");
		keyList.add(word2.length>11?word2[11]:"");
		keyList.add(word2.length>12?word2[12]:"");
		return keyList;
	}
	
	public List<String> buildKeyValuesList(String[] word2,String key,String value){
		List<String> keyList = new ArrayList<String>();
		if(word2.length>1) {
			String tempStr= TamilUtil.எழுத்துகளைசேர்(word2[0]);
			keyList.add(tempStr); 
			word2[0] = tempStr;
		}
		keyList.add(word2.length>1?TamilUtil.எழுத்துகளைசேர்(word2[1]):"");
 		keyList.add(word2.length>2?TamilUtil.எழுத்துகளைசேர்(word2[2]):"");
		keyList.add(word2.length>3?TamilUtil.எழுத்துகளைசேர்(word2[3]):"");
		keyList.add(word2.length>4?TamilUtil.எழுத்துகளைசேர்(word2[4]):"");
		keyList.add(word2.length>5?TamilUtil.எழுத்துகளைசேர்(word2[5]):"");
		keyList.add(word2.length>6?TamilUtil.எழுத்துகளைசேர்(word2[6]):"");
		keyList.add(word2.length>7?TamilUtil.எழுத்துகளைசேர்(word2[7]):"");
		keyList.add(word2.length>8?TamilUtil.எழுத்துகளைசேர்(word2[8]):"");
		keyList.add(word2.length>9?TamilUtil.எழுத்துகளைசேர்(word2[9]):"");
		keyList.add(word2.length>10?TamilUtil.எழுத்துகளைசேர்(word2[10]):"");
		keyList.add(word2.length>11?TamilUtil.எழுத்துகளைசேர்(word2[11]):"");
		keyList.add(word2.length>12?TamilUtil.எழுத்துகளைசேர்(word2[12]):"");
		return keyList;
	}

	List<String> verbResidueList =Arrays.asList("க்கின்ற்","கின்ற்","க்கிற்","கிற்","ப்ப்","ப்","வ்","ந்த்","இன்","இ","க்க்","ட்","த்","ஈ");//0 Verb
	//List<String> nounResidue1List =Arrays.asList("ய்","வ்");		
	//List<String> nounResidue1List =Arrays.asList("");		//12 Noun	
	List<String> nounResidueList =Arrays.asList("கள்","ஐ","கு","ஆல்","ஓடு","ஒடு","இடம்","உடன்","இன்","அது","ஓட்","ஒட்","த்து");	  //13 Noun	

	public String[] buildRegularValues(Object word2[],TamilConstantTable tct, String checkValue,String value,String frontDelimitor,String backDelimitor)
	{
		TamilStringIterator ist = new TamilStringIterator(checkValue);
		String lastValue = ist.last();
		if(word2[0]!=null && TamilUtil.existInCheckList(lastValue,TamilConstants.வல்லினம்_மெய்யெழுத்து_வ்toற்_முடிகிறதா))
		{
			if(frontDelimitor.equals(""))
				value = value +  checkValue + backDelimitor +(" ");
			checkValue= (word2.length>0?TamilUtil.எழுத்துகளைசேர்(word2[0]+""+(char)TamilConstants.உ):"");
			value = value + checkValue +(" ");
		}
		else if(word2[0]!=null && TamilUtil.எழுத்துகளைபிரி(checkValue).endsWith("அ") )
		{
			if(frontDelimitor.equals(""))
				value = value +  checkValue + backDelimitor +(" ");
			String tempValue= (word2.length>0?TamilUtil.எழுத்துகளைசேர்(word2[0]+""+TamilConstants.ம்):"");
			//If exists in Noun then add ம்
			if(tct.isInIgnoreNounWordList(tempValue))
			{
				checkValue= (word2.length>0?TamilUtil.எழுத்துகளைசேர்(word2[0]+""+TamilConstants.ம்):"");
			}
			
			value = value + checkValue +(" ");
		}
		else if(word2[0]!=null && TamilUtil.எழுத்துகளைபிரி(checkValue).endsWith(TamilConstants.ஞ்) )
		{
			checkValue = checkValue.replace(TamilConstants.ஞ், TamilConstants.ம்);
			value = value +  checkValue + backDelimitor +(" ");
		}
		else if(word2[0]!=null && TamilUtil.எழுத்துகளைபிரி(checkValue).endsWith(TamilConstants.ங்) )
		{
			checkValue = checkValue.replace(TamilConstants.ங், TamilConstants.ம்);
			value = value + checkValue + backDelimitor +(" ");
		}
		else
		{
			value = value + checkValue +(" ");
		}
		return new String[]{checkValue,value,null};
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
			if(staticTct!=null && staticTct.isInIgnoreVerbWordList(orgWord))
				return word;	
			
			//If exists in Noun then add ம் or ர்
			 modifiedWord = TamilUtil.எழுத்துகளைசேர்(word+""+TamilConstants.ம்);
			 if(staticTct!=null && staticTct.isInIgnoreNounWordList(modifiedWord))
				return modifiedWord;	
			   
			modifiedWord = TamilUtil.எழுத்துகளைசேர்(word+""+TamilConstants.ர்);
			if(staticTct!=null && staticTct.isInIgnoreNounWordList(modifiedWord))
				return modifiedWord;
			
			modifiedWord = TamilUtil.எழுத்துகளைசேர்(word+""+TamilConstants.ன்);
			if(staticTct!=null && staticTct.isInIgnoreNounWordList(modifiedWord))
				return modifiedWord;
			
			return null;
		}
		else if(word.endsWith(TamilConstants.ஞ்) )
		{
			word = word.substring(0, word.lastIndexOf(TamilConstants.ஞ்)) + TamilConstants.ம்;
			modifiedWord = TamilUtil.எழுத்துகளைசேர்(word);
		}
		else if(word.endsWith(TamilConstants.ங்) )
		{
			word = word.substring(0, word.lastIndexOf(TamilConstants.ங்)) + TamilConstants.ம்;
			modifiedWord = TamilUtil.எழுத்துகளைசேர்(word);
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
 
	public static void mainOld(String args[]) throws Exception{
		TamilMultiLoop  trwp=new TamilMultiLoop();
		List<String>  deeperInnerList= new ArrayList<String>();
		deeperInnerList.add("வ");
		deeperInnerList.add("[VERB]");
		deeperInnerList.add("அன்");
		deeperInnerList.add("[NOUN]");
		deeperInnerList.add("அர்" );
		trwp.loopMain(deeperInnerList);
	}
	
	public StringBuilder createMultipleInstance(List<String> wordList) throws Exception {
		Map<String,List<String[]>> map = null;
		List<String> wordOfList = null;
		List<WordClass> mySortList = new ArrayList<>();
		int index =0;
		if(wordList==null || wordList.isEmpty()) {
			wordList=staticTct.getWordList();
		}
		for(String word:wordList) {
			wordOfList = new ArrayList<>();
			map = parse(Arrays.asList(word) ,0,false,false,wordOfList);
			spu.buildSortList(map, word,wordOfList,mySortList,index,getParseMapProperty()); 
			index= index + 1;
		}
		StringBuilder sb= spu.sortAndPrint(mySortList);
		writeOtherFiles(mySortList);
		return sb;
	}

	public static void main(String args[]) throws Exception
	{
		TamilRootWordParser  trwp=new TamilRootWordParser();
		//String strArray[]= new String[]{"வ","ந்த்","அன்","அர்","ஆ"};
		//System.out.println(TamilRootWordParser.checkIgnoreList(strArray));
		 
		StringBuilder str= new StringBuilder();
		str.append("வாழ்த்துகிறார்கள்");

//		str.append("இன்றைக்கு சோழ நாட்டு மக்கள் இவர் பெயரைக் கூறி வாழ்த்துகிறார்கள். இவரைச் சிங்காதனம் ஏற வேண்டும் என்று வற்புறுத்துகிறார்கள். பொது ஜனங்களின் மனம் இப்படியே என்றுமிருக்குமா? மக்களின் உள்ளம் அடிக்கடி சலிக்கும் இயல்புடையதல்லவா? இதே ஜனங்கள் நாளைக்கு இவர் பேரிலேயே பழி கூறினாலும் கூறுவார்கள். சிங்காதனம் ஏறுவதற்காகச் சித்தப்பன் மதுராந்தகனைக் கொலை செய்வித்த பாதகன் என்று சொன்னாலும் சொல்வார்கள். ஏன்? கடம்பூர் மாளிகையில் ஆதித்த கரிகாலர் இறந்ததற்குக் கூடத் தம் பேரில் பழி சுமத்தினாலும் சுமத்துவார்கள்.தெய்வமே! இத்தகைய பயங்கரமான பழிகளைச் சுமப்பதற்காகவா என்னைக் காவேரியில் முழுகிச் சாகாமல் மந்தாகினி தேவி காப்பாற்றினாள்? இன்று தெய்வமாகியிருக்கும் அந்தப் பெண்ணரசிதான் இந்த இக்கட்டான நிலையிலிருந்து என்னைக் காப்பாற்ற வேண்டும். வாழ்க்கையில் ஒருவன் அடையக்கூடிய அபகீர்த்திகளில் மிகக் கொடிய அபகீர்த்தி என்னைச் சாராமல் தடுத்து அருள் புரிய வேண்டும்.");
//		str.append("ஈழநாட்டு இராஜ வம்சத்தில் நெருங்கிய உறவினர் ஒருவரையொருவர் கொன்று விட்டுச் சிங்காதனம் ஏறிய வரலாறுகள் அருள்மொழிவர்மனின் உள்ளத்தில் பதிந்திருந்தன. ஆகையால் அம்மாதிரியான இழிவைத் தரும் அபகீர்த்தி தன்னை யும் அடையலாம் என்ற எண்ணமே அவருக்குச் சகிக்க முடியாத வேதனையை உண்டாக்கிற்று. யாரிடமாவது தம் அந்தரங்கத்தைச் சொல்லி யோசனை கேட்கலாம் என்றால், அதற்கும் தகுதியுள்ளவராக யாரும் தென்படவில்லை. அவரைச் சுற்றியுள்ளவர்கள் அனைவருமே அவருக்கு விரோதமாகச் சதி செய்கிறார்கள் என்று தோன்றியது. அவர்களில் சிலர் உண்மையாகவே அவரிடம் விரோத பாவம் கொண்டிருந்தார்கள். மற்றும் சிலர் அவருக்கு நன்மை செய்வதாக எண்ணிக் கொண்டு பயங்கரமான பழியை அவர் தலையில் சுமத்திவிடுவதற்குப் பிரயத்தனம் செய்து கொண்டிருந்தார்கள்."); 
//		str.append("இந்த நிலைமையில் யாரை நம்புவது, தம் அந்தரங்கத்தை யாரிடம் வெளியிட்டு ஆலோசனை கேட்பது என்று அவரால் நிர்ணயம் செய்யமுடியவில்லை. ஏன்? அவரிடம் இணையில்லா அன்பு கொண்டவரும் அவருடைய பக்திக்கு உரியவருமான தமக்கை குந்தவைப் பிராட்டியிடங்கூட அவருக்கு நம்பிக்கை குன்றிவிட்டது. அவரும் தனக்குத் தெரியாமல் ஏதோ காரியம் செய்து கொண்டிருப்பதாகத் தோன்றியது. ஏன்! அவருடைய உயிருக்கு உயிரான வானதிகூட அல்லவா அவருக்குத் தெரியாமல் எதையோ மறைத்து வைக்கப் பார்க்கிறாள்? திருட்டுத்தனமாக எங்கேயோ போய்விட்டு மர்மமான முகபாவத்துடன் திரும்பி வருகிறாள்?...."); 
		//trwp.parse(Arrays.asList("எப்படிப்பட்டவனாயிருந்தாலும்" ,"எப்பேர்ப்பட்டவனாயிரு","எப்பேர்ப்பட்டவனாயிர்","எப்படிப்பட்டவனா"),0,false,true);
		 
		//பார்த்தான் ராஜாமணீ      ன்=n  ண்=N  ந்=W  ர்=r  ற்=rr அ=a  ஆ=aa  எ=e  ஏ=ee இ=i  ஈ=ii  ஒ=o  ஓ=oo  ஐ=ai ஔ=au உ=u  ஊ=uu 
		//Map<String,List<String[]>> map= trwp.parse(Arrays.asList("எப்படிப்பட்டவனாயிருந்தாலும்"."உயிரைக்கொடுக்கக்கூடியவர்கள்"),0,false,true,wordOfList);
		//Map<String,List<String[]>> map=trwp.parse(Arrays.asList("எடுக்கின்றான்","பார்ப்பான்","தூங்குவாள்","மரங்களா","அவனைப்","","பார்","","அவனுடைய","கை","வந்தவர்கள்","","எல்லாம்","போனார்கள்","அவனுக்குப்","பார்த்தல்","வேலை"),0,false,true,wordOfList);
		//Map<String,List<String[]>> map= trwp.parse(Arrays.asList("வழங்கினான்","மகளின்","மலரை","மலர்ந்தது","பறவையின்","உலகத்தை","வழங்கினான்","வழங்குவான்"),0,false,true,wordOfList);
		//"பார்த்தேன்","எடுப்பான்","வென்றான்","கற்றான்","வருகிறான்","செத்தான்","வெந்தது","வந்தான்","தந்தான்","நொந்தது","கேட்டான்","கேட்ப்பான்","சென்றான்","விற்றான்"	
		//Map<String,List<String[]>> map= trwp.parse(Arrays.asList("மூவேந்தர்","முத்தமிழ்","வாழைப்பழம்","அப்பழம்","முத்தமிழ்"),0,false,true,wordOfList);
		//map= trwp.parse(Arrays.asList("முத்தமிழ்","மூவேந்தர்"),0,false,true,wordOfList);"வந்தவர்கள்",
		//Map<String,List<String[]>> map= trwp.parse(Arrays.asList("அவனை"),0,false,false,wordOfList); //"தின்று","வாங்கு","நடு",,"மண்ணில"
		//Map<String,List<String[]>> map= trwp.parse(null,0,false,true,wordOfList);//"இப்பையன்", "அப்பையன்"உள்ளத்திலே","வந்தவர்கள்
		//Map<String,List<String[]>> map= trwp.parse(Arrays.asList("கூறி"),0,false,true,wordOfList);
		//Map<String,List<String[]>> map= trwp.parse(Arrays.asList("வந்தான்","மூவேந்தர்","வாழைப்பழம்","அப்பழம்","முத்தமிழ்"),0,false,true,wordOfList);//"இப்பையன்", "அப்பையன்"
		//Map<String,List<String[]>> map= trwp.parse(Arrays.asList("வந்தான்","பார்த்தான்","முன்னீடு","வழங்கினான்","பழத்தை","தூங்கினான்","மலர்ந்தது","இப்பையனின்"),0,false,true,wordOfList);//"இப்பையன்", "அப்பையன்"
		long beginTime = System.currentTimeMillis();
		//Map<String,List<String[]>> map= trwp.parse(Arrays.asList("கடா","எலி","சாவு ","தின்று", "வாங்கு","போக", "எழு","நடு","கண்ணைப்","கண்டேன்"),0,false,false,wordOfList);
		//Map<String,List<String[]>> map= trwp.parse(Arrays.asList("கடாவரைக்கும்","இப்பையன்","கண்டேன்","கண்கள்") ,0,false,false,wordOfList);
		
		List<String> wordOfList= new ArrayList<>();
		//wordOfList.add("அக்கரைக்கு");
		trwp.createMultipleInstance(wordOfList);
		//Map<String,List<String[]>> map= trwp.parse(Arrays.asList("கண்ணைப்","கண்டேன்","தூக்கத்தில்","வழங்கினான்","மகளின்","மலரை","மலர்ந்தது","பறவையின்","உலகத்தை","வழங்கினான்","வழங்குவான்"),0,false,true,wordOfList);
		 
		
		//Map<String,List<String[]>> map= trwp.parse(Arrays.asList("பன்னிரண்டு","முன்னீடு","பையனின்","இப்பையன்","இப்பையனின்"),0,false,true,wordOfList);//"இப்பையன்", "அப்பையன்"
		//Map<String,List<String[]>> map= trwp.parse(Arrays.asList("இப்பையன்","அப்பையன்"),0,false,true,wordOfList);
//		TODO 1. பார்த்தேன் எடுப்பான் , 2. Prefix
//		3. "தந்தான்","வந்தான்","வெந்தது","நொந்தது" ,"மரங்களைச்"
//		த ந்த் ஆன்  //   வ ந்த் ஆன்   //  வெ ந்த் அது   //   நொ ந்த் அது   1,2
//		தா , வா , நோ , வே  "அப்பையன்",,"இப்பையனின்"

		//System.out.println(map);,"கண்டேன்","கண்கள்",,"கடாவரைக்கும்","கண்டேன்","கண்கள்","ஆண்கள்","ஆண்டேன்"))ஓட்டுவீடு,,"நேற்றையதினம்","நேற்றுவரை","நேற்றிரவு","நேற்றைய","நேற்றைக்கு"
		//		"நாட்கள்"
		//,"தள்ளிவிடுவது","பதினொன்று","மரத்தை","பார்த்தான்"//////,"வீட்டுப்பாடம்","வீட்டுவேலை","ஓட்டுவீடு 
//		StringBuilder sb =trwp.createMultipleInstance(Arrays.asList("நாளை","நாட்கள்"))	;

//		List<WordClass> wcList= trwp.createSingleInstance("கொள்ளும்");
//		for(WordClass wc:wcList)
//			System.out.println(wc.getWord()+":"+wc.getType());
		//StringBuilder sb =trwp.createMultipleInstance(null);
		
//		StringBuilder sb =trwp.createMultipleInstance(Arrays.asList("நரேந்திர மோடி","நரேந்திர மோடியும்","நரேந்திர மோடியின்","நரேந்திர மோடியையும்","நரேந்திர மோடியைப்","நரேந்திர மோடியை","நரேந்திர மோடியா","நரேந்திர மோடியே","நரேந்திர மோடியோ","நரேந்திர மோடியோட","நரேந்திர மோடியோடு","நரேந்திர மோடியுடைய","நரேந்திர மோடியால்தான்","நரேந்திர மோடியால்","நரேந்திர மோடிக்கு","நரேந்திர மோடிக்கே","நரேந்திர மோடிதான்","நரேந்திர மோடிஜி","நரேந்திர மோடிஜியின்"));
//		WriteToFile.writeToFile(sb,"modfiedResult.txt");
		 //"கடாவரைக்கும்","இப்பையன்",
		//trwp.parse(Arrays.asList("விளக்கேற்றப்பட்டிருக்கிறது"),0,false,true); 
		//trwp.parse(Arrays.asList("கவலையாகத்தானிருந்தது"),0,false,true);
		//trwp.parse(Arrays.asList("கூடியவர்கள்","வாழ்த்துகள்","உயிரைக்கொடுத்தவர்கள்"),0,false,true);
		//,"உயிரைக்கொடுத்தவர்கள்",உயிரைக்கொடுக்கக்கூடியவர்கள்,கூடியவர்கள்,அவர்கள்,வாழ்த்துகள்
		//trwp.parse(Arrays.asList("திடுக்கிடச்செய்ததற்காகத்தான்"),0,false,true);
		//trwp.parse(Arrays.asList("எப்பேர்ப்பட்டவனாயிருந்தாலும்"),0,false,true);
		//எப்பேர்ப்பட்டவனாயிருந்தாலும்"

		//trwp.parse(null,0,false,true);

//		trwp.parse(Arrays.asList("அக்கரை","அக்கரைக்கு","அக்கரைக்குக்",
//				"அக்கரைக்குச்","அக்கரையர்","அக்கரையிலா","அக்கரையிலே","அக்கரையில்",
//				"அக்கரையை","அக்கரையையும்","அக்கறை"),false,true);
		
		long endTime = System.currentTimeMillis() - beginTime;
		System.out.println("EndTime:"+endTime);
	}
	

}