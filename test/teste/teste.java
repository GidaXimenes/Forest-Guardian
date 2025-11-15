/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author rivai
 */
public class teste extends JPanel {

    ObjImagem n;

    public teste() {
        setLayout(null);
     //    n =new ObjImagem(150, 275, 15, 20, new ImageIcon("src/imagens/hud/numero_00.png"));
         n =new ObjImagem(0, 0, 200, 200, new ImageIcon("src/imagens/hud/numero_00.png"));

    }

    @Override
    public void paintComponent(Graphics g) {
        n.draw(g);
    }

}
