/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo2.dominio;

/**
 *
 * @author simasware
 */
public class NearestNeighbor {

    private int[] path;    
   
    public NearestNeighbor(final int[][] distanceMatrix, final int startCity) {

        path = new int[distanceMatrix[0].length];

        path[0] = startCity;
        int currentCity = startCity;

        /**
         * until there are cities that are not yet been visited
         */
        int i = 1;
        while (i < path.length) {
            // find next city
            int nextCity = findMin(distanceMatrix[currentCity]);
            // if the city is not -1 (meaning if there is a city to be visited
            if (nextCity != -1) {
                // add the city to the path
                path[i] = nextCity;
                // update currentCity and i
                currentCity = nextCity;
                i++;
            }
        }
    }
    
    private int findMin(int[] row) {

        int nextCity = -1;
        int i = 0;
        int min = Integer.MAX_VALUE;

        while (i < row.length) {
            if (isCityInPath(path, i) == false && row[i] < min) {
                min = row[i];
                nextCity = i;
            }
            i++;
        }
        return nextCity;
    }

    /**
     * @return the array that contains the path
     */
    public int[] getPath() {
        return path;
    }

    public boolean isCityInPath(int[] path, int city) {
        for (int i = 0; i < path.length; i++) {
            if (path[i] == city) {
                return true;
            }
        }
        return false;
    }
}
