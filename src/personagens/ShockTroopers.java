package personagens;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import som.Som;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */
public class ShockTroopers extends Inimigo {

    private boolean podeAtacar;
    private boolean comImagem;
    private boolean direcao;
    private boolean sumindo;

    private int auxTempo;

    private boolean sofrendoDano;
    private int tDano;
    private int auxDano;

    private final ImageIcon explosao;
    private final ImageIcon andarDireita;
    private final ImageIcon andarEsquerda;
    private final ImageIcon ataqueDireita;
    private final ImageIcon ataqueEsquerda;

    private ImageIcon atualDireita;
    private ImageIcon atualEsquerda;

    private int cont;
    private final int distancia;

    private Som efeito;

    public ShockTroopers(int x, int y, int largura, int altura, int velocX, int velocY, int distancia) {
        super(1, 2, x, y, largura, altura, velocX, velocY);
        comImagem = true;

        efeito = new Som();

        sumindo = false;
        podeAtacar = false;
        tDano = 0;
        auxDano = 0;
        sofrendoDano = false;
        atacando = false;
        auxTempo = 0;
        direcao = velocX >= 0;
        cont = 0;

        explosao = new ImageIcon("src/imagens/explosao.gif");
        andarDireita = new ImageIcon("src/imagens/shock/vigia_andando.gif");
        andarEsquerda = new ImageIcon("src/imagens/shock/vigia_esq_andando.gif");
        ataqueDireita = new ImageIcon("src/imagens/shock/vigia_atacando.gif");
        ataqueEsquerda = new ImageIcon("src/imagens/shock/vigia_esq_atacando.gif");

        atualDireita = andarDireita;
        atualEsquerda = andarEsquerda;

        this.distancia = distancia;
    }

    public boolean getPodeAtacar() {
        return podeAtacar;
    }

    public boolean getDirecao() {
        return direcao;
    }

    public void setDirecao(boolean direcao) {
        this.direcao = direcao;
    }

    public boolean getSumindo() {
        return sumindo;
    }

    public boolean getSofrendoDano() {
        return sofrendoDano;
    }

    public void setSofrendoDano(boolean dano) {
        this.sofrendoDano = dano;
    }

    public void move() {
        x += velocX;
        cont++;
        if (cont == distancia) {
            inverteDirecao();
            cont = 0;
        }
    }

    @Override
    public void ataque() {
        if (!atacando) {
            efeito.iniciarSom("src/som/ataque_vigia.mp3");
        }
        if (!sumindo) {
            atacando = true;
            atualEsquerda = ataqueEsquerda;
            atualDireita = ataqueDireita;
        }
    }

    public void inverteDirecao() {
        velocX *= -1;
        direcao = !direcao;
    }

    @Override
    public void soferDano() {
        if (danoSofrido + 1 == 2) {
            sumindo = true;
            podeAtacar = false;
            atualDireita = explosao;
            atualEsquerda = explosao;
        } else {
            danoSofrido++;
            ataque();
            sofrendoDano = true;
        }

    }

    public void sofrerAtaque() {
        if (!sofrendoDano) {
            soferDano();
        }
    }

    @Override
    public void update() {
        if (!atacando && !sumindo) {
            move();
            atualDireita = andarDireita;
            atualEsquerda = andarEsquerda;
        }
        if (sumindo) {
            auxTempo++;
            if (auxTempo > 80) {
                danoSofrido++;
            }
        } else if (atacando) {
            auxTempo++;
            if (auxTempo == 20) {
                podeAtacar = true;
                auxTempo = 0;
            } else {
                podeAtacar = false;
            }
        } else {
            auxTempo = 0;
        }

        if (sofrendoDano && !sumindo) {
            auxDano++;
            tDano++;
            if (auxDano > 15) {
                auxDano = 0;
                comImagem = !comImagem;
            }
            if (tDano > 170) {
                sofrendoDano = false;
                tDano = 0;
                auxDano = 0;
                comImagem = true;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        if (comImagem) {
            if (direcao) {
                g.drawImage(atualDireita.getImage(), x, y, largura, altura, null);
            } else {
                g.drawImage(atualEsquerda.getImage(), x, y, largura, altura, null);
            }
        }
    }
}
