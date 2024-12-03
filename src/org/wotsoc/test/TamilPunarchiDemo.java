/**
 * @author Rajamani David
 * @since	May 9, 2019
 *
 */
package org.wotsoc.test;

import org.wotsoc.illakanam.TamilPunarchi;

/**
 * @author rdavid
 *
 */
public class TamilPunarchiDemo {
	
	public static void testகுற்றியலுகரப்புணரச்சி(){
		TamilPunarchi tp = new TamilPunarchi();
		//System.out.println("சேறி+ஆயின் = "+tp.குற்றியலுகரப்புணரச்சி("சேறி","ஆயின்"));
		
	}
	
	public static void testஉயிரீற்றிப்புணரச்சி(){
		TamilPunarchi tp = new TamilPunarchi();
		System.out.println("சேறி+ஆயின் = "+tp.உயிரீற்றிப்புணரச்சி("சேறி","ஆயின்"));
		System.out.println("நீ+அதை = "+tp.உயிரீற்றிப்புணரச்சி("நீ","அதை"));
		System.out.println("பல+என்று = "+tp.உயிரீற்றிப்புணரச்சி("பல","என்று"));
		System.out.println("நிலா+ஒளி = "+tp.உயிரீற்றிப்புணரச்சி("நிலா","ஒளி"));
		System.out.println("ஒன்றே+என்னின் = "+tp.உயிரீற்றிப்புணரச்சி("ஒன்றே","என்னின்"));
	}
	
	public static void testமெய்யிற்றிப்புணரச்சி(){
		TamilPunarchi tp = new TamilPunarchi();
//		System.out.println(tp.மெய்யிற்றிப்புணரச்சி ("பலா","சுளை"));
		System.out.println("பொன்+சிலை = "+tp.மெய்யிற்றிப்புணரச்சி ("பொன்","சிலை"));
		System.out.println("படம்+காட்சி = "+tp.மெய்யிற்றிப்புணரச்சி ("படம்","காட்சி"));
 		
		System.out.println("தமிழ்+அன்னை = "+tp.மெய்யிற்றிப்புணரச்சி ("தமிழ்","அன்னை"));
		System.out.println("முள்+இலை = "+tp.மெய்யிற்றிப்புணரச்சி ("முள்","இலை"));
		System.out.println("மரம்+இலை = "+tp.மெய்யிற்றிப்புணரச்சி ("மரம்","இலை"));
		
		System.out.println("மரம்+கிளை = "+tp.மெய்யிற்றிப்புணரச்சி ("மரம்","கிளை"));
		System.out.println("தினம்+தினம் = "+tp.மெய்யிற்றிப்புணரச்சி ("தினம்","தினம்"));
		System.out.println("இனம்+மணி = "+tp.மெய்யிற்றிப்புணரச்சி ("இனம்","மணி"));
		System.out.println("மரம்+வேர் = "+tp.மெய்யிற்றிப்புணரச்சி ("மரம்","வேர்"));
		System.out.println("மண்+கலம் = "+tp.மெய்யிற்றிப்புணரச்சி ("மண்","கலம்"));
		System.out.println("பொன்+குடம் = "+tp.மெய்யிற்றிப்புணரச்சி ("பொன்","குடம்"));
		System.out.println("வேல்+காளை = "+tp.மெய்யிற்றிப்புணரச்சி ("வேல்","காளை"));
		System.out.println("திரைகவுள்+பயனில் = "+tp.மெய்யிற்றிப்புணரச்சி ("திரைகவுள்","பயனில்"));
		System.out.println("கயல்+முள் = "+tp.மெய்யிற்றிப்புணரச்சி ("கயல்","முள்"));
		System.out.println("அருள்+மொழி = "+tp.மெய்யிற்றிப்புணரச்சி ("அருள்","மொழி"));
		
		System.out.println("பொன் +தீது = "+tp.மெய்யிற்றிப்புணரச்சி ("பொன்","தீது"));
		System.out.println("கல் +தீது = "+tp.மெய்யிற்றிப்புணரச்சி ("கல்","தீது"));
		System.out.println("பொன் +நன்று = "+tp.மெய்யிற்றிப்புணரச்சி ("பொன்","நன்று"));
		System.out.println("மண் +தீது = "+tp.மெய்யிற்றிப்புணரச்சி ("மண்","தீது"));
		System.out.println("முள் +தீது = "+tp.மெய்யிற்றிப்புணரச்சி ("முள்","தீது"));
		System.out.println("கண் +நீர் = "+tp.மெய்யிற்றிப்புணரச்சி ("கண்","நீர்"));
		System.out.println("முள் +நன்று ="+tp.மெய்யிற்றிப்புணரச்சி ("முள்","நன்று"));
		
	}
	public static void main(String args[]){
		System.out.println("****மெய்யிற்றிப்புணரச்சி*****");
		testமெய்யிற்றிப்புணரச்சி();
		System.out.println("****உயிரீற்றிப்புணரச்சி*****");
		testஉயிரீற்றிப்புணரச்சி();
	}
}
