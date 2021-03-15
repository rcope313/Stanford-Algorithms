package algorithmsWeek3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random; 
import tester.Tester;

public class QuickSortRandomPivot {
  
  void quickSort(ArrayList<Integer> a) { this.quickSortHelper(a, 0, (a.size()-1), new PartitionResult(0)); } 
  
  void quickSortHelper(ArrayList<Integer> a, int fromIndex, int toIndex, PartitionResult partitionResult) {
   
    if (fromIndex >= toIndex) {}
    else { 
      
      // step 1 - choose a pivot (picks a random index between 0 thru a.size()-1)
      // step 1a - swap pivot with first element as preprocessing step 
//      int randomIdx = this.randomGenerator(fromIndex, toIndex+1); 
//      this.swap(a, randomIdx, fromIndex);
    
      int i = this.partition(a, fromIndex, toIndex, partitionResult).indexToSplit; 
      this.quickSortHelper(a, fromIndex, i-2, partitionResult);
      this.quickSortHelper(a, i, toIndex, partitionResult); 
      
    
    }
  }
  
 
  PartitionResult partition (ArrayList<Integer> a, int fromIndex, int toIndex, PartitionResult partitionResult) {
    
    int pivot = a.get(fromIndex);

    // step 2 instantiate i & j as first index (i = tracker of what is < and > of pivot,
    // j = tracker of what has & hasn't been seen)
    int i = fromIndex + 1;
   
    // step 3: go thru whole list until seen all elements 
    // if a(j) is > than p, j ++ 
    // if a(j) is < than p, swap a(j) and a(i), j++ & i++
    for (int j = fromIndex + 1;                                   
        j < toIndex + 1;                               
        j ++) {  
      
      if (a.get(j) < pivot) {
        this.swap(a,i,j);
        i ++; 
      }
      
    }
    
    // step 4: swap a(0) and a(i-1)
    this.swap(a, fromIndex, i-1);
    partitionResult.indexToSplit = i; 
    return partitionResult; 
     
    
  
  }
  

  
  
  public void swap (ArrayList<Integer> arr1, int idx1, int idx2) {
    
    int element1 = arr1.get(idx1);
    int element2 = arr1.get(idx2);
    
    arr1.set(idx1, element2);
    arr1.set(idx2, element1);
   
  }
  
  // This gives you a random number in between low (inclusive) and high (exclusive)
  public int randomGenerator (int low, int high) {

    Random r = new Random(); 
    return r.nextInt(high-low) + low; 
    
  }
  

}


class ExamplesArrays {
  
  ArrayList<Integer> arr1, arr2, arr3, arr4, arr5, arr6;   
  
  void initData() {
     
    arr1 = new ArrayList<Integer>(Arrays.asList(3,5,1,9,7,2,0,4,8,6));
    arr2 = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
    arr3 = new ArrayList<Integer>(Arrays.asList(15,16,2,3,100,13,82,34,51,99,300,4,6,7)); 
    arr4 = new ArrayList<Integer>(Arrays.asList(2,3,4,6,7,13,15,16,34,51,82,99,100,300)); 
    arr5 = new ArrayList<Integer>(Arrays.asList(2,5,1,3,4));
    arr6 = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
    
    
  }
  
  
  void testQuickSort (Tester t) {
    this.initData();
    new QuickSortRandomPivot().quickSort(arr1); 
    new QuickSortRandomPivot().quickSort(arr3);
    new QuickSortRandomPivot().quickSort(arr5);
    t.checkExpect(arr1, arr2); 
    t.checkExpect(arr3, arr4);
    t.checkExpect(arr5, arr6);
    
  }
  
  
}
