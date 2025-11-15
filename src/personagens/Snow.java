package personagens;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import som.Som;
import util.ObjPersonagem;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */
public class Snow extends ObjPersonagem {

    //Variaveis de controles de tempo
    private int tAtaque;
    private int tDano;
    private int tTransicaoImagem;

    //Variaveis de Estado
    private boolean comImagem;
    private boolean pulando;
    private boolean caindo;
    private boolean ataque;
    private boolean sofrendoDano;
    private boolean morrendo;
    private boolean morto;

    private boolean direcao;
    //TRUE = DIREITA
    //FALSE = ESQUERDA

    //Variaveis Finais
    final int MAXIMA_VELOCIDADE_CAIDA = 10;

    //Variaveis auxiliares
    private int aux;
    private int tempoPulo;
    private int frameAtualizar;

    //Variavel que guardara o valor de Y ao pular
    private int inicial;

    //Variaveis de Imagem
    private final ImageIcon ImgPulando;
    private final ImageIcon ImgCaindo;
    private final ImageIcon andarDireita;
    private final ImageIcon stopSnow;
    private final ImageIcon ImgAtaque;
    private ImageIcon atual;

    private final ImageIcon ImgEsqPulando;
    private final ImageIcon ImgEsqCaindo;
    private final ImageIcon andarEsquerda;
    private final ImageIcon ImgEsqAtaque;
    private final ImageIcon stopEsqSnow;
    private ImageIcon esqAtual;

    private final ImageIcon explosao;
    
    private Som efeitos;

    //Construtor
    public Snow(int x, int y, int largura, int altura, int velocX, int velocY) {

        super(x, y, largura, altura, velocX, velocY);
        //Inicialização das imagens Dirito
        andarDireita = new ImageIcon("src/imagens/snow/snow_andando.gif");
        stopSnow = new ImageIcon("src/imagens/snow/snow_respirando.gif");
        ImgCaindo = new ImageIcon("src/imagens/snow/snow_caindo.png");
        ImgPulando = new ImageIcon("src/imagens/snow/snow_pulando.png");
        ImgAtaque = new ImageIcon("src/imagens/snow/snow_rodopiar.gif");

        andarEsquerda = new ImageIcon("src/imagens/snow/snow_esq_andando.gif");
        stopEsqSnow = new ImageIcon("src/imagens/snow/snow_esq_respirando.gif");
        ImgEsqCaindo = new ImageIcon("src/imagens/snow/snow_esq_caindo.png");
        ImgEsqPulando = new ImageIcon("src/imagens/snow/snow_esq_pulando.png");
        ImgEsqAtaque = new ImageIcon("src/imagens/snow/snow_esq_rodopiar.gif");

        explosao = new ImageIcon("src/imagens/explosao.gif");
        //Inicialização dos estados
        pulando = false;
        caindo = false;
        morto = false;
        sofrendoDano = false;
        direcao = true;
        comImagem = true;
        morrendo = false;

        //Inicialização da imagem atual
        atual = stopSnow;
        esqAtual = stopEsqSnow;
        aux = 0;
        
        efeitos = new Som();
    }

    public void morrer() {
        this.morrendo = true;
        aux = 0;
    }

    public boolean getSofrendoDano() {
        return sofrendoDano;
    }

    public boolean getMorrendo() {
        return morrendo;
    }

    public boolean getMorto() {
        return morto;
    }

    public boolean getPulando() {
        return pulando;
    }

    public void setPulando(boolean pulando) {
        this.pulando = pulando;
    }

    public boolean getCaindo() {
        return caindo;
    }

    public void setCaindo(boolean caindo) {
        if (caindo && !ataque) {
            atual = ImgCaindo;
            esqAtual = ImgEsqCaindo;
            tempoPulo = 0;
        }
        this.caindo = caindo;
    }

    public boolean getDirecao() {
        return direcao;
    }

    public void setDirecao(boolean direcao) {
        this.direcao = direcao;
    }

    public boolean getAtaque() {
        return ataque;
    }

    public void setAtaque(boolean ataque) {
        this.ataque = ataque;
    }

    public void setSofrendoDano(boolean sofrendoDano) {
        this.sofrendoDano = sofrendoDano;
    }

    public void moveLeft() {
        if (!(x < 0)) {
            x -= velocX;
            if (!pulando && !caindo && !ataque) {
                esqAtual = andarEsquerda;

            }
        }
    }

    public void moveRight() {
        if (!(x + largura > 820)) {
            x += velocX;
        }
        if (!pulando && !caindo && !ataque) {
            atual = andarDireita;
        }
    }

    public void pulo(int jump, int frame) {
        if (!pulando && !caindo) {
            if (!ataque) {
                efeitos.iniciarSom("src/som/snow_pulo.mp3");
                atual = ImgPulando;
                esqAtual = ImgEsqPulando;
            /*    if (velocX > 0) {
                    velocX--;
                } else if (velocX < 0) {
                    velocX++;
                }*/
            }
            this.frameAtualizar = frame;
            this.tempoPulo = jump;
            pulando = true;
            inicial = getY();
            aux = 0;
        }
    }

    public void ataque() {

        if (!ataque) {
         /*   if (velocX > 0) {
                velocX--;
            } else if (velocX < 0) {
                velocX++;
            }*/
            ataque = true;
            atual = ImgAtaque;
            esqAtual = ImgEsqAtaque;
            tAtaque = 0;
            efeitos.iniciarSom("src/som/rodopiar_snow.mp3");
        }
    }

    public void dano(int dano) {
        if (forestguardian.ForestGuardian.usuario.getNumeroVidas() - dano < 0 && !sofrendoDano) {
            forestguardian.ForestGuardian.usuario.perdaVida(dano);
            morrer();
        } else if (!sofrendoDano) {
            tDano = 0;
            tTransicaoImagem = 0;
            comImagem = false;
            sofrendoDano = true;
            forestguardian.ForestGuardian.usuario.perdaVida(dano);
            if (forestguardian.ForestGuardian.usuario.getNumeroVidas() == 0) {
                morrer();
            }
        }
    }

    public void ImagemInicial() {
        if (!ataque) {
            atual = stopSnow;
            esqAtual = stopEsqSnow;
        }
    }

    //Metodo que desenhara a imagem atual de Snow
    @Override
    public void draw(Graphics g) {
        if (!morrendo) {
            if (comImagem) {
                if (direcao) {
                    g.drawImage(atual.getImage(), x, y, largura, altura, null);
                } else {
                    g.drawImage(esqAtual.getImage(), x, y, largura, altura, null);
                }
            }
        } else {
            g.drawImage(explosao.getImage(), x, y, largura, altura, null);

        }
    }

    //Metodo que atualizara as coordenadas, estados e imagens de Snow
    public void update() {
        if (!morrendo && !morto) {
            
            
            if (pulando) {
                aux++;
                if (aux >= frameAtualizar) {
                    y += tempoPulo;
                    tempoPulo++;
                    if (tempoPulo == 0) {
                        pulando = false;
                        caindo = true;
                        if (!ataque) {
                            atual = ImgCaindo;
                            esqAtual = ImgEsqCaindo;
                        }
                    }
                    aux = 0;
                }
            } else if (caindo) {
                aux++;
                if (aux >= frameAtualizar) {
                    y += tempoPulo;
                    tempoPulo++;
                    if (tempoPulo > MAXIMA_VELOCIDADE_CAIDA) {
                        tempoPulo--;
                    }
                    aux = 0;
                }
            } else if (!ataque) {
                velocX = 5;
            }

            if (ataque) {
                tAtaque++;
                if (tAtaque > 65) {
                    ataque = false;
                    if (pulando) {
                        atual = ImgPulando;
                        esqAtual = ImgEsqPulando;
                    } else if (caindo) {
                        atual = ImgCaindo;
                        esqAtual = ImgEsqCaindo;
                    } else {
                        ImagemInicial();
                    }
                }
            }

            if (sofrendoDano) {
                tDano++;
                tTransicaoImagem++;

                if (tTransicaoImagem > 15) {
                    comImagem = !comImagem;
                }
                if (tDano > 120) {
                    sofrendoDano = false;
                    comImagem = true;
                }
            } else {
                comImagem = true;
            }

            if (!ataque) {
                setLargura(95);
                setAltura(118);
            } else {
                setLargura(124);
                setAltura(125);
            }
            if (forestguardian.ForestGuardian.usuario.getNumeroVidas() <= 0 && !sofrendoDano) {
                morrer();
            }
        } else {
            aux++;
            if (aux == 100) {
                morto = true;
            }
        }
    }
}
