package org.wotsoc.nlp;

/**
 * @author Rajamani David
 * @since	Jan 20, 2018
 *
 */
public class RecursiveConstants 
{
	private Long recursiveId;
	private int index;
	private String key;
	private String possibleCombination;
	private String recordActiveInd;
	
	public RecursiveConstants(int index,String key,String possibleCombination,String recordActiveInd)
	{
		this.index=index;
		this.key=key;
		this.possibleCombination=possibleCombination;
		this.recordActiveInd=recordActiveInd;		
	}
	
	public RecursiveConstants()
	{
		
	}
	
	public Long getRecursiveId() 
	{
		return recursiveId;
	}
	
	public void setRecursiveId(Long recursiveId) 
	{
		this.recursiveId = recursiveId;
	}
	
	public int getIndex() 
	{
		return index;
	}
	
	public void setIndex(int index) 
	{
		this.index = index;
	}
	
	public String getKey() 
	{
		return key;
	}
	
	public void setKey(String key) 
	{
		this.key = key;
	}
	
	public String getPossibleCombination() 
	{
		return possibleCombination;
	}
	
	public void setPossibleCombination(String possibleCombination) 
	{
		this.possibleCombination = possibleCombination;
	}
	
	public String getRecordActiveInd() 
	{
		return recordActiveInd;
	}
	
	public void setRecordActiveInd(String recordActiveInd) 
	{
		this.recordActiveInd = recordActiveInd;
	}
}
