package algorithmsWeek4;

import java.util.ArrayList;

public class AdjacencyMatrix {
  
  private boolean adjMatrix[][];
  private int numVertices;

  // Initialize the matrix
  public AdjacencyMatrix(int numVertices) {
    this.numVertices = numVertices;
    adjMatrix = new boolean[numVertices][numVertices];
  }

  // Add edges
  public void addEdge(int i, int j) {
    adjMatrix[i][j] = true;
    adjMatrix[j][i] = true;
  }

  // Remove edges
  public void removeEdge(int i, int j) {
    adjMatrix[i][j] = false;
    adjMatrix[j][i] = false;
  }
}

//class AdjacencyMatrix_v2 {
//  
//  private ArrayList<ArrayList<Boolean>> adjMatrix;
//  private int numVertices;
//
//  // Initialize the matrix
//  public AdjacencyMatrix_v2(int numVertices) {
//    this.numVertices = numVertices; 
//    adjMatrix = new ArrayList<ArrayList<Boolean>>(); 
//  }
//
//  // Add edges
//  public void addEdge(int i, int j) {
//    adjMatrix.get(i).get(j) = true;  
//  }
//
//  // Remove edges
//  public void removeEdge(int i, int j) {
//    adjMatrix.get(i).get(j) = false;
//  }
//}
