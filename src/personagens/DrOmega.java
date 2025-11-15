package personagens;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import util.ObjImagem;
import util.Tiro;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */

public class DrOmega extends Inimigo {

    private boolean morrendo;
    private boolean morto;
    private int auxMorto;

    private boolean sofrendoDano;
    private boolean comImagem;
    private int auxComImagem;
    private int tempoDano;

    private boolean isAtivado;

    private boolean podeAtacar;
    private int auxAtaque;

    private boolean isAtirando;
    private int auxTiro;

    private boolean isDash;
    private boolean direcao;

    private ArrayList<Tiro> tiros;
    private ArrayList<Explosao> explosoes;

    private ObjImagem barraVida;

    private final ImageIcon vida0;
    private final ImageIcon vida1;
    private final ImageIcon vida2;
    private final ImageIcon vida3;
    private final ImageIcon vida4;
    private final ImageIcon vida5;
    private final ImageIcon vida6;
    private final ImageIcon vida7;
    private final ImageIcon vida8;

    private final ImageIcon parado;
    private final ImageIcon atirando;
    private final ImageIcon dash;
    private final ImageIcon recarregando;

    private final ImageIcon paradoEsq;
    private final ImageIcon atirandoEsq;
    private final ImageIcon dashEsq;
    private final ImageIcon recarregandoEsq;

    private ImageIcon atual;
    private ImageIcon atualEsq;

    public DrOmega(int x, int y, int largura, int altura, int velocX, int velocY) {
        super(2, 8, x, y, largura, altura, velocX, velocY);
        tiros = new ArrayList<>();
        explosoes = new ArrayList<>();

        auxAtaque = 0;
        tempoDano = 0;

        morrendo = false;
        morto = false;

        comImagem = true;
        sofrendoDano = false;
        isAtivado = false;
        podeAtacar = false;
        direcao = false;
        isDash = false;
        isAtirando = false;

        vida0 = new ImageIcon("src/imagens/drOmega/vida_0.png");
        vida1 = new ImageIcon("src/imagens/drOmega/vida_1.png");
        vida2 = new ImageIcon("src/imagens/drOmega/vida_2.png");
        vida3 = new ImageIcon("src/imagens/drOmega/vida_3.png");
        vida4 = new ImageIcon("src/imagens/drOmega/vida_4.png");
        vida5 = new ImageIcon("src/imagens/drOmega/vida_5.png");
        vida6 = new ImageIcon("src/imagens/drOmega/vida_6.png");
        vida7 = new ImageIcon("src/imagens/drOmega/vida_7.png");
        vida8 = new ImageIcon("src/imagens/drOmega/vida_8.png");

        barraVida = new ObjImagem(320, 0, 180, 36, barraVida());

        parado = new ImageIcon("src/imagens/drOmega/parado.png");
        atirando = new ImageIcon("src/imagens/drOmega/atirando.gif");
        dash = new ImageIcon("src/imagens/drOmega/dash.png");
        recarregando = new ImageIcon("src/imagens/drOmega/recarregando.gif");

        paradoEsq = new ImageIcon("src/imagens/drOmega/parado_esq.png");
        atirandoEsq = new ImageIcon("src/imagens/drOmega/atirando_esq.gif");
        dashEsq = new ImageIcon("src/imagens/drOmega/dash_esq.png");
        recarregandoEsq = new ImageIcon("src/imagens/drOmega/recarregando_esq.gif");

        atual = parado;
        atualEsq = paradoEsq;
    }

    public boolean getSofrendoDano() {
        return sofrendoDano;
    }
    
    

    public boolean getMorrendo() {
        return morrendo;
    }

    public boolean getMorto() {
        return morto;
    }

    public void ativar() {
        isAtivado = true;
    }

    public ArrayList<Tiro> getTiros() {
        return tiros;
    }

    public boolean getDash() {
        return isDash;
    }

    public void removeTiro(Tiro t) {
        explosoes.add(new Explosao(new ObjImagem(t.getX(), t.getY(), t.getLargura(), t.getAltura(), new ImageIcon("src/imagens/megaKart/kart_explodindo.gif"))));
        tiros.remove(t);

    }

    private ImageIcon barraVida() {
        switch (qVidas - danoSofrido) {
            case 0:
                return vida0;
            case 1:
                return vida1;
            case 2:
                return vida2;
            case 3:
                return vida3;
            case 4:
                return vida4;
            case 5:
                return vida5;
            case 6:
                return vida6;
            case 7:
                return vida7;
            case 8:
                return vida8;
        }
        return vida0;
    }

    public void fazerTiro() {
        isAtirando = true;
        podeAtacar = false;
        atual = atirando;
        atualEsq = atirandoEsq;
        auxTiro = 0;
    }

    private void atirar() {
        if (isAtirando) {
            if (auxTiro >= 100) {
                isAtirando = false;
                atual = recarregando;
                atualEsq = recarregandoEsq;
            } else if (auxTiro == 20 || auxTiro == 40 || auxTiro == 60 || auxTiro == 80) {
                if (direcao) {
                    tiros.add(new Tiro(x + largura - 30, y + (altura / 2), 46, 27, 10, new ImageIcon("src/imagens/drOmega/projetil.png"), direcao));
                } else {
                    tiros.add(new Tiro(x - 10, y + (altura / 2), 46, 27, 10, new ImageIcon("src/imagens/drOmega/projetil_esq.png"), direcao));
                }
            }
            auxTiro++;
        }

    }

    private void fazerDash() {
        isDash = true;
        podeAtacar = false;
        atual = dash;
        atualEsq = dashEsq;
    }

    private void dash() {
        if (isDash) {
            if (direcao) {
                if (!(x + largura + velocX > 820)) {
                    moveHorizontal(velocX);

                } else {
                    direcao = !direcao;
                    atual = recarregando;
                    atualEsq = recarregandoEsq;
                    isDash = false;
                }
            } else {
                if (!(x - velocX < 0)) {
                    moveHorizontal(-velocX);
                } else {
                    direcao = !direcao;
                    atual = recarregando;
                    atualEsq = recarregandoEsq;
                    isDash = false;
                }
            }
        }
    }

    public void sofrerAtaque() {
        if (!sofrendoDano) {
            sofrendoDano = true;
            soferDano();
            auxComImagem = 0;
            tempoDano = 0;
            comImagem = true;
        }
    }

    @Override
    public void update() {
        if (isAtivado) {
            if (estaMorto() && !morrendo && !morto) {
                morrendo = true;
                comImagem = true;
                sofrendoDano = false;
                podeAtacar = false;
                isDash = false;
                isAtirando = false;
                auxMorto = 0;
                tiros.clear();
                atual = recarregando;
                atualEsq = recarregandoEsq;
                barraVida.setImage(barraVida());
            }
            if (morrendo) {
                for (Explosao e : explosoes) {
                    e.tempo++;
                }
                if(auxMorto <200 ){
                    explosoes.removeIf(e -> e.tempo == 40);
                }
                if (auxMorto % 20 == 0 && auxMorto < 200) {
                    Random r = new Random();
                    int x = r.nextInt(109) + this.x;
                    int y = r.nextInt(150) + this.y;
                    explosoes.add(new Explosao(new ObjImagem(x, y, 96, 54, new ImageIcon("src/imagens/megaKart/kart_explodindo.gif"))));
                }
                
                if (auxMorto == 200 ) {
                    explosoes.clear();
                    explosoes.add(new Explosao(new ObjImagem(x, y, largura, altura, new ImageIcon("src/imagens/megaKart/kart_explodindo.gif"))));
                    largura = 0;
                    altura = 0;
                }
                if (auxMorto == 320) {
                    explosoes.clear();
                    altura = 121;
                    largura = 171;
                    if (!direcao) {
                        x -= 30;
                    }
                    y += 56;
                    atual = new ImageIcon("src/imagens/drOmega/morto.png");
                    atualEsq = new ImageIcon("src/imagens/drOmega/morto_esq.png");
                    morto = true;
                    morrendo = false;
                }
                auxMorto++;
            } else if (!morto) {
                if (podeAtacar) {
                    Random r = new Random();

                    int ataqueOpcao = (r.nextInt(20) + 1) % 2;
                    if (ataqueOpcao == 1) {
                        fazerDash();
                    } else {
                        fazerTiro();
                    }
                }

                if (!isAtirando && !isDash) {
                    if (auxAtaque > 150) {

                        podeAtacar = true;
                        auxAtaque = 0;
                    }
                    if (auxAtaque == 100) {
                        atual = parado;
                        atualEsq = paradoEsq;
                    }
                    auxAtaque++;
                }

                for (Tiro t : tiros) {
                    t.update();
                }
                for (Explosao e : explosoes) {
                    e.tempo++;
                }
                explosoes.removeIf(e -> e.tempo > 50);

                barraVida.setImage(barraVida());
                atirar();
                dash();

                if (sofrendoDano) {
                    auxComImagem++;
                    tempoDano++;
                    if (auxComImagem > 15) {
                        auxComImagem = 0;
                        comImagem = !comImagem;
                    }
                    if (tempoDano > 170) {
                        sofrendoDano = false;
                        tempoDano = 0;
                        auxComImagem = 0;
                        comImagem = true;
                    }
                }
                tiros.removeIf(t -> t.getX() > 820 || t.getX() < 0-t.getLargura());
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        if (comImagem) {
            if (direcao) {
                g.drawImage(atual.getImage(), x, y, largura, altura, null);
            } else {
                g.drawImage(atualEsq.getImage(), x, y, largura, altura, null);
            }
        }
        for (Tiro t : tiros) {
            t.draw(g);
        }

        for (Explosao o : explosoes) {
            o.o.draw(g);
        }
        barraVida.draw(g);
    }

}

class Explosao {

    ObjImagem o;
    int tempo;

    public Explosao(ObjImagem o) {
        this.o = o;
        tempo = 0;
    }
}
