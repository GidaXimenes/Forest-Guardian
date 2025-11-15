package util;

import javax.swing.ImageIcon;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */
public class Tiro extends ObjImagem{
    
    private final boolean direcao;
    private final int veloc;
    
    public Tiro(int x, int y, int largura, int altura,int veloc,ImageIcon imagem,boolean direcao) {
        super(x, y, largura, altura, imagem);
        this.direcao = direcao;
        this.veloc = veloc;
    }
    
    public void update(){
        if(direcao){
            moveHorizontal(veloc);
        }else{
            moveHorizontal(-veloc);
        }
    }
    
}
