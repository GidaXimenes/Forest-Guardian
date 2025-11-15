package telas;

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

public class TelaPause implements Tela {

    private boolean ativada;

    private boolean podeMover;
    private int auxTempo;
    private int itemSelecionado;

    private final JPanel panel;

    private JButton btnMenu;
    private JButton btnVoltar;
    private JButton btnMusica;
    private JButton btnSom;
    
    private ImageIcon imgSomAtual;
    private ImageIcon imgSomPressionadaAtual;
    private ImageIcon imgMusicaAtual;
    private ImageIcon imgMusicaPressionadaAtual;

    private final ImageIcon pause;
    private final ImageIcon imgVoltar;
    private final ImageIcon imgMenu;
    private final ImageIcon imgVoltarPressionada;
    private final ImageIcon imgMenuPressionada;
    private final ImageIcon imgSom;
    private final ImageIcon imgMusica;
    private final ImageIcon imgSomPressionada;
    private final ImageIcon imgMusicaPressionada;
    private final ImageIcon imgSomBloqueada;
    private final ImageIcon imgMusicaBloqueada;
    private final ImageIcon imgSomBloqueadaPressionada;
    private final ImageIcon imgMusicaBloqueadaPressionada;
    
    private Som som;
    private Som efeitos;

    public TelaPause(JPanel panel) {

        this.panel = panel;

        ativada = false;
        
        som = new Som();
        efeitos = new Som();

        itemSelecionado = 1;
        podeMover = false;
        auxTempo = 0;

        pause = new ImageIcon("src/imagens/pause/pause.png");

        btnMenu = new JButton();
        btnVoltar = new JButton();
        btnMusica = new JButton();
        btnSom = new JButton();

        imgMenu = new ImageIcon("src/imagens/pause/menu.png");
        imgMenuPressionada = new ImageIcon("src/imagens/pause/menu_selecionado.png");

        imgVoltar = new ImageIcon("src/imagens/pause/continuar.png");
        imgVoltarPressionada = new ImageIcon("src/imagens/pause/continuar_selecionado.png");

        imgSom = new ImageIcon("src/imagens/pause/som.png");
        imgSomPressionada = new ImageIcon("src/imagens/pause/som_selecionado.png");

        imgMusica = new ImageIcon("src/imagens/pause/musica.png");
        imgMusicaPressionada = new ImageIcon("src/imagens/pause/musica_selecionado.png");

        imgSomBloqueada = new ImageIcon("src/imagens/pause/som_bloqueado.png");
        imgSomBloqueadaPressionada = new ImageIcon("src/imagens/pause/som_bloqueado_selecionado.png");

        imgMusicaBloqueada = new ImageIcon("src/imagens/pause/musica_bloqueada.png");
        imgMusicaBloqueadaPressionada = new ImageIcon("src/imagens/pause/musica_bloqueado_selecionado.png");
        
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

        //Botão de Continuar
        btnVoltar.setBounds(345, 300, 57, 58);
        btnVoltar.setIcon(imgVoltar);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setContentAreaFilled(false);

        //Botão de Menu
        btnMenu.setBounds(430, 300, 57, 58);
        btnMenu.setIcon(imgMenu);
        btnMenu.setBorderPainted(false);
        btnMenu.setContentAreaFilled(false);
        
        //Botão de Som
        btnSom.setBounds(450, 210, 57, 58);
        if (forestguardian.ForestGuardian.usuario.getSom()== 1) {
            btnSom.setIcon(imgSom);
        } else {
            btnSom.setIcon(imgSomBloqueada);
        }
        btnSom.setBorderPainted(false);
        btnSom.setContentAreaFilled(false);

        //Botão de Música
        btnMusica.setBounds(300, 210, 57, 58);
        if (forestguardian.ForestGuardian.usuario.getMusica() == 1) {
            btnMusica.setIcon(imgMusica);
        } else {
            btnMusica.setIcon(imgMusicaBloqueada);
        }
        btnMusica.setBorderPainted(false);
        btnMusica.setContentAreaFilled(false);

        btnVoltar.addActionListener((ActionEvent ae) -> {
            ativada = false;
        });

        btnMenu.addActionListener((ActionEvent ae) -> {
            forestguardian.GameRunnable.mudarTela(0);
            forestguardian.GameRunnable.tela_menu.som.iniciarMusica("src/som/menu.mp3");
        });

        btnMusica.addActionListener((ActionEvent ae) -> {
            if (forestguardian.ForestGuardian.usuario.getMusica() == 1) {
                som.pararMusica();
                forestguardian.ForestGuardian.usuario.setMusica(2);
            } else {
                forestguardian.ForestGuardian.usuario.setMusica(1);
                
                if(forestguardian.GameRunnable.telaAtual() == 1){
                    som.iniciarMusica("src/som/fase1.mp3");
                }
                else if(forestguardian.GameRunnable.telaAtual() == 2){
                    som.iniciarMusica("src/som/fase2.mp3");
                }
                else if(forestguardian.GameRunnable.telaAtual() == 3){
                    som.iniciarMusica("src/som/fase3.mp3");
                } 
            }
            btnMusica.setFocusable(false);
        });

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

        //Adicionando eventos aos botões
        btnVoltar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               btnVoltar.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                efeitos.iniciarSom("src/som/mouse.mp3");
                itemSelecionado = 3;
            }
        });

        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               btnMenu.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                efeitos.iniciarSom("src/som/mouse.mp3");
                itemSelecionado = 4;
            }
        });

        btnSom.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnSom.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                efeitos.iniciarSom("src/som/mouse.mp3");
                itemSelecionado = 2;
            }
        });

        btnMusica.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnMusica.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                efeitos.iniciarSom("src/som/mouse.mp3");
                itemSelecionado = 1;
            }
        });
    }

    public boolean getAtivada() {
        return ativada;
    }

    public void setAtivada(boolean ativada) {
        this.ativada = ativada;
    }

    @Override
    public void iniciar() {
        fechar();
        panel.add(btnVoltar);
        panel.add(btnMenu);
        panel.add(btnSom);
        panel.add(btnMusica);
    }

    @Override
    public void fechar() {
        podeMover = false;

        itemSelecionado = 1;
        auxTempo = 0;

        btnMenu.setIcon(imgMenu);
        btnVoltar.setIcon(imgVoltar);
        btnSom.setIcon(imgSom);
        btnMusica.setIcon(imgMusica);

        panel.remove(btnMenu);
        panel.remove(btnVoltar);
        panel.remove(btnSom);
        panel.remove(btnMusica);
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
        
        switch (itemSelecionado) {
            case 1:
                btnMenu.setIcon(imgMenu);
                btnVoltar.setIcon(imgVoltar);
                btnSom.setIcon(imgSomAtual);
                btnMusica.setIcon(imgMusicaPressionadaAtual);
                break;
            case 2:
                btnMenu.setIcon(imgMenu);
                btnVoltar.setIcon(imgVoltar);
                btnSom.setIcon(imgSomPressionadaAtual);
                btnMusica.setIcon(imgMusicaAtual);
                break;
            case 3:
                btnMenu.setIcon(imgMenu);
                btnVoltar.setIcon(imgVoltarPressionada);
                btnSom.setIcon(imgSomAtual);
                btnMusica.setIcon(imgMusicaAtual);
                break;
            case 4:
                btnMenu.setIcon(imgMenuPressionada);
                btnVoltar.setIcon(imgVoltar);
                btnSom.setIcon(imgSomAtual);
                btnMusica.setIcon(imgMusicaAtual);
                break;
        }

        if (!podeMover) {
            auxTempo++;
            if (auxTempo == 10) {
                podeMover = true;
                auxTempo = 0;
            }
        }

        if (podeMover) {
            if (forestguardian.ForestGuardian.tecla.teclaAcionada(0)) {
                if (itemSelecionado > 1) {
                    itemSelecionado--;
                }
            } else if (forestguardian.ForestGuardian.tecla.teclaAcionada(1)) {
                if (itemSelecionado < 4) {
                    itemSelecionado++;
                }
            } else if (forestguardian.ForestGuardian.tecla.teclaAcionada(6)) {
                if (itemSelecionado == 1) {
                    itemSelecionado = 3;
                } else if (itemSelecionado == 2) {
                    itemSelecionado = 4;
                }
            } else if (forestguardian.ForestGuardian.tecla.teclaAcionada(5)) {
                if (itemSelecionado == 3) {
                    itemSelecionado = 1;
                } else if (itemSelecionado == 4) {
                    itemSelecionado = 2;
                }
            }
            auxTempo = 0;
            podeMover = false;
        }
        
        if (forestguardian.ForestGuardian.tecla.teclaAcionada(7)) {
            switch (itemSelecionado) {
                case 1:
                    btnSom.doClick();
                    break;
                case 2:
                    btnMusica.doClick();
                    break;
                case 3:
                    btnVoltar.doClick();
                    break;
                case 4:
                    btnMenu.doClick();
                    break;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(pause.getImage(), 196, 70, 429, 399, null);
    }
}
