package grafo2.dominio;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JPanelGrafo extends JPanel implements MouseListener {

    private Grafo grafo;
    private Vertice verticeSelecionada = null;
    private final int diametroVertice = 50;
    private final int raioVertice = diametroVertice / 2;
    private final String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private boolean exibirMelhoRota = false;
    private boolean definirValoresLigacoes = false;

    public JPanelGrafo() {
        this.grafo = new Grafo(false);
        this.setBackground(Color.white);
        this.addMouseListener(this);
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public void setDefinirValoresLigacoes(boolean definirValoresLigacoes) {
        this.definirValoresLigacoes = definirValoresLigacoes;
    }

    public boolean getDefinirValoresLigacoes() {
        return definirValoresLigacoes;
    }

    public void setExibirMelhoRota(boolean exibirMelhoRota) {
        this.exibirMelhoRota = exibirMelhoRota;
    }

    public boolean getExibirMelhoRota() {
        return exibirMelhoRota;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // imprimo as ligações
        for (Ligacao vPes : getGrafo().getLigacoes()) {
            if (exibirMelhoRota) {
                g2d.setColor(Color.red);
            } else {
                g2d.setColor(Color.black);
            }

            if (getGrafo().isDirecionado()) {
                g2d.setStroke(new BasicStroke(1));
            } else {
                g2d.setStroke(new BasicStroke(2));
            }

            g2d.drawLine(vPes.getOrigem().getPosX(), vPes.getOrigem().getPosY(), vPes.getDestino().getPosX(), vPes.getDestino().getPosY());

            // aqui eu imprimo as pontas de flechas, se direcionado
            if (getGrafo().isDirecionado()) {
                for (int i = -5; i < 6; i++) {
                    g2d.drawLine(vPes.getOrigem().getPosX() + i, vPes.getOrigem().getPosY() + i, vPes.getDestino().getPosX(), vPes.getDestino().getPosY());
                    g2d.drawLine(vPes.getOrigem().getPosX() + i, vPes.getOrigem().getPosY(), vPes.getDestino().getPosX(), vPes.getDestino().getPosY());
                    g2d.drawLine(vPes.getOrigem().getPosX(), vPes.getOrigem().getPosY() + i, vPes.getDestino().getPosX(), vPes.getDestino().getPosY());
                }
            }
            
            // imprimindo os valores das ligações
            if (getDefinirValoresLigacoes()) {
                int metadeX = Math.abs(vPes.getOrigem().getPosX() - vPes.getDestino().getPosX()) / 2;
                int metadeY = Math.abs(vPes.getOrigem().getPosY() - vPes.getDestino().getPosY()) / 2;
                int pontoX;
                int pontoY;
                
                if (vPes.getOrigem().getPosX() < vPes.getDestino().getPosX()) {
                    pontoX = vPes.getOrigem().getPosX() + metadeX;
                } else {
                    pontoX = vPes.getOrigem().getPosX() - metadeX;
                }
                
                if (vPes.getOrigem().getPosY() < vPes.getDestino().getPosY()) {
                    pontoY = vPes.getOrigem().getPosY() + metadeY;
                } else {
                    pontoY = vPes.getOrigem().getPosY() - metadeY;
                }                
                g2d.setColor(Color.black);
                g2d.setFont(new Font("Arial", 0, 18));
                g2d.drawString(String.valueOf(vPes.getValue()), pontoX +15, pontoY +15);
            }
        }

        // imprimo as vertices e os textos
        for (Vertice vPes : getGrafo().getVertices()) {
            g2d.setStroke(new BasicStroke(2));
            if (vPes.getCor() == Color.black) {
                g2d.setColor(Color.white);
            } // if black the neutro (branco)
            else {
                g2d.setColor(vPes.getCor());
            }
            g2d.fillOval(vPes.getPosX() - raioVertice, vPes.getPosY() - raioVertice, diametroVertice, diametroVertice);

            g2d.setColor(Color.black);
            g2d.drawOval(vPes.getPosX() - raioVertice, vPes.getPosY() - raioVertice, diametroVertice, diametroVertice);
            g2d.setFont(new Font("Arial", 1, 30));
            g2d.drawString(vPes.getId(), vPes.getPosX() - 10, vPes.getPosY() + 10);
        }

        // imprime informações do vértice selecionado
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Arial", 1, 15));
        if (verticeSelecionada != null) {
            g2d.drawString("Selecionado vértice \"" + verticeSelecionada.getId() + "\"", 5, 15);
        }

        g2d.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // limpando
        getGrafo().limpaCoresVertices();
        exibirMelhoRota = false;

        // adicona vértice, clique do direito
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (getGrafo().getVerticeByPos(e.getX(), e.getY(), raioVertice) == null) { // para não criar uma vértice em cima da outra
                String s = "";
                if (getGrafo().getVertices().size() < alfabeto.length()) {
                    for (int i = 0; i <= alfabeto.length(); i++) {
                        Vertice v = getGrafo().getVerticeById(String.valueOf(alfabeto.charAt(i)));
                        if (v == null) {
                            s = String.valueOf(alfabeto.charAt(i));
                            break;
                        }
                    }
                } else {
                    s = String.valueOf(JOptionPane.showInputDialog("Digite o ID do vértice:"));
                    s = s.trim().toUpperCase();
                }

                Vertice v = new Vertice(s);
                v.setPosX(e.getX());
                v.setPosY(e.getY());
                getGrafo().addVertice(v);
            }
        }

        // seleciona/move/liga vértice, clique do meio
        if (e.getButton() == MouseEvent.BUTTON2) {
            if (verticeSelecionada == null) {
                verticeSelecionada = getGrafo().getVerticeByPos(e.getX(), e.getY(), raioVertice);
            } else {
                Vertice v = getGrafo().getVerticeByPos(e.getX(), e.getY(), raioVertice);
                if (v == null) {
                    // clicou no vazio e quer mover
                    verticeSelecionada.setPosX(e.getX());
                    verticeSelecionada.setPosY(e.getY());
                } else {
                    // clicou numa vértice e quer ligar
                    int val = 0;
                    
                    if (getDefinirValoresLigacoes()) {
                        String s = String.valueOf(JOptionPane.showInputDialog("Digite o valor da ligação:"));
                        s = s.trim().toUpperCase();
                        try {
                            val = Integer.valueOf(s);
                        } catch (Exception ee) { 
                            val = 0;
                        }
                    }

                    getGrafo().addLigacao(verticeSelecionada, v, val);
                }
                verticeSelecionada = null;
            }
        }

        // apaga vértice, clique da direita
        if (e.getButton() == MouseEvent.BUTTON3) {
            verticeSelecionada = getGrafo().getVerticeByPos(e.getX(), e.getY(), raioVertice);
            if (verticeSelecionada != null) {
                getGrafo().removeVertice(verticeSelecionada);
                verticeSelecionada = null;
            }
        }

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }
}
