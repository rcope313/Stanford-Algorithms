package algorithmsWeek4;

import java.util.ArrayList;

public class Node {
  
    public int id; 
    public ArrayList<Integer> connectedNodes;
    public boolean isContracted;
    public ArrayList<Integer> contractedNodes; 
       
    public Node (int id, ArrayList<Integer> connectedNodes, boolean isContracted, ArrayList<Integer> contractedNodes) {
      this.id = id;
      this.connectedNodes= connectedNodes;
      this.isContracted = isContracted;
      this.contractedNodes = contractedNodes; 
          
    }
    
    public Node () {
      
    }
  

}
