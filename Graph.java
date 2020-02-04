import java.util.*;
import java.io.*;

/** Clase que permite representar un grafo como una 
lista de adyacencias y dar un camino hamiltoneano.

* @author: Amin Arriaga 16-10072; Angel Garces 16-10400.
* @version: 01/02/2020
*/


//System.out.println( 1000+v );


class Graph{ 
    private int N;   					// Cantidad de nodos
    private int M;   					// Cantidad de aristas
    ArrayList<ArrayList<Integer> > adj;	// Lista de adyacencias
  
    public void buildGraph(String f) throws IOException{ 
    	String stringGraph="";
    	stringGraph = this.readGraph(f);
        String[] lines = stringGraph.split(System.getProperty("line.separator"));

        this.N = (int) Integer.parseInt( lines[0].trim() );
        this.M = (int) Integer.parseInt( lines[1].trim() );

        // Definimos el tamaño de la lista de adyacencias
        this.adj = new ArrayList<ArrayList<Integer> >(this.N); 
        for (int i = 0; i<this.N; i++)  this.adj.add(new ArrayList<Integer>()); 

        // Cargamos c/u de los edges en el grafo
        for(int i=2; i<this.M; i++){
            lines[i] = lines[i].trim();                 // Elimino espacios al principio y final
            String[] edges = lines[i].split( "\\s+" );  // Separo los numeros por espacio

            int v = Integer.parseInt( edges[0] ), w = Integer.parseInt( edges[1] );

            this.adj.get(v).add(w);
            this.adj.get(w).add(v);
        }

    } 

    public void printGraph(){
        for( int i=0; i<this.N; i++ ){
            System.out.print( i + " => " );
            for( int a : adj.get(i) ) System.out.print( a + " " );
            System.out.println(  );
        }
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