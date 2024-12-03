package org.wotsoc.nlp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Rajamani David
 * @since	Oct 13, 2017
 */
public class TamilConstantTable 
{
	//public static String mainWords[][]=null;
	//public static String twinWords[][]=null;
	//static String mainConstantFileName =null;
//	static String twinConstantFileName =null;
//	static List<List<Integer>> mainListOfList= null;
//	static List<List<Integer>> twinListOfList= null;
	
	public  List<List<String>> getMainWordList(String mainWords[][])
	{
		List<List<String>> allList=new ArrayList<List<String>>();
		List<String> innerList=new ArrayList<String>();
		for (int outerCountValue=0;outerCountValue< mainWords.length;outerCountValue++)
		{
			innerList=new ArrayList<String>();
			int innerSize=mainWords[outerCountValue].length;			
			for(int innerCountValue=0;innerCountValue<innerSize ;innerCountValue++)
			{
				innerList.add(mainWords[outerCountValue][innerCountValue]);
			}
			allList.add(innerList);
		}
		return allList;
	}
	
	private List<String> ignoreVerbList = new ArrayList<String>();
	private List<String> ignoreNounList = new ArrayList<String>();
	private List<String> ignoreList	    = new ArrayList<String>();
	private List<String> ignorePlaceList	    = new ArrayList<String>();
	private List<String> ignorePersonList	    = new ArrayList<String>();
	//private Properties	 mainParseProperty		= null;
	private Properties	 specialCharacterProperty = null;
	private Properties	 conditionalProperty = null;
	private List<String> preFixList		= new ArrayList<String>();

	private TamilConstantTable() 
	{
		 
	}
	
	static TamilConstantTable tct=null;

	public static TamilConstantTable getInstance() throws Exception
	{
		if(tct==null)
		{
			tct = new TamilConstantTable();
			//tct.setFileNames();
			tct.loadBasicFiles();
		}
		return tct;
	}
	
	static String parseOrderFileName  =null;
	static String twinParseOrderFileName = null;
	static String mainParseMapFileName=null;
	static String ignoreListFileName =null;
	static String ignoreVerbListFileName =null;
	static String ignoreNounListFileName =null;
	static String ignorePlaceListFileName =null;
	static String ignorePersonListFileName =null;
	static String wordListFileName = null;
	static String uniqueListFileName = null;
	static String specialCharacterFileName = null;
	static String conditionalFileName = null;
	static String preFixFileName = null;	
	
	static List<String> wordList =null;
	static List<String> uniqueList = null;
	
	public List<String> getWordList()
	{
		return wordList;
	}
	
	private void loadBasicFiles() throws Exception{
		ReadConfig rc=ReadConfig.getInstance();
	 
		String current = new java.io.File( "." ).getCanonicalPath();
		current = "";//rc.getCurrentRoot();
		
		Map<String,String> map =rc.getProperties();
		mainParseMapFileName= map.get(ConfigConstants.MAIN_PARSE_MAP_FILE_NAME);
		ignoreVerbListFileName = map.get(ConfigConstants.IGNORE_VERB_LIST_FILE_NAME);
		ignoreNounListFileName = map.get(ConfigConstants.IGNORE_NOUN_LIST_FILE_NAME);
		ignorePersonListFileName = map.get(ConfigConstants.IGNORE_PERSON_LIST_FILE_NAME);
		ignorePlaceListFileName = map.get(ConfigConstants.IGNORE_PLACE_LIST_FILE_NAME);
		wordListFileName = map.get(ConfigConstants.WORD_LIST_FILE_NAME);
		specialCharacterFileName = map.get(ConfigConstants.SPECIAL_CHARACTER_FILE_NAME);
		conditionalFileName = map.get(ConfigConstants.CONDITIONAL_FILE_NAME);
		preFixFileName = map.get(ConfigConstants.PREFIX_FILE_NAME);
		
		System.out.println("Path:"+current+ignoreVerbListFileName);
		ignoreVerbList = rc.readDelimitorSeperatedFile(current+ignoreVerbListFileName,",");
		ignoreNounList = rc.readDelimitorSeperatedFile(current+ignoreNounListFileName,",");
		ignorePlaceList = rc.readDelimitorSeperatedFile(current+ignorePlaceListFileName,",");
		ignorePersonList = rc.readDelimitorSeperatedFile(current+ignorePersonListFileName,",");
		wordList = rc.readDelimitorSeperatedFile(current+wordListFileName," ");
		 
		specialCharacterProperty = rc.readPropertiesFile(current+specialCharacterFileName);
		conditionalProperty = rc.readPropertiesFile(current+conditionalFileName);
		preFixList = rc.readDelimitorSeperatedFile(current+preFixFileName,",");
	}
	
	public Object[] getParseAndMainValue(String mainConstantFile,String parseOrderFile,String parseMapFile) throws Exception
	{
		ReadConfig rc=ReadConfig.getInstance();

		String current = new java.io.File( "." ).getCanonicalPath();
		current = "";//rc.getCurrentRoot();
		
		Map<String,String> map =rc.getProperties();

		return new Object[] {rc.convertMainConstantFileAsArray(current+map.get(mainConstantFile)),
				rc.readParseOrderFileAsList(current+map.get(parseOrderFile)), rc.readPropertiesFile(current+map.get(parseMapFile))};
		
	}
	
	public Map<String,String> getParseAndMainValue(String mainConstantFile) throws Exception
	{
		ReadConfig rc=ReadConfig.getInstance();

		String current = new java.io.File( "." ).getCanonicalPath();
		current = "";//rc.getCurrentRoot();
		
		Map<String,String> map =rc.getProperties();
		return rc.readFileAsMap(current+map.get(mainConstantFile));
		
	}
	
	public boolean isInUniqueConstantWordList(String word)
	{
		return uniqueList.contains(word);
	}
	
	public boolean isInIgnoreWordList(String word)
	{
		return ignoreList.contains(word);
	}
	
	public boolean isInIgnoreVerbWordList(String word)
	{
		return ignoreVerbList.contains(word);
	}
	
	public boolean isInPreFixList(String word)
	{
		return preFixList.contains(word);
	}
	
	public String getPreFixList(String word)
	{
		return isInPreFixList(word)?word:null;
	}
	
	public List<String> getIgnoreNounList(){
		return ignoreNounList;
	}
	
	public List<String> getIgnoreVerbList(){
		return ignoreVerbList;
	}
	
	public boolean isInIgnoreNounWordList(String word)
	{
		return ignoreNounList.contains(word);
	}
	
	public boolean isInIgnorePlaceList(String word)
	{
		return ignorePlaceList.contains(word);
	}
	
	public boolean isInIgnorePersonList(String word)
	{
		return ignorePersonList.contains(word);
	}
	
	public List<List<Integer>> getParseOrderList(List<List<Integer>> mainListOfList)
	{
		return mainListOfList;
	}

	public String getProperty(Properties prop,String key)  {
//		try {
			String strVal = prop.getProperty(key);
			if(strVal!=null)
				//return new String(strVal.getBytes("ISO-8859-1"), "UTF-8");
				return strVal;
			else
				return null;
//		} catch (Exception e) {
//			System.out.println(key+":has no property" );
//			e.printStackTrace();
//			throw 
//		}
		//return "NA";
	}

	public String getMainParseProperty(Properties parseMapProperty, String key) {
		return getProperty(parseMapProperty,key);
	}
	
	public String getConditionalProperty(String key) {
		return getProperty(conditionalProperty,key);
	}
	
	public String getProperty(String key) {
		return getProperty(conditionalProperty,key);
	}
	
	public void printMainParseProperty(Properties parseMapProperty)  {
		Enumeration<?> element = parseMapProperty.propertyNames();
		String strKey = null;
		String value = null;
		
		while(element.hasMoreElements()) {
			strKey = (String)element.nextElement();
			value =  parseMapProperty.getProperty(strKey);
			System.out.println(strKey+"-:-"+value);
			//System.out.println(new String(strKey.getBytes("ISO-8859-1"),"UTF-8") +"-:-"+new String(value.getBytes("ISO-8859-1"),"UTF-8"));
		}
	}

	public String getSpecialProperty(String key) {
		return getProperty(specialCharacterProperty,key);
	}
	
	int START_VALUE=0;
	public List<List<List<String>>> getMainTable(String mainWords[][],List<List<Integer>> mainListOfList,Map<List<Integer>,
			List<List<String>>> mainParseOrderToValue,Map<List<List<String>>,List<Integer>> mainValueToParseOrder) throws Exception
	{
		return getCommonTable(mainListOfList,mainWords,mainParseOrderToValue,mainValueToParseOrder);
	}
	
	public List<List<List<String>>> getCommonTable(List<List<Integer>> commonListOfList,String commonWords[][],
			Map<List<Integer>,List<List<String>>> commonParseOrderToValue,Map<List<List<String>>,List<Integer>> commonValueToParseOrder) throws Exception{
		List<List<List<String>>> globalListStr = new ArrayList<>();
		List<List<String>> outerListStr = null;
		List<String> innerListStr = null;
		Integer outerCountValue=0;
		for (List<Integer> list:commonListOfList)
		{
			outerListStr = new ArrayList<>();
			int outerSize=list.size();
			for(int outerCount=0;outerCount<outerSize;outerCount++)
			{
				innerListStr = new ArrayList<>();
				outerCountValue=list.get(outerCount);
				try {
				int innerSize=commonWords[outerCountValue-START_VALUE].length;
				for(int innerCount=0;innerCount<innerSize;innerCount++)
				{
					innerListStr .add(commonWords[outerCountValue-START_VALUE][innerCount]);
				}
				outerListStr.add(innerListStr);
				}catch(Exception exp) {
					System.out.println(exp);
				}
			}
			commonParseOrderToValue.put(list,outerListStr);
			commonValueToParseOrder.put(outerListStr,list);
			globalListStr.add(outerListStr); 
		}
		return globalListStr;
	}
	
	public static List<List<List<String>>> getMainTableOld()
	{
		List<List<Integer>> listOfList = new ArrayList<List<Integer>>();
		String mainWords[][] = null;
//		listOfList.add(Arrays.asList(12,13,14,15,16));
		listOfList.add(Arrays.asList(10,11,12,13));
		listOfList.add(Arrays.asList(12,13,14,15));
		listOfList.add(Arrays.asList(0,1,2,3));
		listOfList.add(Arrays.asList(0,9,0,2));

//		listOfList.add(Arrays.asList(10,11,13));
//		listOfList.add(Arrays.asList(11,13,15));
//		listOfList.add(Arrays.asList(15,17,18));
		listOfList.add(Arrays.asList(0,1,2));
		listOfList.add(Arrays.asList(0,2,3));
		listOfList.add(Arrays.asList(0,2,7));
		listOfList.add(Arrays.asList(0,4,22));
		
		listOfList.add(Arrays.asList(10,13));
		listOfList.add(Arrays.asList(12,13));
		listOfList.add(Arrays.asList(11,15));
		listOfList.add(Arrays.asList(11,13));
		listOfList.add(Arrays.asList(0,2));
		listOfList.add(Arrays.asList(0,4));
		listOfList.add(Arrays.asList(0,8));

		listOfList.add(Arrays.asList(22));
		listOfList.add(Arrays.asList(13));
		listOfList.add(Arrays.asList(10));
		listOfList.add(Arrays.asList(20));
		listOfList.add(Arrays.asList(21));
		listOfList.add(Arrays.asList(15));

		List<List<List<String>>> globalListStr = new ArrayList<List<List<String>>>();
		List<List<String>> outerListStr = new ArrayList<List<String>>();
		List<String> innerListStr = new ArrayList<String>();
		Integer outerCountValue=0;
		for (List<Integer> list:listOfList)
		{
			outerListStr = new ArrayList<List<String>>();
			int outerSize=list.size();
			for(int outerCount=0;outerCount<outerSize;outerCount++)
			{
				innerListStr = new ArrayList<String>();
				outerCountValue=list.get(outerCount);
				int innerSize=mainWords[outerCountValue].length;
				for(int innerCount=0;innerCount<innerSize;innerCount++)
				{
					innerListStr .add(mainWords[outerCountValue][innerCount]);
				}
				outerListStr.add(innerListStr);
			}
			globalListStr.add(outerListStr); 
		}
		return globalListStr;
	}
	
	public static void main(String args[])
	{
		List<List<Integer>> listOfList = new ArrayList<List<Integer>>();
		String mainWords[][] =null;
		listOfList.add(Arrays.asList(0,1,2));
		List<List<List<String>>> globalListStr = new ArrayList<List<List<String>>>();
		List<List<String>> outerListStr = new ArrayList<List<String>>();
		List<String> innerListStr = new ArrayList<String>();
		for (List<Integer> list:listOfList)
		{
			outerListStr = new ArrayList<List<String>>();
			int outerSize=list.size();
			for(int outerCount=0;outerCount<outerSize;outerCount++)
			{
				innerListStr = new ArrayList<String>();
				int innerSize=mainWords[outerCount].length;
				for(int innerCount=0;innerCount<innerSize;innerCount++)
				{
					innerListStr .add(mainWords[outerCount][innerCount]);
				}
				outerListStr.add(innerListStr);
			}
			globalListStr.add(outerListStr); 
		}
		System.out.println(globalListStr);
	}
}
