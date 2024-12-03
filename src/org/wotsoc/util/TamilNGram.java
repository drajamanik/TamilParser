package org.wotsoc.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.wotsoc.illakanam.TamilUtil;

/**
 * @author Rajamani David
 * @since	Apr 1, 2019
 * Take a string and size to get any form of nGram by word or letter  
 */
public class TamilNGram {
	public static final String DELIMITOR =" ";
	
	public List<List<String>> nGramWord(String str, int gramSize, String parseDelimitor, String ignoreDelimitor){
		List<String> strList = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str,parseDelimitor,false);
		String tempStr=null;
		while(st.hasMoreTokens()){
			tempStr = st.nextToken();
			if(!tempStr.trim().equals(""))
				strList.add(tempStr);
		}
		//System.out.println(strList.size()/gramSize);
		return buildGramsList(strList,strList.size(),gramSize,ignoreDelimitor);
		//return null;
	}
	
	public List<List<String>> nGramWord(List<String> strList, int gramSize, String parseDelimitor, String ignoreDelimitor){
		return buildGramsList(strList,strList.size(),gramSize,ignoreDelimitor);
	}

	private List<List<String>> buildGramsList(List<String> strList, int actualLength, int gramSize, String ignoreDelimitor){
		List<List<String>> newList = new ArrayList<List<String>>();
		int finalIds =0;
		int index=0;
		List<String> internalList = null;
		while(actualLength>=finalIds){
			internalList = new ArrayList<String>();
			for(index=0;index<gramSize;index++){
				if(finalIds+index < strList.size()){
					internalList.add(strList.get(finalIds+index));
				}
			}
			newList.add(internalList);
			//System.out.println(internalList);
			if(finalIds+index >= strList.size())
				break;
			//finalIds = finalIds + index;
			finalIds++;
			index = 0;
		}
		return newList;
	}
	
	private List<String> buildGrams(List<String> strList, int actualLength, int gramSize){
		List<String> newList = new ArrayList<String>();
		int finalIds =0;
		int index=0;
		StringBuilder sb = null;

		while(actualLength>=finalIds){
			sb = new StringBuilder();
			for(index=0;index<gramSize;index++){
				if(finalIds+index < strList.size()){
					sb.append(strList.get(finalIds+index));
				}
			}
			//newList.add(sb.toString());
			newList.add(TamilUtil.எழுத்துகளைசேர்(sb.toString()));
			if(finalIds+index >= strList.size())
				break;
			if(finalIds+index >= gramSize)
				break;
			finalIds = finalIds + index;
			index = 0;
		}
		return newList;
	}
	
	public List<String> nGramLetter(String str, int size, String resultDelimitor)
	{
		TamilStringIterator tsi = new TamilStringIterator(str);
		List<String> strList = tsi.forwardIterator();
		if(size <= 1)
			return strList;
		return buildGrams(strList, tsi.length(), size);
	}

	public List<String> nGramLetterSplitted(String str, int size){
		return nGramLetter(TamilUtil.எழுத்துகளைபிரி(str), size);
	}
	
	public Set<String> nGramLetterUnique(String str, int size){
		return new HashSet<String>(nGramLetter(str, size));
	}
	
	public List<String> nGramLetter(String str, int size)
	{
		TamilStringIterator tsi = new TamilStringIterator(str);
		List<String> strList = tsi.forwardIterator();
		
		if(size <= 1)
			return strList;
		
		List<String> nGramList = new ArrayList<String>();
		
		for(int i =0; i<=size; i++){
			if(i>0)
				nGramList.addAll(strList.subList(0, i));			
			nGramList.addAll(buildGrams(strList.subList(i, strList.size()), tsi.length(), size));
		}
		
		return nGramList;
	}
	
	public static void testLetterGram1(){
		List<String> tamilList= null;
		String str="கண்களால்கைதுசெய்தாள்";
		TamilNGram nGram = new TamilNGram();
		for(int i=0;i<str.length();i++){
			tamilList=nGram.nGramLetter(str,i,"");
			System.out.println(tamilList);
		}
	}
	
	public List<String> allPossibleLetterNGram(String word){
		TamilStringIterator tsi= new TamilStringIterator(word);
		List<String> list = new ArrayList<String>();
		int length=  tsi.length();
		for(int index=1;index <=length;index++)
			list.addAll(letterGram(word,index));
		return list;
	}
	
	public static List<String> letterGram(String str,int size){
		List<String> tamilList= null;
		TamilNGram nGram = new TamilNGram();
		tamilList= nGram.nGramLetter(str,size);
		//System.out.println(tamilList);
		return tamilList;
	}
	
	public static void testLetterGram(String str,int size){
		List<String> tamilList= null;
		TamilNGram nGram = new TamilNGram();
		tamilList= nGram.nGramLetter(str,size);
		System.out.println(tamilList);
	}
	
	public static void testSplittedLetterGram(String str,int size){
		List<String> tamilList= null;
		TamilNGram nGram = new TamilNGram();
		tamilList= nGram.nGramLetterSplitted(str,size);
		System.out.println(tamilList);
	}
	
	public static void testLetterUniqueGram(String str,int size){
		Set<String> tamilSet= null;
		TamilNGram nGram = new TamilNGram();
		tamilSet= nGram.nGramLetterUnique(str,size);
		System.out.println(tamilSet);
	}
	
	public static void testWordGram(String str,int gram){
		if(str==null)
			str="வான மழை போலே புது பாடல்கள் கான மழை தூவும் முகில் ஆடல்கள் நிலைக்கும் கானம் இது "+   
					"நெடுநாள் வாழும் இது வான மழை போலே புது பாடல்கள் கான மழை தூவும் முகில் ஆடல்கள் " + 
					"இதயம் ராத்திரியில் இசையால் அமைதி பெறும் இருக்கும் காயமெல்லாம் இசையால் ஆறிவிடும் " + 
					"கொதிக்கும் பாலையிலும் இசையால் பூ மலரும் இரும்பு பாறையிலும் இசையால் நீர் கசியும் " + 
					"பழி வாங்கும் பகை நெஞ்சும் இசையால் சாந்தி பெறும் வான மழை போலே புது பாடல்கள் " + 
					"கான மழை தூவும் முகில் ஆடல்கள் நிலைக்கும் கானம் இது நெடுநாள் வாழும் இது ";
		
		List<List<String>> listOfList = createWordGram(str," \r\n",gram);
		for(List<String> list:listOfList) {
			System.out.println(list);
//			for(String strVal:list) {	
//				System.out.println(strVal);
//			}
		}
	}
	
	public static List<List<String>> createWordGram(String str,String delimitor, int gram){
		if(str==null)
			return new ArrayList<List<String>>();
		List<List<String>> tamilList= null;
		TamilNGram nGram = new TamilNGram();
		tamilList=nGram.nGramWord(str,gram,delimitor,",");
		return tamilList;
	}
	
	public static List<List<String>> createWordGram(String str,int gram){
		if(str==null)
			return new ArrayList<List<String>>();
		List<List<String>> tamilList= null;
		TamilNGram nGram = new TamilNGram();
		tamilList=nGram.nGramWord(str,gram,DELIMITOR,",");
		return tamilList;
	}
	
	public static List<List<String>> createWordGram(List<String> strList,int gram){
 		TamilNGram nGram = new TamilNGram();
		return nGram.nGramWord(strList,gram,DELIMITOR,",");
	}

	public static void main(String args[]) throws Exception{
		
//		String word="கண்களால்கைதுசெய்தாள்";
		String word="@பி.மணி, குப்பம், ஆந்திர மாநிலம்.கொரோனாவால் இனி மக்களின் எதிர்காலம் என்னவாகும்?‘பொழைச்சுக் கிடந்தா பாப்போம்’ என்று எப்போதுமே கேலி, கிண்டல் கலந்து பேச்சுவாக்கில் ஒரு வசனம் கிராமங்களில் உயிர்ப்புடன் ஒலிக்கும். சொன்ன வேகத்தில் கைவேலையில் மூழ்கியும்விடுவார்கள், நம்பிக்கை பொங்க! @வாசுதேவன் பெங்களூரு.தண்ணீரை வீணடிப்பது, நேரத்தை வீணாக்குவது - ஒப்பிடுக?முன்னது, நேரடியாக பொதுச்சொத்தைச் சேதப்படுத்துவது. பின்னது, மறைமுகமாக பொதுச்சொத்தைச் சேதப்படுத்துவது! @மா.உலகநாதன், திருநீலக்குடி.‘உலக சுகாதார நிறுவனத்துக்கான நிதியை நிறுத்தி விடுவேன்’ என்று மிரட்டுகிறாரே ட்ரம்ப்?ம்... இப்படியும் ஒரு பிழைப்பு. இதற்கு பெயர்... வல்லரசு.@நீலன், கோயம்புத்தூர்.‘சட்டத்தை, அரசால் இயற்ற மட்டுமே முடியும்... மக்கள்தான் கடைப்பிடிக்க வேண்டும்’ என்று முதல்வர் எடப்பாடி பழனிசாமி கூறியிருக்கிறார். இதன் விளக்கம் என்ன?‘கைமீறிப் போய்விட்டது’ என்பதைத்தான் இப்படிச் சொல்கிறாரோ என்னவோ! ‘அரசாங்கத்தைத் தேர்ந்தெடுக்க மட்டுமே மக்களால் முடியும். அதை சரிவர நடத்த வேண்டியது தேர்ந்தெடுக்கப்பட்டவர்கள்தான்’ என யார்தான் அவருக்குப் புரியவைப்பதோ!.@கே.ஆர்.உபேந்திரன், தஞ்சாவூர்-6.கழுகார், ‘வீட்டில் இருந்தே’ ஆபீஸ் வேலையைச் செய்கிறாரோ?காட்டிலிருந்து. அதுதானே கழுகாரின் வீடு.@பிரதீபா ஈஸ்வரன், தேவூர் மேட்டுக்கடை, சேலம் மாவட்டம்.அறிவியல்வாதிகள், ஆன்மிகவாதிகள், அரசியல் வாதிகள் என எல்லோரையும் மூச்சடைக்கவைத்துவிட்டதே இந்த கொரோனா?இயற்கையின் எச்சரிக்கை மணி, எப்போதுமே ஒரேமாதிரியாக ஒலிக்காது... ஆனால் எல்லோருக்கும் ஒரேமாதிரியாகத்தான் ஒலிக்கும்.@ப.கோபிபச்சமுத்து, கிருஷ்ணகிரி.‘சீனாவிலிருந்து ஒரு லட்சம் கொரோனா பரிசோதனைக் கருவிகள் கொள்முதல் செய்யப்படும்’ என முதல்வர் பழனிசாமி அறிவித்தது என்னவாயிற்று?அதுதான் ‘அப்படியே பறிமுதல் செய்யப்படும்’ என்று வழியிலேயே தட்டிப்பறித்துச் சென்றுவி ட்டதே அமெரிக்கா! ஆனால், மத்தியில் ஆளும் மோடிக்கு பயந்து இந்த உண்மையைக்கூட மக்களிடம் சொல்லாமல், ‘சீன அதிகாரிகளின் தவற்றால் அது அமெரிக்காவுக்குச் சென்று விட்டது’ என, பயத்தை பைக்குள் அல்லவா மறைத்துக் கொண்டிருக்கிறார் பழனிசாமி!.@வி.கருணாநிதி, திருமக்கோட்டை, திருவாரூர் மாவட்டம்.அமெரிக்காவில் கொத்துக்கொத்தாக மக்கள் இறந்துகொண்டிருப்பதைப் பார்க்கும்போது, ‘சீனாதான் இந்த வைரஸைப் பரப்பியது’ என்ற அமெரிக்காவின் சந்தேகம் வலுப்படுகிறதுதானே?சீனாவின் அண்டைநாடுகளான தைவான், ரஷ்யா, தென்கொரியா, வியட்நாம் போன்றவை எல்லாம் இந்த அளவுக்கு பாதிக்காத சூழலில், இந்தச் சந்தேகம் வலுப்படாமல் இருப்பதுதான் நியாயம். பிரச்னை வெடித்ததுமே, விழித்துக்கொண்டு எல்லையை மூடிய வேகத்தில் தடுப்பு நடவடிக்கைளையும் மேற்கொண்டார்கள் அவர்கள். ஆனால், ‘பொருளாதாரம்’ முக்கியம் என நியூயார்க் உட்பட பல இடங்களையும் கடைசி வரை பரபரப்பு் குறையாமல் பார்த்துக்கொண்டார்கள் இவர்கள். ஆக, வேடிக்கை பார்த்துகொண்டிருந்த அரசுத் தலைவர்கள் மீதுதான் நியாயமாக சந்தேகம் வலுக்க வேண்டும்..@சு.சரத்குமார், கடலூர்.கொரோனா நிவாரணத்துக்கு நிதி வழங்க முன்னணி நடிகர்கள் பலரும் முன்வரவில்லையே?அதுகுறித்துக் கவலைப்படுவதைவிட, முன்வந்தவர்களை வாழ்த்துங்கள்... பாராட்டுங்கள். ராகவா லாரன்ஸ்போல், ‘மூன்று கோடி ரூபாய் கொடுத்தது போதாது... இன்னும் கொடுப்பேன்’ என்று அந்த நல்உள்ளங்கள் மேலும் இளகுமே!@கிருஷ்ணசுவாமி விஜயராகவன்.குளிரான பிரதேசங்களில்தான் கொரோனா தொற்று வேகமெடுத்திருக்கிறது. இந்த நிலையில், நாம் ஏ.சி-யைப் பயன்படுத்துவது சரியாக இருக்குமா?‘வெயிலுக்கு முன் இந்த வைரஸின் தாக்கம் குறைவாக இருக்கும்’ என்று ஆரம்பத்திலிருந்தே சொல்கிறார்கள். ஆனால், உண்மையென்று நிரூபிக்கப்படவில்லை. எது எப்படியிருந்தாலும், எப்போதுமே வெயில் நல்லது. கொஞ்சகாலத்துக் காவது ‘குளிர்விட்டு’க் கிடப்பதில் தப்பில்லை. @ஆர்.ஜி.அரசு அனுமதித்திருக்கும் நேரத்தில் வெளியில் வரும்போதும், ‘ஏன்... எதற்கு?’ எனக் கேட்காமலேயே போலீஸார் அராஜகம் செய்வது தண்டிக்கப்பட வேண்டிய ஒன்றுதானே?நிச்சயமாக. காரணம் நியாயமானதாக இருக்கும்போது வழக்கமான ‘போலீஸ் புத்தி’யைக் காட்டினால், கண்டிப்பாகத் தண்டிக்கத்தான் வேண்டும். பணியை, அதற்குண்டான ஒழுங்குடன் பார்க்க முடியாது என்றால், ஒட்டுமொத்தமாக வெளியில் நடமாட தடைபோட்டுவிட்டு, பொருள்களை வீடு வீடாக அரசாங்கமே சப்ளை செய்ய வேண்டியதுதான்.@`கடல்’ நாகராஜன், கடலூர்-1.பிரேசில் அதிபர், நமது பிரதமர் மோடியை ‘அனுமன் சஞ்சீவி’ மருந்து கொடுத்ததற்கு இணையாக ஒப்பிட்டு கடிதம் எழுதியிருக்கிறாரே... அவருக்கு ராமாயணம் தெரியுமா?நாம்கூடத்தான்... ‘யூ டூ புரூட்டஸ்’, ‘டு பி ஆர் நாட் டு பி’ என்றெல்லாம் அவ்வப்போது பேசுகிறோம்..@வி.எஸ்.ராமு, செம்பட்டி, திண்டுக்கல் மாவட்டம். ‘நாட்டுவைத்திய முறை’க்கு நல்ல எதிர்காலம் உண்டா?என்றென்றும் நம்முடைய எதிர்காலமே அந்த நல்முறையில்தானே அடங்கியிருக்கிறது.‘மருந்தென வேண்டாவாம் யாக்கைக்கு அருந்தியதுஅற்றது போற்றி உணின்’,‘உணவே மருந்து, மருந்தே உணவு’இப்படி வள்ளுவர் உட்பட பலரும் பலநூறு வடிவங்களில் வலியுறுத்திவைத்திருப்பது தானே நம்முடைய நாட்டுவைத்தியம். ‘மஞ்சள் காமாலைக்கு கீழாநெல்லிச் செடியை அரைத்து மோரில் கலந்து சாப்பிடு’ என்பார் நம் பாட்டி. அந்தச் செடியின் படத்தை பாட்டிலில் போட்டுத்தான் அதே நோய்க்கு மருந்து விற்கிறார்கள் வெளிநாட்டு பார்ட்டிகள். ஆனாலும், பாட்டிகளை ஏற்பவர்கள் இங்கே குறைவுதான்.@ஜெ.ஜானி, போரூர், சென்னை-116.தமிழகத்துக்கு உரிய நிதி வராத சூழலில், ‘மோடியை தமிழர்கள் ஆதரிக்கவில்லை. அதனால், அப்படித்தான்’ என்று மோடி பக்தர்கள் எதிர்வாதம் பேசுகிறார்களே?அவர்கள் பக்தர்கள் அல்லர்... எதிரிகள். இதை மோடிதான் முக்கியமாக உணர வேண்டும்..@ம.ரம்யா, வெள்ளக்கோவில், திருப்பூர் மாவட்டம்.எப்போதும் தள்ளிவைத்துப் பார்க்கப்படும் துப்புரவுப் பணியாளர்களுக்கு தற்போது பாதபூஜை, மலர்மாலை எல்லாம் கிடைக்கின்றனவே?‘ஒரு நாள் கூத்து’. அதேசமயம், இப்போதாவது உணர்கிறார்களே என்ற வகையில் சந்தோஷமே! மாலை, பூஜை இதையெல்லாம் தாண்டி, ‘துப்புரவுப் பணி’ என்பதன் மகத்துவத்தை உணர்ந்து, அவர்களுக்கு உரிய ஊதியத்தையும் சமூக அந்தஸ்தையும் எப்போதுமே தருவதுதான் உண்மையான மரியாதையாக இருக்கும். ‘நம் உடலிலேயே எப்போதும் ஒரு செப்டி டேங்குடன் தான் நாம் அலைகிறோம்’ என்பதையும் உணர்ந்தால், அருவருப்பெல்லாம் நொடிகளில் காணாமல்போய்விடும்..@‘திருப்பூர்’ அர்ஜுனன்.ஜி, அவிநாசி.‘உலக நாடுகளின் மருந்தகம்’ என்ற பெருமை இந்தியாவுக்குக் கிடைத்துள்ளதே?‘உலக நாடுகளின் மருத்துவக் குப்பைக் கூடை’ என்ற பெயரும் சேர்ந்தேதான் கிடைத்துள்ளது.@பா. ஜெயப்பிரகாஷ், பொள்ளாச்சி, கோவை மாவட்டம்‘வாய்விட்டுச் சிரிச்சா... நோய்விட்டுப்போகும்’ எனவே, அடுத்தபடியாக குடும்பத்தோடு மொட்டை மாடியில் நின்று சிரிக்கச் சொல்வாரோ?‘இதுவும் கடந்துபோகும்’ எனச் சிரித்துவிட்டால் போச்சு!@இந்து குமரப்பன், விழுப்புரம். ஏப்ரல் மாதம் 30-ம் தேதி வரை ஊரடங்கு உத்தரவை நீட்டித்து தமிழக அரசு அறிவித்திருப்பது, எதிர்பார்த்ததுதானே?ஆனால், எதிர்பார்க்கக் கூடாத விஷயம் இது. ஆரம்பத்தில் சில விஷயங்களில் அரசாங்கம் கோட்டைவிட்டது என்றால், இப்போது ஒரேயொரு விஷயத்தில் நாம் கோட்டை விடுவதுதான் சிக்கலை நீட்டிக்கிறது. மீண்டும் இப்படியோர் எதிர்பார்ப்பு உருவாகாதவண்ணம் ஒழுங்காக அனைவரும் ஊரடங்குவோம்... அடங்கவும் வைப்போம்..கேள்விகள் அனுப்ப  வேண்டிய முகவரி:கழுகார் பதில்கள், ஜூனியர் விகடன், 757, அண்ணா சாலை, சென்னை- 600002 என்ற இமெயிலுக்கும் அனுப்பலாம்!";
		System.out.println(createWordGram(word,2));
		System.out.println(createWordGram(word,3));
		//word=TamilUtil.எழுத்துகளைபிரி(word);
		//List<String> wordList = Arrays.asList("பிறவிப் பெருங்கடல் நீந்துவர் நீந்தார் இறைவன் அடிசேரா தார்");
		//List<String> wordList = Arrays.asList("பிறவிப்", "பெருங்கடல்", "நீந்துவர்", "நீந்தார்", "இறைவன்", "அடிசேராதார்");
		//List<String> wordList = Arrays.asList("அகர", "முதல", "எழுத்தெல்லாம்", "ஆதி", "பகவன்", "முதற்றே", "உலகு");
		//List<String> wordList =Arrays.asList("முதற்றே");
//		for(String word:wordList) {
//			word=TamilUtil.எழுத்துகளைபிரி(word);
//			TamilStringIterator tsi= new TamilStringIterator(word);
//			int length=  tsi.length();
//			for(int index=1;index<=length;index++)
//				testLetterGram(word,index);
//		}
		
		
		//testWordGram(null, 4);
//		testLetterUniqueGram("கண்களால்கைதுசெய்தாள்",3);
//		testSplittedLetterGram("கண்களால்கைதுசெய்தாள்",3);
//		testLetterGram("கண்களால்கைதுசெய்தாள்",2);
//		testLetterUniqueGram("கண்களால்கைதுசெய்தாள்",2);
//		testSplittedLetterGram("கண்களால்கைதுசெய்தாள்",2);
//		String fileName = "c:\\Workspaces\\Tamil\\properties\\endsWith.txt";
//		testWordGram(ReadFromFile.readFileAsString(fileName),3);  
		//testWordGram(null,3);
	}
}
