package personagens;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import som.Som;
import util.Tiro;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */
public class OmegaTroopers extends Inimigo {

    private boolean comImagem;
    private boolean sumindo;
    private int auxTempo;
    private boolean atacando;
    private int auxAtaque;

    private boolean sofrendoDano;
    private int tDano;
    private int auxDano;

    private ImageIcon atual;
    private final ImageIcon explosao;

    private ArrayList<Tiro> tiros;

    private Som efeito;

    public OmegaTroopers(int x, int y, int largura, int altura, int velocX, int velocY) {
        super(1, 2, x, y, largura, altura, velocX, velocY);
        comImagem = true;
        tDano = 0;
        auxDano = 0;
        auxTempo = 0;
        sofrendoDano = false;
        atacando = false;
        sumindo = false;

        efeito = new Som();

        auxAtaque = 0;
        tiros = new ArrayList<>();
        explosao = new ImageIcon("src/imagens/explosao.gif");
        atual = new ImageIcon("src/imagens/omega/omega.gif");
    }

    public ArrayList<Tiro> getTiros() {
        return tiros;
    }

    public void removerTiro(Tiro t) {
        tiros.remove(t);
    }

    @Override
    public void moveHorizontal(int x) {
        this.x += x;
        for (Tiro t : tiros) {
            t.moveHorizontal(x);
        }
    }

    public boolean getSumindo() {
        return sumindo;
    }

    public void setSumindo(boolean sumindo) {
        this.sumindo = sumindo;
    }

    public boolean getSofrendoDano() {
        return sofrendoDano;
    }

    @Override
    public void ataque() {
        tiros.add(new Tiro(x - 114, y + 20, 114, 9, 8, new ImageIcon("src/imagens/omega/projetil.png"), false));
        if (x > 0 && x < 820) {
            efeito.iniciarSom("src/som/ataque_omega.mp3");
        }
    }

    public void sofreAtaque() {
        if (!sofrendoDano) {
            if (danoSofrido + 1 == qVidas) {
                sumindo = true;
                atual = explosao;
                sofrendoDano = false;
                comImagem = true;
            } else {
                soferDano();
                sofrendoDano = true;
            }
        }
    }

    @Override
    public void update() {
        if (auxAtaque == 150 && !sumindo) {
            ataque();
            auxAtaque = 0;
        } else if (!sumindo) {
            auxAtaque++;

        }
        if (sumindo) {
            auxTempo++;
            if (auxTempo > 80) {
                danoSofrido++;
            }
        } else if (sofrendoDano) {
            auxDano++;
            tDano++;
            if (auxDano > 15) {
                auxDano = 0;
                comImagem = !comImagem;
            }
            if (tDano > 150) {
                sofrendoDano = false;
                tDano = 0;
                auxDano = 0;
                comImagem = true;
            }
        }
        for (Tiro t : tiros) {
            t.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        if (comImagem) {
            g.drawImage(atual.getImage(), x, y, largura, altura, null);
        }

        for (Tiro t : tiros) {
            t.draw(g);
        }
    }
}
