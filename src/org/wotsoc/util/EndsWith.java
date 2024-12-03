package org.wotsoc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.wotsoc.illakanam.TamilUtil;

public class EndsWith {
	
	public static void main(String args[]) throws Exception {
		ReadFromFile rff = new ReadFromFile();
		List<String> strList=rff.readFileAsList(args[0]);
		Map<String,String> strMap = new HashMap<String,String>();
		List<String> condList = rff.readFileAsList(args[1]);
		String strCondArr[]=null;
		String strCondArrMap[]=null;
		List<Map<String,String>> listOfStrMap = new ArrayList<Map<String,String>>();
		for(String strCond:condList) {
			strCondArr = strCond.split(",");
			strMap = new HashMap<String,String>();
			for(String strCondArrTemp:strCondArr) {
				strCondArrMap =strCondArrTemp.split("=");
				strMap.put(strCondArrMap[0],strCondArrMap[1]);
			}
			listOfStrMap.add(strMap);
		}
		 
		String tempStr= null;
		boolean nounExist = false;
		boolean verbExist = false;
		boolean personExist = false;
		boolean placeExist = false;
		Set<String> allSet = new TreeSet<String>();
		Set<String> nonExistSet = new HashSet<String>();
		Set<String> existSet 	= new HashSet<String>();
		Set<String> notCheckedSet 	= new HashSet<String>();
		Set<String> allSplitSet 			= new HashSet<String>();
		String modifiedStr = null;
		for(String str:strList){
			allSplitSet.add(str+":"+TamilUtil.எழுத்துகளைபிரி(str,false,false));
			for(Map<String,String> strMapInner:listOfStrMap) {
				String split = strMapInner.get("SPLIT");
				boolean bSplit = split==null?false:split.trim().equalsIgnoreCase("true");
				if(bSplit)
					tempStr = TamilUtil.எழுத்துகளைபிரி(str,false,false);
				else
					tempStr = str;
				
				String ends= strMapInner.get("ENDS");
				String delete= strMapInner.get("DELETE");
				//TamilStringIterator tsi= new TamilStringIterator(tempStr);
				if(tempStr.endsWith(ends)){
					String add = strMapInner.get("ADD");
					if(add==null)
						add="";
					tempStr = tempStr.substring(0,tempStr.lastIndexOf(delete));
					
					if(bSplit) {
						String tempStrSplit = TamilUtil.எழுத்துகளைசேர்(tempStr);
//						nounExist = TamilConstantTable.getInstance().isInIgnoreNounWordList(tempStrSplit);
//						verbExist = TamilConstantTable.getInstance().isInIgnoreNounWordList(tempStrSplit);
//						personExist = TamilConstantTable.getInstance().isInIgnoreNounWordList(tempStrSplit);
//						placeExist = TamilConstantTable.getInstance().isInIgnoreNounWordList(tempStrSplit);
					}else {
//						nounExist = TamilConstantTable.getInstance().isInIgnoreNounWordList(tempStr);
//						verbExist = TamilConstantTable.getInstance().isInIgnoreVerbWordList(tempStr);
//						personExist = TamilConstantTable.getInstance().isInIgnorePersonList(tempStr);
//						placeExist = TamilConstantTable.getInstance().isInIgnorePlaceList(tempStr);
					}
					
					if(! (nounExist || verbExist ||personExist || placeExist) )
						tempStr = tempStr + add;
					if(bSplit) {
						String tempStrSplit = TamilUtil.எழுத்துகளைசேர்(tempStr);
//						nounExist = TamilConstantTable.getInstance().isInIgnoreNounWordList(tempStrSplit);
//						verbExist = TamilConstantTable.getInstance().isInIgnoreVerbWordList(tempStrSplit);
//						personExist = TamilConstantTable.getInstance().isInIgnorePersonList(tempStrSplit);
//						placeExist = TamilConstantTable.getInstance().isInIgnorePlaceList(tempStrSplit);
						if(! (nounExist || verbExist ||personExist || placeExist) )	{
							nonExistSet.add(tempStrSplit);
						}else{
							existSet.add(tempStrSplit);
						}
					}else {
//						nounExist = TamilConstantTable.getInstance().isInIgnoreNounWordList(tempStr);
//						verbExist = TamilConstantTable.getInstance().isInIgnoreVerbWordList(tempStr);
//						personExist = TamilConstantTable.getInstance().isInIgnorePersonList(tempStr);
//						placeExist = TamilConstantTable.getInstance().isInIgnorePlaceList(tempStr);
						if(! (nounExist || verbExist ||personExist || placeExist) )	{
							nonExistSet.add(tempStr);
						}else{
							existSet.add(tempStr);
						}
					}
					break;
				}else {
					notCheckedSet.add(TamilUtil.எழுத்துகளைசேர்(tempStr));
					//allSet.add(str+":"+null);
				}
			}
			allSet.add(str+":"+TamilUtil.எழுத்துகளைசேர்(tempStr));
		}
		//removeSet(notCheckedSet, existSet);
		//removeSet(notCheckedSet, nonExistSet);
		//WriteToFile.writeToFile(builder(allSet), args[2]);
		WriteToFile.writeToFile(builder(nonExistSet), args[2]);
		//WriteToFile.writeToFile(builder(notCheckedSet), args[4]);
		//WriteToFile.writeToFile(builder(alSplitlSet), args[5]);
	}

	public static void removeSet(Set<String> notCheckedSet, Set<String> set) {
		for(String str:set) {
			notCheckedSet.remove(str);
		}
	}
	
	public static StringBuilder builder(Set<String> set) {
		StringBuilder sb = new StringBuilder();
		for(String str:set) {
			sb.append(str).append("\n");
		}
		return sb;
	}
}
