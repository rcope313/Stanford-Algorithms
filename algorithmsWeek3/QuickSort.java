package algorithmsWeek3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import tester.Tester;

public class QuickSort {
  
  
  public void quickSort(ArrayList<Integer> a) { 
    
    this.quickSortByIndices(a, 0, (a.size()-1)); 
    
  } 
  
  public void quickSortByIndices(ArrayList<Integer> a, int fromIndex, int toIndex) {
    
    
    if (fromIndex >= toIndex) {}
    else { 
      
      int i = this.partition(a, fromIndex, toIndex).indexToSplit; 
      this.quickSortByIndices(a, fromIndex, i-2);
      this.quickSortByIndices(a, i, toIndex);  
       
    }

    
  }
  
  public PartitionResult partition (ArrayList<Integer> a, int fromIndex, int toIndex) {
    
    int randomIndex = this.randomGenerator(fromIndex, toIndex); 
    this.swap(a, fromIndex, randomIndex);
    int pivot = a.get(fromIndex);
    
    int i = fromIndex + 1;
   
    
    for (int j = fromIndex + 1;                                   
        j < toIndex + 1;                               
        j ++) {  
      
      if (a.get(j) < pivot) {
        this.swap(a,i,j);
        i ++; 
      }
      
    }
    
    this.swap(a, fromIndex, i-1);
    return new PartitionResult(i); 
    
   
  }
  
  public void swap (ArrayList<Integer> arr1, int idx1, int idx2) {
    
    int element1 = arr1.get(idx1);
    int element2 = arr1.get(idx2);
    
    arr1.set(idx1, element2);
    arr1.set(idx2, element1);
   
  }
  
  public int randomGenerator (int low, int high) {

    Random r = new Random(); 
    return r.nextInt(high-low) + low; 
    
  }


}

class ExamplesArraysQuickSort {
  
  
  ArrayList<Integer> arrI1, arrI2, arrO1, arrO2; 
  QuickSort test; 
 
    
  void initData() {
     
    
    arrI1 = new ArrayList<Integer>(Arrays.asList(4,3,2,1));
    arrI2 = new ArrayList<Integer>(Arrays.asList(9,6,7,8,1,2,5,4,3,0));
   
    arrO1 =  new ArrayList<Integer>(Arrays.asList(1,2,3,4));
    arrO2 = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9)); 
    
    test = new QuickSort(); 
    
    
  }
  

  void testQuickSortMedianOf3 (Tester t) {
    this.initData();
    test.quickSort(arrI1);
    test.quickSort(arrI2);
    t.checkExpect(arrI1, arrO1); 
    t.checkExpect(arrI2, arrO2); 
    

  }
  
  
}
