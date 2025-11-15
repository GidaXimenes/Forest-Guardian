package telas;

import forestguardian.GameRunnable;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */
public class TelaAjuda implements Tela {
    private final ImageIcon tela;
    private final ImageIcon ajuda;

    private final ImageIcon esc;
    
    public TelaAjuda() {
        tela = new ImageIcon("src/imagens/fundo.png");
        ajuda = new ImageIcon("src/imagens/menu_ajuda.png");
        esc = new ImageIcon("src/imagens/esc.png");
    }

    @Override
    public void iniciar() {}

    @Override
    public void fechar() {}

    @Override
    public void draw(Graphics g) {
        g.drawImage(tela.getImage(), 0, 0, 820, 500, null);
        g.drawImage(ajuda.getImage(), 196, 40, 429, 399, null);
        g.drawImage(esc.getImage(), 0, 410, 154, 39, null);
    }

    @Override
    public void update() {
        if(forestguardian.ForestGuardian.tecla.teclaAcionada(2)){
            GameRunnable.mudarTela(0);
        }
    }
}
