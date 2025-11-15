package telas;

import forestguardian.GameRunnable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import som.Som;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */

public class TelaOpcoes implements Tela {

    private boolean podeMover;
    private int auxTempo;
    private int itemSelecionado;

    private int auxTempoVoltar;

    private JButton btnSom;
    private JButton btnMusica;
    private JButton btnCreditos;
    private JButton btnResetar;

    private final JPanel painel;

    private final ImageIcon esc;
    private final ImageIcon enter;
    
    private final ImageIcon tela;
    private final ImageIcon opcoes;
    private final ImageIcon resetar;
    private final ImageIcon creditos;
    private final ImageIcon resetarSelecionado;
    private final ImageIcon creditosSelecionado;

    private ImageIcon imgSomAtual;
    private ImageIcon imgSomPressionadaAtual;
    private ImageIcon imgMusicaAtual;
    private ImageIcon imgMusicaPressionadaAtual;

    private final ImageIcon imgSom;
    private final ImageIcon imgMusica;
    private final ImageIcon imgSomPressionada;
    private final ImageIcon imgMusicaPressionada;
    private final ImageIcon imgSomBloqueada;
    private final ImageIcon imgMusicaBloqueada;
    private final ImageIcon imgSomBloqueadaPressionada;
    private final ImageIcon imgMusicaBloqueadaPressionada;

    private Som som;

    public TelaOpcoes(JPanel painel) {

        this.painel = painel;

        btnSom = new JButton();
        btnMusica = new JButton();
        btnCreditos = new JButton();
        btnResetar = new JButton();

        itemSelecionado = 1;
        auxTempo = 0;
        podeMover = false;
        auxTempoVoltar = 0;

        som = new Som();

        esc = new ImageIcon("src/imagens/esc.png");
        enter = new ImageIcon("src/imagens/enter.png");
        
        tela = new ImageIcon("src/imagens/fundo.png");
        opcoes = new ImageIcon("src/imagens/opcao/opcoes.png");

        imgSom = new ImageIcon("src/imagens/pause/som.png");
        imgSomPressionada = new ImageIcon("src/imagens/pause/som_selecionado.png");

        imgMusica = new ImageIcon("src/imagens/pause/musica.png");
        imgMusicaPressionada = new ImageIcon("src/imagens/pause/musica_selecionado.png");

        imgSomBloqueada = new ImageIcon("src/imagens/pause/som_bloqueado.png");
        imgSomBloqueadaPressionada = new ImageIcon("src/imagens/pause/som_bloqueado_selecionado.png");

        imgMusicaBloqueada = new ImageIcon("src/imagens/pause/musica_bloqueada.png");
        imgMusicaBloqueadaPressionada = new ImageIcon("src/imagens/pause/musica_bloqueado_selecionado.png");

        creditos = new ImageIcon("src/imagens/opcao/creditos.png");
        creditosSelecionado = new ImageIcon("src/imagens/opcao/credito_selecionado.png");

        resetar = new ImageIcon("src/imagens/opcao/resetar.png");
        resetarSelecionado = new ImageIcon("src/imagens/opcao/resetar_selecionado.png");

        //Botão de Som
        btnSom.setBounds(450, 150, 57, 58);
        if (forestguardian.ForestGuardian.usuario.getSom()== 1) {
            btnSom.setIcon(imgSom);
        } else {
            btnSom.setIcon(imgSomBloqueada);
        }
        btnSom.setBorderPainted(false);
        btnSom.setContentAreaFilled(false);

        //Botão de Música
        btnMusica.setBounds(450, 220, 57, 58);
        if (forestguardian.ForestGuardian.usuario.getMusica() == 1) {
            btnMusica.setIcon(imgMusica);
        } else {
            btnMusica.setIcon(imgMusicaBloqueada);
        }
        btnMusica.setBorderPainted(false);
        btnMusica.setContentAreaFilled(false);

        //Botão de Creditos
        btnCreditos.setBounds(362, 290, 108, 32);
        btnCreditos.setIcon(creditos);
        btnCreditos.setBorderPainted(false);
        btnCreditos.setContentAreaFilled(false);

        //Botão de Resetar
        btnResetar.setBounds(365, 335, 108, 32);
        btnResetar.setIcon(resetar);
        btnResetar.setBorderPainted(false);
        btnResetar.setContentAreaFilled(false);

        if (forestguardian.ForestGuardian.usuario.getMusica() == 1) {
            imgMusicaAtual = imgMusica;
            imgMusicaPressionadaAtual = imgMusicaPressionada;
        } else {
            imgMusicaAtual = imgMusicaBloqueada;
            imgMusicaPressionadaAtual = imgMusicaBloqueadaPressionada;
        }

        if (forestguardian.ForestGuardian.usuario.getSom() == 1) {
            imgSomAtual = imgSom;
            imgSomPressionadaAtual = imgSomPressionada;
        } else {
            imgSomAtual = imgSomBloqueada;
            imgSomPressionadaAtual = imgSomBloqueadaPressionada;
        }
        
        btnSom.addActionListener((ActionEvent ae) -> {
            if (forestguardian.ForestGuardian.usuario.getSom() == 1) {
                som.paraSom();
                forestguardian.ForestGuardian.usuario.setSom(2);
            } else {
                forestguardian.ForestGuardian.usuario.setSom(1);
                som.iniciarSom("src/som/mouse.mp3");
            }
            btnSom.setFocusable(false);
        });

        btnMusica.addActionListener((ActionEvent ae) -> {
            if (forestguardian.ForestGuardian.usuario.getMusica() == 1) {
                som.pararMusica();
                forestguardian.ForestGuardian.usuario.setMusica(2);
            } else {
                forestguardian.ForestGuardian.usuario.setMusica(1);
                som.iniciarMusica("src/som/menu.mp3");
            }
            btnMusica.setFocusable(false);
        });

        btnCreditos.addActionListener((ActionEvent ae) -> {
            GameRunnable.mudarTela(8);
        });

        btnResetar.addActionListener((ActionEvent ae) -> {
            btnResetar.setFocusable(false);
            forestguardian.ForestGuardian.Frame.resetarUsuario();
            forestguardian.ForestGuardian.usuario = forestguardian.ForestGuardian.Frame.carregaEstado();
        });

        btnSom.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnSom.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                itemSelecionado = 1;
                som.iniciarSom("src/som/mouse.mp3");
            }
        });

        btnMusica.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnMusica.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                itemSelecionado = 2;
                som.iniciarSom("src/som/mouse.mp3");
            }
        });

        btnCreditos.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnCreditos.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                itemSelecionado = 3;
                som.iniciarSom("src/som/mouse.mp3");
            }
        });

        btnResetar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnResetar.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                itemSelecionado = 4;
                som.iniciarSom("src/som/mouse.mp3");
            }
        });
    }

    @Override
    public void iniciar() {
        itemSelecionado = 1;
        auxTempoVoltar = 0;
        painel.add(btnSom);
        painel.add(btnMusica);
        painel.add(btnCreditos);
        painel.add(btnResetar);
    }

    @Override
    public void fechar() {
        auxTempo = 0;
        podeMover = false;

        painel.remove(btnSom);
        painel.remove(btnMusica);
        painel.remove(btnCreditos);
        painel.remove(btnResetar);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawImage(tela.getImage(), 0, 0, 820, 500, null);
        g.drawImage(opcoes.getImage(), 196, 40, 429, 399, null);
        g.drawImage(esc.getImage(), 0, 410, 154, 39, null);
        g.drawImage(enter.getImage(),640,410,154,39,null);
    }

    @Override
    public void update() {
        if (forestguardian.ForestGuardian.usuario.getMusica() == 1) {
            imgMusicaAtual = imgMusica;
            imgMusicaPressionadaAtual = imgMusicaPressionada;
        } else {
            imgMusicaAtual = imgMusicaBloqueada;
            imgMusicaPressionadaAtual = imgMusicaBloqueadaPressionada;
        }

        if (forestguardian.ForestGuardian.usuario.getSom() == 1) {
            imgSomAtual = imgSom;
            imgSomPressionadaAtual = imgSomPressionada;
        } else {
            imgSomAtual = imgSomBloqueada;
            imgSomPressionadaAtual = imgSomBloqueadaPressionada;
        }

        if (auxTempoVoltar < 30) {
            auxTempoVoltar++;
        }
        if (forestguardian.ForestGuardian.tecla.teclaAcionada(2) && auxTempoVoltar == 30) {
            GameRunnable.mudarTela(0);
        }

        switch (itemSelecionado) {
            case 1:
                btnSom.setIcon(imgSomPressionadaAtual);
                btnMusica.setIcon(imgMusicaAtual);
                btnCreditos.setIcon(creditos);
                btnResetar.setIcon(resetar);
                break;
            case 2:
                btnSom.setIcon(imgSomAtual);
                btnMusica.setIcon(imgMusicaPressionadaAtual);
                btnCreditos.setIcon(creditos);
                btnResetar.setIcon(resetar);
                break;
            case 3:
                btnSom.setIcon(imgSomAtual);
                btnMusica.setIcon(imgMusicaAtual);
                btnCreditos.setIcon(creditosSelecionado);
                btnResetar.setIcon(resetar);
                break;
            case 4:
                btnSom.setIcon(imgSomAtual);
                btnMusica.setIcon(imgMusicaAtual);
                btnCreditos.setIcon(creditos);
                btnResetar.setIcon(resetarSelecionado);
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
            if (forestguardian.ForestGuardian.tecla.teclaAcionada(5)) {
                if (itemSelecionado > 1) {
                    itemSelecionado--;
                    auxTempo = 0;
                    podeMover = false;
                    som.iniciarSom("src/som/mouse.mp3");
                }
            } else if (forestguardian.ForestGuardian.tecla.teclaAcionada(6)) {
                if (itemSelecionado < 4) {
                    itemSelecionado++;
                    auxTempo = 0;
                    podeMover = false;
                    som.iniciarSom("src/som/mouse.mp3");
                }
            }
        }
        if (forestguardian.ForestGuardian.tecla.teclaAcionada(7) && podeMover) {
            switch (itemSelecionado) {
                case 1:
                    btnSom.doClick();
                    podeMover = false;
                    auxTempo = 0;
                    break;
                case 2:
                    btnMusica.doClick();
                    podeMover = false;
                    auxTempo = 0;
                    break;
                case 3:
                    btnCreditos.doClick();
                    podeMover = false;
                    auxTempo = 0;
                    break;
                case 4:
                    btnResetar.doClick();
                    podeMover = false;
                    auxTempo = 0;
                    break;
            }
        }
    }
}
