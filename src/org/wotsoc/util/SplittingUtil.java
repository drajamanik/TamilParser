package org.wotsoc.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.wotsoc.nlp.TamilConstantTable;

public class SplittingUtil {
	
	static TamilConstantTable staticTct = null;
	
	public SplittingUtil(TamilConstantTable tct) {
		staticTct = tct;
	}
	
    public static List<List<String>> getSplittedList(List<String> valueList) {
    	List<List<String>> listOfList = new ArrayList<List<String>> (); 
		if(valueList.size()==1) {
			listOfList.add(valueList);
		}else {
			int size =valueList.size()/12;
			int count =0;
			try {
				for(int i=0; i<size;i++) {
					count = i * 12; 
					if(i+count+12<valueList.size())
						listOfList.add(valueList.subList(i+count, i+count+12));
				}
			}catch(Exception exp) {
				exp.printStackTrace();
			}
		}
		return listOfList;
    }
    
	public static String getSubValueByKeys(String mainKey,String subKey,Properties localParseMapProperty) {
 		return staticTct.getMainParseProperty(localParseMapProperty,mainKey+"_"+subKey);
 	}
	
    public static List<String> mergeSplittedListWithMap(List<List<String>> listOfList,Map<String,String> map, int index,Properties localParseMapProperty) {
    	List<String> listOfTemp = new ArrayList<> ();
    	try {
    	if(!listOfList.isEmpty()) {
	    	List<String> listOfStr = listOfList.get(index);
	    	String tempStr =null;
	    	String tempFirstStr =null;
	    	String tempSecondStr =null;
	    	int counter=0;
	    	for(String strVal:listOfStr) {
	    		if(strVal!=null && !strVal.trim().equals("")) {
		    		tempStr = map.get(counter+"");
		    		if(tempStr==null)
		    			tempStr ="";
		    		else {
		    			//System.out.println(tempStr.trim()+"_"+strVal.trim()+":"+getSubValueByKeys(tempStr.trim(),strVal.trim()));
		    			//System.out.println(strVal.trim()+"_"+tempStr.trim()+":"+getSubValueByKeys(strVal.trim(),tempStr.trim()));
		    			tempFirstStr = getSubValueByKeys(strVal.trim(),tempStr.trim(),localParseMapProperty);
		    			tempSecondStr =getSubValueByKeys(tempStr.trim(),strVal.trim(),localParseMapProperty);
		    			tempStr = "/"+tempStr;
		    			if(tempSecondStr!=null) {
		    				tempStr = tempStr+ "/"+tempSecondStr;
//		    				strVal ="";
//		    				tempStr = tempSecondStr;
		    			}
			    		if(tempFirstStr!=null) {
			    			strVal = tempFirstStr;
			    			tempFirstStr = null;
			    		}
		    		}
		    		listOfTemp.add(strVal+tempStr);
	    		}
	    		counter++;
	    	}
    	}
    	}catch(Exception exp) {
    		System.out.println("Error:"+listOfList);
    		exp.printStackTrace();
    	}
    	return listOfTemp;
    }
    
	public static int getIndexNumber(String str) {
		return Integer.parseInt(str.substring(1, str.indexOf(",")));
	}
	
	public static Map<String,String> parseValues(String str,Properties prop) {
 		Map<String,String> map = new HashMap<String,String>();
 		if(str!=null) {
 			str = str.trim();
 			String strArray[] = str.split("\\[");
 			if(str.contains("null")){
 				map.put("0","NA");
 				map.put("Type","NA");
 			}
 			else if(strArray.length>2 && strArray[2].contains("]")) {
 				setDescription(strArray[2],strArray[2],map,prop);
 				setDescription(strArray[2],map,prop);
 			}else if(strArray[1].contains("(")){
 				setDescription(strArray[1],map,prop);
 			}
 			if(map.get("0")==null && str.contains("NA")){
 				map.put("0","NA");
 				map.put("Type","NA");
 			}
 		}
 		return map;
 	}
 	
 	public static void setDescription(String mainStr,Map<String,String> map,Properties localParseMapProperty) {
 		String strAllElement[] = mainStr.split(":");
		if(mainStr.contains("V")) {
			map.put("0",staticTct.getMainParseProperty(localParseMapProperty,"V"));
			map.put("Type","V");
			if(mainStr.contains("NA"))
			map.put("SubType","NA");
		}
//		else if(mainStr.contains("PL")) {
//			map.put("0",staticTct.getMainParseProperty(localParseMapProperty,"PL"));
//			map.put("Type","PL");
//		}
		else if(mainStr.contains("PRE")) {
			map.put("0",staticTct.getMainParseProperty(localParseMapProperty,"PRE"));
			map.put("Type","PRE");
		}else if(mainStr.contains("PR")) {
			map.put("0",staticTct.getMainParseProperty(localParseMapProperty,"PR"));
			map.put("Type","PR");
		}else if(mainStr.contains("NU")) {
			map.put("0",staticTct.getMainParseProperty(localParseMapProperty,"NU"));
			map.put("Type","NU");
		}
		if(mainStr.contains("N") && !mainStr.contains("NA") && !mainStr.contains("NU") && !mainStr.contains("Not")) {
			if(map.get("0")!=null) {
				String tempValue=map.get("0")+" or "+staticTct.getMainParseProperty(localParseMapProperty,"N");
				map.put("0",tempValue);
				map.put("Type","N");
			}
			else {	
				map.put("0",staticTct.getMainParseProperty(localParseMapProperty,"N"));
				map.put("Type","N");
			}
		}
		if(mainStr.contains("PL") ) {
			if(map.get("0")!=null) {
				String tempValue=map.get("0")+" or "+staticTct.getMainParseProperty(localParseMapProperty,"PL");
				map.put("0",tempValue);
				map.put("Type","PL");
			}
			else {	
				map.put("0",staticTct.getMainParseProperty(localParseMapProperty,"PL"));
				map.put("Type","PL");
			}
		}
		String firstValue = null;
		try { 
			if(mainStr.contains("Symbol")) {
				map.put("0",staticTct.getMainParseProperty(localParseMapProperty,"Symbol"));
				if(strAllElement!=null && strAllElement.length>0 && map.get("0")==null) {
					String value= strAllElement[3];
					value = value.replace(",", "");
					if(value!=null && value.trim().equals(""))
						value=" ";
					map.put("0",staticTct.getMainParseProperty(localParseMapProperty,value));
				}
			}
			if(strAllElement!=null && strAllElement.length>0) {
			   firstValue= map.get("0") +"_"+strAllElement[strAllElement.length-1];
			   if(firstValue!=null) {
				   firstValue = firstValue.replace("]", "");
				   firstValue = firstValue.replace("[", "");
				   firstValue= staticTct.getMainParseProperty(localParseMapProperty,firstValue.trim());
			   }
			   if(firstValue!=null)
				   map.put("0",firstValue);
			}
		}catch(Exception exp) {
			System.out.println(mainStr+"$$$1$$$"+(strAllElement==null?null:strAllElement[0])+"$$$$2$$$$"+firstValue); 
			exp.printStackTrace();
		}
 	}
 	
 	public static void setDescription(String str,String mainStr,Map<String,String> map,Properties localParseMapProperty) {
 		if(str !=null) {
 			str = str.trim();
 			String strTemp = "";
 			String type ="";
 			if(str.contains("]")) {
 				String strWithNum = str.substring(0,str.indexOf("]"));
 				str = str.replace("[", "");
 				str = str.replace("]", "");
 				String strArray[] = strWithNum.split(",");
 				
 				for(String strVal:strArray) {
 					if(strVal.contains(":"))
 						break;
 					strTemp = staticTct.getMainParseProperty(localParseMapProperty,strVal.trim());
 					if(strTemp!=null) {
 					    map.put((map.size()+1)+"",staticTct.getMainParseProperty(localParseMapProperty,strVal.trim()));
 					    type  =  strVal.trim();
 					    continue;
 					}
 					if(strTemp==null) {
 						if(staticTct.isInIgnoreNounWordList(strVal.trim()))
 							strTemp = "N";
 					}
 					if(strTemp==null) {
 						if(staticTct.isInIgnoreVerbWordList(strVal.trim()))
 							strTemp = "V";
 					}
 					if(strTemp==null) {
 						if(staticTct.isInIgnorePlaceList(strVal.trim()))
 							strTemp = "PL";
 					}
 					if(strTemp==null) {
 						if(staticTct.isInIgnorePersonList(strVal.trim()))
 							strTemp = "PR";
 					}
 					if(strTemp==null) {
 						if(staticTct.isInPreFixList(strVal.trim()))
 							strTemp = "PRE";
 					}
 					if(strTemp!=null) {
 						strVal = strTemp;
 						map.put((map.size()+1)+"",staticTct.getMainParseProperty(localParseMapProperty,strVal.trim()));
 						type  =  strTemp;
 						
 					}
 				}
 				map.put("Key","["+strWithNum+"]");
 				map.put("Type",type);
 			}
 		}
 	}

 	public List<WordClass> buildWordClass(Map<String,List<String[]>> map,String word,List<String> wordOfList,Properties prop) {
 		List<WordClass>  wcList = new ArrayList<>();
		List<String[]>strList= (ArrayList<String[]>)map.get(word);
		if(strList!=null) {
			List<List<String>> rawSplitList =  SplittingUtil.getSplittedList(wordOfList);
			String  mergeSplitStr = null;
 			for(String strArr[]:strList) {
				String strVal ="["+StringUtils.join(strArr, ",")+"]";
				Map<String,String> mapParseVals =parseValues(strVal,prop);
				strVal =strVal+":"+mapParseVals;
				mergeSplitStr= mergeSplittedListWithMap(rawSplitList, mapParseVals, 0,prop).toString();
				WordClass myClass = new WordClass(0,word,strVal,mergeSplitStr,mapParseVals,rawSplitList);
				wcList.add(myClass);
			}
		} 		
		return wcList;
 	}
 	
	public void buildSortList(Map<String,List<String[]>> map,String word,List<String> wordOfList,List<WordClass> mySortList,int indexGlobal,Properties localParseMapProperty) {
		List<String[]>strList= (ArrayList<String[]>)map.get(word);
		if(strList!=null) {
			List<List<String>> rawSplitList =  SplittingUtil.getSplittedList(wordOfList);
			String  mergeSplitStr = null;
			int index =0;
			for(String strArr[]:strList) {
				String strVal ="["+StringUtils.join(strArr, ",")+"]";
				Map<String,String> mapParseVals =parseValues(strVal,localParseMapProperty);
				//if(mapParseVals.size()>2 && mapParseVals.get("Type").contains("NA")) {
					strVal =strVal+":"+mapParseVals;
					mergeSplitStr= mergeSplittedListWithMap(rawSplitList, mapParseVals, index,localParseMapProperty).toString();
					WordClass myClass = new WordClass(indexGlobal,word,strVal,mergeSplitStr,mapParseVals,rawSplitList); 
					mySortList.add(myClass);
				//}
				index = index +1;
			}
		}
	}
	
	public StringBuilder sortAndPrint(List<WordClass> mySortList) {
		Collections.sort(mySortList);
		StringBuilder sb = new StringBuilder();
		for(WordClass mySClass:mySortList) {
			sb.append(mySClass.getValue()).append("||");
			sb.append(mySClass.getSplittedVal()).append("\n");
		}
		return sb;
	}
	
	public void printMapValues(Map<String,List<String[]>> mapOfList)
	{
		Iterator<String> iter=mapOfList.keySet().iterator();
		while(iter.hasNext())
		{
			String key= iter.next();
			//System.out.println(key+":"+toString(mapOfList.get(key)));
		}
	}

 	public static String toString(List<String[]> listOfString){
 		StringBuilder sb = new StringBuilder();
		for(String strArr[]:listOfString){
			for(String str:strArr){
				sb.append(str).append(" ");
			}
	    }
		return sb.toString();
 	}
 	
	public StringBuilder printMap(Map<String,List<String[]>> mapOfList)
	{
		StringBuilder sb = new StringBuilder();
		Iterator<String> iter=mapOfList.keySet().iterator();

		while(iter.hasNext())
		{
			String key= iter.next();
			List<String[]> listOfString = mapOfList.get(key);
			for(String strArr[]:listOfString)
			{
				for(String str:strArr)
				{
					sb.append(str).append(" ");
				}
				sb.append("\n");
		    }
		}
		return sb;
	}

	public static void printMapVal(Map<String,String> map) {
		for(String keyStr:map.keySet()) {
			System.out.print(keyStr+":");
			System.out.print(map.get(keyStr)+":");
		}
	}

	public static void main(String args[]) throws Exception{
		Properties localParseMapProperty = new Properties();
		String str ="[14, :, 	:(, (V)(OTHER)(N)(OTHER)(N)(OTHER), ) IgnoreList, :, வேலை, :வேலை]";;
		printMapVal(parseValues(str,localParseMapProperty));
		System.out.println("***************");
		
		str ="[10, :, 	:(, (V)(OTHER)(N)(OTHER), ) IgnoreList, :, எல்லாம், :எல்லாம்]";;
		printMapVal(parseValues(str,localParseMapProperty)); 
		System.out.println("***************");
		
		str ="[6, :, 		:(, (V)(OTHER), ) IgnoreList, :, பார், :பார்]";
		printMapVal(parseValues(str,localParseMapProperty));
		System.out.println("***************");
		
		str ="[9, :, 		:(Symbol) IgnoreList, :, , :, :]";;
		printMapVal(parseValues(str,localParseMapProperty));
		System.out.println("***************");
		
		str ="[9, :, null, :, (NA), , NA, :வந்தவர்கள்:வ்அந்த்அவ்அர்க்அள்:[வந்தவர், க், அள், , , , , , , , , , ]]";;
		printMapVal(parseValues(str,localParseMapProperty));
		System.out.println("***************");
		
		str ="[12, :, [4, 3, 9], :, , , N, :அவனுக்குப்:அவ்அன்உக்க்உப்:அவன் [அவன், உக், கு, ப், , , , , , , , , ]]";;
		printMapVal(parseValues(str,localParseMapProperty));
		System.out.println("***************");
 		
		str ="[4, :, [6, 8], :, , , N, :மரங்களா:ம்அர்அங்க்அள்ஆ:மரம்: [மரங், கள், ஆ, , , , , , , , , , ]]";;
		printMapVal(parseValues(str,localParseMapProperty));
		System.out.println("***************");
 		
		str ="[13, :, [12], :, , , V, :பார்த்தல்:ப்ஆர்த்த்அல்:பார் [பார், த்தல், , , , , , , , , , , ]]";;
		printMapVal(parseValues(str,localParseMapProperty));
		System.out.println("***************");
	}
}
