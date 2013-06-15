/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo2.dominio;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author simasware
 */
public class TabelaLigacoes extends AbstractTableModel {
    private String[] colunas = {"De", "Para", "Valor da Ligação"};
    private Object dados[][];
    private int rowCount;
    private int colCount;
    
    public TabelaLigacoes(int rowCount){
        this.rowCount = rowCount;
        this.colCount = 3;
        this.dados = new Object[rowCount][3];
    }
    
    
    @Override
    public int getRowCount() {
        return this.rowCount;
    }

    @Override
    public int getColumnCount() {
        return this.colCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.dados[rowIndex][columnIndex];
    }
    
    public void setValueAt(int rowIndex, int columnIndex, Object data){
        System.out.println("[" + rowIndex + "][" + columnIndex + "] = " + data);
        this.dados[rowIndex][columnIndex] = data;
    }
    
    public String[] getColunas(){
        return this.colunas;
    }
    
    public Object[][] getDados(){
        return this.dados;
    }
}
