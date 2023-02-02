import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Polygon;

import java.util.ArrayList;

public class MCWindow extends JPanel{
    private ArrayList<City> cities = new ArrayList<City>();
    
    public MCWindow(){
        super();
        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        //g2.drawImage(new ImageIcon("missile.jpg").getImage(), 0, 0, getWidth(), getHeight(), null);
        
        g2.setColor(Color.GREEN);
        int[][] offsets = new int[][]{{9, 57, 98, 163, 221, 339, 371, 447, 482, 630, 662, 723, 782},{534, 534, 480, 480, 530, 530, 480, 480, 530, 530, 480, 480, 530}};
        g2.fill(new Polygon(offsets[0], offsets[1], offsets[0].length));

        g2.setColor(Color.BLUE);
        for(City c: cities){
            g2.fill(c);
        }
    }
}
