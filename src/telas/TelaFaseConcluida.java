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
public class TelaFaseConcluida implements Tela {

    private boolean podeMover;
    private int auxTempo;
    private int itemSelecionado;

    private boolean ultimaFase;

    private int faseAtual;

    private final JPanel panel;

    private final ObjImagem faseConcluida;

    private JButton btnReiniciar;
    private JButton btnVoltar;
    private JButton btnAvancar;

    private final ImageIcon imgVoltar;
    private final ImageIcon imgReiniciar;
    private final ImageIcon imgAvançar;
    private final ImageIcon imgVoltarPressionada;
    private final ImageIcon imgReiniciarPressionada;
    private final ImageIcon imgAvançarPressionada;
    
    private Som efeito;
    
    public TelaFaseConcluida(JPanel panel) {
        this.panel = panel;
        this.faseAtual = 0;

        itemSelecionado = 1;
        podeMover = false;
        auxTempo = 0;
        
        efeito = new Som();

        btnReiniciar = new JButton();
        btnVoltar = new JButton();
        btnAvancar = new JButton();

        imgReiniciar = new ImageIcon("src/imagens/gameOver/reiniciar.png");
        imgVoltar = new ImageIcon("src/imagens/gameOver/menu_principal.png");
        imgAvançar = new ImageIcon("src/imagens/conclusaoFase/avancar.png");
        imgReiniciarPressionada = new ImageIcon("src/imagens/gameOver/reiniciar_pressionado.png");
        imgVoltarPressionada = new ImageIcon("src/imagens/gameOver/menu_principal_pressionado.png");
        imgAvançarPressionada = new ImageIcon("src/imagens/conclusaoFase/avancar_pressionado.png");

        //Botão de Jogar
        btnReiniciar.setBounds(315, 255, 57, 58);
        btnReiniciar.setIcon(imgReiniciar);
        btnReiniciar.setBorderPainted(false);
        btnReiniciar.setContentAreaFilled(false);

        //Botão de Jogar
        btnVoltar.setBounds(380, 255, 57, 58);
        btnVoltar.setIcon(imgVoltar);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setContentAreaFilled(false);

        //Botão de Jogar
        btnAvancar.setBounds(445, 255, 57, 58);
        btnAvancar.setIcon(imgAvançar);
        btnAvancar.setBorderPainted(false);
        btnAvancar.setContentAreaFilled(false);

        faseConcluida = new ObjImagem(267, 130, 276, 105, new ImageIcon("src/imagens/conclusaoFase/fase_concluida.png"));

        btnReiniciar.addActionListener((ActionEvent ae) -> {
            forestguardian.GameRunnable.mudarTela(faseAtual);
        });
        btnAvancar.addActionListener((ActionEvent ae) -> {
            forestguardian.GameRunnable.mudarTela(faseAtual + 1);
        });
        btnVoltar.addActionListener((ActionEvent ae) -> {     
            forestguardian.GameRunnable.mudarTela(0);
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

            @Override
            public void mouseExited(MouseEvent e) {
                btnReiniciar.setIcon(imgReiniciar);
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

        btnAvancar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnAvancar.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                efeito.iniciarSom("src/som/mouse.mp3");
                itemSelecionado = 3;
            }

        });
    }

    public TelaFaseConcluida(JPanel panel, boolean ultimaFase) {

        this.panel = panel;
        this.faseAtual = 0;
        this.ultimaFase = ultimaFase;
        
        efeito = new Som();
        
        itemSelecionado = 1;
        podeMover = false;
        auxTempo = 0;

        btnReiniciar = new JButton();
        btnVoltar = new JButton();
        btnAvancar = new JButton();

        imgReiniciar = new ImageIcon("src/imagens/gameOver/reiniciar.png");
        imgVoltar = new ImageIcon("src/imagens/gameOver/menu_principal.png");
        imgAvançar = new ImageIcon("src/imagens/conclusaoFase/avancar.png");
        imgReiniciarPressionada = new ImageIcon("src/imagens/gameOver/reiniciar_pressionado.png");
        imgVoltarPressionada = new ImageIcon("src/imagens/gameOver/menu_principal_pressionado.png");
        imgAvançarPressionada = new ImageIcon("src/imagens/conclusaoFase/avancar_pressionado.png");

        //Botão de Jogar
        btnReiniciar.setBounds(315, 255, 57, 58);
        btnReiniciar.setIcon(imgReiniciar);
        btnReiniciar.setBorderPainted(false);
        btnReiniciar.setContentAreaFilled(false);

        //Botão de Jogar
        btnVoltar.setBounds(380, 255, 57, 58);
        btnVoltar.setIcon(imgVoltar);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setContentAreaFilled(false);

        //Botão de Jogar
        btnAvancar.setBounds(445, 255, 57, 58);
        btnAvancar.setIcon(imgAvançar);
        btnAvancar.setBorderPainted(false);
        btnAvancar.setContentAreaFilled(false);

        faseConcluida = new ObjImagem(267, 130, 276, 105, new ImageIcon("src/imagens/conclusaoFase/fase_concluida.png"));

        btnReiniciar.addActionListener((ActionEvent ae) -> {
            forestguardian.GameRunnable.mudarTela(faseAtual);
        });
        btnAvancar.addActionListener((ActionEvent ae) -> {
            forestguardian.GameRunnable.mudarTela(++faseAtual);
        });
        btnVoltar.addActionListener((ActionEvent ae) -> {
            forestguardian.GameRunnable.mudarTela(0);
        });

        btnReiniciar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnReiniciar.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                itemSelecionado = 1;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnReiniciar.setIcon(imgReiniciar);
            }

        });

        btnVoltar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnVoltar.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                itemSelecionado = 2;
            }
        });

        btnAvancar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnAvancar.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                itemSelecionado = 3;
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
        if (!ultimaFase) {
            panel.add(btnAvancar);
        }
        efeito.iniciarMusica("src/som/vitoria.mp3");
    }

    @Override
    public void fechar() {
        podeMover = false;
        itemSelecionado = 1;
        auxTempo = 0;
        btnReiniciar.setIcon(imgReiniciar);
        btnVoltar.setIcon(imgVoltar);
        btnAvancar.setIcon(imgAvançar);
        panel.remove(btnReiniciar);
        panel.remove(btnVoltar);
        panel.remove(btnAvancar);
        efeito.pararMusica();
    }

    @Override
    public void update() {
        switch (itemSelecionado) {
            case 1:
                btnReiniciar.setIcon(imgReiniciarPressionada);
                btnVoltar.setIcon(imgVoltar);
                btnAvancar.setIcon(imgAvançar);
                break;
            case 2:
                btnReiniciar.setIcon(imgReiniciar);
                btnVoltar.setIcon(imgVoltarPressionada);
                btnAvancar.setIcon(imgAvançar);
                break;
            case 3:
                btnReiniciar.setIcon(imgReiniciar);
                btnVoltar.setIcon(imgVoltar);
                btnAvancar.setIcon(imgAvançarPressionada);
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
                int maximo = (ultimaFase ? 2 : 3);
                if (itemSelecionado < maximo) {
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
                case 3:
                    btnAvancar.doClick();
                    break;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        faseConcluida.draw(g);
    }
}
