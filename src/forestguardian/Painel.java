package forestguardian;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Ayumi, Francisco e Gideão
 */

public class Painel extends JPanel {
    public static boolean play;
    
    private final Thread threadScreenGame; 
    private final GameRunnable game;
   
    public Painel() {
        super(null);
        play = true;
         
        game = new GameRunnable(this);
        
        threadScreenGame = new Thread(new DrawOnScreen());
        threadScreenGame.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        game.draw(g);
    }

    class DrawOnScreen implements Runnable {
        @Override
        public void run() {
            while (play) {
                    game.update();
                    repaint();
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            ForestGuardian.Frame.salvarUsuario();
            ForestGuardian.Frame.dispose();
        }
    }

}