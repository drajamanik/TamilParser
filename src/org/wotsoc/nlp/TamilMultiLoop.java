package org.wotsoc.nlp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.wotsoc.illakanam.TamilUtil;

public class TamilMultiLoop {
	
	 
	List<String> verbList = Arrays.asList("V1","V2"); 
	List<String> nounList = Arrays.asList("N1","N2","N3","N4");
	 

	public static void main(String args[]) throws Exception{
		TamilMultiLoop  trwp=new TamilMultiLoop();
		List<String>  deeperInnerList= new ArrayList<String>();
		//deeperInnerList.add("A");
		deeperInnerList.add("[VERB]");
		//deeperInnerList.add("B");
		deeperInnerList.add("[NOUN]");
		//deeperInnerList.add("C" );
		//deeperInnerList.add("[VERB]");
		//deeperInnerList.add("D" );
		//deeperInnerList.add("[VERB]");
		trwp.loopMain(deeperInnerList);
	}
	
	public void loopMain(List<String> deeperInnerList){
		List<String> verbList = new ArrayList<String>();//staticTct.getIgnoreVerbList();
		verbList.add("V1");
		verbList.add("V2");
		List<String> nounList = new ArrayList<String>();//staticTct.getIgnoreNounList();
		nounList.add("N1");
		nounList.add("N2");
		nounList.add("N3");
		nounList.add("N4");
		loopMain(deeperInnerList, verbList, nounList);
	}

	boolean EXIT_LOOP= false;
	
	Map<String,List<List<String>>> cacheLoop = new HashMap<String,List<List<String>>>();
	
	public List<List<String>> loopMain(List<String> deeperInnerList, List<String> verbList, 
			List<String> nounList) {
		/**If Cached return it*/
		List<List<String>> listOfList = null;
		listOfList = cacheLoop.get(deeperInnerList.toString());
		if(listOfList != null)
			return listOfList;
		
		listOfList = new ArrayList<List<String>>();
		String strValue = null;
		this.verbList = verbList;
		this.nounList = nounList;
		init(deeperInnerList);
		List<String> listOfString = new ArrayList<String>();
		
		while(!EXIT_LOOP) {
			listOfString = new ArrayList<String>();
			for(int index=0;index<deeperInnerList.size(); index++) {
				try {
					strValue = spinner(index,deeperInnerList.get(index));
					strValue = TamilUtil.எழுத்துகளைபிரி( (strValue),false,false);
				}catch(Exception exp) {
					System.out.println("Exception:"+strValue);
					exp.printStackTrace();
				}
				listOfString.add(strValue);
				//System.out.print(strValue);
				if(isAllSizeMax()) {
					EXIT_LOOP=true;
				}
			}
			listOfList.add(listOfString);
		}
		cacheLoop.put(deeperInnerList.toString(),listOfList);
		return listOfList;
	}
	
	int totalItemSize =0;
	Map<Integer,Object[]> map = new LinkedHashMap<Integer,Object[]>();//0-Current Count, 1-Total Current Item Size, 2- Item Position, 3-Type
	Map<Integer,Object[]> mapWithItem = new LinkedHashMap<Integer,Object[]>();//0-Current Count, 1-Total Current Item Size, 2- Item Position, 3-Type
	
	public void init(List<String> deeperInnerList) {
		Object[] objArray= null;
		for(int index=0;index<deeperInnerList.size();index++) {
			String inner= deeperInnerList.get(index);
			if(isVerb(inner)) {
				objArray=new Object[] {0,verbList.size(),++totalItemSize,"Verb"};
				map.put(index,objArray);
				mapWithItem.put(totalItemSize,objArray);
			}else if(isNoun(inner)) {
				objArray= new Object[] {0,nounList.size(),++totalItemSize,"Noun"};
				map.put(index,objArray);
				mapWithItem.put(totalItemSize,objArray);
			}
		}
	}
	
	public String spinner(int index,String value) {
		int currentCount = 0;
		int totalCurrentItemSize = 0;
		Object[] currentArray = map.get(index);
		 
		if(currentArray!=null) {
			currentCount = (Integer)currentArray[0];
			totalCurrentItemSize = (Integer)currentArray[1];
			if(isVerb((String)currentArray[3])) {
				value = verbList.get((Integer)currentArray[0]);
	 			currentArray[0] = incrementCount(currentCount, totalCurrentItemSize, (Integer)currentArray[2]);
	 			if(totalCurrentItemSize == (Integer)currentArray[0] && !isAllSizeMax()) {
					currentArray[0] = 0;
				}
			}
			else if(isNoun((String)currentArray[3])) {
				value = nounList.get((Integer)currentArray[0]);
				currentArray[0] = incrementCount(currentCount, totalCurrentItemSize, (Integer)currentArray[2]);
				if(totalCurrentItemSize == (Integer)currentArray[0] && !isAllSizeMax()) {
					currentArray[0] = 0;
				}
			}
		}
		return value;
	}
	
	public boolean isAllSizeMax() {
		for(Integer key:map.keySet()) {
			Object[] objValue = map.get(key);
			if(!objValue[0].equals(objValue[1]))
				return false;
		}
		return true;
	}
	
	public int incrementCount(int currentCount,int totalCurrentItemSize, int currentItemPosition) {
		if(totalItemSize == currentItemPosition) {
			if(currentCount!=totalCurrentItemSize) {
				currentCount=currentCount+1;
				if(currentCount == totalCurrentItemSize && currentItemPosition!=0 ) {
					for(int index=1; index<=currentItemPosition; index++) {
						Object[] prevArray = mapWithItem.get(currentItemPosition-index);
						if(prevArray!=null && prevArray.length>0) {
							prevArray[0] = (Integer) prevArray[0] + 1;
							if(prevArray[0].equals(prevArray[1]) ) {
								prevArray[0] = 0;
								if(currentItemPosition-index==0) {
									EXIT_LOOP = true;
								}
							}else {
								break;
							}
						}else {
							EXIT_LOOP = true;
						}
					}
				}
			}
		}
		return currentCount;
	}

	
	public String getVerb(int currGlobalPos,int currIndex, int totalSize) {
		return verbList.get(currIndex);
	}
	
	public String getNoun(int currGlobalPos,int currIndex, int totalSize) {
		return nounList.get(currIndex);
	}
	
	public boolean isVerb(String str) {
		if(str.equalsIgnoreCase("VERB")) {
			return true;
		}
		return false;
	}
	
	public boolean isNoun(String str) {
		if(str.equalsIgnoreCase("NOUN")) {
			return true;
		}
		return false;
	}

}
