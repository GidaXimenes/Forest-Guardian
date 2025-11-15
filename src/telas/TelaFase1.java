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
import personagens.ShockTroopers;
import som.Som;
import util.Colisao;
import util.Hud;
import util.ObjImagem;
import util.Objeto;
import util.Parede;

/**
 *
 * @author Ayumi, Francico e Gideão
 */
public class TelaFase1 implements Tela {

    private JButton btnPause;
    private JButton btnPularFase;

    private ObjImagem msgAviso;

    private final ArrayList<ObjImagem> listaMensagens;
    private boolean aviso;
    private int auxAviso;

    private final ImageIcon pauseImg;
    private final ImageIcon pauseSelecionadoImg;

    private boolean gameOver;
    private final TelaGameOver telaGameOver;

    private boolean faseConcluida;
    private final TelaFaseConcluida telaFaseConcluida;

    private boolean pause;
    private final TelaPause telaPause;
    private int auxPause;

    private final JPanel panel;

    private Hud hud;

    private final ArrayList<ObjImagem> listaCerejas;
    private final ArrayList<ObjImagem> listaCenarios;
    private final ArrayList<ObjImagem> listaJaulas;
    private final ArrayList<ObjImagem> listaAnimais;
    private final ArrayList<Objeto> listaObstaculos;
    private final ArrayList<Parede> listaParedes;
    private final ArrayList<ShockTroopers> shocks;
    private final ArrayList<MegaKart> karts;

    private final int numeroMaximoAnimais;

    private Snow snow;

    private final ArrayList<ObjImagem> listaCenariosCamada1;
    private final ArrayList<ObjImagem> listaCenariosCamada2;
    private final ArrayList<ObjImagem> listaCenariosCamada3;

    private final ImageIcon camada0;
    private final ImageIcon camada1;
    private final ImageIcon camada2;
    private final ImageIcon camada3;

    private final Som som;
    private final Som efeitos;

    private ArrayList<ObjImagem> animacoes;
    private int auxAnimacao;
    private boolean inicio;
    private int animacaoAtual;

    public TelaFase1(JPanel j) {
        panel = j;

        animacoes = new ArrayList<>();
        auxAnimacao = 0;
        inicio = true;
        animacaoAtual = 0;

        som = new Som();
        efeitos = new Som();

        btnPause = new JButton();
        btnPularFase = new JButton();

        listaMensagens = new ArrayList<>();
        auxAviso = 0;
        aviso = true;

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

        pause = false;
        gameOver = false;
        faseConcluida = false;

        telaPause = new TelaPause(panel);
        telaFaseConcluida = new TelaFaseConcluida(panel);
        telaGameOver = new TelaGameOver(panel);

        telaGameOver.setFaseAtual(1);
        telaFaseConcluida.setFaseAtual(1);

        auxPause = 0;

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

        listaCenarios = new ArrayList<>();
        listaObstaculos = new ArrayList<>();
        listaParedes = new ArrayList<>();
        listaCerejas = new ArrayList<>();
        listaAnimais = new ArrayList<>();
        listaJaulas = new ArrayList<>();
        shocks = new ArrayList<>();
        karts = new ArrayList<>();

        listaCenariosCamada1 = new ArrayList<>();
        listaCenariosCamada2 = new ArrayList<>();
        listaCenariosCamada3 = new ArrayList<>();

        camada0 = new ImageIcon("src/imagens/fase1/camada0.png");
        camada1 = new ImageIcon("src/imagens/fase1/camada1.png");
        camada2 = new ImageIcon("src/imagens/fase1/camada2.png");
        camada3 = new ImageIcon("src/imagens/fase1/camada3.png");

        numeroMaximoAnimais = 3;
        hud = new Hud(numeroMaximoAnimais);

        posicionaObjetos();
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

        if (!inicio) {
            panel.add(btnPause);
        }
        forestguardian.ForestGuardian.usuario.setNumeroVidas(5);
        hud.setPontos(0);
        forestguardian.ForestGuardian.usuario.setNumeroVidas(5);
        pause = false;
        gameOver = false;
        faseConcluida = false;
        iniciarMusica();
    }

    public void iniciarMusica() {
        som.iniciarMusica("src/som/fase1.mp3");
    }

    public void fecharMusica() {
        som.pararMusica();
    }

    public final void posicionaObjetos() {
        snow = new Snow(0, 312, 85, 118, 5, 15);

        animacoes.clear();
        animacoes.add(new ObjImagem(0, 0, 815, 470, new ImageIcon("src/imagens/animacao1.png")));
        animacoes.add(new ObjImagem(0, 0, 815, 470, new ImageIcon("src/imagens/animacao2.png")));
        animacoes.add(new ObjImagem(0, 0, 815, 470, new ImageIcon("src/imagens/animacao3.png")));
        animacoes.add(new ObjImagem(0, 0, 815, 470, new ImageIcon("src/imagens/animacao4.png")));
        animacoes.add(new ObjImagem(0, 0, 815, 470, new ImageIcon("src/imagens/animacao5.png")));

        listaMensagens.clear();
        listaMensagens.add(new ObjImagem(336, 150, 148, 38, new ImageIcon("src/imagens/aviso.png")));
        listaMensagens.add(new ObjImagem(336, 150, 148, 38, new ImageIcon("src/imagens/animais/msg_arara.png")));
        listaMensagens.add(new ObjImagem(336, 150, 148, 38, new ImageIcon("src/imagens/animais/msg_lobo.png")));
        listaMensagens.add(new ObjImagem(336, 150, 148, 38, new ImageIcon("src/imagens/animais/msg_macaco.png")));
        msgAviso = listaMensagens.get(0);

        hud.pause(false);
        shocks.clear();
        shocks.add(new ShockTroopers(1150, 327, 125, 114, 4, 5, 200));
        shocks.add(new ShockTroopers(1485, 132, 125, 114, 4, 5, 150));
        shocks.add(new ShockTroopers(5300, 307, 125, 114, 4, 5, 200));

        karts.clear();
        karts.add(new MegaKart(2600, 387, 57, 53, 6, 5, 130));
        karts.add(new MegaKart(4100, 145, 57, 53, 6, 5, 155));
        karts.add(new MegaKart(6460, 145, 57, 53, 6, 5, 35));

        listaCerejas.clear();
        listaCerejas.add(new ObjImagem(3750, 305, 35, 30, new ImageIcon("src/imagens/cereja.gif")));
        listaCerejas.add(new ObjImagem(6380, 270, 35, 30, new ImageIcon("src/imagens/cereja.gif")));

        listaJaulas.clear();
        listaJaulas.add(new ObjImagem(2105, 117, 170, 118, new ImageIcon("src/imagens/jaula.png")));
        listaJaulas.add(new ObjImagem(6105, 292, 170, 118, new ImageIcon("src/imagens/jaula.png")));
        listaJaulas.add(new ObjImagem(6840, 222, 170, 118, new ImageIcon("src/imagens/jaula.png")));

        listaAnimais.clear();
        listaAnimais.add(new ObjImagem(2160, 150, 104, 86, new ImageIcon("src/imagens/animais/arara_azul.png")));
        listaAnimais.add(new ObjImagem(6125, 290, 156, 129, new ImageIcon("src/imagens/animais/raposa.png")));
        listaAnimais.add(new ObjImagem(6860, 257, 104, 86, new ImageIcon("src/imagens/animais/macaco.png")));

        listaCenarios.clear();
        listaCenarios.add(new ObjImagem(0, 0, 820, 480, new ImageIcon("src/imagens/fase1/mapa_00.png")));
        listaCenarios.add(new ObjImagem(820, 0, 820, 480, new ImageIcon("src/imagens/fase1/mapa_01.png")));
        listaCenarios.add(new ObjImagem(1640, 0, 820, 480, new ImageIcon("src/imagens/fase1/mapa_02.png")));
        listaCenarios.add(new ObjImagem(2460, 0, 820, 480, new ImageIcon("src/imagens/fase1/mapa_03.png")));
        listaCenarios.add(new ObjImagem(3280, 0, 820, 480, new ImageIcon("src/imagens/fase1/mapa_04.png")));
        listaCenarios.add(new ObjImagem(4100, 0, 820, 480, new ImageIcon("src/imagens/fase1/mapa_05.png")));
        listaCenarios.add(new ObjImagem(4920, 0, 820, 480, new ImageIcon("src/imagens/fase1/mapa_06.png")));
        listaCenarios.add(new ObjImagem(5740, 0, 820, 480, new ImageIcon("src/imagens/fase1/mapa_07.png")));
        listaCenarios.add(new ObjImagem(6560, 0, 820, 480, new ImageIcon("src/imagens/fase1/mapa_08.png")));
        listaCenarios.add(new ObjImagem(7380, 0, 820, 480, new ImageIcon("src/imagens/fase1/mapa_09.png")));

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

        listaObstaculos.clear();
        listaObstaculos.add(new Objeto(0, 435, 930, 10));
        listaObstaculos.add(new Objeto(1150, 435, 2240, 10));
        listaObstaculos.add(new Objeto(1485, 245, 775, 10));
        listaObstaculos.add(new Objeto(3615, 338, 225, 10));
        listaObstaculos.add(new Objeto(4100, 197, 940, 10));
        listaObstaculos.add(new Objeto(5035, 310, 137, 10));
        listaObstaculos.add(new Objeto(5200, 420, 1075, 10));
        listaObstaculos.add(new Objeto(6305, 310, 120, 10));
        listaObstaculos.add(new Objeto(6445, 195, 330, 10));
        listaObstaculos.add(new Objeto(6730, 340, 1570, 10));

        listaParedes.clear();
        listaParedes.add(new Parede(3585, 360, 10, 200, true));
        listaParedes.add(new Parede(3860, 360, 10, 200, false));
        listaParedes.add(new Parede(4070, 220, 10, 300, true));
        listaParedes.add(new Parede(5050, 220, 10, 300, false));
        listaParedes.add(new Parede(5192, 325, 10, 200, false));
        listaParedes.add(new Parede(6280, 325, 10, 200, true));
        listaParedes.add(new Parede(6420, 220, 10, 100, true));
        listaParedes.add(new Parede(6795, 220, 10, 200, false));
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
        listaParedes.forEach((o) -> {
            o.moveHorizontal(x);
        });
        shocks.forEach((o) -> {
            o.moveHorizontal(x);
        });
        karts.forEach((o) -> {
            o.moveHorizontal(x);
        });

        listaCerejas.forEach((o) -> {
            o.moveHorizontal(x);
        });
        listaJaulas.forEach((o) -> {
            o.moveHorizontal(x);
        });
        listaAnimais.forEach((o) -> {
            o.moveHorizontal(x);
        });

        for (ObjImagem o : listaCenariosCamada1) {
            if (x > 0) {
                o.moveHorizontal(x - 2);
            } else {
                o.moveHorizontal(x + 2);
            }
        }

        for (ObjImagem o : listaCenariosCamada2) {
            if (x > 0) {
                o.moveHorizontal(x - 1);
            } else {
                o.moveHorizontal(x + 1);
            }
        }

        for (ObjImagem o : listaCenariosCamada3) {
            o.moveHorizontal(x);
        }
    }

    public void moverCenarioVerticalmente(int y) {
        listaCenarios.forEach((objeto) -> {
            objeto.moveVertical(y);
        });
        listaObstaculos.forEach((o) -> {
            o.moveVertical(y);
        });
        listaAnimais.forEach((o) -> {
            o.moveHorizontal(y);
        });
        listaParedes.forEach((o) -> {
            o.moveVertical(y);
        });
        shocks.forEach((o) -> {
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
                    efeitos.iniciarSom("src/som/explosao.mp3");

                } else if (!i.getAtacando()) {
                    if (!i.getSumindo()) {
                        hud.aumentarPontuacao(50);
                    }
                    i.sofrerAtaque();
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
                msgAviso = listaMensagens.get(listaJaulas.indexOf(o) + 1);
                listaMensagens.remove(listaAnimais.indexOf(o) + 1);

                listaJaulas.remove(o);
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
            ForestGuardian.usuario.setFaseAtual(2);
            ForestGuardian.usuario.setPontuacaoFase1(hud.getPontos());
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
        if (!inicio) {
            if (!pause && !gameOver && !faseConcluida) {
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

                if (!snow.getPulando()) {
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
                    hud.pause(false);

                    telaPause.setAtivada(false);
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
        } else {
            auxAnimacao++;
            if (auxAnimacao > 500) {
                animacaoAtual++;
                auxAnimacao = 0;
                if (animacaoAtual > animacoes.size() - 1) {
                    inicio = false;
                    panel.add(btnPause);
                }
            }
            if(forestguardian.ForestGuardian.tecla.teclaAcionada(7) && auxAnimacao > 50){
                animacaoAtual++;
                auxAnimacao = 0;
                if (animacaoAtual > animacoes.size() - 1) {
                    inicio = false;
                    panel.add(btnPause);
                }
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        if (!inicio) {
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
        } else if(animacaoAtual <= animacoes.size()-1){
            g.drawImage(animacoes.get(animacaoAtual).getImagem(), animacoes.get(animacaoAtual).getX(), animacoes.get(animacaoAtual).getY(), animacoes.get(animacaoAtual).getLargura(), animacoes.get(animacaoAtual).getAltura(), null);
        }
    }

}
