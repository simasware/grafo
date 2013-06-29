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
}
