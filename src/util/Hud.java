package util;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */
public class Hud {

    private int pontos;
    
    private final boolean animais;

    private final ImageIcon numero0;
    private final ImageIcon numero1;
    private final ImageIcon numero2;
    private final ImageIcon numero3;
    private final ImageIcon numero4;
    private final ImageIcon numero5;
    private final ImageIcon numero6;
    private final ImageIcon numero7;
    private final ImageIcon numero8;
    private final ImageIcon numero9;

    private final ObjImagem pata;
    private final ObjImagem barra;
    private final ObjImagem pause;

    private ObjImagem nAtualAnimais;
    private ObjImagem nMaximoAnimais;

    private final ObjImagem unidade;
    private final ObjImagem dezena;
    private final ObjImagem centena;
    private final ObjImagem milhar;

    private final ObjImagem vidas;

    private int numeroAtualAnimais;
    private final int numeroMaximoAnimais;

    public Hud(int numeroMaximoAnimais) {
        this.numeroMaximoAnimais = numeroMaximoAnimais;
        numeroAtualAnimais = 0;
        pontos = 0;
        
        this.animais = true;

        int x, y;

        numero0 = new ImageIcon("src/imagens/hud/numero_00.png");
        numero1 = new ImageIcon("src/imagens/hud/numero_01.png");
        numero2 = new ImageIcon("src/imagens/hud/numero_02.png");
        numero3 = new ImageIcon("src/imagens/hud/numero_03.png");
        numero4 = new ImageIcon("src/imagens/hud/numero_04.png");
        numero5 = new ImageIcon("src/imagens/hud/numero_05.png");
        numero6 = new ImageIcon("src/imagens/hud/numero_06.png");
        numero7 = new ImageIcon("src/imagens/hud/numero_07.png");
        numero8 = new ImageIcon("src/imagens/hud/numero_08.png");
        numero9 = new ImageIcon("src/imagens/hud/numero_09.png");

        x = 27;
        y = 33;

        pause = new ObjImagem(774, 0, 40, 40, new ImageIcon("src/imagens/hud/pause.png"));

        nAtualAnimais = new ObjImagem(356, 0, x, y, numero0);
        barra = new ObjImagem(377, 0, x + 10, y, new ImageIcon("src/imagens/hud/barra.png"));
        nMaximoAnimais = new ObjImagem(410, 0, x, y, numero(numeroMaximoAnimais));
        pata = new ObjImagem(445, 0, x, y, new ImageIcon("src/imagens/hud/pata1.png"));

        vidas = new ObjImagem(0, 0, 39, 36, new ImageIcon("src/imagens/hud/vidas.png"));
        unidade = new ObjImagem(x * 3, 36, x, y, numero0);
        dezena = new ObjImagem(x * 2, 36, x, y, numero0);
        centena = new ObjImagem(x, 36, x, y, numero0);
        milhar = new ObjImagem(0, 36, x, y, numero0);

    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    
    
    public Hud() {
        numeroMaximoAnimais = 0;
        numeroAtualAnimais = 0;

        this.animais = false;

        int x, y;

        pata = null;
        barra = null;

        numero0 = new ImageIcon("src/imagens/hud/numero_00.png");
        numero1 = new ImageIcon("src/imagens/hud/numero_01.png");
        numero2 = new ImageIcon("src/imagens/hud/numero_02.png");
        numero3 = new ImageIcon("src/imagens/hud/numero_03.png");
        numero4 = new ImageIcon("src/imagens/hud/numero_04.png");
        numero5 = new ImageIcon("src/imagens/hud/numero_05.png");
        numero6 = new ImageIcon("src/imagens/hud/numero_06.png");
        numero7 = new ImageIcon("src/imagens/hud/numero_07.png");
        numero8 = new ImageIcon("src/imagens/hud/numero_08.png");
        numero9 = new ImageIcon("src/imagens/hud/numero_09.png");

        x = 27;
        y = 33;

        pause = new ObjImagem(774, 0, 40, 40, new ImageIcon("src/imagens/hud/pause.png"));

        vidas = new ObjImagem(0, 0, 39, 36, new ImageIcon("src/imagens/hud/vidas.png"));
        unidade = new ObjImagem(x * 3, 36, x, y, numero0);
        dezena = new ObjImagem(x * 2, 36, x, y, numero0);
        centena = new ObjImagem(x, 36, x, y, numero0);
        milhar = new ObjImagem(0, 36, x, y, numero0);

    }

    public int getPontos() {
        return pontos;
    }
    

    public void pause(boolean pause) {
        if (pause) {
            this.pause.setImage(new ImageIcon("src/imagens/hud/pause_ativado.png"));
        } else {
            this.pause.setImage(new ImageIcon("src/imagens/hud/pause.png"));
        }
    }

    public void draw(Graphics g) {
      //  pause.draw(g);

        unidade.draw(g);
        dezena.draw(g);
        centena.draw(g);
        milhar.draw(g);

        if (animais) {
            nAtualAnimais.draw(g);
            barra.draw(g);
            nMaximoAnimais.draw(g);
            pata.draw(g);
        }

        for (int i = 0; i < forestguardian.ForestGuardian.usuario.getNumeroVidas(); i++) {
            vidas.draw(g);
            vidas.moveHorizontal(35);
        }
        vidas.setX(0);
    }

    public void update(int n) {
        nAtualAnimais.setImage(numero(n));
        unidade.setImage(numero(pontos % 10));
        dezena.setImage(numero(pontos / 10 % 10));
        centena.setImage(numero(pontos / 100 % 10));
        milhar.setImage(numero(pontos/ 1000 % 10));
    }

    public void update() {
        unidade.setImage(numero(pontos % 10));
        dezena.setImage(numero(pontos / 10 % 10));
        centena.setImage(numero(pontos / 100 % 10));
        milhar.setImage(numero(pontos/ 1000 % 10));
    }

    public final ImageIcon numero(int n) {
        switch (n) {
            case 0:
                return numero0;
            case 1:
                return numero1;
            case 2:
                return numero2;
            case 3:
                return numero3;
            case 4:
                return numero4;
            case 5:
                return numero5;
            case 6:
                return numero6;
            case 7:
                return numero7;
            case 8:
                return numero8;
            case 9:
                return numero9;

        }
        return numero0;
    }
    
    public void aumentarPontuacao(int pontos){
        this.pontos += pontos;
    }
}
