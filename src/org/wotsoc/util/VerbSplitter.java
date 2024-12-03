package org.wotsoc.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VerbSplitter {
	//க்கின்ற்,கின்ற்,- Trying to find Verb
	//கின்றது,கின்றான்,கின்றாள்,கின்றார்,கின்றேன்,கின்றோம்,
	//க்கின்றது,க்கின்றான்,க்கின்றாள்,க்கின்றார்,க்கின்றேன்,க்கின்றோம்,
	//வோம்,வோர்,வாள்,வார்,வான்,
	//பான்,பார்,பாள்,பேன்,போம்,
	//ப்பான்,ப்பார்,ப்பாள்,ப்பேன்,ப்போம்,
	List<String> list = Arrays.asList("க்கின்ற்","கின்ற்","கின்றது","கின்றான்","கின்றாள்","கின்றார்","கின்றேன்","கின்றோம்",
			"க்கின்றது","க்கின்றான்","க்கின்றாள்","க்கின்றார்","க்கின்றேன்","க்கின்றோம்","வோம்","வோர்","வாள்","வார்","வான்",
			"பான்","பார்","பாள்","பேன்","போம்","ப்பான்","ப்பார்","ப்பாள்","ப்பேன்","ப்போம்");
	
	public String verbSplitter(String word) {
		for(String str:list){
			if(word.contains(str)){
				str =word.replace(str, "");
				return word+":"+str;
			}
		}
		return null;
	}
	
	public List<String> readFile(String fileName) throws Exception {
		ReadFromFile rff = new ReadFromFile(); 
		return rff.readFileAsList(fileName);
	}
	
	public void writeFile(List<String> strList,String fileName) throws Exception{
		WriteToFile.writeToFile(strList, fileName);
	}
	
	public void findVerb(String readFileName,String writeFileName) throws Exception {
		List<String> strList = readFile(readFileName);
		List<String> finalList = new ArrayList<String>();
		for(String str:strList) {
			str=verbSplitter(str);
			if(str!=null)		
				finalList.add(str);
		}
		if(finalList!=null && finalList.size()>0)
			writeFile(finalList, writeFileName);
	}

	public static void main(String args[]) throws Exception{
		VerbSplitter vs = new VerbSplitter();
		vs.findVerb(args[0], args[1]);
	}
}
