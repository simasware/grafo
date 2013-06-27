/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo2.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Implementação do Caixeiro viajante.
 *
 * @author simasware
 */
public class CaixeiroViajante {

    private int[][] matriz;
    private int tamanho;
    private final String alfabeto = "ABCDEFGHIJKLMONPQRSTUVWXYZ";

    public CaixeiroViajante(Grafo g) {
        this.tamanho = g.getVerticesCount();
        this.matriz = new int[tamanho][tamanho];
        for (Ligacao l : g.getLigacoes()) {
            int idOrigem = alfabeto.indexOf(l.getOrigem().getId());
            int idDestino = alfabeto.indexOf(l.getDestino().getId());
            this.matriz[idOrigem][idDestino] = l.getValue();
        }
    }

    public int[][] getMatriz() {
        return this.matriz;
    }

    public void tracaRota(Vertice inicial) {
        NearestNeighbor n = new NearestNeighbor(matriz, alfabeto.indexOf(inicial.getId()));
        int[] caminho = n.getPath();
        for (int i = 0; i < caminho.length; i++){
            System.out.print( alfabeto.charAt(caminho[i]) + "->" );
        }
        /*Stack s = new Stack();
        int[] caminho = new int[tamanho];
        int i = 0;
        List<Vertice> visitados = new ArrayList<>();
        int index = getMin(alfabeto.indexOf(inicial.getId()));
        visitados.add(inicial);
        s.push(index);

        //enquanto todos os vértices não forem visitados...
        while (i < this.tamanho){
            index = getMin(index);
            if ( !isVerticeVisitada(index, caminho)){
                caminho[i] = index;
                i++;
            }            
        }
        
        for (int j = 0; j < caminho.length; j++){
            System.out.print( alfabeto.charAt(j) + "->" );
        }*/
    }    

    public boolean isVerticeVisitada(int vertice, int[] visitados){
        
        for (int i = 0; i < visitados.length; i++){
            if (visitados[i] == vertice){
                return true;
            }
        }
        
        return false;
    }
    
    public int getMin(int vertice) {
        int aux = Integer.MAX_VALUE;
        int indice = -1;
        for (int i = 0; i < tamanho; i++) {
            //System.out.printf("matriz[%d][%d] - aux[%d]\n", vertice, i, aux);
            if (this.matriz[vertice][i] > 0 && this.matriz[vertice][i] < aux) {
                aux = this.matriz[vertice][i];
                indice = i;
            }
        }

        return indice;
    }
}
