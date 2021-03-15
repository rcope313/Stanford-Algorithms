package algorithmsWeek2;
import java.util.ArrayList;
import java.util.Arrays;

import algorithmsWeek3.FileToGraph;
import tester.Tester;

public class InversionCounter_v2 {
  
  InversionsAccumulator mergeSort (ArrayList<Integer> arr) {
    return mergeSortHelper(arr, 0, arr.size()-1, new InversionsAccumulator(0)); 
  }
  
  InversionsAccumulator mergeSortHelper (ArrayList<Integer> arr, int indexFrom, 
      int indexTo, InversionsAccumulator inversionAccumulator) {
    
    int middleIndex = (indexTo + indexFrom) / 2 + 1; 
   
    if (indexTo - indexFrom <= 0) {
      return new InversionsAccumulator(0); 
    }
    
    else {
      this.mergeSortHelper(arr, indexFrom, middleIndex-1, inversionAccumulator);
      this.mergeSortHelper(arr, middleIndex, indexTo, inversionAccumulator);
      inversionAccumulator.totalInversions += this.merge(arr, indexFrom, indexTo).inversionCount;  
     
      
    }
    
    return inversionAccumulator; 
  }
  
  MergeResult merge (ArrayList<Integer> arr, int indexFrom, int indexTo) {
    
    ArrayList<Integer> arrTemp = new ArrayList<Integer>(); 
    long inversionCounter = 0L;
    int middleIndex = (indexTo + indexFrom) / 2 + 1; 
    
    int i = indexFrom;
    int j = middleIndex; 
     
    while (i < middleIndex && j <= indexTo) {
      
      if (arr.get(i) < arr.get(j)) {
        arrTemp.add(arr.get(i)); 
        i ++;  
      }
      else {
        arrTemp.add(arr.get(j));
        j ++; 
        inversionCounter += middleIndex - i; 
      }
      
    }
    
    while (i < middleIndex) {
      arrTemp.add(arr.get(i));
      i ++; 
      
    }
    
    while (j <= indexTo) {
      arrTemp.add(arr.get(j));
      j ++;
    }
    
    int k = indexFrom;
    int l = 0; 
    
    while (k <= indexTo) {
      arr.set(k, arrTemp.get(l)); 
      k++;
      l++; 
      
    }
    
        
   
    
    return new MergeResult(arr,inversionCounter); 
    
  }
  
  void swap (ArrayList<Integer> arr1, int idx1, int idx2) {
  
    int element1 = arr1.get(idx1);
    int element2 = arr1.get(idx2);
  
    arr1.set(idx1, element2);
    arr1.set(idx2, element1);
 
  }

}

class ExamplesData {

  ArrayList<Integer> arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8, arr9, arr10, arr20, arr30, arr40, arr64, arrPA;  
  MergeResult mr1, mr2, mr3, mr4; 
  InversionsAccumulator ic1, ic2, ic3, ic4, ic8, ic9, ic10, ic20, ic30, ic40, ic64, icPA; 
  InversionCounter_v2 test; 
  FileToGraph file8, file9, file10, file20, file30, file40, file64, filePA;  

  void initData() {
    
    
    file8 = new FileToGraph("/Users/rachelcope/Desktop/AlgorithmsRepo/testCases/course1/assignment2Inversions/input_beaunus_8_8.txt");
    file9 = new FileToGraph("/Users/rachelcope/Desktop/AlgorithmsRepo/testCases/course1/assignment2Inversions/input_beaunus_9_16.txt");
    file10 = new FileToGraph("/Users/rachelcope/Desktop/AlgorithmsRepo/testCases/course1/assignment2Inversions/input_beaunus_10_16.txt"); 
    file20 = new FileToGraph("/Users/rachelcope/Desktop/AlgorithmsRepo/testCases/course1/assignment2Inversions/input_beaunus_20_64.txt"); 
    file30 = new FileToGraph("/Users/rachelcope/Desktop/AlgorithmsRepo/testCases/course1/assignment2Inversions/input_beaunus_30_512.txt"); 
    file40 = new FileToGraph("/Users/rachelcope/Desktop/AlgorithmsRepo/testCases/course1/assignment2Inversions/input_beaunus_40_2048.txt"); 
    file64 = new FileToGraph("/Users/rachelcope/Desktop/AlgorithmsRepo/testCases/course1/assignment2Inversions/input_dgrcode_64_100000.txt"); 
    filePA = new FileToGraph("/Users/rachelcope/Desktop/Test/MergeSort.txt"); 
    
    arr1 = new ArrayList<Integer>(Arrays.asList(1, 3, 5, 2, 4, 6));
    arr2 = new ArrayList<Integer>(Arrays.asList(2, 4, 6, 1, 3, 5));
    arr3 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6)); 
    arr4 = new ArrayList<Integer>(Arrays.asList(10,11,12,13,14,15,16,17,18,19,0,1,2,3,4,5,6,7,8,9)); 
    arr5 = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19)); 
    arr6 = new ArrayList<Integer>(Arrays.asList(1,4,5,2,3)); 
    arr7 = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5)); 
    arr8 = file8.createsArrayFromFile(); 
    arr9 = file9.createsArrayFromFile(); 
    arr10 = file10.createsArrayFromFile(); 
    arr20 = file20.createsArrayFromFile(); 
    arr30 = file30.createsArrayFromFile(); 
    arr40 = file40.createsArrayFromFile(); 
    arr64 = file64.createsArrayFromFile(); 
    arrPA = filePA.createsArrayFromFile();
    
    mr1 = new MergeResult(arr3, 3L); 
    mr2 = new MergeResult(arr3, 6L); 
    mr3 = new MergeResult(arr5, 100L); 
    mr4 = new MergeResult(arr7, 4L); 
    
    ic1 = new InversionsAccumulator(3L); 
    ic2 = new InversionsAccumulator(6L); 
    ic3 = new InversionsAccumulator(100L); 
    ic4 = new InversionsAccumulator(4L);
    ic8 = new InversionsAccumulator(16L);
    ic9 = new InversionsAccumulator(65L);
    ic10 = new InversionsAccumulator(64L); 
    ic20 = new InversionsAccumulator(1000L); 
    ic30 = new InversionsAccumulator(63957L); 
    ic40 = new InversionsAccumulator(1056516L); 
    ic64 = new InversionsAccumulator(2504602956L);
    icPA = new InversionsAccumulator(0L);
    
    test = new InversionCounter_v2(); 


  }

//  void testMerge(Tester t) {
//    this.initData(); 
//    t.checkExpect(test.merge(arr2, 0, 5), mr2);
//    this.initData(); 
//    t.checkExpect(test.merge(arr4, 0, 19), mr3);
//    this.initData(); 
//    t.checkExpect(test.merge(arr6, 0, 4), mr4);

//  }
  
  void testMergeSort (Tester t) {
    this.initData(); 
    t.checkExpect(test.mergeSort(arr1), ic1); 
    this.initData(); 
    t.checkExpect(test.mergeSort(arr2), ic2); 
    this.initData(); 
    t.checkExpect(test.mergeSort(arr4), ic3); 
    this.initData(); 
    t.checkExpect(test.mergeSort(arr6), ic4);
    this.initData();
    t.checkExpect(test.mergeSort(arr8), ic8); 
    this.initData();
    t.checkExpect(test.mergeSort(arr9), ic9); 
    this.initData();
    t.checkExpect(test.mergeSort(arr10), ic10); 
    this.initData(); 
    t.checkExpect(test.mergeSort(arr20), ic20); 
    this.initData(); 
    t.checkExpect(test.mergeSort(arr30), ic30); 
    this.initData(); 
    t.checkExpect(test.mergeSort(arr40), ic40);
    this.initData(); 
    t.checkExpect(test.mergeSort(arr64), ic64);
    this.initData(); 
    t.checkExpect(test.mergeSort(arrPA), icPA);
    
  }
}
  
