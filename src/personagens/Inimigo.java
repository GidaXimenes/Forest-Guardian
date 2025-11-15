package personagens;

import util.ObjPersonagem;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */
public abstract class Inimigo extends ObjPersonagem {

    protected boolean atacando;
    protected int danoSofrido;
    protected int danoCausado;
    protected int qVidas;

    public Inimigo(int danoCausado, int qVidas, int x, int y, int largura, int altura, int velocX, int velocY) {
        super(x, y, largura, altura, velocX, velocY);
        atacando = false;
        danoSofrido = 0;
        this.danoCausado = danoCausado;
        this.qVidas = qVidas;
    }

    public int getDanoCausado() {
        return danoCausado;
    }

    public boolean getAtacando() {
        return atacando;
    }

    public void setAtacando(boolean atacando) {
        this.atacando = atacando;
    }

    protected void soferDano() {
        danoSofrido++;
    }

    public boolean estaMorto() {
        return danoSofrido >= qVidas;
    }

    public abstract void update();

    public void ataque(){};
    

    private void sofrerAtaque() {
    }

}
