package util;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */
public class Parede extends Objeto{
    
    //Se true direita senao esquerda
    private final boolean direcaoBloqueada;
    
    public Parede(int x, int y, int largura, int altura,boolean d) {
        super(x, y, largura, altura);
        direcaoBloqueada = d;
    }

    public boolean getDirecaoBloqueada() {
        return direcaoBloqueada;
    }
    
    
}
