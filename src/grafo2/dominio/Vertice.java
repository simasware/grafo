package grafo2.dominio;

import java.awt.Color;

public class Vertice {

    private String id;
    private int posX = -1;
    private int posY = -1;
    private Color cor = Color.black; // black é vazio

    public Vertice(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }
}