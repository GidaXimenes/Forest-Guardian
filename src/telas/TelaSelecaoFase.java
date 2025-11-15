package telas;

import forestguardian.GameRunnable;
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

public class TelaSelecaoFase implements Tela {

    private boolean podeMover;
    private int auxTempo;
    private int itemSelecionado;

    private final ImageIcon esc;
    private final ImageIcon enter;
    
    private final JPanel painel;

    private JButton btnFase1;
    private JButton btnFase2;
    private JButton btnFase3;

    private final ImageIcon titulo;
    private final ImageIcon placa;
    private final ImageIcon tela;

    private final ImageIcon fase1;
    private final ImageIcon fase1_selecionada;
    private final ImageIcon fase2;
    private final ImageIcon fase2_selecionada;
    private final ImageIcon fase2_bloqueada;
    private final ImageIcon fase3;
    private final ImageIcon fase3_selecionada;
    private final ImageIcon fase3_bloqueada;

    private final ObjImagem fase1_milhar;
    private final ObjImagem fase1_centena;
    private final ObjImagem fase1_dezena;
    private final ObjImagem fase1_unidade;

    private final ObjImagem fase2_milhar;
    private final ObjImagem fase2_centena;
    private final ObjImagem fase2_dezena;
    private final ObjImagem fase2_unidade;

    private final ObjImagem fase3_milhar;
    private final ObjImagem fase3_centena;
    private final ObjImagem fase3_dezena;
    private final ObjImagem fase3_unidade;

    private final ImageIcon numero0;
    private final ImageIcon numero1;
    private final ImageIcon numero2;
    private final ImageIcon numero3;
    private final ImageIcon numero4;
    private final ImageIcon numero5;
    private final ImageIcon numero6;
    private final ImageIcon numero7;
    private final ImageIcon numero8;
    private final ImageIcon numero9;
    
    private Som efeitos;

    public TelaSelecaoFase(JPanel painel) {
        this.painel = painel;

        itemSelecionado = 1;
        auxTempo = 0;
        podeMover = false;
        
        efeitos = new Som();

        esc = new ImageIcon("src/imagens/esc.png");
        enter = new ImageIcon("src/imagens/enter.png");
        
        titulo = new ImageIcon("src/imagens/selecao_fase.png");
        tela = new ImageIcon("src/imagens/fundo.png");

        numero0 = new ImageIcon("src/imagens/hud/numero_00.png");
        numero1 = new ImageIcon("src/imagens/hud/numero_01.png");
        numero2 = new ImageIcon("src/imagens/hud/numero_02.png");
        numero3 = new ImageIcon("src/imagens/hud/numero_03.png");
        numero4 = new ImageIcon("src/imagens/hud/numero_04.png");
        numero5 = new ImageIcon("src/imagens/hud/numero_05.png");
        numero6 = new ImageIcon("src/imagens/hud/numero_06.png");
        numero7 = new ImageIcon("src/imagens/hud/numero_07.png");
        numero8 = new ImageIcon("src/imagens/hud/numero_08.png");
        numero9 = new ImageIcon("src/imagens/hud/numero_09.png");

        fase1_milhar = new ObjImagem(165, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        fase1_centena = new ObjImagem(190, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        fase1_dezena = new ObjImagem(215, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        fase1_unidade = new ObjImagem(240, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));

        fase2_milhar = new ObjImagem(360, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        fase2_centena = new ObjImagem(385, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        fase2_dezena = new ObjImagem(410, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        fase2_unidade = new ObjImagem(435, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));

        fase3_milhar = new ObjImagem(555, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        fase3_centena = new ObjImagem(580, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        fase3_dezena = new ObjImagem(605, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));
        fase3_unidade = new ObjImagem(630, 245, 25, 30, new ImageIcon("src/imagens/hud/numero_00.png"));

        btnFase1 = new JButton();
        btnFase2 = new JButton();
        btnFase3 = new JButton();

        placa = new ImageIcon("src/imagens/botoes/level_placa.png");

        fase1 = new ImageIcon("src/imagens/botoes/level_1.png");
        fase1_selecionada = new ImageIcon("src/imagens/botoes/level_1_selecionado.png");

        fase2 = new ImageIcon("src/imagens/botoes/level_2.png");
        fase2_selecionada = new ImageIcon("src/imagens/botoes/level_2_selecionado.png");
        fase2_bloqueada = new ImageIcon("src/imagens/botoes/level_2_bloqueado.png");

        fase3 = new ImageIcon("src/imagens/botoes/level_3.png");
        fase3_selecionada = new ImageIcon("src/imagens/botoes/level_3_selecionado.png");
        fase3_bloqueada = new ImageIcon("src/imagens/botoes/level_3_bloqueado.png");

        //Botão de Jogar
        // btnFase1.setBounds(143, 157, 145, 146);
        btnFase1.setBounds(188, 157, 57, 57);
        btnFase1.setIcon(fase1);
        btnFase1.setBorderPainted(false);
        btnFase1.setContentAreaFilled(false);

        //Botão de Jogar
        // btnFase2.setBounds(338, 157, 145, 146);
        btnFase2.setBounds(383, 157, 57, 57);
        btnFase2.setIcon(fase2);
        btnFase2.setBorderPainted(false);
        btnFase2.setContentAreaFilled(false);

        //Botão de Jogar
        //btnFase3.setBounds(533, 157, 145, 146);
        btnFase3.setBounds(578, 157, 57, 57);
        btnFase3.setIcon(fase3);
        btnFase3.setBorderPainted(false);
        btnFase3.setContentAreaFilled(false);

        btnFase1.addActionListener((ActionEvent ae) -> {
            GameRunnable.tela_menu.som.pararMusica();
            GameRunnable.tela_carregamento.setProximo(1);
            GameRunnable.mudarTela(4);
        });

        btnFase2.addActionListener((ActionEvent ae) -> {
            GameRunnable.tela_menu.som.pararMusica();
            GameRunnable.tela_carregamento.setProximo(2);
            GameRunnable.mudarTela(4);
        });

        btnFase3.addActionListener((ActionEvent ae) -> {
            GameRunnable.tela_menu.som.pararMusica();
            GameRunnable.tela_carregamento.setProximo(3);
            GameRunnable.mudarTela(4);
        });

        btnFase1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnFase1.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                efeitos.iniciarSom("src/som/mouse.mp3");
                itemSelecionado = 1;
            }
        });

        btnFase2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnFase2.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                efeitos.iniciarSom("src/som/mouse.mp3");
                itemSelecionado = 2;
            }
        });

        btnFase3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnFase3.doClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                efeitos.iniciarSom("src/som/mouse.mp3");
                itemSelecionado = 3;
            }
        });
    }

    @Override
    public void iniciar() {
        fechar();
        itemSelecionado = 1;
        painel.add(btnFase1);
        if (forestguardian.ForestGuardian.usuario.getFaseAtual() > 1) {
            painel.add(btnFase2);
        }
        if (forestguardian.ForestGuardian.usuario.getFaseAtual() > 2) {
            painel.add(btnFase3);
        }

        fase1_unidade.setImage(numero(forestguardian.ForestGuardian.usuario.getPontuacaoFase1() % 10));
        fase1_dezena.setImage(numero(forestguardian.ForestGuardian.usuario.getPontuacaoFase1() / 10 % 10));
        fase1_centena.setImage(numero(forestguardian.ForestGuardian.usuario.getPontuacaoFase1() / 100 % 10));
        fase1_milhar.setImage(numero(forestguardian.ForestGuardian.usuario.getPontuacaoFase1() / 1000 % 10));

        fase2_unidade.setImage(numero(forestguardian.ForestGuardian.usuario.getPontuacaoFase2() % 10));
        fase2_dezena.setImage(numero(forestguardian.ForestGuardian.usuario.getPontuacaoFase2() / 10 % 10));
        fase2_centena.setImage(numero(forestguardian.ForestGuardian.usuario.getPontuacaoFase2() / 100 % 10));
        fase2_milhar.setImage(numero(forestguardian.ForestGuardian.usuario.getPontuacaoFase2() / 1000 % 10));

        fase3_unidade.setImage(numero(forestguardian.ForestGuardian.usuario.getPontuacaoFase3() % 10));
        fase3_dezena.setImage(numero(forestguardian.ForestGuardian.usuario.getPontuacaoFase3() / 10 % 10));
        fase3_centena.setImage(numero(forestguardian.ForestGuardian.usuario.getPontuacaoFase3() / 100 % 10));
        fase3_milhar.setImage(numero(forestguardian.ForestGuardian.usuario.getPontuacaoFase3() / 1000 % 10));
    }

    @Override
    public void fechar() {
        auxTempo =0;
        podeMover = false;
        btnFase1.setIcon(fase1);
        btnFase2.setIcon(fase2);
        btnFase3.setIcon(fase3);

        painel.remove(btnFase1);
        painel.remove(btnFase2);
        painel.remove(btnFase3);
    }

    public final ImageIcon numero(int n) {
        switch (n) {
            case 0:
                return numero0;
            case 1:
                return numero1;
            case 2:
                return numero2;
            case 3:
                return numero3;
            case 4:
                return numero4;
            case 5:
                return numero5;
            case 6:
                return numero6;
            case 7:
                return numero7;
            case 8:
                return numero8;
            case 9:
                return numero9;

        }
        return numero0;
    }

    @Override
    public void update() {
        switch (itemSelecionado) {
            case 1:
                btnFase1.setIcon(fase1_selecionada);
                btnFase2.setIcon(fase2);
                btnFase3.setIcon(fase3);
                break;
            case 2:
                btnFase1.setIcon(fase1);
                btnFase2.setIcon(fase2_selecionada);
                btnFase3.setIcon(fase3);
                break;
            case 3:
                btnFase1.setIcon(fase1);
                btnFase2.setIcon(fase2);
                btnFase3.setIcon(fase3_selecionada);
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
                if (itemSelecionado < forestguardian.ForestGuardian.usuario.getFaseAtual()) {
                    itemSelecionado++;
                    auxTempo = 0;
                    podeMover = false;
                }
            }
        }
        if (forestguardian.ForestGuardian.tecla.teclaAcionada(7) &&podeMover) {
            switch (itemSelecionado) {
                case 1:
                    btnFase1.doClick();
                    break;
                case 2:
                    btnFase2.doClick();
                    break;
                case 3:
                    btnFase3.doClick();
                    break;
            }
        }
        if (forestguardian.ForestGuardian.tecla.teclaAcionada(2) && podeMover) {
            GameRunnable.mudarTela(0);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(tela.getImage(), 0, 0, 820, 500, null);
        g.drawImage(placa.getImage(), 143, 157, 145, 146, null);
        g.drawImage(esc.getImage(), 0, 410, 154, 39, null);
        g.drawImage(enter.getImage(),640,410,154,39,null);
        fase1_milhar.draw(g);
        fase1_centena.draw(g);
        fase1_dezena.draw(g);
        fase1_unidade.draw(g);
        g.drawImage(titulo.getImage(), 267, 30, 276,105,null);
        
        if (!(forestguardian.ForestGuardian.usuario.getFaseAtual() > 1)) {
            g.drawImage(fase2_bloqueada.getImage(), 338, 157, 145, 146, null);
        } else {
            g.drawImage(placa.getImage(), 338, 157, 145, 146, null);
            fase2_milhar.draw(g);
            fase2_centena.draw(g);
            fase2_dezena.draw(g);
            fase2_unidade.draw(g);
        }
        if (!(forestguardian.ForestGuardian.usuario.getFaseAtual() > 2)) {
            g.drawImage(fase3_bloqueada.getImage(), 533, 157, 145, 146, null);
        } else {
            g.drawImage(placa.getImage(), 533, 157, 145, 146, null);
            fase3_milhar.draw(g);
            fase3_centena.draw(g);
            fase3_dezena.draw(g);
            fase3_unidade.draw(g);
        }
    }

}
