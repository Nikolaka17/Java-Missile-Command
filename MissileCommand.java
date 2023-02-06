import javax.swing.JFrame;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MissileCommand {
    
    public static void main(String[] args){
        JFrame window = new JFrame();
        MCWindow panel = new MCWindow();

        window.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent me){
                System.out.println("(" + me.getX() + ", "+ me.getY() + ")");
            }

            @Override
            public void mousePressed(MouseEvent me) {
                
            }

            @Override
            public void mouseReleased(MouseEvent me){
            }

            @Override
            public void mouseEntered(MouseEvent me){
            }

            @Override
            public void mouseExited(MouseEvent me){
            }
        });
        window.add(panel);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.setup();
    }
}
