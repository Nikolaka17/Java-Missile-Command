import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Polygon;

import java.util.ArrayList;

public class MCWindow extends JPanel{
    private ArrayList<City> cities = new ArrayList<City>();
    private int[] missiles = new int[]{10, 10, 10};
    
    public MCWindow(){
        super();
        setBackground(Color.BLACK);
    }

    public void setup(){
        missiles = new int[]{10, 10, 10};
        if(cities.size() != 0){
            for(City c: cities){
                cities.remove(c);
            }
        }
        int w = getWidth();
        int h = getHeight();
        System.out.println(h);
        cities.add(new City(w/5, h - (h/10) - (w/24), w/192));
        cities.add(new City(w/5 + w/12,  h - (h/10) - (w/24), w/192));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();
        setup();
        
        g2.drawImage(new ImageIcon("missile.jpg").getImage(), 0, 0, w, h, null);
        
        g2.setColor(Color.GREEN);
        int[][] offsets = new int[][]{{0, 0, w/20, w/10, 3*w/20, w/5, 9*w/20, w/2, 11*w/20, 12*w/20, 17*w/20, 18*w/20, 19*w/20, w, w},{h, h-(h/10), h-(h/10), h-(h/5), h-(h/5), h-(h/10), h-(h/10), h-(h/5), h-(h/5), h-(h/10), h-(h/10), h-(h/5), h-(h/5), h-(h/10), h}};
        g2.fill(new Polygon(offsets[0], offsets[1], offsets[0].length));

        g2.setColor(Color.BLUE);
        for(City c: cities){
            g2.fill(c);
        }
    }
}
