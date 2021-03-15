package algorithmsWeek4;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import algorithmsWeek1.MergeSort;
import algorithmsWeek3.Index;
import algorithmsWeek3.QuickSort;
import tester.Tester;


public class DeterministicSelection {
  
  OrderStatisticValue dSelect(ArrayList<Integer> arr, int orderStatistic) {
    return dSelectByIndices (arr, orderStatistic, 0, arr.size()-1); 
  }
  
  OrderStatisticValue dSelectByIndices (ArrayList<Integer> arr, int orderStatistic, int fromIndex, int toIndex) {
    
    
    if (toIndex - fromIndex <= 0) {
      return new OrderStatisticValue(arr.get(fromIndex)); 
    
    } 
 
    else {
      
      ArrayList<Integer> medians = this.sort_Arr_By_Groups_of_Five_And_Create_List_Of_Middle_Elements(arr, fromIndex, toIndex);
      OrderStatisticValue medianOfMedians = this.medianOfMedians(medians, 0, medians.size()-1);
      int medianOfMediansIndex = this.findElementAndReturnIndex(arr, medianOfMedians.value); 
      this.swap(arr, fromIndex, medianOfMediansIndex);
      PartitionPivot pivot = this.partition(arr, fromIndex, toIndex); 
      
      if (pivot.index == orderStatistic - 1) {
        return new OrderStatisticValue(pivot.value); 
      }
      
      else if (pivot.index > orderStatistic - 1) {
        return dSelectByIndices(arr,
                             orderStatistic, 
                             fromIndex, 
                             (pivot.index - 1)); 
      }
      
      else {
        return dSelectByIndices(arr, 
                            orderStatistic, 
                            pivot.index + 1, 
                            toIndex); 
        
      }
        
       
    }
    
  }
  
  OrderStatisticValue medianOfMedians (ArrayList<Integer> arr, int fromIndex, int toIndex) {
    return dSelectByIndices (arr, (fromIndex + toIndex)/2, fromIndex, toIndex); 
  }
  
  ArrayList<Integer> sort_Arr_By_Groups_of_Five_And_Create_List_Of_Middle_Elements (ArrayList<Integer> arr, int fromIndex, int toIndex) {
 
    int arrIndex = fromIndex; 
    
    ArrayList<Integer> arrTemp = new ArrayList<Integer>(); 
    int arrTempIndexHigh = -1; 
    int arrTempIndexLow = 0; 
    int groupsOfFiveTally = 0; 
    
    ArrayList<Integer> arrOfMedians = new ArrayList<Integer>(); 
    
    
    while (arrIndex <= toIndex) {
      
      if (groupsOfFiveTally != 5) {
        
        groupsOfFiveTally ++; 
        arrTemp.add(arr.get(arrIndex)); 
        arrIndex ++; 
        arrTempIndexHigh ++; 

      }
    
      else {
      
          
        new QuickSort().quickSortByIndices(arrTemp, arrTempIndexLow, arrTempIndexHigh);
        
        int median = arrTemp.get((arrTempIndexHigh + arrTempIndexLow) / 2); 
        arrOfMedians.add(median); 
        
        groupsOfFiveTally = 0; 
        arrTempIndexLow = arrTempIndexHigh + 1; 
       
       
      }
      
    }
   
    new QuickSort().quickSortByIndices(arrTemp, arrTempIndexLow, arrTempIndexHigh);
    
    int median = arrTemp.get((arrTempIndexHigh + arrTempIndexLow) / 2); 
    arrOfMedians.add(median); 

    
    for (int idx = fromIndex; idx <= toIndex; idx ++) {
      
      arr.set(idx, arrTemp.get(idx)); 
      
    }
    
    return arrOfMedians; 
   
    
  }

  PartitionPivot partition (ArrayList<Integer> a, int fromIndex, int toIndex) {
    
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
    return new PartitionPivot(i-1, pivot); 
    
  }
  
  void swap (ArrayList<Integer> arr1, int idx1, int idx2) {
    
    int element1 = arr1.get(idx1);
    int element2 = arr1.get(idx2);
    
    arr1.set(idx1, element2);
    arr1.set(idx2, element1);
   
  }
    
  int findElementAndReturnIndex (ArrayList<Integer> arr, int x) {
    
    int xIndex = 0; 
    
    for (int idx = 0; idx < arr.size(); idx ++) {
     
      if (arr.get(0) == x) {
        xIndex = idx;  
      }
      
    }
    
    return xIndex; 
    
  }

}
 
class ExamplesDeterministicSelection {
  
  ArrayList<Integer> arr1, arr1i, arr2, arr2i, arr3, arr3i, arr4, arr4i, arr5, arr5i, arr6, arr6i;
  
  OrderStatisticValue osv1, osv2, osv3, osv5, osv6;  
 
  
  DeterministicSelection test; 
  
  void initData() {
    
    arr1 = new ArrayList<Integer>(Arrays.asList(2,1,3,5,4));  
    arr1i = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5)); 
    
    
    arr2 = new ArrayList<Integer>(Arrays.asList(2,1,3,5,4,6));
    arr2i = new ArrayList<Integer>(Arrays.asList(3,6)); 
    
    arr3 = new ArrayList<Integer>(Arrays.asList(2,5,6,10,9,3,1,7,8,4)); 
    arr3i = new ArrayList<Integer>(Arrays.asList(6,4)); 
    
    arr4 = new ArrayList<Integer>(Arrays.asList(2,5,6,10,9,3,1,7,8,4)); 
    arr4i = new ArrayList<Integer>(Arrays.asList(6)); 
    
    arr5 = new ArrayList<Integer>(Arrays.asList(2,5,6,10,9,3,1,7,8)); 
    arr5i = new ArrayList<Integer>(Arrays.asList(6,3));
    
    arr6 = new ArrayList<Integer>(Arrays.asList(2,5,6,10,9,3,1)); 
    arr6i = new ArrayList<Integer>(Arrays.asList(6,1));
    
    osv1 = new OrderStatisticValue(1);
    osv2 = new OrderStatisticValue(2);
    osv3 = new OrderStatisticValue(3);
    osv5 = new OrderStatisticValue(5);
    osv6 = new OrderStatisticValue(6);
    

    
    test = new DeterministicSelection();  
   
  }
  
  void testDSelect (Tester t) {
    this.initData();
    t.checkExpect(test.dSelect(arr1, 3), osv3); 
    this.initData();
    t.checkExpect(test.dSelect(arr1, 5), osv5); 
    this.initData();
    t.checkExpect(test.dSelect(arr2, 3), osv3); 
    this.initData();
    t.checkExpect(test.dSelect(arr2, 5), osv5); 
    this.initData();
    t.checkExpect(test.dSelect(arr3, 5), osv5); 
     
  }
  
  void sort_Arr_By_Groups_of_Five_And_Create_List_Of_Middle_Elements (Tester t) {
    this.initData();
    t.checkExpect(test.sort_Arr_By_Groups_of_Five_And_Create_List_Of_Middle_Elements(arr2, 0, 5), arr2i); 
    t.checkExpect(test.sort_Arr_By_Groups_of_Five_And_Create_List_Of_Middle_Elements(arr3, 0, 9), arr3i); 
    t.checkExpect(test.sort_Arr_By_Groups_of_Five_And_Create_List_Of_Middle_Elements(arr4, 0, 4), arr4i); 
    t.checkExpect(test.sort_Arr_By_Groups_of_Five_And_Create_List_Of_Middle_Elements(arr5, 0, 8), arr5i);
    t.checkExpect(test.sort_Arr_By_Groups_of_Five_And_Create_List_Of_Middle_Elements(arr6, 0, 6), arr6i);
    this.initData();
    test.sort_Arr_By_Groups_of_Five_And_Create_List_Of_Middle_Elements(arr1, 0, 4);
    t.checkExpect(arr1, arr1i); 
       
  }
  
  void testMedianOfMedians (Tester t) {
    this.initData();
    t.checkExpect(test.medianOfMedians(arr5i, 0, 1), osv3);
    this.initData();
    t.checkExpect(test.medianOfMedians(arr5i, 0, 0), osv6);
    this.initData();
    t.checkExpect(test.medianOfMedians(arr6i, 0, 1), osv1);
    
     
     
  }
  
}