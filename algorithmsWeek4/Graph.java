package algorithmsWeek4;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import tester.Tester;

public class Graph {
  
  public ArrayList<Node> graph;
  
  public Graph (ArrayList<Node> graph) {
    this.graph = graph;
    
  }
  
  int minCutOfMutipleInstances (int numberOfOutputs) {
    
    ArrayList<Integer> outputsOfMinCut = new ArrayList<Integer>();
   
    for (int idx = 0; idx < numberOfOutputs; idx ++) {
      
      Graph tempGraph = this.replicateGraph(); 
      outputsOfMinCut.add(tempGraph.minCutOneInstance()); 
      
    }
  
    return new Utils().findSmallestNumber(outputsOfMinCut); 
     
  }
 
  int minCutOneInstance () {
        
    if (this.graph.size() <= 2) {
      Node aNode = this.graph.get(0);
      return aNode.connectedNodes.size() - 1; 
    }
    
    else {
      
      this.nodeContraction();
      return this.minCutOneInstance(); 
    
    }
  }
  
  void nodeContraction () {
    
    Edge edge = this.chooseRandomEdge();
    this.add_Connected_Nodes_From_Node2_to_Node1(edge);
    this.remove_Instances_of_Node2ID_From_Node1_ConnectedNodes(edge);
    this.remove_Instances_of_Node2_and_Node2IDs_From_Graph(edge);
    
  }
  
  Edge chooseRandomEdge () {
    
    Node node1 = this.graph.get(new Utils().randomGenerator(0, this.graph.size())); 
    int node2ID = node1.connectedNodes.get(new Utils().randomGenerator(1, node1.connectedNodes.size()));
    Node node2 = new Utils().find_Node_In_List_Of_Nodes_By_NodeID(this.graph, node2ID); 
    
    Edge edge = new Edge (node1, node2); 
    
    return edge; 
     
  }
  
  void add_Connected_Nodes_From_Node2_to_Node1 (Edge edge) {
    
    for (int idx = 0; idx < edge.node2.connectedNodes.size(); idx ++) {
      
      int currentNodeID = edge.node2.connectedNodes.get(idx); 
      
      if (currentNodeID != edge.node1.id &&
          currentNodeID != edge.node2.id) {
        
        edge.node1.connectedNodes.add(currentNodeID);      
      
      }
       
    }
    

    
  }
  
  void remove_Instances_of_Node2ID_From_Node1_ConnectedNodes (Edge edge) {
    
    int indexOfNode1ConnectedNodes = 0; 
    
    while (indexOfNode1ConnectedNodes < edge.node1.connectedNodes.size()) {
      
      if (edge.node1.connectedNodes.get(indexOfNode1ConnectedNodes) 
          == edge.node2.id) {
        
        edge.node1.connectedNodes.remove(indexOfNode1ConnectedNodes); 
        
      }
      
      else {
        
        indexOfNode1ConnectedNodes ++;  
        
      }
      
    }
    
    
  }
  
  void remove_Instances_of_Node2_and_Node2IDs_From_Graph (Edge edge) {
    
    this.graph.remove(edge.node2);
    
    edge.node1.isContracted = true; 
    edge.node1.contractedNodes.add(edge.node2.id);
    
    
    

    for (int idxGraph = 0; 
        idxGraph < this.graph.size(); 
        idxGraph ++) {
     
   
     
    
     for (int idxConnectedNodes = 0; 
         idxConnectedNodes < this.graph.get(idxGraph).connectedNodes.size();
         idxConnectedNodes ++) {
       
       Node currentNode = this.graph.get(idxGraph); 
       
       if (currentNode.connectedNodes.get(idxConnectedNodes) == edge.node2.id) {
         
         currentNode.connectedNodes.set(idxConnectedNodes, edge.node1.id);
          
       }
       
     
     }
    }
    
//    this.removeNodeAndInstancesOfNodeID(edge);
    
  }
     
  Graph replicateGraph () {
    
    Graph resultGraph = new Graph (new ArrayList<Node>()); 
    
    
    for (int idxGraph = 0; idxGraph < this.graph.size(); idxGraph ++) {
      
      int NodeID = this.graph.get(idxGraph).connectedNodes.get(0); 
      
      Node newNode = new Node(NodeID, new ArrayList<Integer>(), false, new ArrayList<Integer>());
      resultGraph.graph.add(newNode); 
      
      for (int idxNode = 0; idxNode < this.graph.get(idxGraph).connectedNodes.size(); idxNode ++) {
        
        int connectedNodeID = this.graph.get(idxGraph).connectedNodes.get(idxNode); 
        newNode.connectedNodes.add(connectedNodeID); 
        
      }
      
    }
    
    return resultGraph; 
    
  }
  
}
  


class Utils {
  
  int randomGenerator (int low, int high) {
    Random r = new Random(); 
    return r.nextInt(high-low) + low; 
    
  }
  
  int find_NodeIndex_In_List_Of_Corresponding_Nodes_By_NodeID (ArrayList<Integer> arrOfNodeIDs, int nodeID) {
    
    int indexOfNode = 0;  
    
    for (int idx = 0; idx < arrOfNodeIDs.size(); idx ++) {
      
      if (arrOfNodeIDs.get(idx) == nodeID) {
        indexOfNode = idx; 
        
      } 
    }
    
    return indexOfNode; 
    
  }
  
  Node find_Node_In_List_Of_Nodes_By_NodeID (ArrayList<Node> arrOfNodes, int nodeID) {
    
    Node resultNode = new Node(); 
    
    for (int idx = 0; idx < arrOfNodes.size(); idx ++) {
      
      if (arrOfNodes.get(idx).id == nodeID) {
        resultNode = arrOfNodes.get(idx); 
        
      } 
    }
    
    return resultNode; 
    
  }
  
  int findSmallestNumber (ArrayList<Integer> arr) {
    
    int smallestNumber = arr.get(0); 
    int smallestNumberSoFar = arr.get(0);
    
    for (int idx = 0; idx < arr.size(); idx ++) {
     
      int currentNumber = arr.get(idx);
      
      if (currentNumber < smallestNumberSoFar) {smallestNumberSoFar = currentNumber;}
      
    }
    
    smallestNumber = smallestNumberSoFar; 
    return smallestNumber; 
    
  }

}
     
class ExamplesNodeType {
    
  Node n1, n1_NodeContractionStep1, n1_NodeContractionStep2, n1_NodeContractionStep3, 
       n2, n2_NodeContractionStep3,
       n3, 
       n4, n4_NodeContractionStep3,
       n5,
       n6,
       n7,
       n10, n10_NodeContractionStep1a, n10_NodeContractionStep1b, n10_NodeContractionStep2a, n10_NodeContractionStep2b, n10_NodeContractionStep3a, n10_NodeContractionStep3b, n10copy,
       n11, n11_NodeContractionStep3a, n11_NodeContractionStep3b, 
       n12, 
       n13, n13_NodeContractionStep3a;
  
 
  Graph graph1, graph1_NodeContractionStep1, graph1_NodeContractionStep2, graph1_NodeContractionStep3,
        graph2,
        graph3,
        graph4, graph4_NodeContractionStep1a, graph4_NodeContractionStep2a, graph4_NodeContractionStep3a, graph4_NodeContractionStep1b, graph4_NodeContractionStep2b, graph4_NodeContractionStep3b,
        g1_6, g15_50, g39_200, gPA; 
  
  
  
  Edge e1_3, e2_4, e10_12, e10_13; 
  
  ArrayList<Integer> noContractedNodes; 
  
  FileToGraph f1_6, f15_50, f39_200, fPA; 
  

  void initData() {
    
    noContractedNodes = new ArrayList<Integer>(); 
    
    n1 = new Node (1, new ArrayList<Integer>(Arrays.asList(1,2,3)), false, noContractedNodes);
    n1_NodeContractionStep1 = new Node (1, new ArrayList<Integer>(Arrays.asList(1,2,3,4)), false, noContractedNodes);
    n1_NodeContractionStep2 = new Node (1, new ArrayList<Integer>(Arrays.asList(1,2,4)), false, noContractedNodes);
    n1_NodeContractionStep3 = new Node (1, new ArrayList<Integer>(Arrays.asList(1,2,4)), true, new ArrayList<Integer>(Arrays.asList(3)));
    
    n2 = new Node (2, new ArrayList<Integer>(Arrays.asList(2,1,4)), false, noContractedNodes);
    n2_NodeContractionStep3 = new Node (2, new ArrayList<Integer>(Arrays.asList(2,1,4)), false, noContractedNodes);

    n3 = new Node (3, new ArrayList<Integer>(Arrays.asList(3,1,4)), false, noContractedNodes);

    n4 = new Node (4, new ArrayList<Integer>(Arrays.asList(4,2,3)), false, noContractedNodes);
    n4_NodeContractionStep3 = new Node (4, new ArrayList<Integer>(Arrays.asList(4,2,1)), false, noContractedNodes);

    n5 = new Node (5, new ArrayList<Integer>(Arrays.asList(5,6,6)), false, noContractedNodes);
    
    n6 = new Node (6, new ArrayList<Integer>(Arrays.asList(6,5,5)), false, noContractedNodes);
    
    n7 = new Node (7, new ArrayList<Integer>(Arrays.asList(7)), false, noContractedNodes);
    
    n10 = new Node (10, new ArrayList<Integer>(Arrays.asList(10,11,12,13)), false, noContractedNodes);
    n10_NodeContractionStep1a = new Node (10, new ArrayList<Integer>(Arrays.asList(10,11,12,13,13)), false, noContractedNodes);
    n10_NodeContractionStep2a = new Node (10, new ArrayList<Integer>(Arrays.asList(10,11,13,13)), false, noContractedNodes);
    n10_NodeContractionStep3a = new Node (10, new ArrayList<Integer>(Arrays.asList(10,11,13,13)), true, new ArrayList<Integer>(Arrays.asList(12)));
    n10_NodeContractionStep1b = new Node (10, new ArrayList<Integer>(Arrays.asList(10,11,13,13,11)), true, new ArrayList<Integer>(Arrays.asList(12)));
    n10_NodeContractionStep2b = new Node (10, new ArrayList<Integer>(Arrays.asList(10,11,11)), true, new ArrayList<Integer>(Arrays.asList(12)));
    n10_NodeContractionStep3b = new Node (10, new ArrayList<Integer>(Arrays.asList(10,11,11)), true, new ArrayList<Integer>(Arrays.asList(12,13)));
    
    n11 = new Node (11, new ArrayList<Integer>(Arrays.asList(11,10,13)), false, noContractedNodes);
    n11_NodeContractionStep3a = new Node (11, new ArrayList<Integer>(Arrays.asList(11,10,13)), false, noContractedNodes);
    n11_NodeContractionStep3b = new Node (11, new ArrayList<Integer>(Arrays.asList(11,10,10)), false, noContractedNodes);
    
    n12 = new Node (12, new ArrayList<Integer>(Arrays.asList(12,10,13)), false, noContractedNodes);
    
    n13 = new Node (13, new ArrayList<Integer>(Arrays.asList(13,12,11,10)), false, noContractedNodes);
    n13_NodeContractionStep3a = new Node (13, new ArrayList<Integer>(Arrays.asList(13,10,11,10)), false, noContractedNodes);

    e1_3 = new Edge (n1, n3);
    e2_4 = new Edge (n2, n4);
    e10_12 = new Edge (n10, n12); 
    e10_13 = new Edge (n10, n13); 

    graph1 = new Graph (new ArrayList<Node>(Arrays.asList(n1,n2,n3,n4)));
    graph1_NodeContractionStep1 = new Graph (new ArrayList<Node>(Arrays.asList(n1_NodeContractionStep1,n2,n3,n4)));
    graph1_NodeContractionStep2 = new Graph (new ArrayList<Node>(Arrays.asList(n1_NodeContractionStep2,n2,n3,n4)));
    graph1_NodeContractionStep3 = new Graph (new ArrayList<Node>(Arrays.asList
        (n1_NodeContractionStep3,n2_NodeContractionStep3,n4_NodeContractionStep3)));

    graph2 = new Graph (new ArrayList<Node>(Arrays.asList(n5,n6)));
    
    graph3 = new Graph (new ArrayList<Node>(Arrays.asList(n7)));
    
    graph4 = new Graph (new ArrayList<Node>(Arrays.asList(n10,n11,n12,n13)));
    graph4_NodeContractionStep1a = new Graph (new ArrayList<Node>(Arrays.asList(n10_NodeContractionStep1a,n11,n12,n13)));
    graph4_NodeContractionStep2a = new Graph (new ArrayList<Node>(Arrays.asList(n10_NodeContractionStep2a,n11,n12,n13)));
    graph4_NodeContractionStep3a = new Graph (new ArrayList<Node>(Arrays.asList
        (n10_NodeContractionStep3a,n11_NodeContractionStep3a,n13_NodeContractionStep3a)));
    graph4_NodeContractionStep1b = new Graph (new ArrayList<Node>(Arrays.asList
        (n10_NodeContractionStep1b,n11_NodeContractionStep3a,n13_NodeContractionStep3a)));
    graph4_NodeContractionStep2b = new Graph (new ArrayList<Node>(Arrays.asList
        (n10_NodeContractionStep2b,n11_NodeContractionStep3a,n13_NodeContractionStep3a)));
    graph4_NodeContractionStep3b = new Graph (new ArrayList<Node>(Arrays.asList
        (n10_NodeContractionStep3b,n11_NodeContractionStep3b)));

    
    f1_6 = new FileToGraph("/Users/rachelCope/Desktop/AlgorithmsRepo/testCases/course1/assignment4MinCut/input_random_1_6.txt");
    f15_50 = new FileToGraph("/Users/rachelCope/Desktop/AlgorithmsRepo/testCases/course1/assignment4MinCut/input_random_15_50.txt");
    f39_200 = new FileToGraph("/Users/rachelCope/Desktop/AlgorithmsRepo/testCases/course1/assignment4MinCut/input_random_39_200.txt");
    fPA =  new FileToGraph("/Users/rachelcope/Desktop/KragerMinCut.txt");
    
    g1_6 = f1_6.convertGraphFromFile();
    g15_50 = f15_50.convertGraphFromFile();
    g39_200 = f39_200.convertGraphFromFile();
    gPA = fPA.convertGraphFromFile();
    
    
    
  }

  
  void testMinCutMultipleInstances (Tester t) {
    this.initData();
//    t.checkExpect(graph1.minCutOfMutipleInstances(10), 2);
//    t.checkExpect(graph4.minCutOfMutipleInstances(10), 2); 
//    t.checkExpect(g1_6.minCutOfMutipleInstances(50), 2);
//    t.checkExpect(g15_50.minCutOfMutipleInstances(100), 14);
//    t.checkExpect(g39_200.minCutOfMutipleInstances(200), 51); 
    t.checkExpect(gPA.minCutOfMutipleInstances(211928), 0);
 
  }
  
  void testMinCutOneInstance (Tester t) {
    this.initData();
    t.checkExpect(graph2.minCutOneInstance(), 2);
    t.checkExpect(graph3.minCutOneInstance(), 0);
    t.checkExpect(graph1.minCutOneInstance(), 2); 
//    t.checkExpect(g1_6.minCutOneInstance(), 2);
//    t.checkExpect(graph4.minCutOneInstance(), 2);

    
  }
  
  void testReplicateGraphs (Tester t) {
    this.initData();
    t.checkExpect(graph1.replicateGraph(), graph1); 
  }
  
  void test_NodeContractionStep1 (Tester t) {
    this.initData();
    graph1.add_Connected_Nodes_From_Node2_to_Node1(e1_3); 
    t.checkExpect(graph1, graph1_NodeContractionStep1); 
    this.initData();
    graph4.add_Connected_Nodes_From_Node2_to_Node1(e10_12);
    t.checkExpect(graph4, graph4_NodeContractionStep1a); 

  }
  
  void test_NodeContractionStep2 (Tester t) {
    this.initData();
    graph1.add_Connected_Nodes_From_Node2_to_Node1(e1_3); 
    graph1.remove_Instances_of_Node2ID_From_Node1_ConnectedNodes(e1_3); 
    t.checkExpect(graph1, graph1_NodeContractionStep2); 
    this.initData();
    graph4.add_Connected_Nodes_From_Node2_to_Node1(e10_12);
    graph4.remove_Instances_of_Node2ID_From_Node1_ConnectedNodes(e10_12);
    t.checkExpect(graph4, graph4_NodeContractionStep2a); 
    
    
  }
  
  void test_NodeContractionStep3 (Tester t) {
    this.initData();
    graph1.add_Connected_Nodes_From_Node2_to_Node1(e1_3); 
    graph1.remove_Instances_of_Node2ID_From_Node1_ConnectedNodes(e1_3); 
    graph1.remove_Instances_of_Node2_and_Node2IDs_From_Graph(e1_3);
    t.checkExpect(graph1, graph1_NodeContractionStep3);
    this.initData();
    graph4.add_Connected_Nodes_From_Node2_to_Node1(e10_12); 
    graph4.remove_Instances_of_Node2ID_From_Node1_ConnectedNodes(e10_12); 
    graph4.remove_Instances_of_Node2_and_Node2IDs_From_Graph(e10_12);
    t.checkExpect(graph4, graph4_NodeContractionStep3a);
    
  }
  
  void testRemoveSelfLoops (Tester t) {
    this.initData();
    graph4.add_Connected_Nodes_From_Node2_to_Node1(e10_12); 
    graph4.remove_Instances_of_Node2ID_From_Node1_ConnectedNodes(e10_12); 
    graph4.remove_Instances_of_Node2_and_Node2IDs_From_Graph(e10_12);
    t.checkExpect(graph4, graph4_NodeContractionStep3a);
    graph4.add_Connected_Nodes_From_Node2_to_Node1(e10_13);
    t.checkExpect(graph4, graph4_NodeContractionStep1b);
    graph4.remove_Instances_of_Node2ID_From_Node1_ConnectedNodes(e10_13);
    t.checkExpect(graph4, graph4_NodeContractionStep2b);
    graph4.remove_Instances_of_Node2_and_Node2IDs_From_Graph(e10_13);
    t.checkExpect(graph4, graph4_NodeContractionStep3b);

    
  }
 
  void testFindNodeIndexInListOfCorrespondingNodes(Tester t) {
    this.initData(); 
    t.checkExpect(new Utils().find_NodeIndex_In_List_Of_Corresponding_Nodes_By_NodeID(new ArrayList<Integer>(Arrays.asList(2,1,4)), 1), 1); 
    t.checkExpect(new Utils().find_NodeIndex_In_List_Of_Corresponding_Nodes_By_NodeID(new ArrayList<Integer>(Arrays.asList(2,1,4)), 4), 2);
    
  }
   
  void testfindNodeByNodeID(Tester t) {
    this.initData(); 
    t.checkExpect(new Utils().find_Node_In_List_Of_Nodes_By_NodeID(new ArrayList<Node>(Arrays.asList(n1,n2,n3)), 1), n1); 
    t.checkExpect(new Utils().find_Node_In_List_Of_Nodes_By_NodeID(new ArrayList<Node>(Arrays.asList(n1,n2,n4)), 4), n4);
    
  }
  
  void testFindSmallestNumber (Tester t) {
    t.checkExpect(new Utils().findSmallestNumber(new ArrayList<Integer>(Arrays.asList(6,7,8,9,3,4,5,2,67))), 2); 
    t.checkExpect(new Utils().findSmallestNumber(new ArrayList<Integer>(Arrays.asList(100,66,79,5000,40029,35))), 35); 
  }
  
  
  
}



