package teste;

import telas.*;
import forestguardian.GameRunnable;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */
public class TelaSelecaoFaseTeste implements Tela {

    private final JPanel painel;

    private JButton btnVoltar;

    public JButton btnFase1;
    public JButton btnFase2;
    public JButton btnFase3;

    private final ImageIcon tela;
    private ImageIcon voltar;
    private ImageIcon voltarSelecionado;

    private final ImageIcon placa;

    private final ImageIcon fase1;
    private final ImageIcon fase1_selecionada;
    private final ImageIcon fase2;
    private final ImageIcon fase2_selecionada;
    private final ImageIcon fase2_bloqueada;
    private final ImageIcon fase3;
    private final ImageIcon fase3_selecionada;
    private final ImageIcon fase3_bloqueada;

    public TelaSelecaoFaseTeste(JPanel painel) {
        this.painel = painel;

        btnFase1 = new JButton();
        btnFase2 = new JButton();
        btnFase3 = new JButton();

        placa = new ImageIcon("src/imagens/botoes/level_placa.png");
        tela = new ImageIcon("src/imagens/fundo.png");

        btnVoltar = new JButton();
        voltar = new ImageIcon("src/imagens/botoes/voltar.png");
        voltarSelecionado = new ImageIcon("src/imagens/botoes/voltar_selecionado.png");

        fase1 = new ImageIcon("src/imagens/botoes/level_1.png");
        fase1_selecionada = new ImageIcon("src/imagens/botoes/level_1_selecionado.png");

        fase2 = new ImageIcon("src/imagens/botoes/level_2.png");
        fase2_selecionada = new ImageIcon("src/imagens/botoes/level_2_selecionado.png");
        fase2_bloqueada = new ImageIcon("src/imagens/botoes/level_2_bloqueado.png");

        fase3 = new ImageIcon("src/imagens/botoes/level_3.png");
        fase3_selecionada = new ImageIcon("src/imagens/botoes/level_3_selecionado.png");
        fase3_bloqueada = new ImageIcon("src/imagens/botoes/level_3_bloqueado.png");

        //Botão de Voltar
        btnVoltar.setBounds(0, 0, 57, 58);
        btnVoltar.setIcon(voltar);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setContentAreaFilled(false);

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

        //Adicionar Evento dos Mouse nos botões        
        btnVoltar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                fechar();
                GameRunnable.mudarTela(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnVoltar.setIcon(voltarSelecionado);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnVoltar.setIcon(voltar);
            }
        });

        btnFase1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                fechar();
                GameRunnable.tela_carregamento.setProximo(1);
                GameRunnable.mudarTela(4);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnFase1.setIcon(fase1_selecionada);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnFase1.setIcon(fase1);
            }
        });

        btnFase2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                fechar();
                GameRunnable.tela_carregamento.setProximo(2);
                GameRunnable.mudarTela(4);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnFase2.setIcon(fase2_selecionada);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnFase2.setIcon(fase2);
            }
        });

        btnFase3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                fechar();
                GameRunnable.tela_carregamento.setProximo(3);
                GameRunnable.mudarTela(4);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnFase3.setIcon(fase3_selecionada);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnFase3.setIcon(fase3);

            }
        });
    }

    @Override
    public void iniciar() {
        fechar();
        btnVoltar.setIcon(voltar);
        painel.add(btnVoltar);
        painel.add(btnFase1);
        painel.add(btnFase2);

        painel.add(btnFase3);

    }

    @Override
    public void fechar() {
        btnVoltar.setIcon(voltar);
        btnVoltar.setIcon(voltar);
        btnVoltar.setIcon(fase1);
        btnVoltar.setIcon(fase2);
        btnVoltar.setIcon(fase3);

        painel.remove(btnVoltar);
        painel.remove(btnFase1);
        painel.remove(btnFase2);
        painel.remove(btnFase3);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(tela.getImage(), 0, 0, 820, 500, null);
        g.drawImage(placa.getImage(), 143, 157, 145, 146, null);

        g.drawImage(placa.getImage(), 338, 157, 145, 146, null);

        g.drawImage(placa.getImage(), 533, 157, 145, 146, null);

    }

}
