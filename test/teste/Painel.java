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
public class Painel extends JPanel {

    public TelaSelecaoFaseTeste s;
    ObjImagem a1;
    ObjImagem a2;
    ObjImagem a3;
    ObjImagem a4;
    
    ObjImagem c1;
    ObjImagem c2;
    ObjImagem c3;
    ObjImagem c4;
    
    ObjImagem b1;
    ObjImagem b2;
    ObjImagem b3;
    ObjImagem b4;


    /*public JButton btnReiniciar;
    public JButton btnVoltar;
    public JButton btnAvancar;

    private ImageIcon imgVoltar;
    private ImageIcon imgReiniciar;
    private ImageIcon imgAvançar;*/
    public Painel() {

        s = new TelaSelecaoFaseTeste(this);
        s.iniciar();
        a1 = new ObjImagem(165, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        a2 = new ObjImagem(190, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        a3 = new ObjImagem(215, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        a4 = new ObjImagem(240, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        
        b1 = new ObjImagem(360, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        b2 = new ObjImagem(385, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        b3 = new ObjImagem(410, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        b4 = new ObjImagem(435, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        
        c1 = new ObjImagem(555, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        c2 = new ObjImagem(580, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        c3 = new ObjImagem(605, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        c4 = new ObjImagem(630, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));

        /*btnReiniciar = new JButton();
        btnVoltar = new JButton();
        btnAvancar = new JButton();

        imgReiniciar = new ImageIcon("src/imagens/botoes/level_1_selecionado.png");
        imgVoltar = new ImageIcon("src/imagens/botoes/level_2_selecionado.png");
        imgAvançar = new ImageIcon("src/imagens/botoes/level_3_selecionado.png");

        //Botão de Jogar
        btnReiniciar.setBounds(143, 157, 145, 146);
        btnReiniciar.setIcon(imgReiniciar);
        btnReiniciar.setBorderPainted(false);
        btnReiniciar.setContentAreaFilled(false);

        //Botão de Jogar
        btnVoltar.setBounds(338, 157, 145, 146);
        btnVoltar.setIcon(imgVoltar);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setContentAreaFilled(false);

        //Botão de Jogar
        btnAvancar.setBounds(533, 157, 145, 146);
        btnAvancar.setIcon(imgAvançar);
        btnAvancar.setBorderPainted(false);
        btnAvancar.setContentAreaFilled(false);
         */
        setLayout(null);
        /*   add(btnReiniciar);
        add(btnVoltar);
        add(btnAvancar);*/
    }

    @Override
    public void paintComponent(Graphics g) {
        s.draw(g);
        a1.draw(g);
        a2.draw(g);
        a3.draw(g);
        a4.draw(g);
        
        b1.draw(g);
        b2.draw(g);
        b3.draw(g);
        b4.draw(g);
        
        c1.draw(g);
        c2.draw(g);
        c3.draw(g);
        c4.draw(g);
    }
}
