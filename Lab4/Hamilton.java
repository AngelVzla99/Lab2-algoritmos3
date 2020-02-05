import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Class with the main to find a hamilton way in a graph
 */
public class Hamilton {
  /**
   * Method that charges graph from a file to an adjacency matrix
   * @param nombreArchivo
   * @return
   * @throws IOException
   * @throws Exception
   */
  static Grafo cargarGrafo(String nombreArchivo) throws IOException, Exception{
    
    // Instance of an graph
    Grafo grafo = new Grafo();

    try {
      // Read file
      BufferedReader Lector = new BufferedReader(new FileReader(nombreArchivo));
      
      // Read first line
      String linea = Lector.readLine();
      
      // Take number of vertex from file
      int vertices = Integer.parseInt(linea);
      // Read next line
      linea = Lector.readLine();
      // Take number of edges from file
      int aristas = Integer.parseInt(linea); 
      
      // Initialize graph with vertexes and edges
      grafo = new Grafo(vertices, aristas);
      
      // Makes the adjacency matrix using the edges of the file
      for(int i=0; i < aristas; i++){
        linea = Lector.readLine();

        String[] verticesArista = linea.split(" ");
        int vertice1 = Integer.parseInt(verticesArista[0]);
        int vertice2 = Integer.parseInt(verticesArista[1]);

        // Checks if there aren't two edges with the same vertexes, or if the file doesn't exist
        try {
          grafo.agregarArista(vertice1, vertice2);
        } catch (Exception e) {
          if (e.getMessage() == "Arista Repetida"){
            System.out.println("No se puede cargar grafos con aristas repetidas.");
            System.exit(0);
          }
        }
      }

    } catch (FileNotFoundException e) {
      System.out.println("El archivo especificado no existe, por favor, introduzca un archivo válido.");
      System.exit(0);
    }

    return grafo;

  }
  /**
   * Main of the program 
   * How to use: java Hamilton <nombreArchivo> <BFS|DFS> <cycle>
   * @param args
   * @throws IOException
   * @throws Exception
   */
  public static void main(String[] args) throws IOException, Exception {

    String mensajeError = "Uso: java Hamilton <nombreArchivo> <BFS|DFS> <cycle>";

    // Checks if there are not arguments and print error if so
    if(args.length < 1){
      System.err.println(mensajeError);
      return;
    } else {
      
      // Load graph from file
      Grafo g = cargarGrafo(args[0]);
      
      boolean quieroCiclo = false;

      // If there are more than two arguments, checks if the third is to find a cycle
      if (args.length > 2){
        if (args[2].equals("cycle")) {
          quieroCiclo = true;
        }
      }
      
      // Check if the user wants to use BFS or DFS, if not prints error
      if (args[1].equals("BFS")){
        // Instance of BFS
        BFS bfs = new BFS(g, quieroCiclo);
        // Using BFS method
        bfs.applyBFS(quieroCiclo);
      } else if(args[1].equals("DFS")){
        // Instance of DFS
        DFS dfs = new DFS(g);
        // Using DFS method
        dfs.applyDFS(quieroCiclo);
      } else {
        System.out.println(mensajeError);
      }

    }
  }

}