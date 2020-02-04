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
        G.printGraph();

    }
}