package util;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */
public class Objeto {

    protected int x, y;
    protected int largura, altura;

    public Objeto(int x, int y, int largura, int altura) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void moveHorizontal(int x) {
        this.x += x;
    }

    public void moveVertical(int y) {
        this.y += y;
    }

    public void draw(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(x, y, largura, altura);
    }
}
