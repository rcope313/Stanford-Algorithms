package algorithmsWeek4;
import java.util.ArrayList;
import java.util.Arrays;

import tester.Tester;

public class AdjacencyList_InitVersion {
  
  ArrayList<Vertice> vertices;
  ArrayList<Edge_Init> edges; 
  
  AdjacencyList_InitVersion (ArrayList<Vertice> vertices, ArrayList<Edge_Init> edges) {
    this.vertices = vertices;
    this.edges = edges;
    
  }
  
}

class Vertice {
  
  ArrayList<Edge_Init> edges;
  
  Vertice () {
    this.edges = new ArrayList<Edge_Init>(); 
    
  }
 
}

class Edge_Init {
  
  Vertice endVertice1;
  Vertice endVertice2; 
  
  Edge_Init (Vertice endVertice1, Vertice endVertice2) {
    this.endVertice1 = endVertice1; 
    this.endVertice2 = endVertice2; 
    
    this.addEdgeToCorrespondingVertice(endVertice1);
    this.addEdgeToCorrespondingVertice(endVertice2);
     
  }
  
  Edge_Init (Vertice endVertice1) {
    this.endVertice1 = endVertice1;
    
    this.addEdgeToCorrespondingVertice(endVertice1);
  
  }
  
  void addEdgeToCorrespondingVertice (Vertice n) {
    n.edges.add(this); 
  }
  
  
}

class ExamplesAdjacencyLists {
   
  Vertice n1, n2; 
  Edge_Init m1;
  AdjacencyList_InitVersion al1; 
  
  ArrayList<ArrayList<Integer>> graph1; 
  ArrayList<Integer> v1, v2, v3, v4, v5, v6, v7, v8;
  
 
  void initDat() {
    
    
    n1 = new Vertice(); 
    n2 = new Vertice();
    m1 = new Edge_Init(n1, n2);
    al1 = new AdjacencyList_InitVersion(new ArrayList<Vertice>(Arrays.asList(n1, n2)), new ArrayList<Edge_Init>(Arrays.asList(m1)));  
    
    
    v1 = new ArrayList<Integer>(Arrays.asList(1,2,5,6));
    v2 = new ArrayList<Integer>(Arrays.asList(2,1,5,6,3));
    v3 = new ArrayList<Integer>(Arrays.asList(3,2,7,4,8));
    v4 = new ArrayList<Integer>(Arrays.asList(4,3,7,8));
    v5 = new ArrayList<Integer>(Arrays.asList(5,1,2,6));
    v6 = new ArrayList<Integer>(Arrays.asList(6,5,1,2,7));
    v7 = new ArrayList<Integer>(Arrays.asList(7,6,3,4,8));
    v8 = new ArrayList<Integer>(Arrays.asList(8,7,3,4));
    
    graph1 = new ArrayList<ArrayList<Integer>>(Arrays.asList(v1, v2, v3, v4, v5, v6, v7, v8)); 
    
    
  }
  
  void testDataStructure(Tester t) {
    this.initDat();
    t.checkExpect(n1.edges, new ArrayList<Edge_Init>(Arrays.asList(m1))); 
    t.checkExpect(n2.edges, new ArrayList<Edge_Init>(Arrays.asList(m1))); 
    
  }
  
  
  
  
  
  
}
