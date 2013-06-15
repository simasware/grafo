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
    private String alfabeto = "ABCDEFGHIJKLMONPQRSTUVWXYZ";    
    
    public ArvoreGeradoraMinima(Grafo g){
        this.tamanho = g.getLigacoesCount();
        this.matriz = new int[tamanho][tamanho];
        for (Ligacao l : g.getLigacoes()) {
            int idOrigem  = alfabeto.indexOf(l.getOrigem().getId());
            int idDestino = alfabeto.indexOf(l.getOrigem().getId());
            this.matriz[idOrigem][idDestino] = l.getValue();
        }
    }
    
    public int[][] getMatriz(){
        return this.matriz;
    }
    
    public int getTamanho(){
        return this.tamanho;
    }
}
