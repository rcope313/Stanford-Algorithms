package algorithmsWeek4;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import algorithmsWeek4.Graph;
import algorithmsWeek4.Node;
import tester.Tester;

class FileToGraph {
  
  String filePath;
  
  FileToGraph (String filePath) {
    this.filePath = filePath; 
    
  }
  
  FileToGraph() {
    
  }
  

  Graph convertGraphFromFile () {
    
    ArrayList<ArrayList<Integer>> arrOfArr = this.convertArrOfArrFromFile (); 
    Graph graph = this.convertArrOfArrtoGraph(arrOfArr);
    
    return graph; 
    
    
  }
  
  ArrayList<ArrayList<Integer>> convertArrOfArrFromFile () {
    
    ArrayList<ArrayList<Integer>> arrOfArr = new ArrayList<ArrayList<Integer>>(); 
    Scanner s; 
  
    
    try {
      
      s = new Scanner (new FileReader(this.filePath));
      
      while (s.hasNextLine()) {
        
        String line = s.nextLine(); 
        ArrayList<Integer> arr = this.convertStringToIntArr(line);
        arrOfArr.add(arr); 
        
      }
      
      return arrOfArr; 
      
      
    }
    
    catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return arrOfArr;
      
    }

  }
  
  ArrayList<ArrayList<Integer>> converArrfArrFromFile_v2() {
    
      BufferedReader reader; 
      ArrayList<ArrayList<Integer>> arrOfArr = new ArrayList<ArrayList<Integer>>(); 
      
      try {
        
        reader = new BufferedReader(new FileReader(this.filePath));
        
        String line = reader.readLine();
        
             
        while (line != null) {
          
          ArrayList<Integer> arr = this.convertStringToIntArr(line);
          arrOfArr.add(arr); 
          line = reader.readLine();
        
        }
        
        reader.close();
      } 
      
      catch (IOException e) {e.printStackTrace();}
      
      return arrOfArr; 
    
  }
  
  Graph convertArrOfArrtoGraph (ArrayList<ArrayList<Integer>> arrOfArr) {
    
    Graph resultGraph = new Graph (new ArrayList<Node>()); 
    
    for (ArrayList<Integer> arr : arrOfArr) {
      
      resultGraph.graph.add(new FileToGraph().convertArrToNode(arr)); 
      
    }
    
    return resultGraph; 
    
    
    
  }
  
  ArrayList<Integer> convertStringToIntArr (String lineOfInt) {
    
    ArrayList<Integer> resultArr = new ArrayList<Integer>(); 
    int idxOfString = 0;
    int idxToComputeIntLength = 1; 
    
    while (idxOfString < lineOfInt.length() ) {
      
      if (idxToComputeIntLength >= lineOfInt.length()) {
        
        int newInt = Integer.parseInt(lineOfInt.substring(idxOfString)); 
        resultArr.add(newInt);
        
        return resultArr; 
        
      }
      
      String nextChar = String.valueOf(lineOfInt.charAt(idxToComputeIntLength));
      
      if (nextChar.equals(" ") || nextChar.equals("\t")) {
        
        int newInt = Integer.parseInt(lineOfInt.substring(idxOfString, idxToComputeIntLength)); 
        resultArr.add(newInt); 
        
        idxOfString = idxToComputeIntLength + 1;   
        idxToComputeIntLength = idxOfString + 1;   
        
      }
      
      else { idxToComputeIntLength ++; }
        
    }
    
    return resultArr; 
   
  }
  
  Node convertArrToNode (ArrayList<Integer> arr) {
    
    Node resultNode = new Node (arr.get(0), new ArrayList<Integer>(), false, new ArrayList<Integer>()); 
    
    for (int idx = 0; idx < arr.size(); idx ++) {
      
      resultNode.connectedNodes.add(arr.get(idx)); 
      
    }
    
    return resultNode; 
    
  }
  
}

class ExamplesFiletoGraphs {

  FileToGraph f1, f2, f3, f15_50, fPA, test; 
  ArrayList<Integer> arr0, arr1, arr2; 
  ArrayList<ArrayList<Integer>> arrOfArr1; 
  Node nA, nB, n1, n2, n3, n4, n5, n6; 
  Graph g1, g2, gPA; 
  
 
  void initData () {
     
    f1 = new FileToGraph("/Users/rachelcope/Desktop/easyArray.txt");  
    f2 = new FileToGraph("/Users/rachelcope/Desktop/firstGraph.txt");
    f3 = new FileToGraph("/Users/rachelCope/Desktop/AlgorithmsRepo/testCases/course1/assignment4MinCut/input_random_1_6.txt");
    f15_50 = new FileToGraph("/Users/rachelCope/Desktop/AlgorithmsRepo/testCases/course1/assignment4MinCut/input_random_15_50.txt");
    fPA =  new FileToGraph("/Users/rachelcope/Desktop/KragerMinCut.txt");


    
    test = new FileToGraph(); 
    
    arr0 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
    arr1 = new ArrayList<Integer>(Arrays.asList(1,2)); 
    arr2 = new ArrayList<Integer>(Arrays.asList(2,1));
    
    arrOfArr1 = new ArrayList<ArrayList<Integer>>(Arrays.asList(arr1, arr2)); 
    
    nA = new Node (1, new ArrayList<Integer>(Arrays.asList(1,2)), false, new ArrayList<Integer>()); 
    nB = new Node (2, new ArrayList<Integer>(Arrays.asList(2,1)), false, new ArrayList<Integer>()); 
    n1 = new Node (1, new ArrayList<Integer>(Arrays.asList(1,2,3,4)), false, new ArrayList<Integer>()); 
    n2 = new Node (2, new ArrayList<Integer>(Arrays.asList(2,1,5,6,4)), false, new ArrayList<Integer>()); 
    n3 = new Node (3, new ArrayList<Integer>(Arrays.asList(3,1,5)), false, new ArrayList<Integer>()); 
    n4 = new Node (4, new ArrayList<Integer>(Arrays.asList(4,2,1,5)), false, new ArrayList<Integer>()); 
    n5 = new Node (5, new ArrayList<Integer>(Arrays.asList(5,2,3,4,6)), false, new ArrayList<Integer>()); 
    n6 = new Node (6, new ArrayList<Integer>(Arrays.asList(6,2,5)), false, new ArrayList<Integer>()); 
    
    
    g1 = new Graph (new ArrayList<Node>(Arrays.asList(nA, nB))); 
    g2 = new Graph (new ArrayList<Node>(Arrays.asList(n1, n2, n3, n4, n5, n6)));
    gPA = fPA.convertGraphFromFile();
    

    
  }
    
//  void testConvertArrToNode (Tester t) {
//    this.initData();
//    t.checkExpect(test.convertArrToNode(arr1), nA); 
//    t.checkExpect(test.convertArrToNode(arr2), nB);
//    
//  }
//  
//  void testConvertArrofArrToGraph (Tester t) {
//    this.initData();
//    t.checkExpect(test.convertArrOfArrtoGraph(arrOfArr1), g1); 
//  }
//
//  void testConvertArrOfArrFromFile (Tester t) {
//    this.initData();
//    t.checkExpect(f2.convertArrOfArrFromFile(), arrOfArr1); 
//  }
//  
//  void testConvertStringToIntArr (Tester t) {
//    this.initData();
//    t.checkExpect(test.convertStringToIntArr("10 1 2 13 4"), new ArrayList<Integer>(Arrays.asList(10,1,2,13,4))); 
//  
//  }
 
  void testConvertGraphFromFile (Tester t) {
    this.initData();
//    t.checkExpect(f2.convertGraphFromFile(), g1); 
//    t.checkExpect(f3.convertGraphFromFile(), g2); 
    t.checkExpect(gPA, 0); 
   
 
  }
  
  
 
}


