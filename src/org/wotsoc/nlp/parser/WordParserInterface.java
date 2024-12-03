package org.wotsoc.nlp.parser;

import java.util.List;
import java.util.Map;

import org.wotsoc.util.WordClass;

public interface WordParserInterface {
	public List<WordClass> createSingleInstance(String word,boolean flag) throws Exception;
	public boolean isInNounList(String str);
	public boolean isInVerbList(String str);
	public Map<String,List<String[]>> parse(List<String> word, int passCounter, boolean noDetails, boolean writeToFile,List<String>  wordOfList) throws Exception;
	public String getParserType();
}
