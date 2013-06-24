/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo2;

import grafo2.dominio.CaixeiroViajante;
import grafo2.dominio.Grafo;
import grafo2.dominio.Main;
import grafo2.dominio.NearestNeighbor;
import grafo2.dominio.ProdutoMatricial;
import javax.swing.UIManager;

/**
 *
 * @author simasware
 */
public class Grafo2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CaixeiroViajante c = new CaixeiroViajante(new Grafo(true));
        NearestNeighbor n = new NearestNeighbor(c.getMatriz(), 2);
        int[] r = n.getPath();
        for (int i = 0; i < r.length; i++){
            System.out.println(r[i]);
        }
        
        /*try {
            UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        FormMain m = new FormMain();
        m.setVisible(true);*/
        
    }
}
