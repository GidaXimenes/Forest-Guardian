package telas;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import util.ObjImagem;

/**
 *
 * @author Ayumi, Francico e Gideão
 */

public class TelaCarregamento implements Tela{
    
    private final ImageIcon fundo;
    private final ImageIcon ajuda;
    private final ObjImagem carregamento;
    
    private int cont;
    private final int tempo;
    private int proximo;
    
    //Som s;
    
    public TelaCarregamento(int tempo,int proximo){
        ajuda = new ImageIcon("src/imagens/menu_ajuda.png");
        fundo = new ImageIcon("src/imagens/fundo.png");
        carregamento = new ObjImagem(700, 290, 78, 168, new ImageIcon("src/imagens/simbolo_carregamento.gif"));
        
        this.proximo = proximo;
        this.tempo = tempo;
        cont = 0;

    }
    
    @Override
    public void iniciar(){}
    
    @Override
    public void fechar(){}

    public void setProximo(int poximo) {
        this.proximo = poximo;
    }
    
    @Override
    public void update(){
        if(cont == tempo){
            forestguardian.GameRunnable.mudarTela(proximo);
            cont = 0;
        }else{
            cont++;
        }
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(fundo.getImage(),0,0 , 820,500,null);
        g.drawImage(ajuda.getImage(), 196, 40, 429, 399,null);
        carregamento.draw(g);
    }
}
