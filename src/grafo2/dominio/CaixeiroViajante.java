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
    private int[] caminho;
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
    
    public CaixeiroViajante(int tamanho, int matriz[][]){
        this.tamanho = tamanho;
        this.matriz  = matriz;        
    }

    public int[][] getMatriz() {
        return this.matriz;
    }

    public void tracaRota(int inicial){
        NearestNeighbor n = new NearestNeighbor(matriz, inicial);
        this.caminho = n.getPath();        
    }
    
    public void tracaRota(Vertice inicial) {
        NearestNeighbor n = new NearestNeighbor(matriz, alfabeto.indexOf(inicial.getId()));
        this.caminho = n.getPath();        
    }    

    public int[] getCaminho(){
        return this.caminho;
    }
    
    private boolean isVerticeVisitada(int vertice, int[] visitados){       
        for (int i = 0; i < visitados.length; i++){
            if (visitados[i] == vertice){
                return true;
            }
        }        
        return false;
    }
    
    private int getMin(int vertice) {
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
