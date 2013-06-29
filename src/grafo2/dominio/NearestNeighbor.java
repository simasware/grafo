/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo2.dominio;

/**
 * Classe que implementa a heurística do vizinho mais próximo (nearest neighbor)
 * @author simasware
 */
public class NearestNeighbor {

    private int[] path; //array do melhor caminho

    public NearestNeighbor(final int[][] distanceMatrix, final int startCity) {

        path = new int[distanceMatrix[0].length]; //inicia o caminho

        //preencho o caminho inicialmente com o infinito
        //pois dava problema com valor 0 ao iniciar com uma cidade diferente de A
        for (int i = 0; i < path.length; i ++){
            path[i] = Integer.MAX_VALUE;
        }
        
        //a cidade inicial é a escolhida pelo usuário.     
        path[0] = startCity;
        int currentCity = startCity;

        // enquanto não houver cidades a serem visitadas         
        int i = 1;
        while (i < path.length) {
            // encontro a próxima cidade
            int nextCity = findMin(distanceMatrix[currentCity]);
            // se a cidade for diferente de - 1 (significa que a mesma não foi visitada)
            if (nextCity != -1) {
                // adiciono a cidade ao caminho
                path[i] = nextCity;
                // atualizo a cidade atual e o contador
                currentCity = nextCity;
                i++;
            }
        }
    }
       
    private int findMin(int[] row) {
        //encontro o menor custo
        //na linha passada por parametro (int[] row)
        int proximaCidade = -1;
        int i = 0;
        int min = Integer.MAX_VALUE;

        while (i < row.length) {
            //se a cidade não está no caminho e o custo dela for menor que min
            //min passa a ser o custo de row[i] e a proxima cidade passa a ser i
            if (!isCityInPath(path, i) && row[i] < min) {
                min = row[i];
                proximaCidade = i;
            }
            i++;
        }
        return proximaCidade;
    }

    //retorna o array com o caminho
    public int[] getPath() {
        return path;
    }

    //verifica se a cidade está no caminho (int[] path)
    public boolean isCityInPath(int[] path, int city) {
        for (int i = 0; i < path.length; i++) {
            if (path[i] == city) {
                return true;
            }
        }
        return false;
    }
}
