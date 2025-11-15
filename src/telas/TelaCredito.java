package telas;

import forestguardian.GameRunnable;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */
public class TelaCredito implements Tela {

    private final ImageIcon tela;
    private final ImageIcon creditos;
    private final ImageIcon esc;

    public TelaCredito() {
        tela = new ImageIcon("src/imagens/fundo.png");
        creditos = new ImageIcon("src/imagens/creditos.png");
        
        esc = new ImageIcon("src/imagens/esc.png");
    }

    @Override
    public void iniciar() {}

    @Override
    public void fechar() {}

    @Override
    public void draw(Graphics g) {
        g.drawImage(tela.getImage(), 0, 0, 820, 500, null);
        g.drawImage(creditos.getImage(), 196, 40, 429, 399, null);
        g.drawImage(esc.getImage(), 0, 410, 154, 39, null);
    }

    @Override
    public void update() {
        if (forestguardian.ForestGuardian.tecla.teclaAcionada(2)) {
            GameRunnable.mudarTela(6);
        }
    }
}
