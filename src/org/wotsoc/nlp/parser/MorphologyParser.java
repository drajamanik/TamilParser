package org.wotsoc.nlp.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.wotsoc.nlp.ConfigConstants;
import org.wotsoc.nlp.ReadConfig;
import org.wotsoc.util.TamilNGram;
import org.wotsoc.util.WordClass;
import org.wotsoc.util.WordSplitter;
import org.wotsoc.util.WriteToFile;

/**
 * Multiple parser can be used to parse different type of feature.
 * @author Rajamani David
 * @since	Jun/7/2020	
 * */
public class MorphologyParser {
	List<List<String>> gramListOfList = null;
	List<WordParserInterface>   listOfParser    = new ArrayList<WordParserInterface>();
	
	public List<List<String>> getGramList(){
		return gramListOfList ;
	}
	
	public void createNGrams(List<String> strList, int gram) {
		gramListOfList = TamilNGram.createWordGram(strList, gram);
	}

	public void createNGrams(String str, int gram) {
		gramListOfList = TamilNGram.createWordGram(str, gram);
	}
	
	public void buildParser(List<ParserEnum> list) throws Exception{
		for(ParserEnum pe:list) {
			switch(pe){
				case VerbParser:
					listOfParser.add(new VerbParser());
					break;
				case NounParser:
					listOfParser.add(new NounParser());
					break;
				case UnicodeLanguageParser:
					listOfParser.add(new UnicodeLanguageParser());
					break;
				case TwinWordParser:	
					listOfParser.add(new TwinWordParser());
					break;
				case NumberParser:
					listOfParser.add(new NumberParser());
					break;
				case SymbolParser:	
					listOfParser.add(new SymbolParser());
					break;
			}
		}
	}

	public void buildParser() throws Exception {
		listOfParser.add(new SymbolParser());
		listOfParser.add(new UnicodeLanguageParser());
		//listOfParser.add(new TwinWordParser());
		listOfParser.add(new NumberParser());
		listOfParser.add(new VerbParser());
		listOfParser.add(new NounParser());
		//listOfParser.add(new TamilRootWordParser()); 
	}
	
	public List<WordClass> runParsers(String word) throws Exception{
		List<WordClass> wpList = new ArrayList<WordClass>();
		List<WordClass> wpPossibleList = new ArrayList<WordClass>();
		List<WordClass> wpFinalList = new ArrayList<WordClass>();
		String type = "NA";
		for(WordParserInterface cp:listOfParser) {
			if(type.equals("NA")) {
				wpList = cp.createSingleInstance(word,true);
				if(wpList!=null) {
					for(WordClass wc:wpList) {
						wpPossibleList.add(wc);
						if(!wc.getType().equals("NA") || (wc.getSubType()!=null && wc.getSubType().equals("NA"))){
							boolean flag = false;
							wpFinalList.add(wc);
							List<List<String>> rawListOfList = wc.getRawSplitList();
							for(List<String> rawList:rawListOfList) {
								if(wc.getType().equals("V") && cp.isInNounList(rawList.get(0))) {
									flag = true;
								}
								if(wc.getType().equals("N") && cp.isInVerbList(rawList.get(0))) {
									flag = true;
								}
								if(wc.getSubType()!=null && wc.getSubType().equals("NA"))
									flag = true;
							}
							if(!flag) {
								type = wc.getType();
								break;
							}
						}
					}
				}
			}
		}
		if(wpFinalList!=null && wpFinalList.size()==0)
			wpFinalList.addAll(wpPossibleList);
		return wpFinalList;
	}
	
	public List<WordClass> runParsers(List<List<String>> strListOfList) throws Exception {
		List<WordClass> wpList = null;
		String type = "NA";
		List<WordClass> mySortList = new ArrayList<WordClass>();
		List<WordClass> myFinalList = new ArrayList<WordClass>();
		for(List<String> strList: strListOfList) {
			type = "NA";
			CoreParser.EXIT_LOOP =10000000l;
			String word = strList.get(0);
			for(WordParserInterface cp:listOfParser) {
				if(type.equals("NA")) {
					wpList = cp.createSingleInstance(word,true);
					if(wpList!=null && wpList.size()>0) {
						for(WordClass wc:wpList) {
							mySortList.add(wc);
							if(!wc.getType().equals("NA") || (wc.getSubType()!=null && wc.getSubType().equals("NA"))){
								boolean flag = false;
								List<List<String>> rawListOfList = wc.getRawSplitList();
								for(List<String> rawList:rawListOfList) {
									if(wc.getType().equals("V") && cp.isInNounList(rawList.get(0))) {
										flag = true;
									}
									if(wc.getType().equals("N") && cp.isInVerbList(rawList.get(0))) {
										flag = true;
									}
									if(wc.getSubType()!=null && wc.getSubType().equals("NA"))
										flag = true;
									//type = wc.getType();
								}
								if(!flag) {
									type = wc.getType();
									break;
								}
							}
						}
						if(!type.equals("NA"))
							break;
					}
				}
			}
//			for(WordClass wc:mySortList) {
//				if(wc.getType().equals("NA")) //&& !type.equals("NA"))
//					myFinalList.add(wc);
//			}
//			mySortList = new ArrayList<WordClass>();
		}
		
//		if(myFinalList!=null && myFinalList.size()>0)
//			writeOtherFiles(myFinalList);
		return myFinalList;
	}
	
	
 	public void writeOtherFiles(List<WordClass> mySortList) throws Exception {
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();
		StringBuilder sb4 = new StringBuilder();
		for(WordClass cl: mySortList) {
			sb1.append(cl.getWord()+":"+cl.getRawSplitList()).append("\n");
			cl.getMapVals().remove("Type");
			cl.getMapVals().remove("Key");
			sb2.append(cl.getWord()+":"+cl.getMapVals()).append("\n");
			sb3.append(cl.getWord()+":"+cl.getSplittedVal()).append("\n");
			if(cl.getType().equals("NA"))
				sb4.append(cl.getWord()+":"+cl.getSplittedVal()).append("\n");
		}
		WriteToFile.writeToFile(sb1,"correctParser.txt");
		WriteToFile.writeToFile(sb2,"grammarWithNo.txt");
		WriteToFile.writeToFile(sb3,"suffixWithGrammar.txt");
		WriteToFile.writeToFile(sb3,"incorrectParser.txt");
	}

	public String readFile() throws Exception {
		ReadConfig rc= ReadConfig.getInstance();
		Map<String,String> map =rc.getProperties();
		String wordListFileName = map.get(ConfigConstants.WORD_LIST_FILE_NAME);
		String current = new java.io.File( "." ).getCanonicalPath();
		current = rc.getCurrentRoot();
		return rc.readFileAsIs(current+wordListFileName);
	}
	
	public void createNGram(int value) throws Exception {
		long beginMillis = System.currentTimeMillis();
		MorphologyParser mp = new MorphologyParser();
		String fileStr = mp.readFile();
		WordSplitter swd= new WordSplitter();	
		List<String> strList = swd.splitWords(fileStr);
		mp.createNGrams(strList,value);
		mp.buildParser();
		mp.runParsers(mp.getGramList());
		System.out.println("End:"+(System.currentTimeMillis()-beginMillis));
	}
	
	public List<WordClass> createParser(String word,List<ParserEnum> parserList) throws Exception {
		MorphologyParser mp = new MorphologyParser();
		mp.buildParser(parserList);
		return mp.runParsers(word);
	}
	
	public static void main(String args[]) throws Exception {
		MorphologyParser mp = new MorphologyParser();
		//mp.createNGram(1);
		List<WordParserInterface> listOfParserList = new ArrayList<>();
		listOfParserList.add(new UnicodeLanguageParser()); //If Tamil Then go next chain else send for printing 
		listOfParserList.add(new TwinWordParser());	//Looks Information still have to go  
		listOfParserList.add(new NumberParser());	//Main - If Found don't go next in chain
		listOfParserList.add(new VerbParser());		//Main - If Found don't go next in chain
		listOfParserList.add(new NounParser());		//Main - If Found don't go next in chain
//		listOfParser.add(new TamilRootWordParser());
//		System.out.println("Wordஅ?1:"+ulf.findLanguage("Wordஅ?1")); 
//		System.out.println("அம்மா:"+ulf.findLanguage("அம்மா"));

		//mp.createParser("Wordஅ?1", listOfParserList);
	}
}
