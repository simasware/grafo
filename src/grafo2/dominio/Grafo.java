package grafo2.dominio;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Grafo {

    private boolean direcionado;
    private List<Vertice> vertices = new ArrayList<>();
    private List<Ligacao> ligacoes = new ArrayList<>();
    private final Color[] cores = {Color.red, Color.blue, Color.green, Color.yellow, Color.cyan, Color.pink, Color.gray, Color.magenta, Color.orange};

    public Grafo(boolean direcionado) {
        this.direcionado = direcionado;
    }

    // -------------------------------------------------------------------------
    public List<Ligacao> getLigacoes() {
        return ligacoes;
    }

    public List<Ligacao> getLigacoesVertice(Vertice v, boolean somenteOrigem) {
        List<Ligacao> result = new ArrayList<>();
        for (Ligacao vLig : getLigacoes()) {
            if ((vLig.getOrigem().getId().equalsIgnoreCase(v.getId()))
                    || (!somenteOrigem && (vLig.getDestino().getId().equalsIgnoreCase(v.getId())))) {
                result.add(vLig);
            }
        }
        return result;
    }

    public Ligacao getLigacao(Vertice ori, Vertice des) {
        for (Ligacao vLig : getLigacoes()) {
            if (vLig.getOrigem().getId().equalsIgnoreCase(ori.getId())) {
                if (vLig.getDestino().getId().equalsIgnoreCase(des.getId())) {
                    return vLig;
                }
            }
        }
        return null;
    }

    public int getLigacaoValue(Vertice ori, Vertice des) {
        Ligacao vLig = getLigacao(ori, des);
        if (vLig != null) {
            return vLig.getValue();
        } else {
            return -1;
        }
    }

    public boolean hasLigacaoDireta(Vertice ori, Vertice des) {
        return (getLigacao(ori, des) != null);
    }

    public boolean hasLigacaoIndireta(Vertice ori, Vertice des) {
        return hasLigacaoIndireta(ori, des, null);
    }

    private boolean hasLigacaoIndireta(Vertice vOrigem, Vertice vDestino, List<Vertice> testados) {
        if (testados == null) {
            testados = new ArrayList<>();
        }

        for (Ligacao vTeste : getLigacoesVertice(vOrigem, true)) {
            if (!testados.contains(vTeste.getDestino())) {
                testados.add(vTeste.getDestino());
                if (vTeste.getDestino() == vDestino) {
                    return true;
                } else {
                    if (hasLigacaoIndireta(vTeste.getDestino(), vDestino, testados)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean addLigacao(Vertice ori, Vertice des, int val) {
        if ((!hasVertice(ori)) || (!hasVertice(des)) || (hasLigacaoDireta(ori, des)) || (val < 0)) {
            return false;
        }

        getLigacoes().add(new Ligacao(ori, des, val));

        // se não é direcionado, faz a ligação nas duas pontas para garantir ida e volta
        if (!isDirecionado()) {
            getLigacoes().add(new Ligacao(des, ori, val));
        }

        return true;
    }

    public boolean removeLigacao(Ligacao l) {
        return getLigacoes().remove(l);
    }

    public boolean removeLigacoesVertice(Vertice v, boolean somenteOrigem) {
        boolean result = false;
        List<Ligacao> vLig = getLigacoesVertice(v, somenteOrigem);
        for (int i = vLig.size() - 1; i >= 0; i--) {
            removeLigacao(vLig.get(i));
            result = true;
        }
        return result;
    }

    public boolean removeLigacao(Vertice ori, Vertice des) {
        return removeLigacao(getLigacao(ori, des));
    }

    public int getLigacoesCount() {
        int count = getLigacoes().size();

        if (isDirecionado()) {
            return count;
        } else {
            return count / 2; // é dividido por dois porque as ligacoes (not direcionadas) sempre vão e voltam
        }
    }

    public void clearLigacoes() {
        for (int i = getLigacoes().size() - 1; i >= 0; i--) {
            removeLigacao(getLigacoes().get(i));
        }
    }

    // -------------------------------------------------------------------------
    public List<Vertice> getVertices() {
        return vertices;
    }

    public Vertice getVerticeById(String id) {
        for (Vertice vPes : getVertices()) {
            if (vPes.getId().equalsIgnoreCase(id)) {
                return vPes;
            }
        }
        return null;
    }

    public Vertice getVerticeByPos(int x, int y, int raio) {
        // eu pego o vertice que está mais proximo dos pontos passados e dentro do raio
        Vertice result = null;
        for (Vertice vPes : getVertices()) {
            // verificando se está dentro do raio            
            if ((((x <= vPes.getPosX()) && (vPes.getPosX() - raio <= x))
                    || ((x >= vPes.getPosX()) && (vPes.getPosX() + raio >= x)))
                    && (((y <= vPes.getPosY()) && (vPes.getPosY() - raio <= y))
                    || ((y >= vPes.getPosY()) && (vPes.getPosY() + raio >= y)))) {
                if (result == null) {
                    result = vPes;
                } else {
                    // verificando se está mais próximo que o anterior
                    if ((Math.abs(vPes.getPosX() - raio) + Math.abs(vPes.getPosY() - raio))
                            < (Math.abs(result.getPosX() - raio) + Math.abs(result.getPosY() - raio))) {
                        result = vPes;
                    }
                }
            }
        }
        return result;
    }

    public boolean hasVertice(Vertice v) {
        if (getVerticeById(v.getId()) == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addVertice(Vertice v) {
        if (!hasVertice(v)) {
            return getVertices().add(v);
        } else {
            return false;
        }
    }

    public boolean removeVertice(String v) {
        for (Vertice vLig : getVertices()) {
            if (vLig.getId().equalsIgnoreCase(v)) {
                return removeVertice(vLig);
            }
        }
        return false;
    }

    public boolean removeVertice(Vertice v) {
        boolean rem = getVertices().remove(v);
        removeLigacoesVertice(v, false);
        return rem;
    }

    public void clearVertices() {
        for (int i = getVertices().size() - 1; i >= 0; i--) {
            removeVertice(getVertices().get(i));
        }
    }

    public void limpaCoresVertices() {
        for (Vertice vPes : getVertices()) {
            vPes.setCor(Color.black);
        }
    }

    public void defineCoresVertices() {        
        limpaCoresVertices();

        // vou vertice a vertice
        for (Vertice vPes : getVertices()) {
            Color cor = Color.black;

            // testo conforme a sequencia de cores qual a que pode ser utilizada
            for (Color co : cores) {
                boolean achou = false;

                // primeiro vejo se as ligações do vértice testado tem esta cor
                for (Ligacao vLis : getLigacoesVertice(vPes, true)) {
                    if ((vPes != vLis.getDestino()) && (co == vLis.getDestino().getCor())) {
                        achou = true;
                    }
                }

                // se não achou, eu vejo os outros vértices que possuem este, e as cores
                if (!achou) {
                    for (Vertice vLis : getVertices()) {
                        if (vPes != vLis) {
                            for (Ligacao vLis2 : getLigacoes()) {
                                if ((vPes != vLis2.getOrigem()) && (vPes == vLis2.getDestino()) && (co == vLis2.getOrigem().getCor())) {
                                    achou = true;
                                }
                            }
                        }
                    }
                }

                if (!achou) {
                    cor = co;
                    break;
                }
            }

            vPes.setCor(cor);
        }
    }

    public int getVerticesCount() {
        return getVertices().size();
    }

    // -------------------------------------------------------------------------
    public boolean isDirecionado() {
        return direcionado;
    }

    public void setDirecionado(boolean direcionado) {
        // quando eu troco o tipo eu tenho que limpar as ligacoes, visto que as ligacoes ficam diferentes
        clearLigacoes();
        this.direcionado = direcionado;
    }

    // -------------------------------------------------------------------------
    public int getRegioesCount() {
        // V – E + R = 2
        // vértices - arestas + regiões = 2
        return getLigacoesCount() - getVerticesCount() + 2;
    }

    public boolean hasCiclosDeComprimentoTres() {
        for (Vertice origem : getVertices()) {
            for (Ligacao l1 : getLigacoesVertice(origem, true)) {
                for (Ligacao l2 : getLigacoesVertice(l1.getDestino(), true)) {
                    for (Ligacao l3 : getLigacoesVertice(l2.getDestino(), true)) {
                        if (origem.getId().equalsIgnoreCase(l3.getDestino().getId())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isPlanar() {
        // Se V >= 3, então
        //    E <=  3V – 6
        // Se V >= 3 e não existem ciclos de comprimento 3 então
        //    E <= 2V – 4 

        if (getVerticesCount() >= 3) {
            if (hasCiclosDeComprimentoTres()) {
                if (getLigacoesCount() <= (3 * getVerticesCount() - 6)) {
                    return true;
                }
            } else {
                if (getLigacoesCount() <= (2 * getVerticesCount() - 4)) {
                    return true;
                }
            }
        }

        return getVerticesCount() <= 3;
    }

    public boolean isConexo() {
        // para ser conexo, qualquer par de vértices tem que poder se comunicar
        for (Vertice vOrigem : getVertices()) {
            for (Vertice vDestino : getVertices()) {
                if (vOrigem != vDestino) {
                    if (!hasLigacaoIndireta(vOrigem, vDestino)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public List<List<Vertice>> getSubGrafosConexos() {
        List<List<Vertice>> result = new ArrayList<>();

        for (Vertice vOrigem : getVertices()) {
            // se já está na lista já está em um subgrafo
            boolean achou = false;
            for (List<Vertice> vList : result) {
                if (vList.contains(vOrigem)) {
                    achou = true;
                    break;
                }
            }

            if (!achou) {
                List<Vertice> lista = new ArrayList<>();
                lista.add(vOrigem);

                for (Vertice vDestino : getVertices()) {
                    // testando agora com o destino
                    achou = false;
                    for (List<Vertice> vList : result) {
                        if (vList.contains(vDestino)) {
                            achou = true;
                            break;
                        }
                    }

                    if ((!achou) && (vOrigem != vDestino)) {
                        if (hasLigacaoIndireta(vOrigem, vDestino) && hasLigacaoIndireta(vDestino, vOrigem)) {
                            lista.add(vDestino);
                        }
                    }
                }

                result.add(lista);
            }
        }
        return result;
    }

    public String getSubGrafosConexosToString() {
        String result = "";

        List<List<Vertice>> aux = getSubGrafosConexos();
        for (List<Vertice> vList : aux) {
            result = result.trim() + "\n";
            for (Vertice vVert : vList) {
                result = result + vVert.getId() + " ";
            }
        }

        return result;
    }

    public List<Ligacao> getMelhorRota(Vertice ori) {
        /*List<Ligacao> result = new ArrayList<>();
        List<Vertice> testados = new ArrayList<>();
        Vertice aux = ori;
        
        for (Ligacao vLig : getLigacoes()) {
            if (vLig)
        }
        
        return result;*/
        List<Ligacao> result = new ArrayList<>();
        List<Vertice> testados = new ArrayList<>();
        testados.add(ori);
        for (Ligacao vLig: getLigacoes()){
            
        }
        return null;
    }    
}
