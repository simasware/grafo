/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo2.dominio;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Implementação do Caixeiro viajante.
 * @author simasware
 */
public class CaixeiroViajante {
    private int[][] matriz = { {999,   23,  17,  34,  44,  19},
                               {23,  999,   20,  37,  81,  38},
                               {17,  20,  999,   17,  27,  18},
                               {34,  37,  17,  999,   10,  23},
                               {44,  31,  27,  10,  999,   31},
                               {19,  38,  18,  23,  31,  999}};
    private int tamanho;
    private final String alfabeto = "ABCDEFGHIJKLMONPQRSTUVWXYZ";    
    
    public CaixeiroViajante(Grafo g){
        this.tamanho = 6;//g.getVerticesCount();
        this.matriz = new int[tamanho][tamanho];
        /*for (Ligacao l : g.getLigacoes()) {
            int idOrigem = alfabeto.indexOf(l.getOrigem().getId());
            int idDestino = alfabeto.indexOf(l.getDestino().getId());            
            this.matriz[idOrigem][idDestino] = l.getValue();
        }*/
    }
    
    public int[][] getMatriz(){
        return this.matriz;
    }
    
    public void tracaRota(){
        Stack s = new Stack();
        int index = getMenorIndice();
        s.push(index);
        
        for (int i = 0; i < tamanho; i++){
            
        }
        
    }
    
    private int getMenorIndice(){
       int aux = Integer.MAX_VALUE;
        int indice = Integer.MAX_VALUE;
        for (int i = 0; i < this.tamanho; i++){
            for (int j = 0; j < this.tamanho; j++){
                if ( this.matriz[i][j] < aux || indice < i){
                    aux = this.matriz[i][j];
                    indice = i;
                }
            }
        }
        System.out.println("Menor valor e indice: " + aux + " "+ indice);
        return indice;
    }       
}
