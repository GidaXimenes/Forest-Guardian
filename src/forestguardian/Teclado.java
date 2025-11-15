package forestguardian;

import java.awt.event.KeyEvent;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */
public class Teclado {

    private final boolean[] botoes;

    public Teclado() {
        botoes = new boolean[8];
        for (boolean i : botoes) {
            i = false;
        }
    }

    public boolean teclaAcionada(int i) {
        return botoes[i];
    }

    public void setTeclas(int numero, boolean status) {
        switch (numero) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                botoes[0] = status;
                break;

            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                botoes[1] = status;
                break;

            case KeyEvent.VK_ESCAPE:
                botoes[2] = status;
                break;

            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_W:
                botoes[3] = status;
                break;
                
            case KeyEvent.VK_E:
                botoes[4] = status;
                break;
            case KeyEvent.VK_UP:
                botoes[5] = status;
                botoes[3] = status;
                break;
            case KeyEvent.VK_DOWN:
                botoes[6] = status;
                break;
            case KeyEvent.VK_ENTER:
                botoes[7] = status;
                break;
        }
        
    }

}
