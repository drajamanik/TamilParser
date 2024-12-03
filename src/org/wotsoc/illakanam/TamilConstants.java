/**
 * @author Rajamani David
 * @since	May 2, 2017
 *
 */
package org.wotsoc.illakanam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Rajamani David
 * @since	18/Apr/2019
 */
public interface TamilConstants 
{
	 // Independent vowels
		static int உயிரெழுத்து[]=
		{ 
			0xB85,// அ TAMIL LETTER A
			0xB86,// ஆ TAMIL LETTER AA
			0xB87,// இ TAMIL LETTER I
			0xB88,// ஈ TAMIL LETTER II
			0xB89,// உ TAMIL LETTER U
			0xB8A,// ஊ TAMIL LETTER UU
			0xB8E,// எ TAMIL LETTER E
			0xB8F,// ஏ TAMIL LETTER EE
			0xB90,// ஐ TAMIL LETTER AI
			0xB92,// ஒ TAMIL LETTER O
			0xB93,// ஓ TAMIL LETTER OO
			0xB94,// ஔ TAMIL LETTER AU
			0xB83 // ஃ TAMIL SIGN VISARGA
		};
		
		static int உயிர்மெய்யெழுத்து[]=
		{
				0xB95,// க TAMIL LETTER KA
				0xB99,// ங TAMIL LETTER NGA
				0xB9A,// ச TAMIL LETTER CA
				0xB9C,// ஜ TAMIL LETTER JA
				0xB9E,// ஞ TAMIL LETTER NYA
				0xB9F,// ட TAMIL LETTER TTA
				0xBA3,// ண TAMIL LETTER NNA
				0xBA4,// த TAMIL LETTER TA
				0xBA8,// ந TAMIL LETTER NA
				0xBA9,// ன TAMIL LETTER NNNA
				0xBAA,// ப TAMIL LETTER PA
				0xBAE,// ம TAMIL LETTER MA
				0xBAF,// ய TAMIL LETTER YA
				0xBB0,// ர TAMIL LETTER RA
				0xBB1,// ற TAMIL LETTER RRA
				0xBB2,// ல TAMIL LETTER LA
				0xBB3,// ள TAMIL LETTER LLA
				0xBB4,// ழ TAMIL LETTER LLLA
				0xBB5,// வ TAMIL LETTER VA
				0xBB6,// ஶ TAMIL LETTER SHA
				0xBB7,// ஷ TAMIL LETTER SSA
				0xBB8,// ஸ TAMIL LETTER SA
				0xBB9 // ஹ TAMIL LETTER HA
		};
		
		static String மெய்யெழுத்து[]=
		{
			"\u0B95\u0BCD",// க்  TAMIL LETTER KA
			"\u0B99\u0BCD",// ங் TAMIL LETTER NGA
			"\u0B9A\u0BCD",// ச் TAMIL LETTER CA
			"\u0B9C\u0BCD",// ஜ் TAMIL LETTER JA
			"\u0B9E\u0BCD",// ஞ் TAMIL LETTER NYA
			"\u0B9F\u0BCD",// ட் TAMIL LETTER TTA
			"\u0BA3\u0BCD",// ண் TAMIL LETTER NNA
			"\u0BA4\u0BCD",// த் TAMIL LETTER TA
			"\u0BA8\u0BCD",// ந் TAMIL LETTER NA
			"\u0BA9\u0BCD",// ன் TAMIL LETTER NNNA
			"\u0BAA\u0BCD",// ப் TAMIL LETTER PA
			"\u0BAE\u0BCD",// ம் TAMIL LETTER MA
			"\u0BAF\u0BCD",// ய் TAMIL LETTER YA
			"\u0BB0\u0BCD",// ர் TAMIL LETTER RA
			"\u0BB1\u0BCD",// ற் TAMIL LETTER RRA
			"\u0BB2\u0BCD",// ல் TAMIL LETTER LA
			"\u0BB3\u0BCD",// ள் TAMIL LETTER LLA
			"\u0BB4\u0BCD",// ழ் TAMIL LETTER LLLA
			"\u0BB5\u0BCD",// வ் TAMIL LETTER VA
			"\u0BB6\u0BCD",// ஶ் TAMIL LETTER SHA
			"\u0BB7\u0BCD",// ஷ் TAMIL LETTER SSA
			"\u0BB8\u0BCD",// ஸ் TAMIL LETTER SA
			"\u0BB9\u0BCD" // ஹ் TAMIL LETTER HA
		};

		static String வல்லினமமெய்[]= 
		{
			TamilConstants.க்,TamilConstants.ச்,TamilConstants.ட்,TamilConstants.த்,TamilConstants.ப்,TamilConstants.ற்  
		};

		static String மெல்லினமமெய்[]= 
		{
			TamilConstants.ங்,TamilConstants.ஞ்,TamilConstants.ண்,TamilConstants.ந்,TamilConstants.ம்,TamilConstants.ன் 
		};

		static String இடையினமமெய்[]= 
		{
			TamilConstants.ய்,TamilConstants.ர்,TamilConstants.ல்,TamilConstants.வ்,TamilConstants.ழ்,TamilConstants.ள் 
		};
		
		static int ஆய்தயெழுத்து =TamilConstants.ஃ;
				
		static String வினாயெழுத்து[]=
		{
			String.valueOf(TamilConstants.ஆ),TamilConstants.யா,String.valueOf(TamilConstants.ஓ),String.valueOf(TamilConstants.எ),String.valueOf(TamilConstants.ஏ)
		};

		static String முன்_வினாயெழுத்து[]=
		{
			TamilConstants.யா,String.valueOf(TamilConstants.எ),String.valueOf(TamilConstants.ஏ)
		};
		
		static int கடை_வினாயெழுத்து[]=
		{
			0xBCB,0xBBE
		};
		
		static int முன்யெழுத்து[]=
		{
			TamilConstants.க,TamilConstants.ச,TamilConstants.த,TamilConstants.ப,TamilConstants.ங,TamilConstants.ஞ,TamilConstants.ந,TamilConstants.ம,TamilConstants.ய,TamilConstants.வ 
		};
		
		static String கடைமெய்யெழுத்து[]=
		{
			TamilConstants.ஞ்,TamilConstants.ண்,TamilConstants.ந்,TamilConstants.ம்,TamilConstants.ன்,TamilConstants.ய்,TamilConstants.ர்,TamilConstants.ல்,TamilConstants.வ்,TamilConstants.ழ்,TamilConstants.ள்
		};
		
		static List<String> ஒற்று_மெய்யெழுத்துமுடிகிறதா=
				Arrays.asList(TamilConstants.க்,TamilConstants.ச்,TamilConstants.த்,TamilConstants.ப் ,TamilConstants.ந்,TamilConstants.ய்);
		 
		static List<String> வல்லினம்_மெய்யெழுத்துமுடிகிறதா=
				Arrays.asList(TamilConstants.க்,TamilConstants.ச்,TamilConstants.ட்,TamilConstants.த்,TamilConstants.ப்
						,TamilConstants.ற்);
		
		static List<String> வல்லினம்_மெய்யெழுத்து_வ்toற்_முடிகிறதா=
				Arrays.asList(TamilConstants.க்,TamilConstants.ச்,TamilConstants.ட்,TamilConstants.த்,TamilConstants.ப்
						,TamilConstants.வ்,TamilConstants.ற்);
		 
		static String இறந்தகாலஉறுபுபா[]=
		{
			 TamilConstants.ந்+TamilConstants.த்,TamilConstants.த்+TamilConstants.த்
		};
		
		static int வல்லினம்[]= 
		{
			TamilConstants.க,TamilConstants.ச,TamilConstants.ட,TamilConstants.த,TamilConstants.ப,TamilConstants.ற  
		};

		static int மெல்லினம்[]= 
		{
			TamilConstants.ங,TamilConstants.ஞ,TamilConstants.ண,TamilConstants.ந,TamilConstants.ம,TamilConstants.ன 
		};
	
		static int இடையினம்[]= 
		{
			TamilConstants.ய,TamilConstants.ர,TamilConstants.ல,TamilConstants.வ,TamilConstants.ழ,TamilConstants.ள 
		};
		
		static int குற்றெழுத்து[] =
		{
			TamilConstants.அ,TamilConstants.இ,TamilConstants.உ,TamilConstants.எ,TamilConstants.ஒ
		};
		
		static int நெட்டெழுத்து [] =
		{
			TamilConstants.ஆ,TamilConstants.ஈ,TamilConstants.ஊ ,TamilConstants.ஐ,TamilConstants.ஏ,TamilConstants.ஓ,TamilConstants.ஔ
		};

		static int சுட்டெழுத்து [] =
		{
			TamilConstants.அ,TamilConstants.இ,TamilConstants.உ
		};
		
		static int முதல்லெழுத்து[]=
		{
			TamilConstants.அ, TamilConstants.ஆ, TamilConstants.இ, TamilConstants.ஈ, TamilConstants.உ, TamilConstants.ஊ, 
			TamilConstants.எ,TamilConstants.ஏ,TamilConstants.ஐ,TamilConstants.ஒ,TamilConstants.ஓ,TamilConstants.ஔ,
			TamilConstants.க,TamilConstants.ச, TamilConstants.ஞ,TamilConstants.த,TamilConstants.ந,TamilConstants.ப,TamilConstants.ம,TamilConstants.ய,
			TamilConstants.வ,TamilConstants.ஜ,TamilConstants.ஷ,TamilConstants.ஸ,TamilConstants.ஹ
		};
		
		static int கடையெழுத்திமுடியும்[]=
		{
			TamilConstants.அ, TamilConstants.ஆ, TamilConstants.இ, TamilConstants.ஈ, TamilConstants.உ, TamilConstants.ஊ, 
			TamilConstants.ஏ,TamilConstants.ஐ,TamilConstants.ஓ,TamilConstants.ஔ
		};
		static int கடையெழுத்தில்முடியாது[]=
		{
			TamilConstants.அ, TamilConstants.ஆ, TamilConstants.இ, TamilConstants.ஈ, TamilConstants.உ, TamilConstants.ஊ, 
			TamilConstants.எ,TamilConstants.ஏ,TamilConstants.ஐ,TamilConstants.ஒ,TamilConstants.ஓ,TamilConstants.ஔ,TamilConstants.எ_EXT
		};

		static int அ =0xB85; // அ TAMIL LETTER A
		static int ஆ = 0xB86;// ஆ TAMIL LETTER AA
		static int இ = 0xB87;// இ TAMIL LETTER I
		static int ஈ = 0xB88;// ஈ TAMIL LETTER II
		static int உ= 0xB89;// உ TAMIL LETTER U
		static int ஊ = 0xB8A;// ஊ TAMIL LETTER UU
		static int எ = 0xB8E;// எ TAMIL LETTER E
		static int ஏ = 0xB8F;// ஏ TAMIL LETTER EE
		static int ஐ = 0xB90;// ஐ TAMIL LETTER AI
		static int ஒ = 0xB92;// ஒ TAMIL LETTER O
		static int ஓ = 0xB93;// ஓ TAMIL LETTER OO
		static int ஔ =0xB94;// ஔ TAMIL LETTER AU
		static int ஃ = 0xB83; // ஃ TAMIL SIGN VISARGA

		static int ஆ_EXT=0xBBE;  //  3006 $ா TAMIL VOWEL SIGN AA
		static int இ_EXT=0xBBF;   //  3007 $ி TAMIL VOWEL SIGN I
		static int ஈ_EXT=0xBC0;	  //  3008 $ீ TAMIL VOWEL SIGN II
		static int உ_EXT=0xBC1;   //  3009 $ு TAMIL VOWEL SIGN U
		static int ஊ_EXT=0xBC2;  //  3010  ூ$ TAMIL VOWEL SIGN UU
		static int எ_EXT=0xBC6;   //  3014  ெ TAMIL VOWEL SIGN E
		static int ஏ_EXT=0xBC7;   //  3015  ே TAMIL VOWEL SIGN EE
		static int ஐ_EXT=0xBC8;   //  3016  ை TAMIL VOWEL SIGN AI
		static int ஒ_EXT=0xBCA;   //  3018 $ொ TAMIL VOWEL SIGN O
		static int ஓ_EXT=0xBCB;   //  3019 $ோ TAMIL VOWEL SIGN OO
		static int ஔ_EXT=0xBCC; //  3020 $ௌ TAMIL VOWEL SIGN AU
		static int ஃ_EXT=0xBCD;   //  3021 $் TAMIL SIGN VIRAMA

		static int க =0xB95;// க TAMIL LETTER KA
		static int ங =0xB99;// ங TAMIL LETTER NGA
		static int ச =0xB9A;// ச TAMIL LETTER CA
		static int ஜ =0xB9C;// ஜ TAMIL LETTER JA
		static int ஞ=0xB9E;// ஞ TAMIL LETTER NYA
		static int ட=0xB9F;// ட TAMIL LETTER TTA
		static int ண=0xBA3;// ண TAMIL LETTER NNA
		static int த=0xBA4;// த TAMIL LETTER TA
		static int ந=0xBA8;// ந TAMIL LETTER NA
		static int ன=0xBA9;// ன TAMIL LETTER NNNA
		static int ப=0xBAA;// ப TAMIL LETTER PA
		static int ம=0xBAE;// ம TAMIL LETTER MA
		static int ய=0xBAF;// ய TAMIL LETTER YA
		static int ர=0xBB0;// ர TAMIL LETTER RA
		static int ற=0xBB1;// ற TAMIL LETTER RRA
		static int ல=0xBB2;// ல TAMIL LETTER LA
		static int ள=0xBB3;// ள TAMIL LETTER LLA
		static int ழ=0xBB4;// ழ TAMIL LETTER LLLA
		static int வ=0xBB5;// வ TAMIL LETTER VA
		static int ஶ=0xBB6;// ஶ TAMIL LETTER SHA
		static int ஷ=0xBB7;// ஷ TAMIL LETTER SSA
		static int ஸ=0xBB8;// ஸ TAMIL LETTER SA
		static int ஹ=0xBB9; // ஹ TAMIL LETTER HA
		
		static String க்="\u0B95\u0BCD";// க்  TAMIL LETTER KA
		static String ங்="\u0B99\u0BCD";// ங் TAMIL LETTER NGA
		static String ச்="\u0B9A\u0BCD";// ச் TAMIL LETTER CA
		static String ஜ்="\u0B9C\u0BCD";// ஜ் TAMIL LETTER JA
		static String ஞ்="\u0B9E\u0BCD";// ஞ் TAMIL LETTER NYA
		static String ட்="\u0B9F\u0BCD";// ட் TAMIL LETTER TTA
		static String ண்="\u0BA3\u0BCD";// ண் TAMIL LETTER NNA
		static String த்="\u0BA4\u0BCD";// த் TAMIL LETTER TA
		static String ந்="\u0BA8\u0BCD";// ந் TAMIL LETTER NA
		static String ன்="\u0BA9\u0BCD";// ன் TAMIL LETTER NNNA
		static String ப்="\u0BAA\u0BCD";// ப் TAMIL LETTER PA
		static String ம்="\u0BAE\u0BCD";// ம் TAMIL LETTER MA
		static String ய்="\u0BAF\u0BCD";// ய் TAMIL LETTER YA
		static String ர்="\u0BB0\u0BCD";// ர் TAMIL LETTER RA
		static String ற்="\u0BB1\u0BCD";// ற் TAMIL LETTER RRA
		static String ல்="\u0BB2\u0BCD";// ல் TAMIL LETTER LA
		static String ள்="\u0BB3\u0BCD";// ள் TAMIL LETTER LLA
		static String ழ்="\u0BB4\u0BCD";// ழ் TAMIL LETTER LLLA
		static String வ் ="\u0BB5\u0BCD";// வ் TAMIL LETTER VA
		static String ஶ்="\u0BB6\u0BCD";// ஶ் TAMIL LETTER SHA
		static String ஷ்="\u0BB7\u0BCD";// ஷ் TAMIL LETTER SSA
		static String ஸ்="\u0BB8\u0BCD";// ஸ் TAMIL LETTER SA
		static String ஹ்="\u0BB9\u0BCD"; // ஹ் TAMIL LETTER HA
		
		
		static String ககரமெய் = "க்";
		static String சகரமெய் = "ச்";
		static String டகரமெய் = "ட்";
		static String தகரமெய் = "த்";
		static String பகரமெய் = "ப்";
		static String றகரமெய் = "ற்";
		static String மகரமெய் = "ம்";

		static String யா="\u0BAF\u0BBE";// ய் TAMIL LETTER YA
		
		static int dVowels[]=
		{
				0xBBE,//  3006 $ா TAMIL VOWEL SIGN AA
				0xBBF,//  3007 $ி TAMIL VOWEL SIGN I
				0xBC0,//  3008 $ீ TAMIL VOWEL SIGN II
				0xBC1,//  3009 $ு TAMIL VOWEL SIGN U
				0xBC2,//  3010  ூ$ TAMIL VOWEL SIGN UU
				0xBC6,//  3014  ெ TAMIL VOWEL SIGN E
				0xBC7,//  3015  ே TAMIL VOWEL SIGN EE
				0xBC8,//  3016  ை TAMIL VOWEL SIGN AI
				0xBCA,//  3018 $ொ TAMIL VOWEL SIGN O
				0xBCB,//  3019 $ோ TAMIL VOWEL SIGN OO
				0xBCC,//  3020 $ௌ TAMIL VOWEL SIGN AU
				0xBCD //  3021 $்    TAMIL SIGN VIRAMA
		};

		//Dependent Vowels
		static int d1Vowels[]=
		{
				0xBBE,//  $ா TAMIL VOWEL SIGN AA
				0xBBF,//  $ி TAMIL VOWEL SIGN I
				0xBC0,//  $ீ TAMIL VOWEL SIGN II
				0xBC1,//  $ு TAMIL VOWEL SIGN U
				0xBC2,//  ூ$ TAMIL VOWEL SIGN UU
				0xBC6,//  ெ TAMIL VOWEL SIGN E
				0xBC7,//  ே TAMIL VOWEL SIGN EE
				0xBC8,//  ை TAMIL VOWEL SIGN AI
				0xBCD // $் TAMIL SIGN VIRAMA
		};
		
		//2 Part Dependent Vowels
		static int d2Vowels[]=
		{
				0xBCA,//   $ொ TAMIL VOWEL SIGN O
					  //   0BC6   $ெ  0BBE $ா
				0xBCB,//   $ோ TAMIL VOWEL SIGN OO
					  //   0BC7   $ே  0BBE $ா
				0xBCC //   $ௌ TAMIL VOWEL SIGN AU
					  //   0BC6   $ெ  0BD7 $ௗ
		};
		
		int[] ஆவில்முடிகிறதா  =new int[]{TamilConstants.ஆ_EXT};
		int[] இயில்முடிகிறதா  =new int[]{TamilConstants.இ_EXT};
		int[] ஐயில்முடிகிறதா  =new int[]{TamilConstants.ஐ_EXT};
		
		//int[] உம்மில்முடிகிறதா  =new int[]{TamilConstants.உ_EXT,TamilConstants.ம,TamilConstants.ஃ_EXT};
		int[] இயர்ரில்முடிகிறதா  =new int[]{TamilConstants.இ_EXT,TamilConstants.ய,TamilConstants.ர,TamilConstants.ஃ_EXT};
		
		int[] கவில்முடிகிறதா  =new int[]{TamilConstants.க};
		int[] பவில்முடிகிறதா  =new int[]{TamilConstants.ப};
		int[] யவில்முடிகிறதா  =new int[]{TamilConstants.ய};
		int[] குவில்முடிகிறதா  =new int[]{TamilConstants.க,TamilConstants.உ_EXT};
		int[] துவில்முடிகிறதா  =new int[]{TamilConstants.த,TamilConstants.உ_EXT};
		int[] றுவில்முடிகிறதா  =new int[]{TamilConstants.ற,TamilConstants.உ_EXT};
		int[] த்தில்முடிகிறதா  =new int[]{TamilConstants.த,TamilConstants.ஃ_EXT};
		int[] ற்ரில்முடிகிறதா  =new int[]{TamilConstants.ற,TamilConstants.ஃ_EXT};
		int[] ட்டில்முடிகிறதா  =new int[]{TamilConstants.ட,TamilConstants.ஃ_EXT};
		int[] டுவில்முடிகிறதா  =new int[]{TamilConstants.ட,TamilConstants.ஃ_EXT};
		int[] ர்ரில்முடிகிறதா  =new int[]{TamilConstants.ர,TamilConstants.ஃ_EXT};
		
		int[] கும்வில்முடிகிறதா  =new int[]{TamilConstants.க,TamilConstants.உ_EXT,TamilConstants.ம,TamilConstants.ஃ_EXT};
		int[] தும்வில்முடிகிறதா  =new int[]{TamilConstants.த,TamilConstants.உ_EXT,TamilConstants.ம,TamilConstants.ஃ_EXT};
		int[] றும்மில்முடிகிறதா  =new int[]{TamilConstants.ற,TamilConstants.உ_EXT,TamilConstants.ம,TamilConstants.ஃ_EXT};
		int[] டும்மில்முடிகிறதா  =new int[]{TamilConstants.ட,TamilConstants.உ_EXT,TamilConstants.ம,TamilConstants.ஃ_EXT};
		int[] மின்னில்முடிகிறதா  =new int[]{TamilConstants.ம,TamilConstants.இ_EXT,TamilConstants.ன,TamilConstants.ஃ_EXT};
		
		
		//int[] அல்வில்முடிகிறதா  =new int[]{TamilConstants.அ_EXT,TamilConstants.ல,TamilConstants.ஃ_EXT};
		//int[] அதுவில்முடிகிறதா =new int[]{TamilConstants.அ_EXT,TamilConstants.த,TamilConstants.உ_EXT};
		//int[] அன்வில்முடிகிறதா  =new int[]{TamilConstants.அ_EXT,TamilConstants.ன,TamilConstants.ஃ_EXT};
		//int[] அய்யில்முடிகிறதா  =new int[]{TamilConstants.அ_EXT,TamilConstants.ய,TamilConstants.ஃ_EXT};
		//int[] அர்ரில்முடிகிறதா  =new int[]{TamilConstants.அ_EXT,TamilConstants.ர,TamilConstants.ஃ_EXT};
		//int[] அள்லில்முடிகிறதா  =new int[]{TamilConstants.அ_EXT,TamilConstants.ள,TamilConstants.ஃ_EXT};
		int[] ஆல்வில்முடிகிறதா  =new int[]{TamilConstants.ஆ_EXT,TamilConstants.ல,TamilConstants.ஃ_EXT};
		int[] ஆதுவில்முடிகிறதா =new int[]{TamilConstants.ஆ_EXT,TamilConstants.த,TamilConstants.உ_EXT};
		int[] ஆன்வில்முடிகிறதா  =new int[]{TamilConstants.ஆ_EXT,TamilConstants.ன,TamilConstants.ஃ_EXT};
		int[] ஆய்யில்முடிகிறதா  =new int[]{TamilConstants.ஆ_EXT,TamilConstants.ய,TamilConstants.ஃ_EXT};
		int[] ஆர்ரில்முடிகிறதா  =new int[]{TamilConstants.ஆ_EXT,TamilConstants.ர,TamilConstants.ஃ_EXT};
		int[] ஆள்லில்முடிகிறதா  =new int[]{TamilConstants.ஆ_EXT,TamilConstants.ள,TamilConstants.ஃ_EXT};
		int[] இர்ரில்முடிகிறதா  =new int[]{TamilConstants.இ_EXT,TamilConstants.ர,TamilConstants.ஃ_EXT};
		int[] இல்லில்முடிகிறதா =new int[]{TamilConstants.இ_EXT,TamilConstants.ல,TamilConstants.ஃ_EXT};
		int[] இன்னில்முடிகிறதா =new int[]{TamilConstants.இ_EXT,TamilConstants.ன,TamilConstants.ஃ_EXT};
		int[] இதுவில்முடிகிறதா =new int[]{TamilConstants.இ_EXT,TamilConstants.த,TamilConstants.உ_EXT};
		int[] ஈர்ரில்முடிகிறதா  =new int[]{TamilConstants.ஈ_EXT,TamilConstants.ர,TamilConstants.ஃ_EXT};
		int[] ஈயர்ரில்முடிகிறதா  =new int[]{TamilConstants.ஈ_EXT,TamilConstants.ய,TamilConstants.ர,TamilConstants.ஃ_EXT};
		int[] என்னில்முடிகிறதா   =new int[]{TamilConstants.எ,TamilConstants.ன,TamilConstants.ஃ_EXT};
		int[] ஏன்னிமுடிகிறதா   =new int[]{TamilConstants.ஏ,TamilConstants.ன,TamilConstants.ஃ_EXT};
		int[] ஒடுவில்முடிகிறதா  =new int[]{TamilConstants.ஒ_EXT,TamilConstants.ட,TamilConstants.உ_EXT};
		int[] ஒம்மில்முடிகிறதா  =new int[]{TamilConstants.ஓ_EXT,TamilConstants.ம,TamilConstants.ஃ_EXT};
		int[] ஓடுவில்முடிகிறதா  =new int[]{TamilConstants.ஓ_EXT,TamilConstants.ட,TamilConstants.உ_EXT};
		int[] ஆள்வில்முடிகிறதா  =new int[]{TamilConstants.ஆ_EXT,TamilConstants.ள,TamilConstants.ஃ_EXT};
		
		int[]	ALL_EXT =new int[]{
				TamilConstants.ஆ_EXT,TamilConstants.இ_EXT,TamilConstants.ஈ_EXT,TamilConstants.உ_EXT,TamilConstants.ஊ_EXT,TamilConstants.எ_EXT,
				TamilConstants.ஏ_EXT,TamilConstants.ஐ_EXT,TamilConstants.ஒ_EXT,TamilConstants.ஓ_EXT,TamilConstants.ஔ_EXT,TamilConstants.ஃ_EXT		};
		
		int[] மார்ரில்முடிகிறதா  =new int[]{TamilConstants.ம,TamilConstants.ஆ_EXT,TamilConstants.ர,TamilConstants.ஃ_EXT};
		
		int[] கண்ணில்முடிகிறதா   =new int[]{TamilConstants.க,TamilConstants.ண,TamilConstants.ஃ_EXT};
		 
		String இருந்து ="ிருந்து";
		String நின்று ="ின்று";
		String இடம் ="ிடம்";
		String உடன் ="ுடன்";
		String த்த்தில்முடிகிறதா = "த்த்";
		String ந்த்தில்முடிகிறதா = "ந்த்";
		 
		String ற்வில்முடிகிறதா ="ற்";
		String உவில்முடிகிறதா ="உ";
		String அதுவில்முடிகிறதா ="அத்உ";
		String அன்வில்முடிகிறதா ="அன்";  
		String அர்ரில்முடிகிறதா ="அர்";
		String அள்லில்முடிகிறதா ="அள்";
		String க்அள்லில்முடிகிறதா ="க்அள்";
		String என்ற்உல்முடிகிறதா ="என்ற்உ";
		String ஆக்இற்வில்முடிகிறதா ="ஆக்இற்";
		String இன்லில்முடிகிறதா ="இன்";
		String ய்லில்முடிகிறதா ="ய்";
		String இய்லில்முடிகிறதா ="இய்";
		String இல்முடிகிறதா ="இல்";
		String ஆல்லில்முடிகிறதா ="ஆல்";
		String அல்லில்முடிகிறதா ="அல்";
		String ஒல்இலில்முடிகிறதா ="ஒல்இ";
		String க்இலில்முடிகிறதா ="க்இ";
		String வ்இலில்முடிகிறதா ="வ்இ";
		String இவில்முடிகிறதா ="இ";
		String உம்மில்முடிகிறதா ="உம்";
		String உல்லில்முடிகிறதா ="உல்";
		String இர்உந்த்உமுடிகிறதா ="இர்உந்த்உ";
		String த்ஆன்முடிகிறதா ="த்ஆன்";
		
		int[] உயிர்ரெழுத்தில்முடிகிறதா = new int[]{TamilConstants.அ,TamilConstants.ஆ,TamilConstants.இ, TamilConstants.ஈ,TamilConstants.உ,TamilConstants.ஊ,TamilConstants.எ,TamilConstants.ஏ,TamilConstants.ஐ,TamilConstants.ஒ,TamilConstants.ஓ,TamilConstants.ஔ };

		String[] மெய்யெழுத்தில்முடிகிறதா = new String[]{ 
				TamilConstants.க்,TamilConstants.ங்,TamilConstants.ச்,TamilConstants.ஜ்,TamilConstants.ஞ்,TamilConstants.ட்,TamilConstants.ண்,
				TamilConstants.த்,TamilConstants.ந்,TamilConstants.ன்,TamilConstants.ப்,TamilConstants.ம்,TamilConstants.ய்,TamilConstants.ர்,TamilConstants.ற்,
				TamilConstants.ல்,TamilConstants.ள்,TamilConstants.ழ்,TamilConstants.வ்,TamilConstants.ஶ்,TamilConstants.ஷ்,TamilConstants.ஸ்,TamilConstants.ஹ்};

		int[] யநிலைமொழியின்ஈறு = new int[]{TamilConstants.இ, TamilConstants.ஈ, TamilConstants.ஐ, TamilConstants.ஓ };
		int[] வநிலைமொழியின்ஈறு = new int[]{TamilConstants.அ,TamilConstants.ஆ,TamilConstants.உ,TamilConstants.ஊ,TamilConstants.எ,TamilConstants.ஏ,TamilConstants.ஒ,TamilConstants.ஔ };
		
		String [] இனவெழுத்து 			= new String[]{"ங்க","ஞ்ச","ண்ட","ந்த","ம்ப","ன்ற"};
		String [] உடனிலைமெய்ம்மயக்கம் = new String[]{"க்க்","ங்ங்","ச்ச்","ஞ்ஞ்","ட்ட்","ண்ண்","த்த்","ந்ந்","ப்ப்","ம்ம்","ய்ய்","ல்ல்","வ்வ்","ள்ள்","ற்ற்","ன்ன்"};

		String க்கரவரிசை = "க்";
		String ச்கரவரிசை = "ச்";
		String ட்கரவரிசை = "ட்";
		String த்கரவரிசை = "த்";
		String ப்கரவரிசை = "ப்";
		String ற்கரவரிசை = "ற்";
		
		String ய்கரவரிசை = "ய்";
		String ர்கரவரிசை = "ர்";
		String ல்கரவரிசை = "ல்";
		String வ்கரவரிசை = "வ்";
		String ழ்கரவரிசை = "ழ்";
		String ள்கரவரிசை = "ள்";

		String ங்கரவரிசை = "ங்";
		String ஞ்கரவரிசை = "ஞ்";
		String ண்கரவரிசை = "ண்";
		String ந்கரவரிசை = "ந்";
		String ம்தகரவரிசை = "ம்";
		String ன்கரவரிசை = "ன்";
		
		String அகரம் = "அ";
		String ஆகரம் = "ஆ";
		String இகரம் = "இ";
		String ஈகரம் = "ஈ";
		String ஐகரம்	= "ஐ";
		String எகரம்	= "எ";
		String ஏகரம்	= "ஏ";
		String ஓகரம்	= "ஓ"; 
		
		List<String> ககரவரிசை = Arrays.asList("க்","க","கா","கி","கீ","கு","கூ","கெ","கே","கை","கொ","கோ","கௌ");
		List<String> சகரவரிசை = Arrays.asList("ச்","ச","சா","சி","சீ","சு","சூ","செ","சே","சை","சொ","சோ","சௌ");
		List<String> டகரவரிசை = Arrays.asList("ட்","ட","டா","டி","டீ","டு","டூ","டெ","டே","டை","டொ","டோ","டௌ");
		List<String> தகரவரிசை = Arrays.asList("த்","த","தா","தி","தீ","து","தூ","தெ","தே","தை","தொ","தோ","தௌ");
		List<String> பகரவரிசை = Arrays.asList("ப்","ப","பா","பி","பீ","பு","பூ","பெ","பே","பை","பொ","போ","பௌ");
		List<String> றகரவரிசை = Arrays.asList("ற்","ற","றா","றி","றீ","று","றூ","றெ","றே","றை","றொ","றோ","றௌ");

		static List<String> வல்லினவரிசை  = new ArrayList<String>();
		public static List<String> வல்லினவரிசைகொடு(){
			if(வல்லினவரிசை.size()>0)
				return வல்லினவரிசை;

			வல்லினவரிசை.addAll(ககரவரிசை);
			வல்லினவரிசை.addAll(சகரவரிசை);
			வல்லினவரிசை.addAll(டகரவரிசை);
			வல்லினவரிசை.addAll(தகரவரிசை);
			வல்லினவரிசை.addAll(பகரவரிசை);
			வல்லினவரிசை.addAll(றகரவரிசை);
			return வல்லினவரிசை;
		}

 		List<String> யகரவரிசை = Arrays.asList("ய்","ய","யா","யி","யீ","யு","யூ","யெ","யே","யை","யொ","யோ","யௌ");
		List<String> ரகரவரிசை = Arrays.asList("ர்","ர","ரா","ரி","ரீ","ரு","ரூ","ரெ","ரே","ரை","ரொ","ரோ","ரௌ");
		List<String> லகரவரிசை = Arrays.asList("ல்","ல","லா","லி","லீ","லு","லூ","லெ","லே","லை","லொ","லோ","லௌ");
		List<String> வகரவரிசை = Arrays.asList("வ்","வ","வா","வி","வீ","வு","வூ","வெ","வே","வை","வொ","வோ","வௌ");
		List<String> ழகரவரிசை = Arrays.asList("ழ்","ழ","ழா","ழி","ழீ","ழு","ழூ","ழெ","ழே","ழை","ழொ","ழோ","ழௌ");
		List<String> ளகரவரிசை = Arrays.asList("ள்","ள","ளா","ளி","ளீ","ளு","ளூ","ளெ","ளே","ளை","ளொ","ளோ","ளௌ");

		static List<String> இடையினவரிசை = new ArrayList<String>();
		public static List<String> இடையினவரிசைகொடு(){
			if(இடையினவரிசை.size()>0)
				return இடையினவரிசை;

			இடையினவரிசை.addAll(யகரவரிசை);
			இடையினவரிசை.addAll(ரகரவரிசை);
			இடையினவரிசை.addAll(லகரவரிசை);
			இடையினவரிசை.addAll(வகரவரிசை);
			இடையினவரிசை.addAll(ழகரவரிசை);
			இடையினவரிசை.addAll(ளகரவரிசை);
			return இடையினவரிசை;
		}

		List<String> ஙகரவரிசை = Arrays.asList("ங்","ங","ஙா","ஙி","ஙீ","ஙு","ஙூ","ஙெ","ஙே","ஙை","ஙொ","ஙோ","ஙௌ");
		List<String> ஞகரவரிசை = Arrays.asList("ஞ்","ஞ","ஞா","ஞி","ஞீ","ஞு","ஞூ","ஞெ","ஞே","ஞை","ஞொ","ஞொ","ஞௌ");
		List<String> ணகரவரிசை = Arrays.asList("ண்","ண","ணா","ணி","ணீ","ணு","ணூ","ணெ","ணே","ணை","ணொ","ணோ","ணௌ");
		List<String> நகரவரிசை = Arrays.asList("ந்","ந","நா","நி","நீ","நு","நூ","நெ","நே","நை","நொ","நௌ","நௌ");
		List<String> மதகரவரிசை = Arrays.asList("ம்","ம","மா","மி","மீ","மு","மூ","மெ","மே","மை","மொ","மோ","மௌ");
		List<String> னகரவரிசை = Arrays.asList("ன்","ன","னா","னி","னீ","னு","னூ","னெ","னே","னை","னொ","னோ","னௌ");

		static List<String> மெல்லினவரிசை = new ArrayList<String>();
		public static List<String> மெல்லினவரிசைகொடு(){
			if(மெல்லினவரிசை.size()>0)
				return மெல்லினவரிசை;

			மெல்லினவரிசை.addAll(ஙகரவரிசை);
			மெல்லினவரிசை.addAll(ஞகரவரிசை);
			மெல்லினவரிசை.addAll(ணகரவரிசை);
			மெல்லினவரிசை.addAll(நகரவரிசை);
			மெல்லினவரிசை.addAll(மதகரவரிசை);
			மெல்லினவரிசை.addAll(னகரவரிசை);
			return மெல்லினவரிசை;
		}
		//வேற்றுநிலை மெய்ம்மயக்கம்
}
