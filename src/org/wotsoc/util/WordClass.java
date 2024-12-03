package org.wotsoc.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WordClass implements Comparable<Object>{
	Integer number = 0;
	String value =null;
	String subType =null;
	String altValue =null;
	String word = null;
	String type = null;
	String splittedVal = null;
	Map<String,String> mapParseVals = null;
	List<List<String>> rawSplitList = null;
	
	public WordClass(int number,String word) {
		
	}
	
	public WordClass(int number,String word,String value,String splittedVal,Map<String,String> mapParseVals,List<List<String>> rawSplitList){
		this.number = number;
		this.word = word;
		this.type = mapParseVals.get("Type");
		this.subType = mapParseVals.get("SubType");
		if(this.type==null)
			this.type="NA";
		this.value = value;
		this.altValue ="";
		//TODO: Rewrite this IgnoreList and Pass AltValue
		if(value.contains("IgnoreList")) {
			String valArr[]= value.split(":");
			this.altValue=valArr[valArr.length-2];
			this.altValue= this.altValue.replace("]", "");
		}
		this.splittedVal = splittedVal;
		this.mapParseVals = mapParseVals;
		this.rawSplitList = rawSplitList;
	}
	
	public String getWord() {
		return word;
	}
	
	public Map<String,String> getMapVals(){
		return mapParseVals;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getAltValue() {
		return altValue;
	}
	
	public String getSplittedVal(){
		return splittedVal;
	}
	
	public List<String> getSplittedValToList(){
		if(splittedVal!=null) {
			String tempArr[]=splittedVal.split(",");
			return Arrays.asList(tempArr);
		}
		return new ArrayList<String>();
	}
	
	public String getType() {
		return type;
	}
	
	public String getSubType() {
		return subType;
	}
	public List<List<String>> getRawSplitList(){
		return rawSplitList;
	}
	
	public Integer getIndex() {
		return number;
	}
	@Override
	public int compareTo(Object o) {
		return this.number.compareTo(((WordClass)o).number);
	}
}
