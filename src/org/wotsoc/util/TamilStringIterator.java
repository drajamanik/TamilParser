/**
 * @author Rajamani David
 * @since	Aug 14, 2018
 *
 */
package org.wotsoc.util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class TamilStringIterator implements ListIterator<String>
{
	/**
	 * This code implements the
	 * ListIterator instead of CharaterIterator as each Tamil Letter is not a single Character (which is not a char its a String)
	 * @see ListIterator
	 * 
	 * Lot of methods given for convince to
	 * move to any position at any time
	 * 
	 */

	static Set<Character> supportCharacter=new HashSet<Character>();
	static
	{
		supportCharacter.add((char)0xBBE); //  3006 $ா TAMIL VOWEL SIGN AA
		supportCharacter.add((char)0xBBF); //  3007 $ி TAMIL VOWEL SIGN I
		supportCharacter.add((char)0xBC0); //  3008 $ீ TAMIL VOWEL SIGN II
		supportCharacter.add((char)0xBC1); //  3009 $ு TAMIL VOWEL SIGN U
		supportCharacter.add((char)0xBC2); //  3010  ூ$ TAMIL VOWEL SIGN UU
		supportCharacter.add((char)0xBC6); //  3014  ெ TAMIL VOWEL SIGN E
		supportCharacter.add((char)0xBC7); //  3015  ே TAMIL VOWEL SIGN EE
		supportCharacter.add((char)0xBC8); //  3016  ை TAMIL VOWEL SIGN AI
		supportCharacter.add((char)0xBCA); //  3018 $ொ TAMIL VOWEL SIGN O
		supportCharacter.add((char)0xBCB); //  3019 $ோ TAMIL VOWEL SIGN OO
		supportCharacter.add((char)0xBCD); //  "்" 
		supportCharacter.add((char)0xBCC); //ௌ 
	}
	    private String text;
	    private int begin;
	    private int end;
	    private int pos;
	    private final static String DONE="";
	    private int size;

	    /**
	     * Constructs an iterator with an initial index of 0.
	     */
	    public TamilStringIterator(String text)
	    {
	        this(text, 0);
	    }

	    /**
	     * Constructs an iterator with the specified initial index.
	     *
	     * @param  text   The String to be iterated over
	     * @param  pos    Initial iterator position
	     */
	    public TamilStringIterator(String text, int pos)
	    {
	    	this(text, 0, text.length(), pos);
	    }

	    /**
	     * Constructs an iterator over the given range of the given string, with the
	     * index set at the specified position.
	     *
	     * @param  text   The String to be iterated over
	     * @param  begin  Index of the first character
	     * @param  end    Index of the character following the last character
	     * @param  pos    Initial iterator position
	     */
	    public TamilStringIterator(String text, int begin, int end, int pos) 
	    {
	        if (text == null)
	            throw new NullPointerException();
	        this.text = text;

	        if (begin < 0 || begin > end || end > text.length())
	            throw new IllegalArgumentException("Invalid substring range");

	        if (pos < begin || pos > end)
	            throw new IllegalArgumentException("Invalid position");

	        this.begin = begin;
	        this.end = end;
	        this.pos = pos;
	    }

	    /**
	     * Reset this iterator to point to a new string.  This package-visible
	     * method is used by other java.text classes that want to avoid allocating
	     * new StringCharacterIterator objects every time their setText method
	     * is called.
	     *
	     * @param  text   The String to be iterated over
	     */
	    public void setText(String text) 
	    {
	        if (text == null)
	            throw new NullPointerException();
	        this.text = text;
	        this.begin = 0;
	        this.end = text.length();
	        this.pos = 0;
	    }

	    public String getText(){
	    	return this.text;
	    }
	    
	    public void moveToEnd()
	    {
	    	move(this.text.length());
	    }
	    
	    public void moveToBegin()
	    {
	    	move(0);
	    }
	    
	    public void move(int position)
	    {
	    	this.pos =position;
	    }
	    
	    public int position()
	    {
	    	return this.pos;
	    }
	    
	    public void reset()
	    {
	    	setText(text);
	    }

	    /**
	     * moves and gets first character string
	     */
	    public String first()
	    {
	        pos = begin;
	        return current();
	    }

	    /**
	     * moves and gets last character stringஇ
	     */
	    public String last()
	    {
	        if (end != begin) {
	            pos = end - 1;
	        } else {
	            pos = end;
	        }
	        return current();
	     }

	    /**
	     * moves indexes to any position between begin and end.
	     */
	    public String setIndex(int p)
	    {
	    if (p < begin || p > end)
	            throw new IllegalArgumentException("Invalid index");
	        pos = p;
	        return current();
	    }

	    /**
	     * Gets Characters from current position.
	     */
	    public String current()
	    {
	        if (pos >= begin && pos < end) 
	        {
	        	if(supportCharacter.contains(text.charAt(pos)))
	        	{
	        		return text.charAt(pos-1)+""+text.charAt(pos);
	        	}
	        	else if(pos+1 <end && supportCharacter.contains(text.charAt(pos+1)))
	        	{
	        		String tempStr = null;
        			if(pos+2 <end && supportCharacter.contains(text.charAt(pos+2))) {
        				tempStr=text.charAt(pos)+""+text.charAt(pos+1)+""+text.charAt(pos+2);
        				pos++;
        			}else {
	        			tempStr=tempStr+text.charAt(pos+1);
        			}
        			pos++;
	        		return tempStr;
	        	}
	        	else
	        		return text.charAt(pos)+"";
	        }
	        else {
	            return DONE;
	        }
	    }

	    public String next() {
	    	String tempStr=DONE;
	    	char[] tempStrArr = text.toCharArray();
	        if (pos <= end - 1) {
	        	tempStr = tempStrArr[pos]+"";
	        	if(pos+2 <= end-1 && supportCharacter.contains(tempStrArr[pos+1]) && supportCharacter.contains(tempStrArr[pos+2])) {
	        		tempStr = tempStr + tempStrArr[pos+1]+tempStrArr[pos+2]+"";
	        		pos++;
	        		pos++;
	        	}else if(pos+1 <= end-1 && supportCharacter.contains(tempStrArr[pos+1])) {
	        		tempStr = tempStr + tempStrArr[pos+1]+"";
	        		pos++;
	        	}
	        	pos++;
	        }
	        return tempStr;
	    }
	    /**
	     * Moves to next Tamil String or Characters.--DELETE THIS METHOD
	     */
	    public String nextOld()
	    {
	    	String tempStr="";
	        if (pos < end - 1) 
	        {
	        	if(pos==begin)
	        	{
	        		tempStr=text.charAt(pos)+"";
	        		if(supportCharacter.contains(text.charAt(pos+1)))
	        		{
	        			if(supportCharacter.contains(text.charAt(pos+2))) {
	        				tempStr=text.charAt(pos)+""+text.charAt(pos+1)+""+text.charAt(pos+2);
	        				pos++;
	        			}else {
		        			tempStr=tempStr+text.charAt(pos+1);
	        			}
	        			pos++;
	        		}
	        		pos++;
	        		return tempStr;
	        	}
	        	
	            //pos++;
	        	if(supportCharacter.contains(text.charAt(pos)))
	        	{
	        		tempStr=text.charAt(pos-1)+""+text.charAt(pos);
	        		pos++;
	        		return tempStr;
	        	}
	        	else if( (pos+1 !=end) && supportCharacter.contains(text.charAt(pos+1)))
	        	{
	        		pos++;
	        		tempStr =text.charAt(pos-1)+"";
	        		//pos++;
	        		return tempStr;
	        	}
	        	else {
	        		tempStr =text.charAt(pos)+"";
	        		pos++;
	        		return tempStr;
	        	}
	        }
	        else 
	        {
	        	if(pos==end)
	        		return DONE;
	        	tempStr=text.charAt(pos)+"";
	            pos = end;
	            return tempStr;
	        }
	    }

	    public String previous() {
	    	String tempStr=DONE;
	    	char[] tempStrArr = text.toCharArray();
	        if (pos > begin) {
	        	if(pos >= begin && !supportCharacter.contains(tempStrArr[pos-1])) {
	        		tempStr = tempStrArr[pos-1]+"";	
	        	}else if(pos-2 >= begin && !supportCharacter.contains(tempStrArr[pos-2])) {
	        		tempStr = text.charAt(pos-2)+""+text.charAt(pos-1);
	        		pos--;
	        	}else if(pos-3 >= begin && !supportCharacter.contains(tempStrArr[pos-3])) {
	        		tempStr = text.charAt(pos-3)+""+text.charAt(pos-2)+""+text.charAt(pos-1);
	        		pos--;
	        		pos--;
	        	}
	        	pos--;
	        }
	        return tempStr;
	    }
	    
	    /**
	     * Moves to previous Tamil String or Characters..--DELETE THIS METHOD
	     */
	    public String previousOld()
	    {
	        if (pos > begin) 
	        {
	            pos--;
	            if(pos <= 0)
	            	return DONE;
	        	if(supportCharacter.contains(text.charAt(pos)))
	        		return text.charAt(pos-1)+""+text.charAt(pos);
	        	else if(supportCharacter.contains(text.charAt(pos-1)))
	        	{
	        		if(pos+1<end && supportCharacter.contains(text.charAt(pos+1)))
	        		{
	        			pos--;
	        			if(supportCharacter.contains(text.charAt(pos-1)) && supportCharacter.contains(text.charAt(pos))) {
	        				String tempStr = text.charAt(pos-2)+""+text.charAt(pos-1)+""+text.charAt(pos);
	        				pos--;
	        				return tempStr;
	        			}else {
	        				return text.charAt(pos-1)+""+text.charAt(pos);
	        			}
	        		}
	        		else
	        			return text.charAt(pos)+"";
	        	}
	        	else
	        	{
	        		pos--;
	        		return text.charAt(pos)+"";
	        	}
	        }
	        else 
	        {
	            return DONE;
	        }
	    }

	    /**
	     * gets Begining of the Index
	     */
	    public int getBeginIndex()
	    {
	        return begin;
	    }

	    /**
	     * gets End of Index
	     */
	    public int getEndIndex()
	    {
	        return end;
	    }

	    /**
	     * Gets Current Index
	     */
	    public int getIndex()
	    {
	        return pos;
	    }

	    /**
	     * Compares the equality of two StringCharacterIterator objects.
	     * @param obj the StringCharacterIterator object to be compared with.
	     * @return true if the given obj is the same as this
	     * StringCharacterIterator object; false otherwise.
	     */
	    public boolean equals(Object obj)
	    {
	        if (this == obj)
	            return true;
	        if (!(obj instanceof TamilStringIterator))
	            return false;

	        TamilStringIterator that = (TamilStringIterator) obj;

	        if (hashCode() != that.hashCode())
	            return false;
	        if (!text.equals(that.text))
	            return false;
	        if (pos != that.pos || begin != that.begin || end != that.end)
	            return false;
	        return true;
	    }

	    /**
	     * Computes a hashcode for this iterator.
	     * @return A hash code
	     */
	    public int hashCode()
	    {
	        return text.hashCode() ^ pos ^ begin ^ end;
	    }

	    /**
	     * Creates a copy of this iterator.
	     * @return A copy of this
	     */
	    public Object clone()
	    {
	        try 
	        {
	        	TamilStringIterator other
	            = (TamilStringIterator) super.clone();
	            return other;
	        }
	        catch (CloneNotSupportedException e) 
	        {
	            throw new InternalError();
	        }
	    }
	    
	    
 		@Override
		public boolean hasNext() {
 			return (this.position()<text.length());
		}

 		 
		public boolean hasPrevious() {
 			return (this.getEndIndex()>=this.getIndex() && this.getIndex()!=0);
		}
		
		/**
		 * Unique Code Length is not helpful many times, this method
		 * gives letter length அம்மா.length = 3 instead of 5 also caches 
		 * */
		public int length(){
			if(size==0){
				List<String> list=forwardIterator();
				size= list.size();
			}
			reset();
			return size;
		}
		
		/**
		 * Unique Code Length
		 * */ 
		public int totalCount(){
			return text.length();
		}

		/**
		 * Get all each letters as list of String
		 * */
		public List<String> forwardIterator()
		{ 
			List<String> list = new ArrayList<>();
			try{
				while(hasNext())
					list.add(this.next());
				size = list.size();
			}catch(Exception exp){
				System.out.println(text);
				exp.printStackTrace();
			}
			return list;
		}
		
		/**
		 * Get all each letters as list of String in backwards
		 * */
		public List<String> backwardIterator()
		{ 
			List<String> list = new ArrayList<>();
			this.moveToEnd();	
			String str ="";
			while(hasPrevious())
	    	{
				str = this.previous();
				if(str.length()>0)
					list.add(str);
	    	}
			size = list.size();
			return list;
		}

		@Override
		public int nextIndex() {
			int nextIndex= this.getIndex() - 1;
			if(this.getEndIndex()>=nextIndex)
			{
				setIndex(nextIndex);
				return nextIndex;
			}
			return -1;
		}

		@Override
		public int previousIndex() {
			int prevIndex= this.getIndex() - 1; 
			if(prevIndex >=0 && this.getBeginIndex()<=prevIndex)
			{
				setIndex(prevIndex);
				return prevIndex;
			}
			return -1;
		}

		@Override
		public void remove() {
			 
			String currStr=current();
			List<String> list=this.forwardIterator();
			String tempStr= list.get(getIndex());
			if(currStr.equals(tempStr))
			{
				list.remove(getIndex());
				setText(list.toString());
			}
		}

		@Override
		public void set(String e) {
			 
			
		}

		@Override
		public void add(String e) {
			
		}
		
		public static void main(String[] args) {
//			TamilStringIterator ister = new TamilStringIterator("சோழ");
//			System.out.println(ister.last());
//			System.out.println(ister.first());
////			TamilStringIterator iter = new TamilStringIterator("சோழ");
////			System.out.println(iter.next());
////			System.out.println(iter.next());
//			TamilStringIterator ister1 = new TamilStringIterator("சலசல");
//			System.out.println(ister1.next());
//			System.out.println(ister1.next());
//			System.out.println(ister1.next());
//			System.out.println(ister1.next());//சோழர்
//			TamilStringIterator ister3 = new TamilStringIterator("சேழொல்");
//			Iterator<String> iter1=ister3.forwardIterator().iterator();
//			while(iter1.hasNext()) {
//				System.out.println(iter1.next());	
//			}
//			System.out.println(ister3.previous());
//			System.out.println(ister3.previous());
//			System.out.println(ister3.previous());
//			System.out.println(ister3.previous());
//			System.out.println(ister3.previous()+"***");
//			iter1=ister3.backwardIterator().iterator();
//			while(iter1.hasNext()) {
//				System.out.println(iter1.next());	
//			}

			TamilStringIterator ister4 = new TamilStringIterator("வெங்ைகயா");
			Iterator<String> iter2=ister4.forwardIterator().iterator();
			while(iter2.hasNext()) {
				System.out.println(iter2.next());	
			}
		}
	}
