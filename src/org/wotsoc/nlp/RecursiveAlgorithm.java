/**
 * @author Rajamani David
 * @since	Oct 22, 2017
 *
 */
package org.wotsoc.nlp;

/**
 * @author rdavid
 *
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RecursiveAlgorithm 
{
    //private static int recursiveCallsCounter = 0;
    
    public static List<List<String>> allCombinedValues = new ArrayList<List<String>>();

    public static void test7Items() {
    	//18,7,16,3,1,4,2
    	List<List<String>> outerList =new ArrayList<List<String>>();
    	outerList.add(Arrays.asList("ல்","ள்","க்","ன்","ண்","ட்","ய்","ற்","ம்","வ்"));
    	outerList.add(Arrays.asList("அ","உ","ய்","ஊ","இ"));
    	outerList.add(Arrays.asList("க்","ச்","த்","ப்","ய்","வ்","ந்","ஞ்","ம்"));
    	outerList.add(Arrays.asList("இரு","கொண்டிரு","கொண்","உள்ள்","உள்","கொள்ள்","பட்","கொள்","படு","பட்ட்","விடு","விட்","பிடி","பார்","கொடு","வை","அடி","போடு","பெறு","பண்ணு","ஆயிற்று","முடி","இட்","காட்டு","தவிர்","விட்ட்","இட்","செய்"));
    	outerList.add(Arrays.asList("ப்ப்","ப்","ந்த்","இன்","ட்","ஈ","குவ்","த்த்","த்","வ்","ற்","கின்ற்","க்கிற்","கிற்","க்கின்ற்","ம்","இ","ஈஇ","ஊ","க்க்","க்","ன்"));
    	outerList.add(Arrays.asList("ய்","அன்","இன்","உ","அ","இற்","அற்"));
    	outerList.add(Arrays.asList("ஓன்","ஏன்","ஓம்","அம்","ஆய்","ஈர்கள்","ஈர்","ஆள்","ஆர்","அ","ஆர்கள்","அது","அத்","அன்","ஆன்","அள்","அர்","ஏம்","ப","அன்று","உம்"));
    	RecursiveAlgorithm ra = new RecursiveAlgorithm();
    	long begin =System.currentTimeMillis();
    	List<List<String>> finalList= ra.cartesian(outerList);
    	begin = System.currentTimeMillis()-begin;
    	System.out.println(begin+":"+finalList.size());
    	
    	begin =System.currentTimeMillis();
    	finalList= ra.cartesian(outerList);
    	begin = System.currentTimeMillis()-begin;
    	System.out.println(begin+":"+finalList.size());
    }
    
    public static void main(String[] args) throws Exception{
    	test7Items();
	}
    public static void Oldmain(String[] args) throws Exception 
    {
    	//RecursiveAlgorithm.getAllCombinedValues(TamilConstantTable.getInstance().getMainWordList());
    	 
    	List<List<String>> outerList =new ArrayList<List<String>>();
    	List<String> innerList =new ArrayList<String>();
    	
    	innerList.add("ய்");
    	innerList.add("வ்");
    	outerList.add(innerList);
    	innerList =new ArrayList<String>();
    	innerList.add("ஐ");
    	innerList.add("கு");
    	innerList.add("ஆல்");
    	innerList.add("ஓடு");
    	innerList.add("ஒடு");
    	innerList.add("இல்");
    	innerList.add("இடம்");
    	innerList.add("உடன்");
    	innerList.add("இன்");
    	innerList.add("அது");
    	innerList.add("உடைய");
    	innerList.add("க்");
    	innerList.add("ஓட்");
    	innerList.add("ஒட்");
    	innerList.add("து");
    	outerList.add(innerList);
    	innerList =new ArrayList<String>();
    	innerList.add("க்");
    	innerList.add("ச்");
    	innerList.add("த்");
    	innerList.add("ப்");
    	outerList.add(innerList);
    	RecursiveAlgorithm ra = new RecursiveAlgorithm();
    	//System.out.println(ra.getAllCombinedValues(outerList));
    	System.out.println(ra.cartesian(outerList));
    	
    	//RecursiveAlgorithm.getAllCombinedValues(TamilConstantTable.getInstance().getMainWordList());
   }
    
    public <T> List<List<T>> cartesian(List<List<T>> lists) {
        List<List<T>> currentCombinations = Arrays.asList(Arrays.asList());
        for (List<T> list : lists) {
            currentCombinations = appendElements(currentCombinations, list);
        }
        return currentCombinations;
    }
    
    public <T> List<List<T>> appendElements(List<List<T>> combinations, List<T> extraElements) {
        return combinations.parallelStream().flatMap(oldCombination
                -> extraElements.parallelStream().map(extra -> {
                    List<T> combinationWithExtra = new ArrayList<>(oldCombination);
                    combinationWithExtra.add(extra);
                    return combinationWithExtra;
                }))
                .collect(Collectors.toList());
    }

     public List<List<String>>  getAllCombinedValues(List<List<String>> outerList)
    {
    	return cartesian(outerList);	
//    	ArrayList<String> combineList = new ArrayList<String>();
//    	  allCombinedValues = new ArrayList<List<String>>();
//        //recursive call
//        recurse(combineList, outerList, 0);
//        //System.out.println(allCombinedValues.size());
//        return allCombinedValues;
    }

    private void recurse(List<String> newOptionsList, List<List<String>> listOfList, int placeHolder)
    {
        //recursiveCallsCounter++;
//        System.out.println("\n\tStart of Recursive Call: " + recursiveCallsCounter);
//        System.out.println("\tOptionsList: " + newOptionsList.toString());
//        System.out.println("\tList Of List: " + listOfList.toString());
//        System.out.println("\tPlaceHolder: "+ placeHolder);

        //check to see if we are at the end of all TestAspects
        if(placeHolder < listOfList.size())
        {
            //remove the first item in the ArrayOfArrays
            List<String> currentAspectsOptions = listOfList.get(placeHolder);
            //iterate through the popped off options

            for (int i=0 ; i<currentAspectsOptions.size();i++)
            {
                List<String> newOptions = new ArrayList<String>();
                //add all the passed in options to the new object to pass on
                for (int j=0 ; j < newOptionsList.size();j++) {
                    newOptions.add(newOptionsList.get(j));
                }

                newOptions.add(currentAspectsOptions.get(i));
                int newPlaceHolder = placeHolder + 1;
                recurse(newOptions,listOfList, newPlaceHolder);
            }
        } 
        else 
        { 
        	// no more arrays to pop off
            List<String> combinedValue = new ArrayList<String>();
            for (int i=0; i < newOptionsList.size();i++){
            	combinedValue.add(newOptionsList.get(i));
            }
            //System.out.println("\t### Adding: "+combinedValue.toString());
            allCombinedValues.add(combinedValue);
        }
    } 
}