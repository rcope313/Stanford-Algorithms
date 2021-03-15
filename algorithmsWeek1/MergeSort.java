package algorithmsWeek1;
import tester.*;
import java.util.ArrayList;
import java.util.Arrays;


public class MergeSort {
  
 public ArrayList<Integer> mergeSort (ArrayList<Integer> arr) {
   
   if (arr.size() ==1 ) {
     return arr; 
   }
   
   else {
     
     int middlePoint = arr.size() / 2; 
     ArrayList<Integer> arrA = new ArrayList<Integer>();
     ArrayList<Integer> arrB = new ArrayList<Integer>(); 
   
     for (int i = 0; i < arr.size(); i = i + 1) { 
       
       if (i < middlePoint) {
         arrA.add(arr.get(i)); 
       }
       else {
         arrB.add(arr.get(i)); 
       }
     }
     
   return mergeSortRecursiveHelp(arrA, arrB); 
   
   }
 }
 
 
 ArrayList<Integer> mergeSortRecursiveHelp (ArrayList<Integer> arrA, ArrayList<Integer> arrB) {
   
   return this.mergeRecursive(this.mergeSort(arrA), this.mergeSort(arrB),
       0, 0, new ArrayList<Integer>());  
 }
 
 
 
 ArrayList<Integer> mergeRecursive (ArrayList<Integer> arrA, ArrayList<Integer> arrB, int tallyA, int tallyB, 
     ArrayList<Integer> result) {
   
   if (tallyA >= arrA.size()) {
     result.addAll(arrB.subList(tallyB, arrB.size())); 
     return result; 
   }
   
   if (tallyB >= arrB.size()) {
     result.addAll(arrA.subList(tallyA, arrA.size())); 
     return result; 
   }
   
   else if (arrA.get(tallyA) < arrB.get(tallyB)) {
     result.add(arrA.get(tallyA)); 
     return this.mergeRecursive(arrA, arrB, tallyA + 1, tallyB, result);
   }
   
   else {
     result.add(arrB.get(tallyB));
     return this.mergeRecursive(arrA, arrB, tallyA, tallyB + 1, result);
     
   }
 }
 
 
 ///////////////////////////////////////////////////////////////
 
 
//In ArrayUtils
//EFFECT: Sorts the provided list according to the given comparator
 void mergesort(ArrayList<Integer> arr) {
  
// Create a temporary array
   
   ArrayList<Integer> temp = new ArrayList<Integer>(); 

// Make sure the temporary array is exactly as big as the given array
   
   for (int i = 0; i < arr.size(); i ++) { 
     temp.add(arr.get(i)); 
   }
   
   this.mergesortHelp(arr, temp, 0, arr.size()); 


}
 
 
 
//EFFECT: Sorts the provided list in the region [loIdx, hiIdx)
//according to the given comparator.
//Modifies both lists in the range [loIdx, hiIdx) 
 void mergesortHelp(ArrayList<Integer> source, ArrayList<Integer> temp, int loIdx, int hiIdx) {

   // Step 0: stop when finished
   if (hiIdx - loIdx <= 1) {
     return ;     
   }

   // Step 1: find the middle index
   int midIdx = (hiIdx + loIdx) / 2; 

   // Step 2: recursively sort both halves
   this.mergesortHelp(source, temp, loIdx, midIdx-1);
   this.mergesortHelp(source, temp, midIdx, hiIdx);
   
   // Step 3: merge the two sorted halves
   this.merge(source, temp, loIdx, midIdx, hiIdx);
}
 
 
 
 
 void merge(ArrayList<Integer> source, ArrayList<Integer> temp, 
     int loIdx, int midIdx, int hiIdx) { 
//Merges the two sorted regions [loIdx, midIdx) and [midIdx, hiIdx) from source
//into a single sorted region according to the given comparator
//EFFECT: modifies the region [loIdx, hiIdx) in both source and temp

// merge things
   
   int tallyLo = loIdx;
   int tallyHigh = midIdx; 
   int tallyTemp = 0; 
   
   while (tallyLo < tallyHigh) {
     
     if (source.get(tallyLo) < source.get(tallyHigh)) {
       temp.set(tallyTemp, source.get(tallyLo));
       tallyLo = tallyLo + 1;  
       tallyTemp = tallyTemp + 1; 
       
       }
     
     else {
       temp.set(tallyTemp, source.get(tallyHigh)); 
       tallyHigh = tallyHigh + 1; 
       tallyTemp = tallyTemp + 1; 
       
     }
   }
   
// copy everything that's left -- at most one of the two half-lists still has items in it

   while (tallyLo < midIdx - 1) {
     temp.set(tallyTemp, source.get(tallyLo));
     tallyLo = tallyLo + 1;  
     tallyTemp = tallyTemp + 1;
     
   }
   
   while (tallyHigh < hiIdx) {
     temp.set(tallyTemp, source.get(tallyHigh));
     tallyLo = tallyHigh + 1;  
     tallyTemp = tallyTemp + 1;
      
   }
   
   temp = source; 

 }
 
  
 
 
}









class ExamplesList {
  
  ArrayList<Integer> arrI, arrO, arrA, arrB; 
  
  void initData() {
    
    arrI = new ArrayList<Integer>(Arrays.asList(3,5,1,9,7,2,0,4,8,6));
    arrO = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
    arrA = new ArrayList<Integer>(Arrays.asList(0,2,4,6,8));
    arrB = new ArrayList<Integer>(Arrays.asList(1,3,5,7,9));
    
  }
  
  void testMergeSort (Tester t) {
    
    this.initData();
    t.checkExpect(new MergeSort().mergeSort(arrI), arrO); 
    new MergeSort().mergesort(arrI);
    t.checkExpect(arrI, arrO); 
  }
  
  void testMergeRecursive (Tester t) {
    this.initData();
    t.checkExpect(new MergeSort().mergeRecursive(arrA, arrB, 0, 0, new ArrayList<Integer>()), arrO); 
      
  }
      
      
}
