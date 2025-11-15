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
import personagens.DrOmega;
import som.Som;
import util.Colisao;
import util.Hud;
import util.ObjImagem;
import util.Objeto;
import util.Tiro;

/**
 *
 * @author Ayumi, Francico e Gideão
 */
public class TelaFase3 implements Tela {

    private JButton btnPause;

    private ImageIcon pauseImg;
    private ImageIcon pauseSelecionadoImg;

    private final JPanel panel;

    private boolean gameOver;
    private final TelaGameOver telaGameOver;

    private boolean faseConcluida;
    private final TelaFaseConcluida telaFaseConcluida;

    private boolean pause;
    private final TelaPause telaPause;
    private int auxPause;
    private boolean estaNaSegunda;
    private boolean transicao;
    private int auxTransicao;

    private Hud hud;

    private final ArrayList<ObjImagem> listaCenarios;
    private final ArrayList<Objeto> listaPlataformas;

    private final ArrayList<ObjImagem> listaCenariosCamada1;
    private final ArrayList<ObjImagem> listaCenariosCamada2;
    private final ArrayList<ObjImagem> listaCenariosCamada3;

    private final ImageIcon camada0;
    private final ImageIcon camada1;
    private final ImageIcon camada2;
    private final ImageIcon camada3;

    private DrOmega omega;
    private Snow snow;

    private int auxFaseConcluida;

    private Som som;
    private Som efeito;

    private ArrayList<ObjImagem> animacoes;
    private int auxAnimacao;
    private boolean inicio;
    private int animacaoAtual;

    public TelaFase3(JPanel j) {
        panel = j;

        animacoes = new ArrayList<>();
        auxAnimacao = 0;
        inicio = false;
        animacaoAtual = 0;

        animacoes.add(new ObjImagem(0, 0, 815, 470, new ImageIcon("src/imagens/animacao6.png")));
        animacoes.add(new ObjImagem(0, 0, 815, 470, new ImageIcon("src/imagens/animacao7.png")));

        auxFaseConcluida = 0;

        pause = false;
        gameOver = false;
        faseConcluida = false;

        telaPause = new TelaPause(panel);
        telaFaseConcluida = new TelaFaseConcluida(panel, true);
        telaGameOver = new TelaGameOver(panel);

        telaGameOver.setFaseAtual(3);
        telaFaseConcluida.setFaseAtual(3);

        auxPause = 0;
        listaCenarios = new ArrayList<>();
        listaPlataformas = new ArrayList<>();

        listaCenariosCamada1 = new ArrayList<>();
        listaCenariosCamada2 = new ArrayList<>();
        listaCenariosCamada3 = new ArrayList<>();

        camada0 = new ImageIcon("src/imagens/fase2/camada0.png");
        camada1 = new ImageIcon("src/imagens/fase2/camada1.png");
        camada2 = new ImageIcon("src/imagens/fase2/camada2.png");
        camada3 = new ImageIcon("src/imagens/fase2/camada3.png");

        hud = new Hud();

        posicionaObjetos();
        estaNaSegunda = false;
        transicao = false;
        auxTransicao = 0;

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
    }

    public void transicao() {
        if (listaCenarios.get(1).getX() > 0) {
            moverCenarioHorizontalmente(-5);
            omega.moveHorizontal(-5);
        }
        if (snow.getX() > 20) {
            snow.moveLeft();

        } else {
            transicao = false;
            estaNaSegunda = true;
            omega.ativar();
        }
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
        panel.add(btnPause);
        auxFaseConcluida = 0;
        forestguardian.ForestGuardian.usuario.setNumeroVidas(5);
        hud.setPontos(0);
        forestguardian.ForestGuardian.usuario.setNumeroVidas(5);
        pause = false;
        gameOver = false;
        faseConcluida = false;
        iniciarMusica();
    }

    public void iniciarMusica() {
        som.iniciarMusica("src/som/fase3.mp3");
        som.setRepetir(true);
    }

    public void fecharMusica() {
        som.pararMusica();
    }

    public final void posicionaObjetos() {
        snow = new Snow(0, 312, 85, 118, 5, 15);
        omega = new DrOmega(1380, 268, 158, 177, 7, 7);
        estaNaSegunda = false;
        hud.pause(false);
        listaCenarios.clear();
        listaCenarios.add(new ObjImagem(0, 0, 820, 480, new ImageIcon("src/imagens/fase3/mapa.png")));
        listaCenarios.add(new ObjImagem(820, 0, 820, 480, new ImageIcon("src/imagens/fase3/mapa.png")));

        listaPlataformas.clear();
        listaPlataformas.add(new Objeto(0, 435, 1600, 10));

        listaCenariosCamada1.clear();
        listaCenariosCamada1.add(new ObjImagem(0, 0, 820, 480, camada1));
        listaCenariosCamada1.add(new ObjImagem(820, 0, 820, 480, camada1));

        listaCenariosCamada2.clear();
        listaCenariosCamada2.add(new ObjImagem(0, 0, 820, 480, camada2));
        listaCenariosCamada2.add(new ObjImagem(820, 0, 820, 480, camada2));

        listaCenariosCamada3.clear();
        listaCenariosCamada3.add(new ObjImagem(0, 0, 820, 480, camada3));
        listaCenariosCamada3.add(new ObjImagem(820, 0, 820, 480, camada3));

    }

    //Move todos os elementos do cenario para Direita 
    //Aumentando o X
    public void moverCenarioHorizontalmente(int x) {
        listaCenarios.forEach((objeto) -> {
            objeto.moveHorizontal(x);
        });
        listaPlataformas.forEach((o) -> {
            o.moveHorizontal(x);
        });

        listaCenariosCamada1.forEach((o) -> {
            if (x > 0) {
                o.moveHorizontal(x - 2);
            } else {
                o.moveHorizontal(x + 2);
            }
        });

        listaCenariosCamada2.forEach((o) -> {
            if (x > 0) {
                o.moveHorizontal(x - 1);
            } else {
                o.moveHorizontal(x + 1);
            }
        });

        listaCenariosCamada3.forEach((o) -> {
            o.moveHorizontal(x);
        });
    }

    public void moverCenarioVerticalmente(int y) {
        listaCenarios.forEach((objeto) -> {
            objeto.moveVertical(y);
        });
        listaPlataformas.forEach((o) -> {
            o.moveVertical(y);
        });

    }

    public void colisaoObjetos() {
        for (Tiro t : omega.getTiros()) {
            if (Colisao.collision(t, snow)) {
                snow.dano(1);
                omega.removeTiro(t);
                break;
            }
        }
        if (Colisao.collision(omega, snow)) {
            if (omega.getDash() && !omega.getMorto()) {
                snow.dano(2);
            } else if (snow.getAtaque()) {
                if (!omega.getSofrendoDano()) {
                    hud.aumentarPontuacao(200);
                }
                omega.sofrerAtaque();
            } else if (omega.getMorrendo()) {
                snow.dano(2);
            } else if (!omega.getMorto()) {
                snow.dano(1);
            }
        }
    }

    public void passaFase() {
        if (!inicio) {
            inicio = true;
            panel.remove(btnPause);
        } else {
            inicio = false;
            auxAnimacao++;
                ForestGuardian.usuario.setPontuacaoFase3(hud.getPontos());
                ForestGuardian.Frame.salvarUsuario();
                faseConcluida = true;
                telaFaseConcluida.iniciar();
            
        }
    }

    public void gameOver() {
        gameOver = true;
        telaGameOver.iniciar();
    }

    public void configInicial() {
        posicionaObjetos();
        forestguardian.ForestGuardian.usuario.setNumeroVidas(5);
    }

    @Override
    public void update() {
        if (!pause && !gameOver && !faseConcluida && !inicio) {
            if (transicao) {
                auxTransicao++;
                if (auxTransicao == 1) {
                    auxTransicao = 0;
                    transicao();
                }
            } else if (!inicio) {
                if (forestguardian.ForestGuardian.tecla.teclaAcionada(0) && !snow.getMorrendo()) {
                    snow.moveLeft();
                    snow.setDirecao(false);
                    if (!estaNaSegunda) {
                        if (listaCenarios.get(0).getX() <= -5) {
                            moverCenarioHorizontalmente(snow.getVelocX());
                            snow.moveRight();
                        }
                    } else {
                        if (listaCenarios.get(1).getX() <= -5) {
                            moverCenarioHorizontalmente(snow.getVelocX());
                            snow.moveRight();
                        }
                    }
                } else if (forestguardian.ForestGuardian.tecla.teclaAcionada(1) && !snow.getMorrendo()) {
                    snow.moveRight();
                    snow.setDirecao(true);
                    if (listaCenarios.get(listaCenarios.size() - 1).getX() + listaCenarios.get(listaCenarios.size() - 1).getLargura() >= 820) {
                        if (snow.getX() + snow.getLargura() >= 820 && !estaNaSegunda) {
                            transicao = true;
                            snow.setVelocX(5);
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

                omega.getTiros().removeIf(t -> t.getX() < 0 - t.getLargura() || t.getX() > 820);
                if (snow.getCaindo() || snow.getAtaque()) {
                    for (Objeto o : listaPlataformas) {
                        if (Colisao.collision(o, snow) && (o.getY() - (snow.getAltura() + snow.getY()) > -15)) {
                            snow.setCaindo(false);
                            snow.setY(o.getY() - snow.getAltura() + 10);
                        }
                    }
                }
                if (!snow.getPulando() && !snow.getCaindo()) {
                    boolean aux = false;
                    for (Objeto o : listaPlataformas) {
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

                hud.update();
                omega.update();
                colisaoObjetos();

                //Condições de reinicio ou terminio da fase
                if (snow.getMorto()) {
                    gameOver();
                }
                if (omega.estaMorto()) {
                    auxFaseConcluida++;
                    if (auxFaseConcluida == 340) {
                        passaFase();
                    }
                }

                if (forestguardian.ForestGuardian.tecla.teclaAcionada(2) && auxPause == 0 && !faseConcluida && !gameOver) {
                    telaPause.iniciar();
                    hud.pause(true);
                    telaPause.setAtivada(true);
                    pause = true;
                    auxPause = 20;
                }
            }
            btnPause.setIcon(pauseImg);
        }
        if (!inicio) {
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
        }

        if (inicio) {
            auxAnimacao++;
            if (auxAnimacao > 500) {
                animacaoAtual++;
                auxAnimacao = 0;
                if (animacaoAtual > animacoes.size() - 1) {
                    inicio = false;
                    panel.add(btnPause);
                }
            }
            if (forestguardian.ForestGuardian.tecla.teclaAcionada(7) && auxAnimacao > 50) {
                animacaoAtual++;
                auxAnimacao = 0;
                if (animacaoAtual > animacoes.size() - 1) {
                    passaFase();
                }
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

            listaCenarios.forEach((o) -> {
                o.draw(g);
            });

            omega.draw(g);
            if (!gameOver) {
                snow.draw(g);
            }

            hud.draw(g);

            if (gameOver) {
                telaGameOver.draw(g);
            } else if (faseConcluida) {
                telaFaseConcluida.draw(g);
            } else if (pause) {
                telaPause.draw(g);
            }
      if (animacaoAtual <= animacoes.size() - 1 && inicio) {
            g.drawImage(animacoes.get(animacaoAtual).getImagem(), animacoes.get(animacaoAtual).getX(), animacoes.get(animacaoAtual).getY(), animacoes.get(animacaoAtual).getLargura(), animacoes.get(animacaoAtual).getAltura(), null);
        }
    }

}
