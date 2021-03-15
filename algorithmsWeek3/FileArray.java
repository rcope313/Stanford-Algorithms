package algorithmsWeek3;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import tester.Tester;

public class FileArray {
  
  String filePath;
  
  public FileArray (String filePath) {
    this.filePath = filePath; 
    
  }
  
  

  public ArrayList<Integer> createsArrayFromFile () {
    
    ArrayList<Integer> array = new ArrayList<Integer>();
    Scanner s;

    try {

      s = new Scanner(new FileReader(filePath));

      while (s.hasNext()) {
      array.add(s.nextInt());
      }

      return array;

    }

    catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return array; 
    }

  }

}

class ExamplesFiles {

  FileArray exampleFile; 
  ArrayList<Integer> exampleArray; 
  
  void initData () {
     
    exampleFile = new FileArray("/Users/rachelcope/Desktop/Test/easyArray.txt"); 
    exampleArray = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
    
  }
    
    
    void testCreatesArrayFromFile (Tester t) {
      this.initData();
      t.checkExpect(exampleFile.createsArrayFromFile(), exampleArray); 
      
      
    
  

  }
  
}


