// Imports utilities for works with
import java.util.Arrays;
import java.util.LinkedList;

/** 
 * Class to apply DFS method to a graph.  
 */
public class BFS {

    // Global Variables
    private int[][] grafo;  // Stores the graph.
    private boolean quieroCiclo;    // Stores conditions.

    /**
     * Init instance of DFS class. Saves a pointer to the graph and condition.
     * @param grafo
     * @param quieroCiclo
     */
    BFS(Grafo grafo, boolean quieroCiclo){
    
        this.grafo = grafo.getGrafo();
        this.quieroCiclo = quieroCiclo;

    }

    /**
     * Method to apply BFS in the graph.
     * @param quieroCiclo
     */
    public void applyBFS(boolean quieroCiclo){

         // Iterate along graph vertexes.
        for(int i=0; i<this.grafo.length; i++){
        
            // Checks exceptions for Hamiltonean way.
            try{
                this.BFS_visit(i);
            } catch (Exception e){
                 if(e.getMessage().equals("Camino Encontrado")){
                   System.out.println("Wuju");
                   System.exit(0);
               }
           }
        }
        // If program didn't find a way, there is no way.
        System.out.println("No existe camino");
    }

    /**
    * Checks if the way made by BFS is an hamilton way or cicle
    * @param vertex
    * @throws Exception
    */
    private boolean Chequeo(boolean quieroCiclo,int[] camino){

        // Variable that have that contain if the way found by BFS is hamiltonian
        boolean hamiltoniano = true;

        // Iteration that checks if the sides of the wave are in the graph
        for(int i=0;i<camino.length - 1;i++){
            if(this.grafo[camino[i]][camino[i + 1]] != 1){
                hamiltoniano = false;
            }
        }

        // Checks if the last vertex and the first one are adjacent
        if(hamiltoniano && quieroCiclo){
            if (this.grafo[camino[0]][camino[this.grafo.length - 1]] != 1){
                hamiltoniano = false;
            }    
        }
        return hamiltoniano;
        
    }
    
    /**
     * Applies DFS algorithms over an specific vertex.
     * @param vertice
     * @throws Exception
     */
    public void BFS_visit(int vertice) throws Exception{

        System.out.println("Recorrido desde "+vertice+":");

        int v= this.grafo.length;  // Variable that has the number of vertexes
        boolean visitado[]=new boolean[v]; // Array that contains if an vertex have been visited before
        LinkedList<Integer> queue=new LinkedList<Integer>(); // List to put discovered vertexes to consider their adjacents
        queue.add(vertice); // Add the first in the queue
        int[] camino = new int[this.grafo.length];  // Array that contains the way to be considered
        int[] identacion = new int[v];  // Array used to know how much identation has every vertex for the output
        int contador = 0;   // Variable used to iterate camino array
        
        String salida = ""; // Output
        String space = "  ";    // Output's identation
        
        identacion[vertice] = 1;
        visitado[vertice]=true;
        
        // While used to consider all vertexes until there is no one that haven't been visited
        while(queue.size()!=0)
        {   
            // Remove vertex in the head of the queue
            int x=queue.remove();

            // Add vertex to the output
            salida += x + "-";
            camino[contador] = x;
            contador += 1;

            // Find all the adjacencies to the vertex
            for (int i=0; i < v; i++) 
            {   
                // If the adj vertex haven't been visited, its added to queue
                if((this.grafo[x][i] == 1) && (!visitado[i]))
                {  
                    queue.add(i);
                    identacion[i]=identacion[x] + 1;
                    for(int j=0; j<identacion[x]; j++){
                        System.out.print(space);
                    }
                    System.out.println(x + "-" + i);
                    
                    visitado[i]=true;
                } else if((this.grafo[x][i] == 1) && (visitado[i])){
                    for(int j=0; j<identacion[x]; j++){
                        System.out.print(space);
                    }
                    System.out.println(x + "-" + i + " ya visitado");

                }
            }
        }

        // If the way given by bfs is an hamilton way, the program stops and print the way.
        if(Chequeo(this.quieroCiclo, camino)){
            if (this.quieroCiclo){
                System.out.print("Ciclo encontrado: ");
                System.out.println(salida.substring(0,salida.length() - 1));
                System.out.println("El ciclo tiene "+ this.grafo.length + " vertices.");
                throw new Exception("Camino Encontrado");       
            } else{
                System.out.print("Camino encontrado: ");
                System.out.println(salida.substring(0,salida.length() - 1));
                System.out.println("El camino tiene "+ this.grafo.length + " vertices.");
                throw new Exception("Camino Encontrado");
            }
        }
    }
}