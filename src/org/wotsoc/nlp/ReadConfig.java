package org.wotsoc.nlp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Rajamani David
 * @since	Oct 23, 2017
 *
 */
public class ReadConfig 
{
	static Map<String,String> map=null; 
	static ReadConfig rc =null;
	static String currentRoot = ""; 
	
	private ReadConfig()
	{
		
	}
	
	public String getCurrentRoot() {
		return currentRoot;
	}
	
	public String[][] convertMainConstantFileAsArray(String fileName) throws Exception
	{
		List<List<String>> mainList = readMainConstantFileAsList(fileName);
		
		String[][] mainTable = new String[mainList.size()][];

		int i = 0;
		for (List<String> innerList : mainList) 
		{
			mainTable[i++] = innerList.toArray(new String[innerList.size()]);
		}
		return mainTable;
	}
	
	public List<List<String>> readMainConstantFileAsList(String fileName) throws Exception
	{
	 	List<List<String>> mainList =new ArrayList<List<String>>();
	 	List<String> lineList = null;
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
				lineList =new ArrayList<String>();
				strValues=sCurrentLine.trim().split(",");
				lineList.addAll(Arrays.asList(strValues));
				mainList.add(lineList);
			}
			System.out.println(mainList);

		} 
		catch (IOException e) 
		{

			e.printStackTrace();

		} finally {
			try {

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
		return mainList;
	 }
	 
	 public List<List<Integer>> readParseOrderFileAsList(String fileName) throws Exception
	 {
	 	List<List<Integer>> mainList =new ArrayList<List<Integer>>();
	 	List<Integer> lineList = null;
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
				lineList =new ArrayList<Integer>();
				strValues=sCurrentLine.trim().split(",");
			
				try
				{
					if(strValues.length>0){
						for(String str:strValues)
						{
							if(!str.trim().equals(""))
								lineList.add (Integer.parseInt(str.trim()));
						}
					}
				}catch(Exception exp)
				{
					exp.printStackTrace();
				}
				
				mainList.add(lineList);
			}
			System.out.println(mainList);

		} 
		catch (IOException e) 
		{

			e.printStackTrace();

		} 
		finally 
		{
			try {

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
		return mainList;
	 }

	 public Properties readPropertiesFile(String fileName) throws Exception{
         Properties prop = new Properties();
		 try (InputStream input = new FileInputStream(fileName)) {
	            prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));
		 }catch (IOException ex) {
	            ex.printStackTrace();
		 }    
		 return prop;
	 }
	 
	 public List<String> readDelimitorSeperatedFile(String fileName,String delimitor) throws Exception
	 {
		 	List<String> ignoreList =new ArrayList<String>();
			BufferedReader br = null;
			FileReader fr = null;
			try 
			{
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);

				//String match1="^[a-zA-Z]*[0-9]*";
				String match1="^[a-zA-Z]*";
				String match2="^[,._-_?]*";
				String sCurrentLine;
				String strValues[]=null;
				while ((sCurrentLine = br.readLine()) != null) 
				{
//					sCurrentLine = sCurrentLine.replaceAll("(?=[]\\[+&|!(){}^\"~*?:\\\\-])", "");

					sCurrentLine = sCurrentLine.replaceAll(match1, "");
					sCurrentLine.replaceAll(match2, "");

					strValues=sCurrentLine.trim().split(delimitor);
					//ignoreList.addAll(Arrays.asList(strValues));
					List<String> list=Arrays.asList(strValues);
					for(String str:list)
						ignoreList.add (str.trim());
					//System.out.println(sCurrentLine+"\n");
				}
				//System.out.println(ignoreList);

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

	 public String readFileAsIs(String fileName) throws Exception
	 {
		 	StringBuilder sb = new StringBuilder();
			BufferedReader br = null;
			FileReader fr = null;
			try 
			{
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);
				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null) 
				{
					sb.append(sCurrentLine).append("\n");
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

	 public  Map<String,String> readIntialProperties(String fileName) throws Exception
	 {
		 System.out.println("Current working directory : " + fileName);
		 List<String> listOfFiles=readDelimitorSeperatedFile(fileName); 
		 
		 Map<String,String> map =new HashMap<String,String>();
		 String strArrValue[]=null;
		 for(String strValue:listOfFiles)
		 {
			 strArrValue=strValue.split("=");
			 map.put(strArrValue[0],strArrValue[1]);
		 }
		 System.out.println("All Files: " + map);
		 return map;
	 }
	 
	 private  Map<String,String> readIntialProperties() throws Exception
	 {
		 String workingDir = System.getProperty("user.dir");
		 System.out.println("Current working directory : " + workingDir);
		 String path = this.getClass().getClassLoader().getResource("").getPath();
		 
		 currentRoot = URLDecoder.decode(path, "UTF-8");
		 currentRoot = currentRoot.replace("classes/","");
		 
		 System.out.println("Current App Full Path : " + currentRoot);
		 System.out.println(currentRoot+":"+"../properties/allFileList.list");
		 
		 List<String> listOfFiles=readDelimitorSeperatedFile(currentRoot+"../properties/allFileList.list"); 
		 
		 Map<String,String> map =new HashMap<String,String>();
		 String strArrValue[]=null;
		 for(String strValue:listOfFiles)
		 {
			 strArrValue=strValue.split("=");
			 map.put(strArrValue[0],strArrValue[1]);
		 }
		 System.out.println("All Files: " + map);
		 return map;
	 }
	 
	 public Map<String,String> readFileAsMap(String fileName) throws Exception
		{
			Map<String,String> mainMap = new LinkedHashMap<String,String>();
			BufferedReader br = null;
			FileReader fr = null;
			try 
			{
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);

				String sCurrentLine;
				String values[] = null; 
				while ((sCurrentLine = br.readLine()) != null) 
				{
					values = sCurrentLine.split("=");
					mainMap.put(values[0],values[1]);
				}
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}finally {
				try {
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
			return mainMap;
		 }
	 
	 public Map<String,String> getProperties()
	 {
		 return map;
	 }
	 
	 public static ReadConfig getInstance() throws Exception
	 {
		 if(rc==null)
		 {
			 rc=new ReadConfig();
			 map=rc.readIntialProperties();
		 }
		 return rc;
	 }

	 public static void main(String[] args) throws Exception 
	 {
		 ReadConfig rc =ReadConfig.getInstance();
		 //System.out.println(rc.getProperties());
		 //List<String> list=rc.readDelimitorSeperatedFile(args[0]," ");
		 Properties prop=rc.readPropertiesFile("c://Workspaces//Tamil//properties//specialCharacter.list");
		 System.out.println(prop);
//		 for(String str:list)
//			 System.out.println(str);
		 //rc.readMainConstantFileAsList(args[0]);
		 //rc.readParseOrderFileAsList(args[0]);
		 

			
	 }
}
