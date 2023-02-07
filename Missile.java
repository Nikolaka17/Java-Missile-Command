import java.awt.Polygon;
import java.awt.Point;

public class Missile extends Polygon{
    private Point target;
    private double heading;
    private int velocity;
    
    public Missile(int x, int y, int l, int v, Point t){
        target = t;
        npoints = 10;
        xpoints = new int[npoints];
        ypoints = new int[npoints];
        
        int[][] offsets = new int[][]{{0,0}, {1,0}, {2,0}, {2,1}, {2,2}, {2,3}, {1,3}, {0,3}, {0,2}, {0,1}};
        for(int i = 0; i < npoints; i++){
            xpoints[i] = x + (offsets[i][0] * l);
            ypoints[i] = y + (offsets[i][0] * l);
        }
        
        heading = Math.atan2((double)(target.getY() - ypoints[6]),(double)(target.getX() - xpoints[6]));
        velocity = v;
    }
    
    public void move(){
        int dx = (int)(velocity * Math.sin(heading));
        int dy = (int)(velocity * Math.cos(heading));
        
        translate(dx, dy);
    }
    
    public Point getHead(){
        return new Point(xpoints[6], ypoints[6]);
    }
    
    public double getHeading(){
        return heading;
    }
    
    public Point getTarget(){
        return target;
    }
}
