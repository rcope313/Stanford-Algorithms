package algorithmsWeek2;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import tester.Tester;


// i really want to try to write this as there is no temp to allocate extra space to


class InversionCounter {

  long inversionCounter(ArrayList<Long> arr) {
    
    ArrayList<Long> temp = new ArrayList<Long>(); 
    temp = arr; 
   
    return this.inversionCounterHelper(arr, temp, 0, arr.size()); 
           
  }
  
  long inversionCounterHelper (ArrayList<Long> arr, ArrayList<Long> temp, int firstIndex, int lastIndex) {
    
    // Step 0: stop when finished
    if (lastIndex - firstIndex <= 1) {
      return 0;      
    }
    
    int middleIndex = ((lastIndex + firstIndex) / 2);
    
    // Step 2: recursively sort both halves
    
    return 
    this.inversionCounterHelper(arr, temp, firstIndex, middleIndex-1) + 
    this.inversionCounterHelper(arr, temp, middleIndex, lastIndex) + 
    this.mergeAndCountSplitInv(arr, temp, firstIndex, middleIndex, lastIndex); 
    
  }
  
  long mergeAndCountSplitInv(ArrayList<Long> arr, ArrayList<Long> temp, int firstIndex, int middleIndex, 
      int lastIndex) {
    
   
    int inversion_Counter = 0;
    int i = firstIndex; 
    int j = middleIndex; 
    // check this
    int k = firstIndex; 

    while (i < middleIndex && j < lastIndex) {

      long bValue = arr.get(i);
      long cValue = arr.get(j);

      if (bValue <= cValue) {
        temp.set(k, arr.get(i));
        i = i + 1;
        k = k + 1; 
        
      }

      else {
        //this first part is wrong 
        inversion_Counter = inversion_Counter + (firstIndex + lastIndex - i);
        temp.set(k, arr.get(j));
        j = j + 1;
        k = k + 1; 
        

      }
    }
    
    while (j < arr.size()) {
      temp.set(k, arr.get(j));
      j = j + 1;
      k = k + 1;   
       
    }
    
    while (i < middleIndex) {
      temp.set(k, arr.get(i));
      i = i + 1;
      k = k + 1;
       
    }
    
    arr = temp;
    return inversion_Counter;
   

  }

  ArrayList<Long> file2Array(String filePath) {

    ArrayList<Long> result = new ArrayList<>();
    Scanner s;

    try {

      s = new Scanner(new FileReader(filePath));

      while (s.hasNext()) {
        result.add(s.nextLong());
      }

      return result;

    }

    catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return result;
    }

  }

}

class Examples {

  ArrayList<Long> exampleArr1, exampleArr2, exampleArr3, exampleArr4, exampleArr5, exampleArr6,
      exampleArrFromText, exampleArrFromText2,
      inputBeanus1216, inputBeanus5410,
      oddArrI;

  InversionCounter test;

  void initData() {
    exampleArr1 = new ArrayList<Long>(Arrays.asList(1L, 3L, 5L, 2L, 4L, 6L));
    exampleArr2 = new ArrayList<Long>(Arrays.asList(6L, 5L, 4L, 3L, 2L, 1L));
    exampleArr3 = new ArrayList<Long>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L));
    exampleArr4 = new ArrayList<Long>(Arrays.asList(4L, 5L, 6L, 1L, 2L, 3L));
    exampleArr5 = new ArrayList<Long>(Arrays.asList(5L, 6L, 1L, 2L, 3L, 4L));
    exampleArr6 = new ArrayList<Long>(Arrays.asList(6L, 5L, 4L, 3L));

    exampleArrFromText = new InversionCounter()
        .file2Array("/Users/rachelcope/Desktop/Test/IntegerArrayTest.txt");
    exampleArrFromText2 = new InversionCounter()
        .file2Array("/Users/rachelcope/Desktop/Test/IntegerArray.txt");
    inputBeanus1216 = new InversionCounter().file2Array(
        "/Users/rachelcope/Desktop/AlgorithmsRepo/testCases/course1/assignment2Inversions/input_beaunus_12_16.txt");
    inputBeanus1216 = new InversionCounter().file2Array(
        "/Users/rachelcope/Desktop/AlgorithmsRepo/testCases/course1/assignment2Inversions/input_beaunus_12_16.txt");
    inputBeanus5410 = new InversionCounter().file2Array(
        "/Users/rachelcope/Desktop/AlgorithmsRepo/testCases/course1/assignment2Inversions/input_dgrcode_54_10.txt");
    oddArrI = new ArrayList<Long>(Arrays.asList(2L, 3L, 1L, 4L, 5L));

    test = new InversionCounter();

  }

  void testInversionCounter(Tester t) {
    this.initData();
//    t.checkExpect(test.inversionCounter(exampleArr1), 3L);
      t.checkExpect(test.inversionCounter(exampleArr2), 15L);
//    t.checkExpect(test.inversionCounter(exampleArr3), 0L);
//    t.checkExpect(test.inversionCounter(exampleArr4), 9L);
//    t.checkExpect(test.inversionCounter(exampleArr5), 8L);
//    t.checkExpect(test.inversionCounter(exampleArr6), 6L);
    
    
   

//   
//    t.checkExpect(test.inversionCounter(inputBeanus5410), 25L);
    
//  t.checkExpect(test.inversionCounter(exampleArrFromText2), 0); 
//  t.checkExpect(test.inversionCounter(exampleArrFromText), 0); 
  

  }

}
