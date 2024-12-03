package org.wotsoc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WithInflection
{
	public static List<String> read(String fileName1,String fileName2) throws Exception{
		ReadFromFile rff = new ReadFromFile();
		List<String> firstList=rff.readFileAsList(fileName1);
		Map<String,List<String>> strListMap = new HashMap<String,List<String>>();
		List<String> secondList = rff.readFileAsList(fileName2);
		List<String> tempList = null;
		for(String first:firstList) {
			for(String second:secondList) {
				if(second.startsWith(first)){
					tempList = strListMap.get(first);
					if(tempList==null)
						tempList = new ArrayList<String>();
					tempList.add(second);
					strListMap.put(first,tempList);
				}
			}
		}
		Set<String> strSet= strListMap.keySet();
		List<String> strList = null;
		tempList = new ArrayList<String>();
		for(String str:strSet) {
			System.out.println(str);
			strList = strListMap.get(str);
			for(String temp:strList)
				tempList.add(temp);
		}
		return tempList;
 	}

	public static void main(String args[]) throws Exception {
		List<String> strList= read("C://Workspaces//Tamil//File1.txt", "C://Workspaces//Tamil//File2.txt");
		WriteToFile.writeToFile(strList,"C://Workspaces//Tamil//File3.txt");
	}
}
