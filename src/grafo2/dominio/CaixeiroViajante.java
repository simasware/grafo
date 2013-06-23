/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo2.dominio;

/**
 * Implementação do Caixeiro viajante.
 * @author simasware
 */
public class CaixeiroViajante {
    private int[][] matriz;
    private int tamanho;
    private final String alfabeto = "ABCDEFGHIJKLMONPQRSTUVWXYZ";
    
    public CaixeiroViajante(Grafo g){
        this.tamanho = g.getVerticesCount();
        this.matriz = new int[tamanho][tamanho];
        for (Ligacao l : g.getLigacoes()) {
            int idOrigem = alfabeto.indexOf(l.getOrigem().getId());
            int idDestino = alfabeto.indexOf(l.getDestino().getId());            
            this.matriz[idOrigem][idDestino] = l.getValue();
        }
    }
    
    public void tracaRota(){
        
    }
}
