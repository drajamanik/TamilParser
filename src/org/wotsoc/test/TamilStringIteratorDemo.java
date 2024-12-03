/**
 * @author Rajamani David
 * @since	Mar 17, 2019
 *
 */
package org.wotsoc.test;

import org.wotsoc.illakanam.TamilUtil;
import org.wotsoc.util.TamilStringIterator;

public class TamilStringIteratorDemo  {
 
	public static void hasNextExample(String str){
		TamilStringIterator tsi= new TamilStringIterator(str);
		while(tsi.hasNext())
			System.out.println(tsi.next()+":"+tsi.getIndex());
	}
	
	public static void hasPreviousExample(String str){
		TamilStringIterator tsi= new TamilStringIterator(str);
		tsi.moveToEnd();
		while(tsi.hasPrevious())
			System.out.println(tsi.previous()+":"+tsi.getIndex());
	}
	
	public static void forwardIteratorExample(String str){
		TamilStringIterator tsi= new TamilStringIterator(str);
		for(String strForward:tsi.forwardIterator())
			System.out.println(strForward);
	}
	
	public static void backwardIteratorExample(String str){
		TamilStringIterator tsi= new TamilStringIterator(str);
		for(String strBack:tsi.backwardIterator())
			System.out.println(strBack);
	}
	
	public static void lengthExample(String str){
		TamilStringIterator tsi= new TamilStringIterator(str);
		System.out.println("Letters length:"+tsi.length() +" , Total Length:"+ tsi.totalCount());
	}
	
    static public void main(String[] args) {
//      System.out.println("---------hasNextExample->பாண்டியன்மதிவாணனா<-------");
//      hasNextExample("பாண்டியன்மதிவாணனா");
//      System.out.println("---------hasPreviousExample->அதிகாரஞ்செலுத்துதல்<------");
//      hasPreviousExample("அதிகாரஞ்செலுத்துதல்");
//      System.out.println("---------forwardIteratorExample->நினைவுத்தடுமாற்றம்<------");
//      forwardIteratorExample("நினைவுத்தடுமாற்றம்");
//      System.out.println("---------backwardIteratorExample->பாண்டியன்மதிவாணனா<-------");
//      backwardIteratorExample("பாண்டியன்மதிவாணனா");
//      System.out.println("---------length->பாண்டியன்மதிவாணனா<-------");
//      lengthExample("பாண்டியன்மதிவாணனா");
//      forwardIteratorExample("பொது");
//      System.out.println("---------forwardIteratorExample->பொது<-------");
//      lengthExample("பொது");
//      System.out.println("---------length->பொது<-------");
//      backwardIteratorExample("பொது");
//      System.out.println("---------backwardIteratorExample->பொது<-------");
      System.out.println(TamilUtil.எழுத்துகளைபிரி("பொது", false, false));
      
    }
 
} // class