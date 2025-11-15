package personagens;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import som.Som;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */
public class MegaKart extends Inimigo {

    private boolean comImagem;
    private boolean direcao;
    private boolean sumindo;

    private int auxTempo;

    private final ImageIcon explosaoFinal;
    private final ImageIcon explosaoAtaque;
    private final ImageIcon andarDireita;
    private final ImageIcon andarEsquerda;

    private Som efeito;
    
    private int cont;
    private final int distancia;
                
    public MegaKart(int x, int y, int largura, int altura, int velocX, int velocY, int distancia) {
        super(2, 1, x, y, largura, altura, velocX, velocY);
        efeito = new Som();
        efeito.setRepetir(false);
        comImagem = true;
        auxTempo = 0;
        atacando = false;

        direcao = velocX >= 0;
                
        cont = 0;

        explosaoFinal = new ImageIcon("src/imagens/explosao.gif");
        explosaoAtaque = new ImageIcon("src/imagens/megaKart/kart_explodindo.gif");
        andarDireita = new ImageIcon("src/imagens/megaKart/kart_andando.gif");
        andarEsquerda = new ImageIcon("src/imagens/megaKart/kart_esq_andando.gif");

        this.distancia = distancia;
    }

    public boolean getSumindo() {
        return sumindo;
    }
    
    public boolean getDirecao() {
        return direcao;
    }

    /*@Override
    public void ataque(){
       efeito.iniciarSom("src/som/explosao.mp3"); 
       atacando = true;
    }*/
    
    public void move() {
        x += velocX;
        cont++;
        if (cont == distancia) {
            inverteDirecao();
            cont = 0;
        }
    }

    public void inverteDirecao() {
        velocX *= -1;
        direcao = !direcao;
    }

    public void sofrerAtaque() {
        if (!atacando) {
            sumindo = true;
        }
    }

    @Override
    public void update() {
        if (sumindo) {
            auxTempo++;
            if (auxTempo > 80) {
                soferDano();
            }
        } else if (atacando) {
            auxTempo++;
            if (auxTempo > 70) {
                soferDano();
            }
        } else {
            move();
        }
    }

    @Override
    public void draw(Graphics g) {
        if (sumindo) {
            g.drawImage(explosaoFinal.getImage(), x, y, largura, altura, null);
        } else if (atacando) {
            g.drawImage(explosaoAtaque.getImage(), x, y, largura, altura, null);
        } else if (direcao) {
            g.drawImage(andarDireita.getImage(), x, y, largura, altura, null);
        } else {
            g.drawImage(andarEsquerda.getImage(), x, y, largura, altura, null);
        }

    }
}
