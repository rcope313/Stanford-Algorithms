package algorithmsWeek3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import tester.Tester;

public class QuickSortLastPivot {
  
  
  ComparisonsAccumulator quickSortLastPivot(ArrayList<Integer> a) { 
    
    return this.quickSortHelperLastPivot(a, 0, (a.size()-1), new ComparisonsAccumulator(0)); 
    
  } 
  
  ComparisonsAccumulator quickSortHelperLastPivot(ArrayList<Integer> a, int fromIndex, int toIndex, 
      ComparisonsAccumulator comparisonAccumulator) {
    
    
    if (fromIndex >= toIndex) {}
    else { 
      
      int i = this.partition(a, fromIndex, toIndex).indexToSplit; 
      comparisonAccumulator.totalComparisons += (toIndex - fromIndex); 
      this.quickSortHelperLastPivot(a, fromIndex, i-2, comparisonAccumulator);
      this.quickSortHelperLastPivot(a, i, toIndex, comparisonAccumulator);  
       
    }
    
    return comparisonAccumulator; 
    

    
  }
  
  PartitionResult partition (ArrayList<Integer> a, int fromIndex, int toIndex) {
    
    int pivot = a.get(toIndex);
    this.swap(a, fromIndex, toIndex);
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
  
  void swap (ArrayList<Integer> arr1, int idx1, int idx2) {
    
    int element1 = arr1.get(idx1);
    int element2 = arr1.get(idx2);
    
    arr1.set(idx1, element2);
    arr1.set(idx2, element1);
   
  }
  
  int randomGenerator (int low, int high) {

    Random r = new Random(); 
    return r.nextInt(high-low) + low; 
    
  }
  

}


class ExamplesArraysLastPivot {
  
  FileArray file11, file14, file16, file20, filePA; 
  ArrayList<Integer> arr1, arr2, arr11, arr14, arr16, arr20, arrPA; 
  ComparisonsAccumulator ca1, ca2, ca11, ca14, ca16, ca20, caPA; 
  
  
  void initData() {
     
    
    file11 = new FileArray("/Users/rachelcope/Desktop/AlgorithmsRepo/testCases/course1/assignment3Quicksort/input_dgrcode_11_20.txt");
    file14 = new FileArray("/Users/rachelcope/Desktop/AlgorithmsRepo/testCases/course1/assignment3Quicksort/input_dgrcode_14_20.txt"); 
    file16 = new FileArray("/Users/rachelcope/Desktop/AlgorithmsRepo/testCases/course1/assignment3Quicksort/input_dgrcode_16_100000.txt"); 
    file20 = new FileArray("/Users/rachelcope/Desktop/AlgorithmsRepo/testCases/course1/assignment3Quicksort/input_dgrcode_20_1000000.txt"); 
    filePA = new FileArray("/Users/rachelcope/Desktop/Test/QuickSort.txt"); 


    arr11 = file11.createsArrayFromFile(); 
    arr14 = file14.createsArrayFromFile(); 
    arr16 = file16.createsArrayFromFile(); 
    arr20 = file20.createsArrayFromFile(); 
    arrPA = filePA.createsArrayFromFile();
    

    ca11 = new ComparisonsAccumulator(73L); 
    ca14 = new ComparisonsAccumulator(69L);
    ca16 = new ComparisonsAccumulator(2079088L);
    ca20 = new ComparisonsAccumulator(25371973L);
    caPA = new ComparisonsAccumulator(164123L);
    
    

    
    
    
    
  }
  
  
  void testQuickSort (Tester t) {
    this.initData();
    t.checkExpect(new QuickSortLastPivot().quickSortLastPivot(arr11), ca11); 
    this.initData();
    t.checkExpect(new QuickSortLastPivot().quickSortLastPivot(arr14), ca14); 
    this.initData();
    t.checkExpect(new QuickSortLastPivot().quickSortLastPivot(arr16), ca16); 
    this.initData();
    t.checkExpect(new QuickSortLastPivot().quickSortLastPivot(arr20), ca20);
    this.initData();
    t.checkExpect(new QuickSortLastPivot().quickSortLastPivot(arrPA), caPA);
    

  }
  
  
}
