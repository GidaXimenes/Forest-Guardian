package som;

import jaco.mp3.player.*;
import java.io.File;

public class Som {

    private static MP3Player musica;
    private static MP3Player som;

    private boolean repetir;

    public Som() {
        repetir = false;
    }

    public void setRepetir(boolean repetir) {
        this.repetir = repetir;

    }
    
    public void iniciarMusica(String caminho) {
        if (forestguardian.ForestGuardian.usuario.getMusica() == 1) {
            musica = new MP3Player(new File(caminho));
            musica.setRepeat(repetir);
            musica.play();
        }
    }

    public void pararMusica() {
        musica.stop();
    }
    
    public void iniciarSom(String caminho) {
        if (forestguardian.ForestGuardian.usuario.getSom() == 1) {
            som = new MP3Player(new File(caminho));
            som.setRepeat(repetir);
            som.play();
        }
    }
    
    public void paraSom(){
        som.stop();
    }

}
