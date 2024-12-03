/**
 * @author Rajamani David
 * @since	Nov 30, 2017
 *
 */
package org.wotsoc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Rajamani David
 *  @description	Converts English Literal word to Tamil Word 
 *  peVN -> பெண்
 *	neram -> நேரம்   
 *  Take a input file and write to output file
 */
public class TransliteralConvertor 
{
	Map<String,String> map = new HashMap<String,String>();

	public TransliteralConvertor(){
		 
	}
	
	public static void main(String args[]) throws Exception{
		String inputFileName  = null;
		String outputFileName = null;
		String configFileName = null;
		
		if(args!=null && args.length>2){
			configFileName = args[0];
			inputFileName = args[1];
			outputFileName = args[2];
		}

		if(configFileName ==null || inputFileName==null || outputFileName==null){
			System.out.println("-----------Usage---------------------");
			System.out.println("Command Line parms should pass -Dfile.encoding=UTF-8"); 
			System.out.println("Three files - Config, Input and Output all file are mandatory.");
			System.out.println("1. Config file should contain key=value per row.");
			System.out.println("2. Input file should contain English tranliteral or Tamil word convert to be tranliteral .");
			System.out.println("3. Output file can be empty file or file to be append for collecting results.");
			System.out.println("-----------Usage---------------------");
			return;
		}
		 
		TransliteralConvertor cett= new TransliteralConvertor();
		TransliteralUtil util = new TransliteralUtil(cett.load(configFileName));
		 
		List<String> list=util.readDelimitorSeperatedFile(inputFileName);
		util.storeWords(list,outputFileName);
	}
	
	public Properties load(String configFileName) throws IOException{
		Properties prop = new Properties();
		FileInputStream   fileStream= new FileInputStream (new File(configFileName));
		prop.load(new InputStreamReader(fileStream, Charset.forName("UTF-8")));
		return prop; 
 	}
}
