/**
 * @author Rajamani David
 * @since	Apr 11, 2019
 *
 */
package org.wotsoc.test;

/**
 * @author rdavid
 *
 */
public class Node {
	private Node left;
	private Node right;
	int data;
	
	public Node(int data){
		this.data= data;
	}
	
	public String toString(){
		return "Root="+data+",Left="+(left==null?null:left.data)+":Right="+(right==null?null:right.data);
	}
	
	public void insert(int value){
		if(value <= data){
			if(left==null)
				left = new Node(value);
			else
				left.insert(value);
		}else{
			if(right == null)
				right = new Node(value);
			else
				right.insert(value);
		}
	}
	
//	public boolean contains(int data){
//		
//	}
	
	public static void main(String args[]){
		Node n1 = new Node(10);
		n1.insert(11);
		n1.insert(9);
		n1.insert(8);
		n1.insert(12);
		System.out.println(n1);
	}
}
