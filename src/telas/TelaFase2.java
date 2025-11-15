package telas;

import forestguardian.ForestGuardian;
import personagens.Snow;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import personagens.MegaKart;
import personagens.OmegaTroopers;
import personagens.ShockTroopers;
import som.Som;
import util.Colisao;
import util.Hud;
import util.ObjImagem;
import util.Objeto;
import util.Parede;
import util.Tiro;

/**
 *
 * @author Ayumi, Francico e Gideão
 */
public class TelaFase2 implements Tela {

    private JButton btnPause;

    private final ImageIcon pauseImg;
    private final ImageIcon pauseSelecionadoImg;

    private final JPanel panel;

    private boolean gameOver;
    private final TelaGameOver telaGameOver;

    private boolean faseConcluida;
    private final TelaFaseConcluida telaFaseConcluida;

    private boolean pause;
    private final TelaPause telaPause;
    private int auxPause;

    private Hud hud;

    private ObjImagem msgAviso;
    private final ArrayList<ObjImagem> listaMensagens;
    private boolean aviso;
    private int auxAviso;

    private final ArrayList<ObjImagem> listaCerejas;
    private final ArrayList<ObjImagem> listaCenarios;
    private final ArrayList<ObjImagem> listaJaulas;
    private final ArrayList<ObjImagem> listaAnimais;
    private final ArrayList<Objeto> listaObstaculos;
    private final ArrayList<Parede> listaParedes;
    private final ArrayList<ShockTroopers> shocks;
    private final ArrayList<OmegaTroopers> omegas;
    private final ArrayList<MegaKart> karts;

    private final ArrayList<ObjImagem> listaCenariosCamada1;
    private final ArrayList<ObjImagem> listaCenariosCamada2;
    private final ArrayList<ObjImagem> listaCenariosCamada3;

    private final ImageIcon camada0;
    private final ImageIcon camada1;
    private final ImageIcon camada2;
    private final ImageIcon camada3;

    private final int numeroMaximoAnimais;

    private Snow snow;

    private final Som som;
    private final Som efeito;

    public TelaFase2(JPanel j) {
        panel = j;

        pause = false;
        gameOver = false;
        faseConcluida = false;

        telaPause = new TelaPause(panel);
        telaFaseConcluida = new TelaFaseConcluida(panel);
        telaGameOver = new TelaGameOver(panel);

        telaGameOver.setFaseAtual(2);
        telaFaseConcluida.setFaseAtual(2);

        listaCenarios = new ArrayList<>();
        listaObstaculos = new ArrayList<>();
        listaAnimais = new ArrayList<>();
        listaParedes = new ArrayList<>();
        listaCerejas = new ArrayList<>();
        listaJaulas = new ArrayList<>();
        shocks = new ArrayList<>();
        karts = new ArrayList<>();
        omegas = new ArrayList<>();

        auxPause = 0;
        numeroMaximoAnimais = 5;
        hud = new Hud(numeroMaximoAnimais);

        listaCenariosCamada1 = new ArrayList<>();
        listaCenariosCamada2 = new ArrayList<>();
        listaCenariosCamada3 = new ArrayList<>();

        listaMensagens = new ArrayList<>();
        auxAviso = 0;
        aviso = true;
       

        camada0 = new ImageIcon("src/imagens/fase2/camada0.png");
        camada1 = new ImageIcon("src/imagens/fase2/camada1.png");
        camada2 = new ImageIcon("src/imagens/fase2/camada2.png");
        camada3 = new ImageIcon("src/imagens/fase2/camada3.png");

        posicionaObjetos();

        btnPause = new JButton();

        pauseImg = new ImageIcon("src/imagens/hud/pause.png");
        pauseSelecionadoImg = new ImageIcon("src/imagens/hud/pause_selecionado.png");

        //Botão de Pause
        btnPause.setBounds(772, 0, 40, 40);
        btnPause.setIcon(pauseImg);
        btnPause.setBorderPainted(false);
        btnPause.setContentAreaFilled(false);

        //Evento do botão de pause
        btnPause.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnPause.doClick();
            }
        });

        btnPause.addActionListener((ActionEvent ae) -> {
            btnPause.setFocusable(false);
            if (!pause) {
                telaPause.iniciar();
                hud.pause(true);
                telaPause.setAtivada(true);
                pause = true;
                auxPause = 20;
            } else {
                hud.pause(false);
                telaPause.setAtivada(false);
                telaPause.fechar();
                pause = false;
                auxPause = 20;
            }
        });

        som = new Som();
        efeito = new Som();
        efeito.setRepetir(false);
    }

    @Override
    public void fechar() {
        configInicial();
        panel.remove(btnPause);
        telaGameOver.fechar();
        telaPause.fechar();
        telaFaseConcluida.fechar();
        fecharMusica();
    }

    @Override
    public void iniciar() {
        fechar();
        auxAviso = 0;
        aviso = true;
        panel.add(btnPause);
        forestguardian.ForestGuardian.usuario.setNumeroVidas(5);
        hud.setPontos(0);
        forestguardian.ForestGuardian.usuario.setNumeroVidas(5);
        pause = false;
        gameOver = false;
        faseConcluida = false;
        iniciarMusica();

    }

    public void iniciarMusica() {
        som.iniciarMusica("src/som/fase2.mp3");
        som.setRepetir(true);
    }

    public void fecharMusica() {
        som.pararMusica();
    }

    public final void posicionaObjetos() {
        snow = new Snow(0, 312, 85, 118, 5, 15);

        hud.pause(false);

        listaMensagens.clear();
        listaMensagens.add(new ObjImagem(336, 150, 148, 38, new ImageIcon("src/imagens/aviso.png")));
        listaMensagens.add(new ObjImagem(336, 150, 148, 38, new ImageIcon("src/imagens/animais/msg_urso.png")));
        listaMensagens.add(new ObjImagem(336, 150, 148, 38, new ImageIcon("src/imagens/animais/msg_panda.png")));
        listaMensagens.add(new ObjImagem(336, 150, 148, 38, new ImageIcon("src/imagens/animais/msg_macaco.png")));
        listaMensagens.add(new ObjImagem(336, 150, 148, 38, new ImageIcon("src/imagens/animais/msg_arara.png")));
        listaMensagens.add(new ObjImagem(336, 150, 148, 38, new ImageIcon("src/imagens/animais/msg_onca.png")));

        msgAviso = listaMensagens.get(0);
        
        listaCenarios.clear();
        listaCenarios.add(new ObjImagem(0, 0, 820, 480, new ImageIcon("src/imagens/fase2/mapa_00.png")));
        listaCenarios.add(new ObjImagem(820, 0, 820, 480, new ImageIcon("src/imagens/fase2/mapa_01.png")));
        listaCenarios.add(new ObjImagem(1640, 0, 820, 480, new ImageIcon("src/imagens/fase2/mapa_02.png")));
        listaCenarios.add(new ObjImagem(2460, 0, 820, 480, new ImageIcon("src/imagens/fase2/mapa_03.png")));
        listaCenarios.add(new ObjImagem(3280, 0, 820, 480, new ImageIcon("src/imagens/fase2/mapa_04.png")));
        listaCenarios.add(new ObjImagem(4100, 0, 820, 480, new ImageIcon("src/imagens/fase2/mapa_05.png")));
        listaCenarios.add(new ObjImagem(4920, 0, 820, 480, new ImageIcon("src/imagens/fase2/mapa_06.png")));
        listaCenarios.add(new ObjImagem(5740, 0, 820, 480, new ImageIcon("src/imagens/fase2/mapa_07.png")));
        listaCenarios.add(new ObjImagem(6560, 0, 820, 480, new ImageIcon("src/imagens/fase2/mapa_08.png")));
        listaCenarios.add(new ObjImagem(7380, 0, 820, 480, new ImageIcon("src/imagens/fase2/mapa_09.png")));
        listaCenarios.add(new ObjImagem(8200, 0, 820, 480, new ImageIcon("src/imagens/fase2/mapa_10.png")));

        listaCenariosCamada1.clear();
        listaCenariosCamada1.add(new ObjImagem(-820, 0, 820, 480, camada1));
        listaCenariosCamada1.add(new ObjImagem(0, 0, 820, 480, camada1));
        listaCenariosCamada1.add(new ObjImagem(820, 0, 820, 480, camada1));
        listaCenariosCamada1.add(new ObjImagem(1640, 0, 820, 480, camada1));
        listaCenariosCamada1.add(new ObjImagem(2460, 0, 820, 480, camada1));
        listaCenariosCamada1.add(new ObjImagem(3280, 0, 820, 480, camada1));
        listaCenariosCamada1.add(new ObjImagem(4100, 0, 820, 480, camada1));
        listaCenariosCamada1.add(new ObjImagem(4920, 0, 820, 480, camada1));
        listaCenariosCamada1.add(new ObjImagem(5740, 0, 820, 480, camada1));
        listaCenariosCamada1.add(new ObjImagem(6560, 0, 820, 480, camada1));
        listaCenariosCamada1.add(new ObjImagem(7380, 0, 820, 480, camada1));

        listaCenariosCamada2.clear();
        listaCenariosCamada2.add(new ObjImagem(-820, 0, 820, 480, camada2));
        listaCenariosCamada2.add(new ObjImagem(0, 0, 820, 480, camada2));
        listaCenariosCamada2.add(new ObjImagem(820, 0, 820, 480, camada2));
        listaCenariosCamada2.add(new ObjImagem(1640, 0, 820, 480, camada2));
        listaCenariosCamada2.add(new ObjImagem(2460, 0, 820, 480, camada2));
        listaCenariosCamada2.add(new ObjImagem(3280, 0, 820, 480, camada2));
        listaCenariosCamada2.add(new ObjImagem(4100, 0, 820, 480, camada2));
        listaCenariosCamada2.add(new ObjImagem(4920, 0, 820, 480, camada2));
        listaCenariosCamada2.add(new ObjImagem(5740, 0, 820, 480, camada2));
        listaCenariosCamada2.add(new ObjImagem(6560, 0, 820, 480, camada2));
        listaCenariosCamada2.add(new ObjImagem(7380, 0, 820, 480, camada2));

        listaCenariosCamada3.clear();
        listaCenariosCamada3.add(new ObjImagem(0, 0, 820, 480, camada3));
        listaCenariosCamada3.add(new ObjImagem(820, 0, 820, 480, camada3));
        listaCenariosCamada3.add(new ObjImagem(1640, 0, 820, 480, camada3));
        listaCenariosCamada3.add(new ObjImagem(2460, 0, 820, 480, camada3));
        listaCenariosCamada3.add(new ObjImagem(3280, 0, 820, 480, camada3));
        listaCenariosCamada3.add(new ObjImagem(4100, 0, 820, 480, camada3));
        listaCenariosCamada3.add(new ObjImagem(4920, 0, 820, 480, camada3));
        listaCenariosCamada3.add(new ObjImagem(5740, 0, 820, 480, camada3));
        listaCenariosCamada3.add(new ObjImagem(6560, 0, 820, 480, camada3));
        listaCenariosCamada3.add(new ObjImagem(7380, 0, 820, 480, camada3));
        listaCenariosCamada3.add(new ObjImagem(8200, 0, 820, 480, camada3));

        listaAnimais.clear();
        listaAnimais.add(new ObjImagem(2150, 114, 156, 129, new ImageIcon("src/imagens/animais/urso.png")));
        listaAnimais.add(new ObjImagem(3445, 332, 104, 86, new ImageIcon("src/imagens/animais/panda.png")));
        listaAnimais.add(new ObjImagem(5190, 216, 104, 86, new ImageIcon("src/imagens/animais/macaco.png")));
        listaAnimais.add(new ObjImagem(6240, 345, 104, 86, new ImageIcon("src/imagens/animais/arara_azul.png")));
        listaAnimais.add(new ObjImagem(7550, 48, 156, 129, new ImageIcon("src/imagens/animais/onca_pintada.png")));

        listaObstaculos.clear();
        listaObstaculos.add(new Objeto(0, 435, 1350, 10));
        listaObstaculos.add(new Objeto(1360, 240, 1005, 10));
        listaObstaculos.add(new Objeto(2345, 190, 220, 10));
        listaObstaculos.add(new Objeto(2600, 308, 222, 10));
        listaObstaculos.add(new Objeto(2822, 420, 960, 10));
        listaObstaculos.add(new Objeto(3738, 308, 222, 10));
        listaObstaculos.add(new Objeto(3980, 195, 232, 10));
        listaObstaculos.add(new Objeto(4510, 303, 790, 10));
        listaObstaculos.add(new Objeto(5620, 435, 790, 10));
        listaObstaculos.add(new Objeto(6395, 280, 550, 10));
        listaObstaculos.add(new Objeto(7185, 175, 505, 10));
        listaObstaculos.add(new Objeto(7185, 370, 2000, 10));

        listaCerejas.clear();
        listaCerejas.add(new ObjImagem(2700, 270, 35, 30, new ImageIcon("src/imagens/cereja.gif")));
        listaCerejas.add(new ObjImagem(5700, 400, 35, 30, new ImageIcon("src/imagens/cereja.gif")));
        listaCerejas.add(new ObjImagem(7230, 140, 35, 30, new ImageIcon("src/imagens/cereja.gif")));

        listaJaulas.clear();
        listaJaulas.add(new ObjImagem(2145, 120, 170, 118, new ImageIcon("src/imagens/jaula.png")));
        listaJaulas.add(new ObjImagem(3400, 300, 170, 118, new ImageIcon("src/imagens/jaula.png")));
        listaJaulas.add(new ObjImagem(5150, 180, 170, 118, new ImageIcon("src/imagens/jaula.png")));
        listaJaulas.add(new ObjImagem(6180, 315, 170, 118, new ImageIcon("src/imagens/jaula.png")));
        listaJaulas.add(new ObjImagem(7545, 55, 170, 118, new ImageIcon("src/imagens/jaula.png")));

        listaParedes.clear();
        listaParedes.add(new Parede(1340, 250, 10, 195, true));
        listaParedes.add(new Parede(2325, 210, 10, 40, true));
        listaParedes.add(new Parede(2590, 210, 10, 80, false));
        listaParedes.add(new Parede(2835, 320, 10, 80, false));
        listaParedes.add(new Parede(3720, 320, 10, 80, true));
        listaParedes.add(new Parede(3970, 210, 10, 80, true));
        listaParedes.add(new Parede(4222, 210, 10, 200, false));
        listaParedes.add(new Parede(4500, 315, 10, 100, true));
        listaParedes.add(new Parede(5310, 315, 10, 100, false));
        listaParedes.add(new Parede(5610, 450, 10, 100, true));
        listaParedes.add(new Parede(6370, 300, 10, 100, true));
        listaParedes.add(new Parede(6960, 300, 10, 100, false));
        listaParedes.add(new Parede(7150, 390, 10, 100, true));

        karts.clear();
        karts.add(new MegaKart(1345, 187, 57, 53, 6, 5, 145));
        karts.add(new MegaKart(3970, 140, 57, 53, 6, 5, 35));
        karts.add(new MegaKart(6370, 225, 57, 53, 6, 5, 90));

        shocks.clear();
        shocks.add(new ShockTroopers(2880, 296, 128, 117, 4, 5, 165));
        shocks.add(new ShockTroopers(4455, 184, 128, 117, 4, 5, 195));
        shocks.add(new ShockTroopers(7120, 260, 128, 117, 4, 5, 125));

        omegas.clear();
        omegas.add(new OmegaTroopers(3570, 323, 95, 92, 6, 5));
        omegas.add(new OmegaTroopers(6075, 340, 95, 92, 6, 5));
        omegas.add(new OmegaTroopers(7430, 80, 95, 92, 6, 5));
    }

    //Move todos os elementos do cenario para Direita 
    //Aumentando o X
    public void moverCenarioHorizontalmente(int x) {
        listaCenarios.forEach((objeto) -> {
            objeto.moveHorizontal(x);
        });
        listaObstaculos.forEach((o) -> {
            o.moveHorizontal(x);
        });
        listaAnimais.forEach((o) -> {
            o.moveHorizontal(x);
        });
        listaParedes.forEach((o) -> {
            o.moveHorizontal(x);
        });
        shocks.forEach((o) -> {
            o.moveHorizontal(x);
        });
        karts.forEach((o) -> {
            o.moveHorizontal(x);
        });
        omegas.forEach((o) -> {
            o.moveHorizontal(x);
        });
        listaCerejas.forEach((o) -> {
            o.moveHorizontal(x);
        });
        listaJaulas.forEach((o) -> {
            o.moveHorizontal(x);
        });

        for (ObjImagem o : listaCenariosCamada1) {
            if (x > 0) {
                o.moveHorizontal(x - 2);
            } else if (x < 0) {
                o.moveHorizontal(x + 2);
            }
        }

        for (ObjImagem o : listaCenariosCamada2) {
            if (x > 0) {
                o.moveHorizontal(x - 1);
            } else if (x < 0) {
                o.moveHorizontal(x + 1);
            }
        }

        for (ObjImagem o : listaCenariosCamada3) {
            if (x != 0) {
                o.moveHorizontal(x);
            }
        }

    }

    public void moverCenarioVerticalmente(int y) {
        listaCenarios.forEach((objeto) -> {
            objeto.moveVertical(y);
        });
        listaObstaculos.forEach((o) -> {
            o.moveVertical(y);
        });
        listaParedes.forEach((o) -> {
            o.moveVertical(y);
        });
        shocks.forEach((o) -> {
            o.moveVertical(y);
        });
        omegas.forEach((o) -> {
            o.moveVertical(y);
        });

        karts.forEach((o) -> {
            o.moveVertical(y);
        });
        listaCerejas.forEach((o) -> {
            o.moveVertical(y);
        });
        listaJaulas.forEach((o) -> {
            o.moveVertical(y);
        });
        listaAnimais.forEach((o) -> {
            o.moveHorizontal(y);
        });

    }

    public void colisaoObjetos() {
        for (ShockTroopers i : shocks) {
            if (Colisao.collision(i, snow)) {
                i.ataque();
                if (!snow.getAtaque() && i.getPodeAtacar() && !snow.getMorrendo() && !snow.getSofrendoDano()) {
                    snow.dano(i.getDanoCausado());
                } else if (snow.getAtaque()) {
                    if (!i.getSofrendoDano() && !i.getSumindo()) {
                        hud.aumentarPontuacao(20);
                    }
                    i.sofrerAtaque();
                }
            } else {
                i.setAtacando(false);
            }
        }

        for (MegaKart i : karts) {
            if (Colisao.collision(i, snow)) {
                if (!snow.getAtaque() && !i.getSumindo() && !snow.getMorrendo() && !snow.getSofrendoDano()) {
                    snow.dano(i.getDanoCausado());
                    i.setAtacando(true);
                    efeito.iniciarSom("src/som/explosao.mp3");
                } else if (!i.getAtacando()) {
                    if (!i.getSumindo()) {
                        hud.aumentarPontuacao(50);
                    }
                    i.sofrerAtaque();
                }
            }
        }

        for (OmegaTroopers o : omegas) {
            for (Tiro t : o.getTiros()) {
                if (Colisao.collision(t, snow)) {
                    o.removerTiro(t);
                    snow.dano(1);
                    break;
                }
            }
            if (Colisao.collision(o, snow)) {
                if (snow.getAtaque()) {
                    if (!o.getSofrendoDano() && !o.getSumindo()) {
                        hud.aumentarPontuacao(20);
                    }
                    o.sofreAtaque();
                } else if (!o.getSumindo()) {
                    snow.dano(1);
                }
            }
        }
        for (ObjImagem o : listaCerejas) {
            if (forestguardian.ForestGuardian.usuario.getNumeroVidas() < 5 && Colisao.collision(o, snow)) {
                forestguardian.ForestGuardian.usuario.aumentarVida();
                listaCerejas.remove(o);
                break;
            }
        }

        for (ObjImagem o : listaJaulas) {
            if (Colisao.collision(o, snow) && snow.getAtaque()) {
                auxAviso = 0;
                aviso = true;
                msgAviso = listaMensagens.get(listaJaulas.indexOf(o)+1);
                listaMensagens.remove(listaAnimais.indexOf(o)+1);
                
                listaJaulas.remove(o);
                hud.aumentarPontuacao(150);
                break;
            }
        }
    }

    public boolean colisaoLateral(boolean lado) {
        for (Parede o : listaParedes) {
            if (Colisao.collision(o, snow) && o.getDirecaoBloqueada() == lado) {
                return true;
            }
        }
        return false;
    }

    public void passaFase() {
        if (listaJaulas.isEmpty()) {
            ForestGuardian.usuario.setFaseAtual(3);
            ForestGuardian.usuario.setPontuacaoFase2(hud.getPontos());
            ForestGuardian.Frame.salvarUsuario();
            faseConcluida = true;
            telaFaseConcluida.iniciar();
        } else {
            auxAviso = 0;
            aviso = true;
            msgAviso = listaMensagens.get(0);
        }
    }

    public void gameOver() {
        gameOver = true;
        telaGameOver.iniciar();
    }

    public void configInicial() {
        posicionaObjetos();
    }

    @Override
    public void update() {
        if (!pause && !faseConcluida && !gameOver) {
            if (forestguardian.ForestGuardian.tecla.teclaAcionada(0) && !snow.getMorrendo()) {
                snow.moveLeft();
                snow.setDirecao(false);
                if (colisaoLateral(false)) {
                    snow.moveRight();
                } else if (listaCenarios.get(0).getX() <= -5) {
                    if (snow.getX() <= 20) {
                        moverCenarioHorizontalmente(snow.getVelocX());
                        snow.moveRight();
                    }
                }
            } else if (forestguardian.ForestGuardian.tecla.teclaAcionada(1) && !snow.getMorrendo()) {
                snow.moveRight();
                snow.setDirecao(true);
                if (colisaoLateral(true)) {
                    snow.moveLeft();
                } else if (listaCenarios.get(listaCenarios.size() - 1).getX() + listaCenarios.get(listaCenarios.size() - 1).getLargura() >= 820) {
                    if (snow.getX() >= 360) {
                        moverCenarioHorizontalmente(-snow.getVelocX());
                        snow.moveLeft();
                    }
                }
            }

            if (forestguardian.ForestGuardian.tecla.teclaAcionada(3)) {
                snow.pulo(-20, 2);
            }
            if (forestguardian.ForestGuardian.tecla.teclaAcionada(4)) {
                snow.ataque();
                forestguardian.ForestGuardian.tecla.setTeclas(KeyEvent.VK_E, false);
            }
            snow.update();

            if (snow.getCaindo() || snow.getAtaque()) {
                for (Objeto o : listaObstaculos) {
                    if (Colisao.collision(o, snow) && (o.getY() - (snow.getAltura() + snow.getY()) > -15)) {
                        snow.setCaindo(false);
                        snow.setY(o.getY() - snow.getAltura() + 10);
                    }
                }
            }
            if (!snow.getPulando() && !snow.getCaindo()) {
                boolean aux = false;
                for (Objeto o : listaObstaculos) {
                    if (Colisao.collision(snow, o)) {
                        aux = true;
                    }
                }
                if (!aux) {
                    snow.setCaindo(true);
                }
            }

            if (!snow.getPulando() && !snow.getCaindo() && !forestguardian.ForestGuardian.tecla.teclaAcionada(0) && !forestguardian.ForestGuardian.tecla.teclaAcionada(1) && !snow.getAtaque() && !forestguardian.ForestGuardian.tecla.teclaAcionada(4)) {
                snow.ImagemInicial();
            }
            for (ShockTroopers i : shocks) {
                i.update();
            }
            for (MegaKart i : karts) {
                i.update();
            }
            for (OmegaTroopers o : omegas) {
                o.update();
                o.getTiros().removeIf(t -> t.getX() > 820 || t.getX() + t.getLargura() < 0);
            }

            omegas.removeIf(i -> i.estaMorto());
            karts.removeIf(i -> i.estaMorto());
            shocks.removeIf(i -> i.estaMorto());
            colisaoObjetos();
            hud.update(numeroMaximoAnimais - listaJaulas.size());

            //Condições de reinicio ou terminio da fase
            if (snow.getY() >= 480) {
                gameOver();
            } else if (listaCenarios.get(listaCenarios.size() - 1).getX() <= 1 && snow.getX() + snow.getLargura() >= 810) {
                passaFase();
            } else if (snow.getMorto()) {
                gameOver();
            }

            if (forestguardian.ForestGuardian.tecla.teclaAcionada(2) && auxPause == 0 && !faseConcluida && !gameOver) {
                telaPause.iniciar();
                hud.pause(true);
                telaPause.setAtivada(true);
                pause = true;
                auxPause = 20;
            }
            btnPause.setIcon(pauseImg);
        }
        if (pause) {
            btnPause.setIcon(pauseSelecionadoImg);
            if (forestguardian.ForestGuardian.tecla.teclaAcionada(2) && auxPause == 0) {
                telaPause.setAtivada(false);
                hud.pause(false);
                pause = false;
                auxPause = 20;
            }
            telaPause.update();
            if (!telaPause.getAtivada()) {
                telaPause.fechar();
                pause = false;
            }
        }
        if (faseConcluida) {
            telaFaseConcluida.update();
        }

        if (gameOver) {
            telaGameOver.update();
        }
        if (auxPause > 0) {
            auxPause--;
        }
        if (auxAviso > 200) {
            aviso = false;
        } else {
            auxAviso++;
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
        listaCenarios.forEach((o) -> {
            o.draw(g);
        });
        shocks.forEach((o) -> {
            o.draw(g);
        });
        karts.forEach((o) -> {
            o.draw(g);
        });
        listaCerejas.forEach((o) -> {
            o.draw(g);
        });
        listaAnimais.forEach((o) -> {
            o.draw(g);
        });
        listaJaulas.forEach((o) -> {
            o.draw(g);
        });
        if (!gameOver) {
            snow.draw(g);
        }

        if (gameOver) {
            telaGameOver.draw(g);
        } else if (faseConcluida) {
            telaFaseConcluida.draw(g);
        } else if (pause) {
            telaPause.draw(g);
        } else {
            omegas.forEach((o) -> {
                o.draw(g);
            });
            shocks.forEach((i) -> {
                i.draw(g);
            });
            karts.forEach((i) -> {
                i.draw(g);
            });
        }

        hud.draw(g);

        if (aviso) {
            g.drawImage(msgAviso.getImagem(), msgAviso.getX(), msgAviso.getY(), msgAviso.getLargura(), msgAviso.getAltura(), null);
        }
    }

}
