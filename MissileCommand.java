import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MissileCommand {
    
    public static void main(String[] args){
        JFrame window = new JFrame();
        MCWindow panel = new MCWindow();
        Timer onTick = new Timer(1000, new ActionListener(){@Override public void actionPerformed(ActionEvent e){panel.update();}});
        
        window.add(panel);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.setup();
        onTick.start();
    }
}
