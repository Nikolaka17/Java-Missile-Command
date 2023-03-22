import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JOptionPane;

/**
 * Main class that runs a game of missile command
 * @see MCWindow
 * @see Missile
 * @see Explosion
 * @see City
 * @see SoundEffect
 */
public class MissileCommand {
    private static boolean hellfire = false;
    private static int x = 0;
    private static int y = 0;
    private static final String[] SOUND_TYPES = new String[]{"background", "intro", "shooting", "explosion", "reload", "nuke", "ping", "win"};
    
    public static void main(String[] args){
        JFrame window = new JFrame();
        MCWindow panel = new MCWindow();
        Timer onTick = new Timer(20, new ActionListener(){@Override public void actionPerformed(ActionEvent e){panel.update();}});
        Timer enemySpawner = new Timer(1000, new ActionListener(){@Override public void actionPerformed(ActionEvent e){panel.spawnEnemy(hellfire);}});
        Timer screenClearer = new Timer(1, new ActionListener(){@Override public void actionPerformed(ActionEvent e){if(y<=800){panel.explode(x,y);x+=50;if(x>1000){x=0;y+=50;}}}});
        Timer winChecker = new Timer(1, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(panel.gameOver()){
                    onTick.stop();
                    enemySpawner.stop();
                    panel.pause();
                    int choice = JOptionPane.showConfirmDialog(window, "Congrats you scored: " + panel.getScore() + "\n\nRestart?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("C:/Users/Nikolas/Documents/code/Math 271/res/Icon.png"));
                    if(choice == JOptionPane.YES_OPTION){
                        panel.setup();
                        onTick.start();
                        enemySpawner.start();
                    }else{
                        System.exit(0);
                    }
                }
            }
        });
        
        window.add(panel);
        window.addMouseListener(new MouseListener(){@Override public void mouseClicked(MouseEvent e){panel.shoot(e.getX()-13, e.getY()-35);}@Override public void mousePressed(MouseEvent e){}@Override public void mouseReleased(MouseEvent e){}@Override public void mouseEntered(MouseEvent e){}@Override public void mouseExited(MouseEvent e){}});
        window.addKeyListener(new KeyListener(){
            @Override public void keyTyped(KeyEvent e){}
            @Override public void keyReleased(KeyEvent e){}
            @Override
            public void keyPressed(KeyEvent e){
                switch(e.getKeyCode()){
                    case KeyEvent.VK_SPACE:
                        System.out.println("Spawning enemy");
                        panel.spawnEnemy(true);
                        break;
                    case KeyEvent.VK_H:
                        System.out.println("Hell fire starting");
                        hellfire = true;
                        break;
                    case KeyEvent.VK_MINUS:
                        System.out.println("Slowing down");
                        panel.slowDown();
                        break;
                    case KeyEvent.VK_EQUALS:
                        System.out.println("Speeding up");
                        panel.speedUp();
                        break;
                    case KeyEvent.VK_E:
                        System.out.println("Exploding all missiles");
                        panel.explodeAll();
                        break;
                    case KeyEvent.VK_C:
                        System.out.println("Clearing screen");
                        if(screenClearer.isRunning()){
                            screenClearer.stop();
                        }
                        x = 0;
                        y = 0;
                        screenClearer.start();
                        break;
                    case KeyEvent.VK_R:
                        System.out.println("Reloading");
                        panel.reload();
                        break;
                    case KeyEvent.VK_N:
                        System.out.println("Nuking the world");
                        panel.nuke();
                        break;
                    case KeyEvent.VK_1: case KeyEvent.VK_2: case KeyEvent.VK_3: case KeyEvent.VK_4: case KeyEvent.VK_5: case KeyEvent.VK_6: case KeyEvent.VK_7: case KeyEvent.VK_8:
                        System.out.println("Switching " + SOUND_TYPES[(int)(e.getKeyChar() - '1')] + " tracks");
                        panel.nextSound((int)(e.getKeyChar() - '1'));
                        break;
                }
            }
        });

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1000, 800);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);

        panel.setup();
        onTick.start();
        winChecker.start();
        enemySpawner.start();
    }
}
