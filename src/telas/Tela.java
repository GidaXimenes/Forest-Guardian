package telas;

import java.awt.Graphics;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */
public interface Tela {
    
    public void iniciar();
    
    public void fechar();
    
    public void update();
    
    public void draw(Graphics g);

}
