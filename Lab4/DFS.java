// Imports utilities for works with
import java.util.ArrayList;
import java.util.Arrays;

/** 
 * Class to apply DFS method to a graph.  
 */
public class DFS {

  // Global Variables
  private int[][] grafo; // Stores the graph.
  private String[] colors; // Stores colors for DFS method.
  private int[] father; // Store antecesor of each vertex.
  private ArrayList<Integer> recorridoActual; // Stores the current way. 
  private String space = ""; // Stores indetation
  private boolean quieroCiclo = false; // Stores conditions.

  /**
   * Init instance of DFS class. Saves a pointer to the graph.
   * @param grafo
   */
  DFS(Grafo grafo){

    System.out.println("DFS Instance");
    this.grafo = grafo.getGrafo();

  }

  /**
   * Method to apply DFS in the graph.
   * @param quieroCiclo // Specifies if the user wants a cycle.
   */
  public void applyDFS(boolean quieroCiclo){

    // Saves the value of quieroCiclo globally in the class.
    this.quieroCiclo = quieroCiclo;
    
    // Initializes colors and father array.
    this.colors = new String[this.grafo[0].length];
    this.father = new int[this.grafo[0].length];

    // Iterate along graph vertexes.
    for (int i = 0; i < this.grafo[0].length; i ++){

      // Fill array with default values.
      Arrays.fill(this.colors, "W");
      Arrays.fill(this.father, -1);

      // Start a way.
      if (colors[i].equals("W")){

        System.out.println("Recorrido desde " + i + ": ");

        // Initialice identation and currentWay.
        this.space = "  ";
        this.recorridoActual = new ArrayList<Integer>();

        // Checks exceptions for Hamiltonean way.
        try {

          this.DFS_visit(i);

        } catch (Exception e) {
          
          if (e.getMessage().equals("Camino Encontrado")){
            System.exit(0);
          } else {
            System.out.println("Ha ocurrido un error desconocido.");
            System.exit(0);
          }

        }   

      }
    }

    // If program didn't find a way, there is no way.
    System.out.println("No existe camino");

  }

  /**
   * Applies DFS algorithms over an specific vertex.
   * @param vertex
   * @throws Exception
   */
  public void DFS_visit(int vertex) throws Exception{

    // Changes the current vertex color.
    this.colors[vertex] = "N";

    //Adds vertex to current way.
    this.recorridoActual.add(vertex);

    // Checks if current way has all the vertexes.
    if (this.recorridoActual.size() == this.grafo[0].length){

      if (quieroCiclo == true){ // Checks if the user wants cicle.
        if (this.chequeaCiclo() == true){
          
          this.printResult("ciclo");

          // Throws exception to end program.
          throw new Exception("Camino Encontrado");

        }
      } else { // Prints the first way found.

        this.printResult("");

        // Throws exception to end program.
        throw new Exception("Camino Encontrado");
      }
    }

    // Gets sucesors of the current vertex.
    ArrayList<Integer> sucesores = adyacentes(vertex);

    // Iterate over sucesors.
    for (int j = 0; j < sucesores.size(); j++){

      // Checks if the vertex has been visited.
      if (this.colors[sucesores.get(j)].equals("W")){

        // Prints current visit
        System.out.println(this.space + vertex + "-" + sucesores.get(j));
        this.father[sucesores.get(j)] = vertex;
        this.space += "  ";

        // Go over sucesors of the current sucesor.
        DFS_visit(sucesores.get(j));

        // Goes back and deletes the last element to go over another sucessor.
        this.recorridoActual.remove(this.recorridoActual.size() - 1);

        
      } else {

        // Prints current visit already visited.
        System.out.println(this.space + vertex + "-" + sucesores.get(j) + " ya visitado");
      }
    } 

    // Quits indentation.
    this.space = this.space.substring(0, this.space.length() - 2);

  }

  /**
   * Method that returns al vertex adjacencies. 
   * @param vertex
   * @return
   */
  private ArrayList<Integer> adyacentes(int vertex){

    // Initialices List.
    ArrayList<Integer> adj = new ArrayList<Integer>();

    // Checks adjacencies matrix.
    for (int k = 0; k < this.grafo[0].length; k++){
      if (this.grafo[vertex][k] == 1 && k != vertex){
        adj.add(k);
      }
    }   

    return adj;

  }

  /**
   * Cheks if there is an edge between the first and the last element.
   * @return
   */
  private boolean chequeaCiclo(){

    // Gets the current vertexes.
    int initialVertex = this.recorridoActual.get(0);
    int finalVertex = this.recorridoActual.get(this.recorridoActual.size() - 1);

    if (this.grafo[initialVertex][finalVertex] == 1){
      return true;
    }

    return false;
  }

  private void printResult(String type){

    String camino;
    if (type.equals("ciclo")){
      camino = "Ciclo encontrado: ";
    } else {
      camino = "Camino encontrado: ";
    }

    // Adds al vertexes in current way to the string.
    for (int i = 0; i < this.recorridoActual.size(); i++){
      camino += this.recorridoActual.get(i) + "-";
    }

    if (type.equals("ciclo")){
      camino += this.recorridoActual.get(0);
    } else {
      camino = camino.substring(0, camino.length() - 1);
    }

    // Print user information.
    System.out.println(camino);
    
    if (type.equals("ciclo")){
      System.out.println("El ciclo tiene " + this.recorridoActual.size() + " vertices.");
    } else {
      System.out.println("El camino tiene " + this.recorridoActual.size() + " vertices.");
    }

  }


}