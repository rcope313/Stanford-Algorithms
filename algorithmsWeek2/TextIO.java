package algorithmsWeek2;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import tester.Tester;
 
class Text2Array {
  
  
  ArrayList<Integer> methodName (String filePath) {
    
    ArrayList<Integer> result = new ArrayList<>(); 
    Scanner s;
    
    try {
      s = new Scanner (new FileReader(filePath));

        
      while (s.hasNext()) {
        result.add(s.nextInt());
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


class TestExamples {
  
  ArrayList<Integer> arr1; 
  
  void initData() {
    
    arr1 = new ArrayList<Integer>(Arrays.asList(12,34,56,78)); 
    
  }
  
  void testText2Array (Tester t) {
   this.initData();
   t.checkExpect(new Text2Array().methodName("/Users/rachelcope/Desktop/Test/output.txt"), arr1); 
  }
}




