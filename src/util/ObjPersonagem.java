package util;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */

public class ObjPersonagem extends Objeto{
    
    protected int velocX,velocY;
    
    public ObjPersonagem(int x, int y, int largura, int altura, int velocX, int velocY) {
        super(x, y, largura, altura);
        this.velocX = velocX;
        this.velocY = velocY;
    }

    public int getVelocX() {
        return velocX;
    }

    public void setVelocX(int velocX) {
        this.velocX = velocX;
    }

    public int getVelocY() {
        return velocY;
    }

    public void setVelocY(int velocY) {
        this.velocY = velocY;
    }
    
  /*  public void moveHorinzontal(){
        x += velocX;
    }
    
    public void moveVertical(){
        y += velocY;
    }*/
}