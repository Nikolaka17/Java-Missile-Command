import java.awt.Rectangle;
import java.util.ArrayList;

public class Trail{
    public ArrayList<Rectangle> spots = new ArrayList<Rectangle>();
    
    public void add(int x, int y){
        spots.add(new Rectangle(x, y, 5, 5));
    }
}
