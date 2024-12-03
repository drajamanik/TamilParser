package org.wotsoc.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.wotsoc.illakanam.TamilUtil;

public class SplitEndsWith
{
	public List<String> readFile(String fileName) throws Exception {
		ReadFromFile rff = new ReadFromFile();
		return rff.readFileAsList(fileName);
	}
	
	public void writeFile(String fileName, Set<String> nonExistSet) throws Exception {
		WriteToFile.writeToFile(builder(nonExistSet), fileName);
	}
	
	public static StringBuilder builder(Set<String> set) {
		StringBuilder sb = new StringBuilder();
		for(String str:set) {
			sb.append(str).append("\n");
		}
		return sb;
	}
	
	public Set<String> splitAndCheck(List<String> listStr) {
		String temp = null;
		Set<String> set =new HashSet<String>();
		for(String str:listStr) {
			temp = TamilUtil.எழுத்துகளைபிரி(str,false,false);
			if(temp.endsWith("அர்") || temp.endsWith("அள்") || temp.endsWith("அது") || temp.endsWith("அன்") ) {
				set.add(str);
			}
		}
		return set;
	}
	
	public static void main(String args[]) throws Exception {
		SplitEndsWith sew = new SplitEndsWith();
		List<String> list = sew.readFile("C:\\Users\\rdavid\\Downloads\\unique_sorted_words_in_all_words\\unique_sorted_words_in_all_words_20200604-133955.txt");
		Set<String> set = sew.splitAndCheck(list);
		sew.writeFile("C:\\Users\\rdavid\\Downloads\\unique_sorted_words_in_all_words\\result.txt", set);
	}
}
