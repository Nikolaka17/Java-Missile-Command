/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package missilecommand;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JOptionPane;

public class MissileCommand {
    
    public static void main(String[] args){
        JFrame window = new JFrame();
        MCWindow panel = new MCWindow();
        Timer onTick = new Timer(20, new ActionListener(){@Override public void actionPerformed(ActionEvent e){panel.update();}});
        Timer enemySpawner = new Timer(1000, new ActionListener(){@Override public void actionPerformed(ActionEvent e){panel.spawnEnemy();}});
        Timer winChecker = new Timer(1, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(panel.gameOver()){
                    onTick.stop();
                    enemySpawner.stop();
                    panel.pause();
                    int choice = JOptionPane.showConfirmDialog(window, "Congrats you scored: " + panel.getScore() + "\n\nRestart?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Icon.png"));
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
