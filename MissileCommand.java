import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class MissileCommand {
    
    public static void main(String[] args){
        JFrame window = new JFrame();
        MCWindow panel = new MCWindow();
        Timer onTick = new Timer(100, new ActionListener(){@Override public void actionPerformed(ActionEvent e){panel.update();}});
        
        window.add(panel);
        window.addMouseListener(new MouseListener(){@Override public void mouseClicked(MouseEvent e){panel.shoot(e.getX(), e.getY());}@Override public void mousePressed(MouseEvent e){}@Override public void mouseReleased(MouseEvent e){}@Override public void mouseEntered(MouseEvent e){}@Override public void mouseExited(MouseEvent e){}});

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.setup();
        onTick.start();
    }
}

