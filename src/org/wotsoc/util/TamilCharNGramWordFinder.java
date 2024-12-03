package org.wotsoc.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.wotsoc.illakanam.TamilConstants;
import org.wotsoc.illakanam.TamilUtil;
import org.wotsoc.nlp.TamilConstantTable;

public class TamilCharNGramWordFinder {
	static TamilConstantTable tct = null;

	static{
		try {
			if(tct==null){
				tct = TamilConstantTable.getInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeFoundWords(String readFileName,String writeFileName,String emptyFileName) throws Exception{
		List<String> wordList = readFile(readFileName);
		//Set<String> globalFinalSet = new TreeSet<String>();
		Set<String> finalSet = null;
		for(String word:wordList) {
			finalSet = wordFinder(word);
			if(finalSet.size()>0) {
				WriteToFile.writeToFile(finalSet, writeFileName);
			}
//			else
//				WriteToFile.writeToFile(word+"\n", emptyFileName);
		}
//		WriteToFile.writeToFile(globalFinalSet, writeFileName);
	}
	
	public Set<String> wordFinder(String word){
		TamilNGram tg = new TamilNGram();
		boolean foundFlag = false;
		Set<String> finalList= new HashSet<String>(); 
		List<String> possibleList= tg.allPossibleLetterNGram(word);
		Map<String,String> map = new HashMap<String,String>();
		String type = null;
		String tempString = null;
		for(String str:possibleList) {
			//System.out.println(str);
			if(str.equals("பிறவிப்")) {
				System.out.println(str);
			}
			if(!finalList.contains(str)) {
				foundFlag=false;
				foundFlag=tct.isInIgnoreVerbWordList(str);
				if(foundFlag) {
					type = "Verb";
					str = "V:" + str ;
				}
				if(!foundFlag){
					foundFlag=tct.isInIgnoreNounWordList(str);
					if(foundFlag) {
						type = "Noun";
						str =  "N:" + str;
					}else{
						try {
							tempString = endWithCertianValues(str);
						}catch(Exception exp) {
							System.out.println(word+":"+str);
						}
						
						//System.out.println(tempString);
						foundFlag=tct.isInIgnoreNounWordList(tempString);
						if(foundFlag)
						  str= "E:"+tempString;	
					}
				}else if(!foundFlag){
					foundFlag=tct.isInIgnorePersonList(str);
					if(foundFlag) {
						type = "Person";
						str = "P:" + str ;
					}
				}else if(!foundFlag){
					foundFlag=tct.isInIgnorePlaceList(str);
					if(foundFlag) {
						type = "Place";
						str = "L:" + str ;
					}
				}else if(!foundFlag) {
					foundFlag=tct.isInIgnoreWordList(str);
					if(foundFlag)
						type = "I:";
				}
				if(foundFlag) {
					TamilStringIterator tsi = new TamilStringIterator(str);
					boolean endsWith = TamilUtil.ஒற்றில்_முடிகிறதா(str); 
					//if(tsi.length()>3 && !endsWith){
					//if(!endsWith){
						finalList.add(str);
						map.put(str,type);
					//} 
				}
			}
		}
		//System.out.println(map);
		return finalList;
	}
	
	public static String endWithCertianValues(String orgWord) {
		String word = TamilUtil.எழுத்துகளைபிரி(orgWord);
		String modifiedWord = null;
		TamilStringIterator ist = new TamilStringIterator(orgWord);
		String lastValue = ist.last();;
		if(TamilUtil.existInCheckList(lastValue,TamilConstants.வல்லினம்_மெய்யெழுத்து_வ்toற்_முடிகிறதா))
		{
			modifiedWord= TamilUtil.எழுத்துகளைசேர்(word+""+(char)TamilConstants.உ);
		}
		else if(word.endsWith("அ") )
		{
			if(tct.isInIgnoreVerbWordList(word))
				return word;	
			
			//If exists in Noun then add ம் or ர்
			 modifiedWord = TamilUtil.எழுத்துகளைசேர்(word+""+TamilConstants.ம்);
			 if(tct.isInIgnoreNounWordList(modifiedWord))
				return modifiedWord;	
			   
			modifiedWord = TamilUtil.எழுத்துகளைசேர்(word+""+TamilConstants.ர்);
			if(tct.isInIgnoreNounWordList(modifiedWord))
				return modifiedWord;
			
			modifiedWord = TamilUtil.எழுத்துகளைசேர்(word+""+TamilConstants.ன்);
			if(tct.isInIgnoreNounWordList(modifiedWord))
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
	
	public List<String> readFile(String fileName) throws Exception {
		ReadFromFile rff = new ReadFromFile(); 
		return rff.readFileAsList(fileName);
	}
	
	public static void main(String args[]) throws Exception {
		TamilCharNGramWordFinder tf = new TamilCharNGramWordFinder();
		//String word ="கருத்தகுழல்மறச்சிறுமிவிழிக்குநிகர்";
		//String word ="கிடக்கும்வாய்ப்புகள்முழுமையாகப்பயன்படுத்துக்மாணவகளுக்குஆரிவுரை";
		//String word ="மாணவகளுக்கு";
		//String word="பச்சைத்தண்ணீா்";
		//System.out.println(word+","+tf.wordFinder(word));
		//List<String> wordList = Arrays.asList("அகர", "முதல", "எழுத்தெல்லாம்", "ஆதி", "பகவன்", "முதற்றே", "உலகு");
		List<String> wordList = Arrays.asList("பிறவிப்", "பெருங்கடல்", "நீந்துவர்", "நீந்தார்", "இறைவன்", "அடிசேரா", "தார்");
		//List<String> wordList = Arrays.asList("பிறவிப்");
		//List<String> wordList = Arrays.asList("முதற்றே");
		Set<String> finalSet = new LinkedHashSet<String>();
		for(String word:wordList) {
			word=TamilUtil.எழுத்துகளைபிரி(word);
			finalSet.addAll(tf.wordFinder(word));
		}
		for(String word:wordList) {
			finalSet.addAll(tf.wordFinder(word));
		}

		//String word="அகர முதல எழுத்தெல்லாம் ஆதி பகவன் முதற்றே உலகு";;
		//String word="எழுத்தெல்லாம்";;
		//word=TamilUtil.எழுத்துகளைபிரி(word);
		//Set<String> finalSet = tf.wordFinder(word);
		System.out.println(finalSet);
		//[E:அகரம், N:முதல, N:முதல், E:எழுத்து, N:எல்லாம், N:ஆதி, N:பகவன், E:பகவன், N:ஏ, N:ற், E:உலகு, N:உலகு]
		//tf.writeFoundWords(args[0],args[1],args[2]);
		
	}
}
