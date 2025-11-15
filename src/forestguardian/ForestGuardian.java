package forestguardian;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */

public final class ForestGuardian extends JFrame{

    public static Teclado tecla;
    public static Usuario usuario;

    public static int LARGURA = 820;
    public static int COMPRIMENTO = 500;

    public static ForestGuardian Frame;

    private final Painel painel;

    public ForestGuardian() {
        super("Forest Guardian");

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int evento = e.getKeyCode();
                tecla.setTeclas(evento, true);
            }

            @Override         
            public void keyReleased(KeyEvent e) {
                int evento = e.getKeyCode();
                tecla.setTeclas(evento, false);
            }
        });

        usuario = carregaEstado();

        tecla = new Teclado();
        Frame = this;
        painel = new Painel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 500);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/imagens/icone.png"));
        setResizable(false);
        setVisible(true);

        add(painel);
    }

    public static void main(String[] args) {
        ForestGuardian game;
        game = new ForestGuardian();
    }

    @Override
    public boolean isFocusTraversable() {
        return true;
    }

    public Usuario carregaEstado() {
        Usuario usu;

        int nFase = 1;
        int pontosFase1 = 0;
        int pontosFase2 = 0;
        int pontosFase3 = 0;
        int musicaAtivada = 1;
        int somAtivado = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(this.getClass().getResource("/forestguardian/Usuario.txt").getFile()))) {
            nFase = Integer.parseInt(br.readLine());
            pontosFase1 = Integer.parseInt(br.readLine());
            pontosFase2 = Integer.parseInt(br.readLine());
            pontosFase3 = Integer.parseInt(br.readLine());
            musicaAtivada = Integer.parseInt(br.readLine());
            somAtivado = Integer.parseInt(br.readLine());

        } catch (IOException e) {
            System.out.println("Error na leitura do arquivo" + e.getMessage());
        }

        usu = new Usuario(nFase, pontosFase1, pontosFase2, pontosFase3, 5, musicaAtivada, somAtivado);

        return usu;
    }

    public void salvarUsuario() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.getClass().getResource("/forestguardian/Usuario.txt").getFile()))) {
            bw.write("" + usuario.getFaseAtual());
            bw.newLine();
            bw.write("" + usuario.getPontuacaoFase1());
            bw.newLine();
            bw.write("" + usuario.getPontuacaoFase2());
            bw.newLine();
            bw.write("" + usuario.getPontuacaoFase3());
            bw.newLine();
            bw.write(""+usuario.getMusica());
            bw.newLine();
            bw.write(""+usuario.getSom());
            
        } catch (IOException e) {
            System.out.println("Error ao salvar o arquivo:" + e.getMessage());
        }
    }

    public void resetarUsuario() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.getClass().getResource("/forestguardian/Usuario.txt").getFile()))) {
            bw.write("1");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("0");
            bw.newLine();
            bw.write("1");
            bw.newLine();
            bw.write("1");
            
        } catch (IOException e) {
            System.out.println("Error ao salvar o arquivo:" + e.getMessage());
        }
    }
}
