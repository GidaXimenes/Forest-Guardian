package telas;

import forestguardian.GameRunnable;
import forestguardian.Painel;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import personagens.MegaKart;
import personagens.Snow;
import som.Som;
import util.ObjImagem;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */
public class TelaMenu implements Tela {

    private final ImageIcon logo;

    private boolean podeMover;
    private int auxTempo;
    private int itemSelecionado;

    //Declaração das Imagens
    private final ObjImagem plataforma1;
    private final ObjImagem plataforma2;

    private final ImageIcon camada0;
    private final ImageIcon camada1;
    private final ImageIcon camada2;
    private final ImageIcon camada3;

    private final ArrayList<ObjImagem> listaCenariosCamada1;
    private final ArrayList<ObjImagem> listaCenariosCamada2;
    private final ArrayList<ObjImagem> listaCenariosCamada3;

    /*private ObjImagem fundo1;
    private ObjImagem fundo2;*/
    private final MegaKart kart;
    private final Snow snow;

    public ImageIcon play;
    public ImageIcon playClick;
    public ImageIcon option;
    public ImageIcon optionClick;
    public ImageIcon help;
    public ImageIcon helpClick;
    public ImageIcon leave;
    public ImageIcon leaveClick;

    //Declaração dos Botões
    public JButton btnPlay;
    public JButton btnOption;
    public JButton btnHelp;
    public JButton btnLeave;

    private final JPanel painel;

    public Som som;
    public Som efeitos;

    public TelaMenu(JPanel painel) {

        this.painel = painel;

        logo = new ImageIcon("src/imagens/logo.png");

        som = new Som();
        som.setRepetir(true);
        som.iniciarMusica("src/som/menu.mp3");

        efeitos = new Som();
        efeitos.setRepetir(false);

        listaCenariosCamada1 = new ArrayList<>();
        listaCenariosCamada2 = new ArrayList<>();
        listaCenariosCamada3 = new ArrayList<>();

        itemSelecionado = 1;
        auxTempo = 0;
        podeMover = false;

        plataforma1 = new ObjImagem(0, 0, 820, 480, new ImageIcon("src/imagens/fase1/mapa_00.png"));
        plataforma2 = new ObjImagem(820, 0, 820, 480, new ImageIcon("src/imagens/fase1/mapa_00.png"));

        camada0 = new ImageIcon("src/imagens/fase1/camada0.png");
        camada1 = new ImageIcon("src/imagens/fase1/camada1.png");
        camada2 = new ImageIcon("src/imagens/fase1/camada2.png");
        camada3 = new ImageIcon("src/imagens/fase1/camada3.png");

        listaCenariosCamada1.add(new ObjImagem(0, 0, 820, 480, camada1));
        listaCenariosCamada1.add(new ObjImagem(820, 0, 820, 480, camada1));

        listaCenariosCamada2.add(new ObjImagem(0, 0, 820, 480, camada2));
        listaCenariosCamada2.add(new ObjImagem(820, 0, 820, 480, camada2));

        listaCenariosCamada3.add(new ObjImagem(0, 0, 820, 480, camada3));
        listaCenariosCamada3.add(new ObjImagem(820, 0, 820, 480, camada3));

        snow = new Snow(20, 320, 85, 118, 5, 15);
        kart = new MegaKart(1583, 387, 57, 53, -5, 5, 130);

        snow.moveLeft();
        snow.moveRight();

        play = new ImageIcon("src/imagens/botoes/jogar.png");
        playClick = new ImageIcon("src/imagens/botoes/jogar_selecionado.png");

        option = new ImageIcon("src/imagens/botoes/opcoes.png");
        optionClick = new ImageIcon("src/imagens/botoes/opcoes_selecionado.png");

        help = new ImageIcon("src/imagens/botoes/ajuda.png");
        helpClick = new ImageIcon("src/imagens/botoes/ajuda_selecionado.png");

        leave = new ImageIcon("src/imagens/botoes/sair.png");
        leaveClick = new ImageIcon("src/imagens/botoes/sair_selecionado.png");

        btnPlay = new JButton();
        btnOption = new JButton();
        btnHelp = new JButton();
        btnLeave = new JButton();

        //Botão de Jogar
        btnPlay.setBounds(340, 130, 140, 80);
        btnPlay.setIcon(play);
        btnPlay.setBorderPainted(false);
        btnPlay.setContentAreaFilled(false);

        //Botão de Opção
        btnOption.setBounds(340, 200, 140, 80);
        btnOption.setIcon(option);
        btnOption.setBorderPainted(false);
        btnOption.setContentAreaFilled(false);

        //Botão de Ajuda
        btnHelp.setBounds(340, 270, 140, 80);
        btnHelp.setIcon(help);
        btnHelp.setBorderPainted(false);
        btnHelp.setContentAreaFilled(false);

        //Botão de Sair
        btnLeave.setBounds(340, 340, 140, 80);
        btnLeave.setIcon(leave);
        btnLeave.setBorderPainted(false);
        btnLeave.setContentAreaFilled(false);

        btnPlay.addActionListener((ActionEvent ae) -> {
            fechar();
            GameRunnable.mudarTela(7);
        });

        btnHelp.addActionListener((ActionEvent ae) -> {
            fechar();
            GameRunnable.mudarTela(5);
        });

        btnOption.addActionListener((ActionEvent ae) -> {
            fechar();
            GameRunnable.mudarTela(6);
        });

        btnLeave.addActionListener((ActionEvent ae) -> {
            Painel.play = false;
        });

        //Adicionar Evento dos Mouse nos botões        
        //Botão de Iniciar Jogo
        btnPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnPlay.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                efeitos.iniciarSom("src/som/mouse.mp3");
                itemSelecionado = 1;
            }

        });

        //Botão de Opções
        btnOption.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnOption.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                efeitos.iniciarSom("src/som/mouse.mp3");
                itemSelecionado = 2;
            }
        });

        //Botão de Ajuda
        btnHelp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnHelp.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                efeitos.iniciarSom("src/som/mouse.mp3");
                itemSelecionado = 3;
            }
        });

        //Botão de Sair
        btnLeave.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnLeave.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                efeitos.iniciarSom("src/som/mouse.mp3");
                itemSelecionado = 4;
            }
        });
    }

    @Override
    public void iniciar() {
        fechar();
        forestguardian.ForestGuardian.usuario.setNumeroVidas(5);
        itemSelecionado = 1;
        painel.add(btnPlay);
        painel.add(btnOption);
        painel.add(btnHelp);
        painel.add(btnLeave);
    }

    @Override
    public void fechar() {
        auxTempo = 0;
        podeMover = false;
        btnPlay.setIcon(play);
        btnOption.setIcon(option);
        btnHelp.setIcon(help);
        btnLeave.setIcon(leave);
        itemSelecionado = 1;

        painel.remove(btnPlay);
        painel.remove(btnOption);
        painel.remove(btnHelp);
        painel.remove(btnLeave);
    }

    @Override
    public void update() {

        plataforma1.moveHorizontal(-5);
        plataforma2.moveHorizontal(-5);

        for (ObjImagem o : listaCenariosCamada1) {
            o.moveHorizontal(-3);
            if (o.getX() < -820) {
                o.setX(815);
            }
        }
        for (ObjImagem o : listaCenariosCamada2) {
            o.moveHorizontal(-4);
            if (o.getX() < -820) {
                o.setX(815);
            }
        }
        for (ObjImagem o : listaCenariosCamada3) {
            o.moveHorizontal(-5);
            if (o.getX() < -820) {
                o.setX(815);
            }
        }
        kart.moveHorizontal(-10);
        if (plataforma1.getX() < -820) {
            plataforma1.setX(815);
        } else if (plataforma2.getX() < -820) {
            plataforma2.setX(815);

        }
        if (kart.getX() < -57) {
            kart.setX(1583);
        }
        if (kart.getX() < 175 && kart.getX() > 50) {
            snow.pulo(-20, 2);
        }

        if (snow.getCaindo() && (snow.getY() + snow.getLargura() > 428)) {
            snow.setCaindo(false);
            snow.moveLeft();
            snow.moveRight();
            snow.setY(320);
        }
        snow.update();

        switch (itemSelecionado) {
            case 1:
                btnPlay.setIcon(playClick);
                btnOption.setIcon(option);
                btnHelp.setIcon(help);
                btnLeave.setIcon(leave);
                break;
            case 2:
                btnPlay.setIcon(play);
                btnOption.setIcon(optionClick);
                btnHelp.setIcon(help);
                btnLeave.setIcon(leave);
                break;
            case 3:
                btnPlay.setIcon(play);
                btnOption.setIcon(option);
                btnHelp.setIcon(helpClick);
                btnLeave.setIcon(leave);
                break;
            case 4:
                btnPlay.setIcon(play);
                btnOption.setIcon(option);
                btnHelp.setIcon(help);
                btnLeave.setIcon(leaveClick);
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
                    efeitos.iniciarSom("src/som/mouse.mp3");
                    itemSelecionado--;
                    auxTempo = 0;
                    podeMover = false;
                }
            } else if (forestguardian.ForestGuardian.tecla.teclaAcionada(6)) {
                if (itemSelecionado < 4) {
                    efeitos.iniciarSom("src/som/mouse.mp3");
                    itemSelecionado++;
                    auxTempo = 0;
                    podeMover = false;
                }
            }
        }
        if (forestguardian.ForestGuardian.tecla.teclaAcionada(7) && podeMover) {
            switch (itemSelecionado) {
                case 1:
                    btnPlay.doClick();
                    break;
                case 2:
                    btnOption.doClick();
                    break;
                case 3:
                    btnHelp.doClick();
                    break;
                case 4:
                    btnLeave.doClick();
                    break;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(camada0.getImage(), 0, 0, 820, 500, null);
        listaCenariosCamada1.forEach((o) -> {
            o.draw(g);
        });
        listaCenariosCamada2.forEach((o) -> {
            o.draw(g);
        });
        listaCenariosCamada3.forEach((o) -> {
            o.draw(g);
        });
        plataforma1.draw(g);
        plataforma2.draw(g);
        snow.draw(g);
        kart.draw(g);

        g.drawImage(logo.getImage(), 199, 30, 412, 105, null);
    }
}
