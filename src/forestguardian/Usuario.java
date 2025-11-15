package forestguardian;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */

public class Usuario {

    private int faseAtual;
    private int pontuacaoFase1;
    private int pontuacaoFase2;
    private int pontuacaoFase3;
    private int numeroVidas;
    private int musica;
    private int som;

    public Usuario(int faseAtual, int pontuacaoFase1, int pontuacaoFase2, int pontuacaoFase3, int numeroVidas, int musica, int som) {
        this.faseAtual = faseAtual;
        this.pontuacaoFase1 = pontuacaoFase1;
        this.pontuacaoFase2 = pontuacaoFase2;
        this.pontuacaoFase3 = pontuacaoFase3;
        this.numeroVidas = numeroVidas;
        this.musica = musica;
        this.som = som;
    }  

    public int getSom() {
        return som;
    }

    public void setSom(int som) {
        this.som = som;
    }
    
    public int getMusica(){
        return musica;
    }
    
    public void setMusica(int musica){
        this.musica = musica;
    }

    public int getFaseAtual() {
        return faseAtual;
    }

    public void setFaseAtual(int faseAtual) {
        this.faseAtual = faseAtual;
    }

    public int getNumeroVidas() {
        return numeroVidas;
    }

    public void setNumeroVidas(int numeroVidas) {
        this.numeroVidas = numeroVidas;
    }

    public void perdaVida() {
        numeroVidas--;
    }

    public void perdaVida(int dano) {
        numeroVidas -= dano;
    }

    public void aumentarVida() {
        numeroVidas++;
    }

    public int getPontuacaoFase1() {
        return pontuacaoFase1;
    }

    public int getPontuacaoFase2() {
        return pontuacaoFase2;
    }

    public int getPontuacaoFase3() {
        return pontuacaoFase3;
    }

    public void setPontuacaoFase1(int pontuacaoFase1) {
        this.pontuacaoFase1 = pontuacaoFase1;

    }

    public void setPontuacaoFase2(int pontuacaoFase2) {
        this.pontuacaoFase2 = pontuacaoFase2;

    }

    public void setPontuacaoFase3(int pontuacaoFase3) {
        this.pontuacaoFase3 = pontuacaoFase3;

    }

}
