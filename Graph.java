import java.util.*;
import java.io.*;

/** Clase que permite representar un grafo como una 
lista de adyacencias y dar un camino hamiltoneano.

* @author: Amin Arriaga 16-10072; Angel Garces 16-10400.
* @version: 01/02/2020
*/
class Graph{ 
    private int N;   					// Cantidad de nodos
    private int M;   					// Cantidad de aristas
    ArrayList<ArrayList<Integer> > adj[];	// Lista de adyacencias
  
    void buildGraph(String f){ 
    	String stringGraph="";
    	try{
    		stringGraph = this.readGraph(f);
   		}catch(Exception e){}
        String[] lines = stringGraph.split(System.getProperty("line.separator"));

        //this.N = (int) Integer.parseInt(lines[0]);
        //this.M = (int) Integer.parseInt(lines[1]);

        System.out.println( stringGraph );
        //System.out.println( lines[1] );

        // Definimos el tamaño de la lista de adyacencias
        ArrayList<ArrayList<Integer> > adj = new ArrayList<ArrayList<Integer> >(this.N); 
        for (int i = 0; i<this.N; i++) 
            adj.add(new ArrayList<Integer>()); 

    } 


    public String readGraph(String f)throws IOException { 

        FileReader file = new FileReader(f);
        StringBuilder graph = new StringBuilder();

        // Leemos el archivo caracter por caracter.
        int nextChar;
        while ((nextChar = file.read()) != -1){
            graph.append((char) nextChar);
        }
        file.close();

        return String.valueOf(graph);
    }

 }