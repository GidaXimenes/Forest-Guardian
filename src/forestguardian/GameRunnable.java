package forestguardian;

import telas.TelaFase1;
import java.awt.Graphics;
import javax.swing.JPanel;
import telas.TelaAjuda;
import telas.TelaCarregamento;
import telas.TelaCredito;
import telas.TelaFase2;
import telas.TelaFase3;
import telas.TelaMenu;
import telas.TelaOpcoes;
import telas.TelaSelecaoFase;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */

public class GameRunnable {

    public static int tela;

    private final JPanel painel;

   // private ArrayList<Tela> telas;
   
    private static TelaFase1 tela_fase1;
    private static TelaFase2 tela_fase2;
    private static TelaFase3 tela_fase3;

    public static TelaMenu tela_menu;
    private static TelaAjuda tela_ajuda;
    private static TelaOpcoes tela_opcao;
    public static TelaCarregamento tela_carregamento;
    private static TelaSelecaoFase tela_selecao_fase;
    private static TelaCredito tela_creditos;
    
    public GameRunnable(JPanel painel) {
        this.painel = painel;

     //   telas = new ArrayList<>();
        
       /* telas.add(new TelaMenu(painel));
        telas.add(new TelaFase1(painel));
        telas.add(new TelaFase2(painel));
        telas.add(new TelaFase3(painel));
        telas.add(new TelaCarregamento(200, 0));
        telas.add(new TelaAjuda(painel));
        telas.add(new TelaOpcoes(painel));
        telas.add(new TelaSelecaoFase(painel));*/
        
        tela_menu = new TelaMenu(painel);
        tela_fase1 = new TelaFase1(painel);
        tela_fase2 = new TelaFase2(painel);
        tela_fase3 = new TelaFase3(painel);
        tela_carregamento = new TelaCarregamento(200, 0);
        tela_ajuda = new TelaAjuda();
        tela_opcao = new TelaOpcoes(painel);
        tela_selecao_fase = new TelaSelecaoFase(painel);
        tela_creditos = new TelaCredito();
        
        tela = 0;

        tela_menu.iniciar();
    }

    public void update() {
        switch (tela) {
            case 0:
                tela_menu.update();
                break;
            case 1:
                tela_fase1.update();
                break;
            case 2:
                tela_fase2.update();
                break;
            case 3:
                tela_fase3.update();
                break;
            case 4:
                tela_carregamento.update();
                break;
            case 5:
                tela_ajuda.update();
                break;
            case 6:
                tela_opcao.update();
                break;
            case 7:
                tela_selecao_fase.update();
                break;
            case 8:
                tela_creditos.update();
                break;
        }

    }

    public void draw(Graphics g) {
        switch (tela) {
            case 0:
                tela_menu.draw(g);
                break;
            case 1:
                tela_fase1.draw(g);
                break;
            case 2:
                tela_fase2.draw(g);
                break;
            case 3:
                tela_fase3.draw(g);
                break;
            case 4:
                tela_carregamento.draw(g);
                break;
            case 5:
                tela_ajuda.draw(g);
                break;
            case 6:
                tela_opcao.draw(g);
                break;
            case 7:
                tela_selecao_fase.draw(g);
                break;
            case 8:
                tela_creditos.draw(g);
                break;
        }
    }

    public static void mudarTela(int proxima) {
        switch (tela) {
            case 0:
                tela_menu.fechar();
                break;
            case 1:
                tela_fase1.fechar();
                break;
            case 2:
                tela_fase2.fechar();
                break;
            case 3:
                tela_fase3.fechar();
                break;
            case 4:
                tela_carregamento.fechar();
                break;
            case 5:
                tela_ajuda.fechar();
                break;
            case 6:
                tela_opcao.fechar();
                break;
            case 7:
                tela_selecao_fase.fechar();
                break;
           case 8:
                tela_creditos.fechar();
                break;
        }
        switch (proxima) {
            case 0:
                tela_menu.iniciar();
                break;
            case 1:
                tela_fase1.iniciar();
                break;
            case 2:
                tela_fase2.iniciar();
                break;
            case 3:
                tela_fase3.iniciar();
                break;
            case 4:
                tela_carregamento.iniciar();
                break;
            case 5:
                tela_ajuda.iniciar();
                break;
            case 6:
                tela_opcao.iniciar();
                break;
            case 7:
                tela_selecao_fase.iniciar();
                break;
            case 8:
                tela_creditos.iniciar();
                break;
        }

        tela = proxima;
    }
    
    public static int telaAtual(){
        return tela;
    }
}
