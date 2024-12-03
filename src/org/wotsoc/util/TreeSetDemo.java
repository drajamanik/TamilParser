package org.wotsoc.util;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetDemo {

	   public static void main(String[] args) {

	      Integer[] nums={2,4,1,6,3,7,9,5};
	      SortedSet<Integer> tree=new TreeSet<>(Arrays.asList(nums));

	      // Print first and last element
	      System.out.println("First:"+tree.first());
	      System.out.println("Last:"+tree.last());

	      printAll(tree);
	      // False. Set does not allow duplicates,
	      // so this will not be added.
	      System.out.println(tree.add(1));

	      // But, this will be added because 11 is not a duplicate
	      System.out.println(tree.add(11));
	      printAll(tree);

	      printAll("HeadSet 7:",tree.headSet(7));
	      printAll("TailSet 7:",tree.tailSet(7));

	   }

	   public static void printAll(String comment,SortedSet<Integer> tree){
		   if(comment!=null)
			   System.out.println("comment:"+comment);
		      for(int s: tree){
		         System.out.println(s);
		      }
		      System.out.println();
		}
	   
	   public static void printAll(SortedSet<Integer> tree){
	      printAll(null,tree);
	   }
	}
