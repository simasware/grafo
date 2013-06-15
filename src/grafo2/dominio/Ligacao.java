package grafo2.dominio;

public class Ligacao {

    private Vertice origem;
    private Vertice destino;
    private int value;

    public Ligacao(Vertice origem, Vertice destino, int value) {
        this.origem = origem;
        this.destino = destino;
        this.value = value;
    }

    public Vertice getOrigem() {
        return origem;
    }

    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
