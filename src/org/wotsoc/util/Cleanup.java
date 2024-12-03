package org.wotsoc.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Cleanup {
	static Map<String,String> char1Map =new HashMap<String,String>();
	static Map<String,String> char2Map =new HashMap<String,String>();
	static Map<String,String> char3Map =new HashMap<String,String>();
	static Map<String,String> char4Map =new HashMap<String,String>();
	static Map<String,String> char5Map =new HashMap<String,String>();
	
	public static void loadNCharList(List<String> strList,int N) {
		for(String str:strList) {
			TamilStringIterator tsi = new TamilStringIterator(str.trim());
			List<String> list = tsi.forwardIterator();
			StringBuilder sb =new StringBuilder();
			if(list.size()==N) {
				for(int index=0; index<N;index++) {
					sb.append(list.get(index));
				}
				if(N==1)
					char1Map.put(sb.toString(),sb.toString());
				if(N==2)
					char2Map.put(sb.toString(),sb.toString());
				else if(N==3)
					char3Map.put(sb.toString(),sb.toString());
				else if(N==4)
					char4Map.put(sb.toString(),sb.toString());
				else if(N==5)
					char5Map.put(sb.toString(),sb.toString());
			}
		}
	}
	
	public static void loadNCharList(List<String> strList) {
		loadNCharList(strList,1);
		loadNCharList(strList,2);
		loadNCharList(strList,3);
		loadNCharList(strList,4);
		loadNCharList(strList,5);
	}
	
	public static String firstNChars(String str,int N) {
		TamilStringIterator tsi = new TamilStringIterator(str.trim());
		List<String> list =tsi.forwardIterator();
		StringBuilder sb =new StringBuilder();
		if(list.size()>=N) {
			for(int index=0; index<N;index++) {
				sb.append(list.get(index));
			}
		}
		return sb.toString();
	}
	
	public static void cleanup(String str1,String str2) throws Exception {
		ReadFromFile rff = new ReadFromFile();
		List<String> strList=rff.readFileAsList(str1);
		Set<String> set = new TreeSet<String>();
		Set<String> newSet = new TreeSet<String>();
		String tempArr[]= null;  
		for(String str:strList) {
//			tempArr = str.split(" ");
//			if(tempArr!=null && tempArr.length>1) {
//				for(String temp:tempArr)
//					set.add(temp.trim());
//			}
//			else {
//				newSet.add(str.trim());
//			}
			TamilStringIterator tsi = new TamilStringIterator(str.trim());
			List<String> tsiList = tsi.forwardIterator();
			newSet.add(str.trim());
		}
		WriteToFile.writeToFile(builder(newSet), str2);
	}
	
	public static void nGramWrite(String fileName1,String fileName2) throws Exception {
		ReadFromFile rff = new ReadFromFile();
		List<String> strList=rff.readFileAsList(fileName1);
		loadNCharList(strList);
		Set<String> newSet = new TreeSet<String>();
		for(String str:strList) {
			newSet.add(str.trim() +":"+ char1Map.get(firstNChars(str, 1)) +":"+ char2Map.get(firstNChars(str, 2)) +":"+ char3Map.get(firstNChars(str, 3))+":"+ char4Map.get(firstNChars(str, 4)) +":"+ char5Map.get(firstNChars(str, 5)));
		}
		WriteToFile.writeToFile(builder(newSet), fileName2);
	}

	public static void main(String args[]) throws Exception {
		//cleanup(args[0],args[1]);
		//nGramWrite(args[0], args[1]);
		//duplicate(args[0], args[1],args[2],args[3]);
		duplicate(args[0], args[1]);
	}
	
	public static void duplicate(String fileName1,String fileName2) throws Exception{
		ReadFromFile rff = new ReadFromFile();
		List<String> strList=rff.readFileAsList(fileName1);
		Set<String> strSet = new HashSet<String>();
		for(String str:strList) {
			strSet.add(str);
		}
		WriteToFile.writeToFile(builder(strSet), fileName2);
		System.out.println("Done.");
	}
	
	public static void duplicate(String fileName1,String fileName2,String fileName3,String fileName4) throws Exception{
		ReadFromFile rff = new ReadFromFile();
		List<String> strList1=rff.readFileAsList(fileName1);
		List<String> strList2=rff.readFileAsList(fileName2);
		 
		List<String> finalList = new ArrayList<String>();
		List<String> dupList = new ArrayList<String>();
		for(String str2:strList2) {
			if(strList1.contains(str2.trim())) {
				dupList.add(str2);
			}else {
				finalList.add(str2);
			}
		}
		//WriteToFile.writeToFile(builder(mapValue.keySet()), fileName3);
		WriteToFile.writeToFile(builder(finalList), fileName3);
		WriteToFile.writeToFile(builder(dupList), fileName4);
	}
	
 	public static StringBuilder builder(Collection<String> set) {
		StringBuilder sb = new StringBuilder();
		for(String str:set) {
			sb.append(str).append("\n");
		}
		return sb;
	}
}
