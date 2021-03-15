package algorithmsWeek4;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import tester.Tester;


public class RandomizedSelection {
  
  OrderStatisticValue rSelect(ArrayList<Integer> arr, int orderStatistic) {
    return rSelectHelper (arr, orderStatistic, 0, arr.size()-1); 
  }
  
  OrderStatisticValue rSelectHelper (ArrayList<Integer> arr, int orderStatistic, int fromIndex, int toIndex) {
    
    
    if (toIndex - fromIndex <= 0) {
      return new OrderStatisticValue(arr.get(fromIndex)); 
    
    }
    
    else {
      
      this.swapFirstElementWithRandomIndex(arr, fromIndex, toIndex);
      PartitionPivot pivot = this.partition(arr, fromIndex, toIndex); 
      
      if (pivot.index == orderStatistic - 1) {
        return new OrderStatisticValue(pivot.value); 
      }
      
      else if (pivot.index > orderStatistic - 1) {
        return rSelectHelper(arr,
                             orderStatistic, 
                             fromIndex, 
                             (pivot.index - 1)); 
      }
      
      else {
        return rSelectHelper(arr, 
                            orderStatistic, 
                            pivot.index + 1, 
                            toIndex); 
        
      }
        
       
    }
    
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
  
  void swapFirstElementWithRandomIndex (ArrayList<Integer> arr, int fromIndex, int toIndex) {
    
    int randomIndex = new Random().nextInt(toIndex-fromIndex) + fromIndex;
    
    int element1 = arr.get(fromIndex);
    int element2 = arr.get(randomIndex);
    
    arr.set(fromIndex, element2);
    arr.set(randomIndex, element1);
   
  }
  
}

class ExamplesRandomizedSelection {
  
  ArrayList<Integer> exArr1, exArr2;
  OrderStatisticValue osv2, osv3, osv5; 
  
  RandomizedSelection test; 
  
  void initData() {
    
    exArr1 = new ArrayList<Integer>(Arrays.asList(2,1,3,5,6,4));  
    exArr2 = new ArrayList<Integer>(Arrays.asList(2,5,6,10,9,3,1,7,8,4)); 
    
    osv2 = new OrderStatisticValue(2);
    osv3 = new OrderStatisticValue(3);
    osv5 = new OrderStatisticValue(5);
    
    test = new RandomizedSelection();  
   
  }
  
  void testRSelect (Tester t) {
    this.initData();
    t.checkExpect(test.rSelect(exArr1, 3), osv3); 
    this.initData();
    t.checkExpect(test.rSelect(exArr1, 5), osv5); 
    this.initData();
    t.checkExpect(test.rSelect(exArr2, 3), osv3); 
    this.initData();
    t.checkExpect(test.rSelect(exArr2, 5), osv5); 
     
  }
  
}