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
    private boolean find = false;       // Indica si se encontro un camino
  
    // -----------------------------------------

    public void buildGraph(String f) throws IOException{ 
    	String stringGraph = this.readGraph(f);
        String[] lines = stringGraph.split(System.getProperty("line.separator"));

        // Cargamos la cantidad de vertices y lados al grafo
        this.N = (int) Integer.parseInt( lines[0].trim() );
        this.M = (int) Integer.parseInt( lines[1].trim() );

        // Definimos el tamaño de la lista de adyacencias
        this.adj = new ArrayList<ArrayList<Integer> >(this.N); 
        for (int i = 0; i<this.N; i++)  this.adj.add(new ArrayList<Integer>()); 

        // Cargamos c/u de los edges en el grafo
        for(int i=2; i<this.M+2; i++){
            lines[i] = lines[i].trim();                 // Elimino espacios al principio y final
            String[] edges = lines[i].split( "\\s+" );  // Separo los numeros por espacio

            int v = Integer.parseInt( edges[0] ), w = Integer.parseInt( edges[1] );

            this.adj.get(v).add(w);
            this.adj.get(w).add(v);
        }
    } 

    // ------------- DFS y BFS -----------------

    // # por que eres asi Java ?, dejame pasar por valor
    public void DFS(int n, ArrayList<Integer> caminoR, boolean visitR[]) throws IOException{

        // Copia de parametros para simular un paso por valor a la funcion
        boolean visit[] = Arrays.copyOf(visitR, visitR.length);
        ArrayList<Integer> camino = new ArrayList<Integer>(caminoR);

        // Se marca el nodo como visto y se carga en el camino actual
        visit[n] = true;
        camino.add(n);

        // Encontre un camino Hamiltoneado
        if( camino.size() == this.N ){
            this.find = true;
            this.printPath( camino );
        }

        // Visito a todos los vecinos del nodo n
        for(int v: adj.get(n)) if( !this.find && !visit[v]) this.DFS(v, camino, visit);
    }

    public void BFS(int n, boolean visit[]){
        // Creamos una cola y un arreglo, donde la posicion i tiene 
        // el camino mas largo que termina en i
        LinkedList<Integer> queue = new LinkedList<Integer>(); 
        ArrayList<ArrayList<Integer> > camino = new ArrayList<ArrayList<Integer> >(this.N);
        for (int i = 0; i<this.N; i++) camino.add(new ArrayList<Integer>()); 
  
        // Marcar el actual nodo como visidato y colocarlo en la cola
        visit[n]=true;
        queue.add(n);
  
        while(queue.size() != 0) { 
            int v = queue.poll(); 
            System.out.print(v+" "); 
            LinkedList<Integer> caminoV = new LinkedList<Integer>( camino.get(v) ); 

            System.out.println("Elemento de la pila "+v);

            for( int w: adj.get(v) ){
                System.out.println("-----hijo "+w);
                if( !visit[w] ){
                    visit[w] = true;
                    queue.add(w); 
                    // camino[w] = camino[v] U <v,w>
                    LinkedList<Integer> caminoTemp = new LinkedList<Integer>( caminoV ); 
                    caminoTemp.add(w); // Camino que va de v hasta w

                    this.conectar(camino, caminoTemp);
                }else{ // (v,w) genera un ciclo
                    LinkedList<Integer> caminoTemp = new LinkedList<Integer>( camino.get(v) ); 
                    LinkedList<Integer> caminoTemp2 = new LinkedList<Integer>( camino.get(w) ); 
                    Collections.reverse(caminoTemp2);
                    for(int z: caminoTemp2) caminoTemp.add(z);

                    if(caminoTemp.size()>0) this.conectar(camino, caminoTemp);
                }
            }
        }

        // Print caminos
        for(int i=0; i<this.N; i++){
            System.out.print(i+" => ");
            for(int v: camino.get(i)) System.out.print(v+" ");
            System.out.println();
        }
    }

    //------------------------------------------

    public Integer getN(){ return this.N; }
    public boolean getFind(){ return this.find; }

    public void conectar(ArrayList<ArrayList<Integer> > caminos, LinkedList<Integer> c1){


        // Cadena desde sC1 hasta eC1
        int eC1 = c1.get( c1.size()-1 );
        if( caminos.get(eC1).size() < c1.size()  ){
            caminos.get(eC1).clear(); 
            for(int z: c1) caminos.get(eC1).add(z);

            System.out.print("Camino a agregar => ");
            for(int z: c1) System.out.print(z + " ");
            System.out.println();
        }

        
        // Cadena inversa
        Collections.reverse(c1);
        eC1 = c1.get( c1.size()-1 );
        if( caminos.get(eC1).size() < c1.size()  ){
            caminos.get(eC1).clear(); 
            for(int z: c1) caminos.get(eC1).add(z);

            System.out.print("Camino a agregar => ");
            for(int z: c1) System.out.print(z + " ");
            System.out.println();
        }
    }

    public void printPath(ArrayList<Integer> camino){
        System.out.print("Camino encontrado: ");
        for(int i=0; i<camino.size(); i++){
            int v = camino.get(i);
            if( i<camino.size()-1 ) System.out.print( v + "-" );
            else System.out.print( v );
        }
        System.out.println();
        System.out.println("El camino tiene "+ camino.size() + " vertines");
    }

    public void printGraph(){
        System.out.println( "----- Grafo -----" );
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