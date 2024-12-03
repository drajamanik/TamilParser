package org.wotsoc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TamilWordNGramWriter {
	Map<String,Integer> frequency = new HashMap<String,Integer>();
	
	public String readFile(String fileName) throws Exception {
		ReadFromFile rff = new ReadFromFile(); 
		return rff.readFileAsString(fileName);	
	}
	
	public void buildNGram(String inFileName,String outFileName,int nGram) throws Exception {
		String str = readFile(inFileName);
		Integer counter = null;
		frequency = new HashMap<String,Integer>();
		List<List<String>> listOfList = TamilNGram.createWordGram(str," \r\n",nGram);
		for(List<String> list: listOfList) {
			counter = frequency.get(list.toString());
			if(counter==null)
				counter = 1;
			frequency.put(list.toString(),counter++); 
		}
 		writeFile(frequency,outFileName);
	}
	
	public void writeFile(Map<String,Integer> map, String fileName) throws Exception {
		WriteToFile.writeToFile(map, fileName);	
	}
	
	public void writeFile(List<List<String>> finalSet, String fileName) throws Exception {
		WriteToFile.writeToFile(finalSet, fileName);	
	}
	
	public static void main(String args[]) throws Exception {
		List<String> fileList = new ArrayList<String>();
		fileList.add("pmuni0169_01_01");
		fileList.add("pmuni0169_01_02");
		fileList.add("pmuni0169_01_03");
		fileList.add("pmuni0169_01_04");
		fileList.add("pmuni0169_01_05");
		fileList.add("pmuni0169_01_06");
		fileList.add("pmuni0169_02_01");
		fileList.add("pmuni0169_02_01");
		fileList.add("pmuni0169_02_02");
		fileList.add("pmuni0169_02_03");
		fileList.add("pmuni0169_02_04");
		fileList.add("pmuni0169_02_05");
		fileList.add("pmuni0169_02_06");
		fileList.add("pmuni0169_03_01");
		fileList.add("pmuni0169_03_01");
		fileList.add("pmuni0169_03_02");
		fileList.add("pmuni0169_03_03");
		fileList.add("pmuni0169_03_04");
		fileList.add("pmuni0169_03_05");
		fileList.add("pmuni0169_04_01");
		fileList.add("pmuni0169_04_02");
		fileList.add("pmuni0169_04_03");
		fileList.add("pmuni0169_04_04");
		fileList.add("pmuni0169_04_05");
		fileList.add("pmuni0169_05_01");
		fileList.add("pmuni0169_05_02");
		fileList.add("pmuni0169_05_03");
		fileList.add("pmuni0169_05_04");
		fileList.add("pmuni0169_05_05");
		fileList.add("pmuni0169_05_06");
		fileList.add("pmuni0169_05_07");
		fileList.add("pmuni0169_05_08");
		fileList.add("pmuni0169_05_09");
		TamilWordNGramWriter tw = new TamilWordNGramWriter();
		for(int i=3;i<=5;i++) {
			for(String fileName:fileList)
			tw.buildNGram("C:\\raj\\Documents\\Documents\\ponnyin_selvan\\"+fileName+".txt","C:\\raj\\Documents\\Documents\\ponnyin_selvan\\"+fileName+"_"+i+"_gram.txt",i);
		}
	}
}
