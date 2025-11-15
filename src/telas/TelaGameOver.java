package telas;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import som.Som;
import util.ObjImagem;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */

public class TelaGameOver implements Tela {

    private boolean podeMover;
    private int auxTempo;
    private int itemSelecionado;

    private int faseAtual;

    private final JPanel panel;

    private final ObjImagem gameOver;

    private JButton btnReiniciar;
    private JButton btnVoltar;

    private final ImageIcon imgVoltar;
    private final ImageIcon imgReiniciar;
    private final ImageIcon imgVoltarPressionada;
    private final ImageIcon imgReiniciarPressionada;
    
    private Som efeito;

    public TelaGameOver(JPanel panel) {
        this.panel = panel;
        this.faseAtual = 0;
        
        efeito = new Som();

        itemSelecionado = 1;
        podeMover = false;
        auxTempo = 0;

        btnReiniciar = new JButton();
        btnVoltar = new JButton();

        imgReiniciar = new ImageIcon("src/imagens/gameOver/reiniciar.png");
        imgVoltar = new ImageIcon("src/imagens/gameOver/menu_principal.png");
        imgReiniciarPressionada = new ImageIcon("src/imagens/gameOver/reiniciar_pressionado.png");
        imgVoltarPressionada = new ImageIcon("src/imagens/gameOver/menu_principal_pressionado.png");

        //Botão de Reiniciar
        btnReiniciar.setBounds(350, 275, 57, 58);
        btnReiniciar.setIcon(imgReiniciar);
        btnReiniciar.setBorderPainted(false);
        btnReiniciar.setContentAreaFilled(false);

        //Botão de Voltar
        btnVoltar.setBounds(415, 275, 57, 58);
        btnVoltar.setIcon(imgVoltar);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setContentAreaFilled(false);

        gameOver = new ObjImagem(295, 100, 230, 168, new ImageIcon("src/imagens/gameOver/game_over.gif"));

        btnReiniciar.addActionListener((ActionEvent ae) -> {
            forestguardian.GameRunnable.mudarTela(faseAtual);
        });

        btnVoltar.addActionListener((ActionEvent ae) -> {
            forestguardian.GameRunnable.mudarTela(0);
            forestguardian.GameRunnable.tela_menu.som.iniciarMusica("src/som/menu.mp3");
        });

        btnReiniciar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnReiniciar.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                efeito.iniciarSom("src/som/mouse.mp3");
                itemSelecionado = 1;
            }
        });

        btnVoltar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnVoltar.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                efeito.iniciarSom("src/som/mouse.mp3");
                itemSelecionado = 2;
            }
        });
    }

    public void setFaseAtual(int faseAtual) {
        this.faseAtual = faseAtual;
    }

    @Override
    public void iniciar() {
        fechar();
        panel.add(btnReiniciar);
        panel.add(btnVoltar);
        efeito.iniciarMusica("src/som/game_over.mp3");
    }

    @Override
    public void fechar() {
        auxTempo = 0;
        podeMover = false;
        itemSelecionado = 1;
        btnReiniciar.setIcon(imgReiniciar);
        btnVoltar.setIcon(imgVoltar);
        panel.remove(btnReiniciar);
        panel.remove(btnVoltar);
        efeito.pararMusica();
    }

    @Override
    public void update() {
        switch (itemSelecionado) {
            case 1:
                btnReiniciar.setIcon(imgReiniciarPressionada);
                btnVoltar.setIcon(imgVoltar);
                break;
            case 2:
                btnReiniciar.setIcon(imgReiniciar);
                btnVoltar.setIcon(imgVoltarPressionada);
                break;
        }

        if (!podeMover) {
            auxTempo++;
            if (auxTempo == 20) {
                podeMover = true;
                auxTempo = 0;
            }
        }
        if (podeMover) {
            if (forestguardian.ForestGuardian.tecla.teclaAcionada(0)) {
                if (itemSelecionado > 1) {
                    itemSelecionado--;
                    auxTempo = 0;
                    podeMover = false;
                }
            } else if (forestguardian.ForestGuardian.tecla.teclaAcionada(1)) {
                if (itemSelecionado < 3) {
                    itemSelecionado++;
                    auxTempo = 0;
                    podeMover = false;
                }
            }
        }
        if (forestguardian.ForestGuardian.tecla.teclaAcionada(7) && podeMover) {
            switch (itemSelecionado) {
                case 1:
                    btnReiniciar.doClick();
                    break;
                case 2:
                    btnVoltar.doClick();
                    break;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        gameOver.draw(g);
    }
}
