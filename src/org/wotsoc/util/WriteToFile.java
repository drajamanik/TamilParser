package org.wotsoc.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.wotsoc.nlp.ConfigConstants;
import org.wotsoc.nlp.ReadConfig;

/**
 * @author Rajamani David
 * @since	Oct 25, 2017
 */
public class WriteToFile 
{
	public WriteToFile() 
	{
		// TODO Auto-generated constructor stub
	}
 
	public static void writeToFile(StringBuilder sb) throws Exception{
		writeToFile(sb,null);
	}

	public static void writeToFile(StringBuilder sb,String fileName) throws Exception{
		writeToFile(sb.toString(),fileName);
	}
	
	public static void writeToFile(String word,Collection<String> sbList,String fileName) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append(word).append(",");
		sb.append(sbList.toString()).append("\n");
		writeToFile(sb,fileName);
	}
	
	public static void writeToFile(List<List<String>> sbListOfList,String fileName) throws Exception{
		StringBuilder sb = new StringBuilder();
		for(List<String> sbList:sbListOfList)
			sb.append(sbList).append("\n");

		writeToFile(sb,fileName);
	}
	
	public static void writeToFile(Map<String,Integer> sbMap,String fileName) throws Exception{
		StringBuilder sb = new StringBuilder();
		Set<String> set =sbMap.keySet();
		for(String str:set)
			sb.append(str).append(",").append(sbMap.get(str)).append("\n");

		writeToFile(sb,fileName);
	}
	
	public static void writeToFile(Collection<String> sbList,String fileName) throws Exception{
		StringBuilder sb = new StringBuilder();
		for(String str:sbList)
			sb.append(str).append("\n");
		if(sb.length()>0)
			writeToFile(sb,fileName);
	}
	
	public static void writeToFile(String sb,String fileName) throws Exception 
	{
		BufferedWriter bw = null;
		FileWriter fw = null;

		try 
		{
			if(fileName==null){
				Map<String,String> map=ReadConfig.getInstance().getProperties();
				fileName = ReadConfig.getInstance().getCurrentRoot()+ map.get(ConfigConstants.RESULT_FILE_NAME);
			}else if( !(fileName.contains("/") || fileName.contains("\\")) ){
				fileName = ReadConfig.getInstance().getCurrentRoot()+fileName;
			}
			FileOutputStream fos = new FileOutputStream(fileName,true);
		    Writer out = new OutputStreamWriter(fos,Charset.forName("UTF-8").newEncoder());
			out.append(sb);
			out.flush();
			out.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
	}
}
