package org.wotsoc.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.wotsoc.nlp.ConfigConstants;
import org.wotsoc.nlp.RecursiveAlgorithm;
import org.wotsoc.nlp.TamilConstantTable;

public class ReadAndWriteParseOrderList {
	
	static List<List<List<String>>> globalList =null;
	static TamilConstantTable staticTct = null;
	public static String mainWords[][]=null;
	static String mainConstantFileName =null;
	static List<List<Integer>> mainListOfList= null;
	static Properties parseMapProperty  = null; 
	static Map<List<Integer>,List<List<String>>> mainParseOrderToValue =new HashMap<List<Integer>,List<List<String>>> ();
	static Map<List<List<String>>,List<Integer>> mainValueToParseOrder =new HashMap<List<List<String>>,List<Integer>> ();

	public ReadAndWriteParseOrderList() throws Exception 
	{
		staticTct=TamilConstantTable.getInstance();
		Object[] objArr=staticTct.getParseAndMainValue(ConfigConstants.VERB_CONSTANT_FILE_NAME, ConfigConstants.VERB_PARSE_ORDER_FILE_NAME,ConfigConstants.VERB_PARSE_MAP_FILE_NAME);
		mainWords  = (String[][])objArr[0];
		mainListOfList = (List<List<Integer>>)objArr[1];
		parseMapProperty  = (Properties)objArr[2];
		globalList= staticTct.getMainTable(mainWords,mainListOfList,mainParseOrderToValue,mainValueToParseOrder);

	}
	
	public void persist(List<List<String>> arrList, String fileName) {
		  try
	        {
	            FileOutputStream fos = new FileOutputStream(fileName);
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            oos.writeObject(arrList);
	            oos.close();
	            fos.close();
	        } 
	        catch (IOException ioe) 
	        {
	            ioe.printStackTrace();
	        }
	}
	
	@SuppressWarnings("unchecked")
	public List<List<String>> read(String fileName) {
		List<List<String>> list = new ArrayList<List<String>>();
        try{
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
            return null;
        } 
        catch (ClassNotFoundException c) 
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }	
        return list;
    }
	
	public void persist() throws Exception {
		String outerKey ="";
		Map<String,List<List<String>>> map = new HashMap<String,List<List<String>>>();
		
		for (List<List<String>> outerList:globalList)
		{
			if(outerList.size()>4) {
				outerKey= getValueToParseOrder().get(outerList).toString();
				//if(outerKey.contains("[1, 21, 12, 11, 20]")) {
					System.out.println("****"+outerKey);
					List<List<String>> listOfInnnerList = new ArrayList <List<String>>();
					RecursiveAlgorithm ra = new RecursiveAlgorithm();
					listOfInnnerList=ra.getAllCombinedValues(outerList);
					System.out.print(outerKey+":");
					persist(listOfInnnerList, outerKey+".txt");
					listOfInnnerList = null;
					listOfInnnerList = read(outerKey+".txt");
					WriteToFile.writeToFile(listOfInnnerList.toString(),outerKey+".txt");
					map.put(outerKey,listOfInnnerList);
					//System.out.println(listOfInnnerList);
				//}
			}
		}
		System.out.println("Done:"+map.size());
	}
	
	public Map<List<List<String>>,List<Integer>> getValueToParseOrder()
	{
		return mainValueToParseOrder;
	}

	public static void main(String args[]) throws Exception {
		long beginTime = System.currentTimeMillis();
		ReadAndWriteParseOrderList wpol = new ReadAndWriteParseOrderList();
		wpol.persist();
		long endTime = System.currentTimeMillis() - beginTime;
		System.out.println(endTime);
		
	}
}
