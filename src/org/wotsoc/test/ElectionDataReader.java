package org.wotsoc.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.wotsoc.util.ReadFromFile;

public class ElectionDataReader {
	public static void main(String args[]) throws Exception {
		System.out.println("Test");
		ElectionDataReader rff = new ElectionDataReader();
		System.out.println(rff.readFileAsString("C:\\software\\apache-tomcat-9.0.24\\webapps\\ac001011.csv"));
	}
	
	public String  readFileAsString(String fileName) throws Exception{
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
				System.out.println(sCurrentLine);
			}
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
		return sb.toString();
	}
}
