import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

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

        g2.setColor(Color.BLUE);
        for(City c: cities){
            g2.fill(c);
        }
    }
}
