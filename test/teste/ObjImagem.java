/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author rivai
 */
public class ObjImagem {

    private int x, y;
    private int largura, altura;
    private ImageIcon imagem;

    public ObjImagem(int x, int y, int largura, int altura, ImageIcon imagem) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.imagem = imagem;
    }

    public int getY() {
        return y;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public void move(int x) {
        this.x += x;
    }

    public void setImage(ImageIcon imagem) {
        this.imagem = imagem;
    }

    public Image getImagem() {
        return imagem.getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(getImagem(), getX(), getY(), getLargura(), getAltura(), null);
    }

}
