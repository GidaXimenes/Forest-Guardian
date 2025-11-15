package util;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */

public class ObjImagem extends Objeto {

    protected ImageIcon imagem;

    public ObjImagem(int x, int y, int largura, int altura, ImageIcon imagem) {
        super(x, y, largura, altura);
        this.imagem = imagem;
    }

    public void setImage(ImageIcon imagem) {
        this.imagem = imagem;
    }

    public Image getImagem() {
        return imagem.getImage();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImagem(), x, y, largura, altura, null);
    }
}
