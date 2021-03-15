package algorithmsWeek3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import tester.Tester;

public class QuickSortMedianOf3 {
  
  
  ComparisonsAccumulator QuickSortMedianOf3Mo3(ArrayList<Integer> a) { 
    
    return this.QuickSortMedianOf3HelperMo3(a, 0, (a.size()-1), new ComparisonsAccumulator(0)); 
    
  } 
  
  ComparisonsAccumulator QuickSortMedianOf3HelperMo3(ArrayList<Integer> a, int fromIndex, int toIndex, 
      ComparisonsAccumulator comparisonAccumulator) {
    
    
    if (fromIndex >= toIndex) {}
    else { 
      
      int i = this.partition(a, fromIndex, toIndex).indexToSplit; 
      comparisonAccumulator.totalComparisons += (toIndex - fromIndex); 
      this.QuickSortMedianOf3HelperMo3(a, fromIndex, i-2, comparisonAccumulator);
      this.QuickSortMedianOf3HelperMo3(a, i, toIndex, comparisonAccumulator);  
       
    }
    
    return comparisonAccumulator; 
    

    
  }
  
  PartitionResult partition (ArrayList<Integer> a, int fromIndex, int toIndex) {
    
    Index median = this.findMedian(a, fromIndex, toIndex);
    
    int pivot = a.get(median.index); 
    this.swap(a, fromIndex, median.index);
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
  
  Index findMedian(ArrayList<Integer> arr, int fromIndex, int toIndex) {
    
    Index x = new Index (fromIndex, arr.get(fromIndex)); 
    Index y = new Index (toIndex, arr.get(toIndex)); 
    Index z = new Index ((fromIndex + toIndex)/2, arr.get((fromIndex + toIndex)/2));  
   
   ArrayList<Index> arr3 = new ArrayList<Index>(); 
   
   arr3.add(x);
   arr3.add(y); 
   arr3.add(z); 
  
   arr3.sort(new CompareIndex()); 
   return arr3.get(1); 
    
  }

}

class CompareIndex implements Comparator<Index> {

  @Override
  public int compare(Index o1, Index o2) {
    if (o1.value < o2.value) {return -1;}
    if (o1.value == o2.value) {return 0;}
    else {return 1;}

  }
  
}

class ExamplesArraysMedianOf3Pivot {
  
  FileArray file11, file14, file16, file20, filePA; 
  ArrayList<Integer> arr1, arr2, arr3, arr4, arr11, arr14, arr16, arr20, arrPA; 
  ComparisonsAccumulator ca1, ca2, ca11, ca14, ca16, ca20, caPA; 
  Index i1, i2, i3, i4, i5; 
    
  void initData() {
     
    
    file11 = new FileArray("/Users/rachelcope/Desktop/AlgorithmsRepo/testCases/course1/assignment3QuickSortMedianOf3/input_dgrcode_11_20.txt");
    file14 = new FileArray("/Users/rachelcope/Desktop/AlgorithmsRepo/testCases/course1/assignment3QuickSortMedianOf3/input_dgrcode_14_20.txt"); 
    file16 = new FileArray("/Users/rachelcope/Desktop/AlgorithmsRepo/testCases/course1/assignment3QuickSortMedianOf3/input_dgrcode_16_100000.txt"); 
    file20 = new FileArray("/Users/rachelcope/Desktop/AlgorithmsRepo/testCases/course1/assignment3QuickSortMedianOf3/input_dgrcode_20_1000000.txt"); 
    filePA = new FileArray("/Users/rachelcope/Desktop/Test/QuickSortMedianOf3.txt"); 

    arr1 = new ArrayList<Integer>(Arrays.asList(0,1,2,3));
    arr2 = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4));
    arr3 = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9)); 
    arr4 =  new ArrayList<Integer>(Arrays.asList(1,2,3,4));
    arr11 = file11.createsArrayFromFile(); 
    arr14 = file14.createsArrayFromFile(); 
    arr16 = file16.createsArrayFromFile(); 
    arr20 = file20.createsArrayFromFile(); 
    arrPA = filePA.createsArrayFromFile();
   
    ca11 = new ComparisonsAccumulator(56L); 
    ca14 = new ComparisonsAccumulator(55L);
    ca16 = new ComparisonsAccumulator(1749103L);
    ca20 = new ComparisonsAccumulator(21214423L);
    caPA = new ComparisonsAccumulator(138382L);
    
    i1 = new Index (1,1);
    i2 = new Index (2,2);
    i3 = new Index (7,7);
    i4 = new Index (1,2);
    i5 = new Index (2,3); 
    
    
    
  }
  
  void testFindMedian (Tester t) {
    this.initData();
    t.checkExpect(new QuickSortMedianOf3().findMedian(arr1, 0, 3), i1); 
    this.initData();
    t.checkExpect(new QuickSortMedianOf3().findMedian(arr2, 0, 4), i2); 
    this.initData();
    t.checkExpect(new QuickSortMedianOf3().findMedian(arr3, 6, 9), i3); 
    this.initData();
    t.checkExpect(new QuickSortMedianOf3().findMedian(arr4, 0, 3), i4);
    this.initData();
    t.checkExpect(new QuickSortMedianOf3().findMedian(arr4, 2, 3), i5);
    
  }
  
  
  void testQuickSortMedianOf3 (Tester t) {
    this.initData();
    t.checkExpect(new QuickSortMedianOf3().QuickSortMedianOf3Mo3(arr11), ca11); 
    this.initData();
    t.checkExpect(new QuickSortMedianOf3().QuickSortMedianOf3Mo3(arr14), ca14); 
    this.initData();
    t.checkExpect(new QuickSortMedianOf3().QuickSortMedianOf3Mo3(arr16), ca16); 
    this.initData();
    t.checkExpect(new QuickSortMedianOf3().QuickSortMedianOf3Mo3(arr20), ca20);
    this.initData();
    t.checkExpect(new QuickSortMedianOf3().QuickSortMedianOf3Mo3(arrPA), caPA);
    

  }
  
  
}
