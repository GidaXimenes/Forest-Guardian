package util;

/**
 *
 * @author Aluno
 */
public class Colisao {
    
    public static boolean collision(Objeto a, Objeto b){
        
        final int pLargA = a.getX() + a.getLargura();
        final int pLargB = b.getX() + b.getLargura();
        
        
        final int pAltA = a.getY() + a.getAltura();
        final int pAltB = b.getY() + b.getAltura();
        
        return pLargA > b.getX() && a.getX() < pLargB && 
                pAltA > b.getY() && a.getY() < pAltB;
    } 
}