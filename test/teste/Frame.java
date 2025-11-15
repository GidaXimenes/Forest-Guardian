/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author rivai
 */
public class Frame extends JFrame implements KeyListener {

    Painel o;

    public Frame() {
        o = new Painel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);
        add(o);
        addKeyListener(this);
    }

    public static void main(String[] args) throws InterruptedException {
        Frame f = new Frame();
        
        while (true) {
            f.o.repaint();
            Thread.sleep(15);
        }
    }

    @Override
    public boolean isFocusTraversable() {
        return true;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        System.out.println("taipado");

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        System.out.println("Pressionado");

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        System.out.println("soltado");

    }
}
