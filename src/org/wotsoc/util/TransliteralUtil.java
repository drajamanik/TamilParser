/**
 * @author Rajamani David
 * @since	Mar 22, 2019
 *
 */
package org.wotsoc.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Rajamani David
 * @desc	Translates each letter to another letter style based on given map 
 *
 */
public class TransliteralUtil {

	Properties map = null;
	
	public TransliteralUtil(Properties prop){
		this.map = prop;
	}
	
	public void storeWords(List<String> wordList, String fileName) throws Exception
	{
		StringBuilder sb = new StringBuilder();
		String match2="^[,._-_?!)]*";
		long counter =0;
		for(String word:wordList)
		{
			
			if(counter<1000)
			{
				try{
					word=word.replaceAll(match2, "");
					word=word.replace(".", "");
					word=word.replace(")", "");
					word=word.replace("`", "");
					word=word.replace("'", "");
					word=word.replace("-", " ");
					word=word.replace("–", " ");
					
					//sb.append(nGram(word.trim(), 5)).append("\n");
					sb.append(word).append(":").append(nGram(word.trim(), 5)).append("\n");
				}catch(Exception exp){
					System.out.println(counter +":"+word);
					exp.printStackTrace();
				}
				counter++;
			}
			else{
				WriteToFile.writeToFile(sb,fileName);
				sb = new StringBuilder();
				//System.out.println("Counter:"+counter);
				counter=0;
			}
			
			
		}
		if(sb!=null) {
			WriteToFile.writeToFile(sb,fileName);
		}
	}

	public String nGram(String word, int start, int end)
	{
		String value= word.substring(start,end);
		return map.getProperty(value);
	}

	public String nGram(String word, int n)
	{
		int totalLength=word.length();
		int start =0;
		int end =n;
		String value=null;
		StringBuilder sb = new StringBuilder();
		StringBuilder og= new StringBuilder();
		Integer counter = new Integer(0);
		Map<String,Integer> map = new HashMap<String,Integer>();
		while(!(start == totalLength))
		{
			try{
			if(end>=totalLength)
				end= totalLength;
			
			counter = map.get(start+":"+end);
			if(counter==null)
				counter = new Integer(0);
			
			if(counter >1)
				return word+" has issue.";
			if(start>=end)
				return word+" has issues.";
			map.put(start+":"+end,++counter);
			value=nGram(word, start, end);
			if(value!=null)
			{
				sb.append(value);
				og.append(word.substring(start, end));
				start =end ;
				end = end + n;
			}
			else
			{
				if(end-1!=0)
					end = end-1;
			}
			}catch(Exception exp){
				exp.printStackTrace();
			}
			
		}
		//System.out.println(og.toString()+":"+sb.toString());
		return sb.toString();
	}
	
	 public List<String> readDelimitorSeperatedFile(String fileName) throws Exception
	 {
		 	List<String> ignoreList =new ArrayList<String>();
			BufferedReader br = null;
			FileReader fr = null;
			try 
			{
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);
				String sCurrentLine;
				String strValues[]=null;
				while ((sCurrentLine = br.readLine()) != null) 
				{
					strValues=sCurrentLine.trim().split(" ");
					ignoreList.addAll(Arrays.asList(strValues));
				}
			} 
			catch (IOException e) 
			{

				e.printStackTrace();

			} 
			finally 
			{
				try 
				{
					if (br != null)
						br.close();
					if (fr != null)
						fr.close();
				}
				catch (IOException ex) 
				{
					ex.printStackTrace();
				}
			}
			return ignoreList;
	 }
}
