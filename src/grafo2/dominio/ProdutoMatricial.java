/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo2.dominio;

/**
 *
 * @author simasware
 */
public class ProdutoMatricial {
    private int[][] matrixA;
    private int[][] matrixB;
    private int tamanhoMatriz;
    
    public ProdutoMatricial(int tamanhoMatriz){
        this.matrixA = new int[tamanhoMatriz][tamanhoMatriz];
        this.matrixB = new int[tamanhoMatriz][tamanhoMatriz];
        this.tamanhoMatriz = tamanhoMatriz;
    }
    
    public void preencheRandomA(){
        for (int i = 0; i < tamanhoMatriz; i++){
            for (int j = 0; j < tamanhoMatriz; j++){
                this.matrixA[i][j] = 2 * i + j;
            }
        }
    }
    
    public void preencheRandomB(){
        for (int i = 0; i < tamanhoMatriz; i++){
            for (int j = 0; j < tamanhoMatriz; j++){
                this.matrixB[i][j] = 2 * i + j;
            }
        }
    }
    
    public void printA(){
        for (int i = 0; i < tamanhoMatriz; i++){
            for (int j = 0; j < tamanhoMatriz; j++){
                System.out.print( matrixA[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }
    
    public void printB(){
        for (int i = 0; i < tamanhoMatriz; i++){
            for (int j = 0; j < tamanhoMatriz; j++){
                System.out.print( matrixB[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }
    
    public int calculaProdutoAlgebrico(int aCol, int aRow){
        /*
         * for int i = 0; i < la; i++
         *      for int j = 0; j < cb; j++;
         *          for int k = 0; k < ca; k++;
         *              c[i,j] = c[i][j] + a[i][k] * b[k,j];
         */
        int aux = 0;
        for (int i = 0; i < this.tamanhoMatriz; i++ ){
            aux += this.matrixA[aRow][i] * this.matrixA[i][aRow];
        }
        return aux;
    }
    
    public int calculaProdutoBooleano(){
        return 0;
    }
}
