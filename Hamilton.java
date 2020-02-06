import java.util.*;
import java.io.*;

/** Clase que implementa las clases "Grafo" para retonar 
un recorrido Hamiltoneado en un grafo dado.

* @author: Amin Arriaga 16-10072; Angel Garces 16-10400.
* @version: 01/02/2020
*/
class Hamilton{
    public static void main(String[] args)throws IOException{

        Graph G = new Graph();
        G.buildGraph( args[0] );
        //G.printGraph();

        boolean visit[] = new boolean[G.getN()]; // Arreglo de los visitados inicialisado en false
        ArrayList<Integer> camino = new ArrayList<Integer>(); // En esta lista se guardara el camino    

        if( args[1].equals("DFS") ){
            // Hago DFS comenzando en c/u de los nodos
            for(int i=0; i<G.getN() && !G.getFind(); i++){
                System.out.println("Recorrido desde "+i+":");
                G.DFS(i,camino,visit);
                if( !G.getFind() ) G.printNoValid(i);
            }
            if( !G.getFind() ) System.out.println("No existe camino");
        }else if( args[1].equals("BFS") ){
            // Hago BFS comenzando en c/u de los nodos
            for(int i=0; i<G.getN() && !G.getFind(); i++){
                System.out.println("Recorrido desde "+i+":");
                G.BFS(i,visit);
            }
            if( !G.getFind() ) System.out.println("No existe camino");    
        }else{
            System.out.println(args[1]+" no es un posible recorrido");
        }
    }
}