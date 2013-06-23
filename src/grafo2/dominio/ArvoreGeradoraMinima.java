/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo2.dominio;

/**
 *
 * @author simasware
 */
public class ArvoreGeradoraMinima {

    private int matriz[][];
    private int tamanho;
    private final String alfabeto = "ABCDEFGHIJKLMONPQRSTUVWXYZ";

    public ArvoreGeradoraMinima(Grafo g) {
        this.tamanho = g.getVerticesCount();
        this.matriz = new int[tamanho][tamanho];
        for (Ligacao l : g.getLigacoes()) {
            int idOrigem = alfabeto.indexOf(l.getOrigem().getId());
            int idDestino = alfabeto.indexOf(l.getDestino().getId());            
            this.matriz[idOrigem][idDestino] = l.getValue();
        }
    }

    public int[][] getMatriz() {
        //aqui eu zero a matriz para utilizar somente o que está abaixo da diagonal principal.        
        return this.matriz;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public Grafo getArvoreGeradoraMinima() {
        Grafo g = new Grafo(true);
        //implementa a Árvore Geradora Minima através do Algoritmo de Prim      
        int[] parent = new int[tamanho]; // Array para armazenar a árvore.
        int[] key = new int[tamanho];   // Array para armazenar os valores minimos das arestas
        boolean[] mstSet = new boolean[tamanho];  // Array para armazenar o conjuto de vértices qeu não foram incluídos na árvore

        // inicializo os valores com o infinito
        for (int i = 0; i < this.tamanho; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // sempre incluo o primeiro vértice a árvore
        key[0] = 0;     // atribuo como zero para ser o primeiro vértice selecionado.
        parent[0] = -1; // primeiro nó é a raiz da árvore.

        // 
        for (int i = 0; i < this.tamanho - 1; i++) {
            // pego o menor valor que não esteja incluida na árvore            
            int u = minKey(key, mstSet);

            // adiciono o valor no conjunto da árvore
            mstSet[u] = true;
            
            for (int v = 0; v < this.tamanho; v++) 
            // graph[u][v] is non zero only for adjacent vertices of m
            // mstSet[v] é falso para vértices não inclusos na AGM.
            // Atualiza a chave somente se graph[u][v] for menor que key[v]
            {
                if ( this.matriz[u][v] != 0 && mstSet[v] == false && this.matriz[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = this.matriz[u][v];
                }
            }
        }
        
        //boolean primeiro = true;
        for (int i = 1; i < parent.length; i++){
            if (g.getVerticeById(String.valueOf(alfabeto.charAt(parent[i]))) == null){
                g.addVertice(new Vertice(String.valueOf(alfabeto.charAt(parent[i]))));
            }
            if (g.getVerticeById(String.valueOf(alfabeto.charAt(i))) == null){
                g.addVertice(new Vertice(String.valueOf(alfabeto.charAt(i))));
            }
            g.addLigacao(new Vertice(String.valueOf(alfabeto.charAt(parent[i]))), 
                         new Vertice(String.valueOf(alfabeto.charAt(i))), 
                         matriz[i][parent[i]]);
            /*if (parent[i] == 0 && primeiro){
                primeiro = false;
            } else {
                break;
            }*/
            //System.out.printf("%d - %d = %d\n",parent[i], i, matriz[i][parent[i]]);
        }
        return g;
    }

    private int minKey(int key[], boolean mstSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE;
        int min_index = 0;

        for (int v = 0; v < this.tamanho; v++) {
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }        
        return min_index;
    }

    public boolean isMatrizZerada() {
        for (int i = 0; i < this.tamanho; i++) {
            for (int j = 0; j < this.tamanho; j++) {
                if (this.matriz[i][j] > 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
