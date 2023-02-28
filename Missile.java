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
        
        xpoints[0] = x;
        ypoints[0] = y;
        
        xpoints[1] = x + l;
        ypoints[1] = y;
        
        xpoints[2] = x + (2 * l);
        ypoints[2] = y;
        
        xpoints[3] = x + (2 * l);
        ypoints[3] = y + l;
        
        xpoints[4] = x + (2 * l);
        ypoints[4] = y + (2 * l);
        
        xpoints[5] = x + (2 * l);
        ypoints[5] = y + (3 * l);
        
        xpoints[6] = x + l;
        ypoints[6] = y + (3 * l);
        
        xpoints[7] = x;
        ypoints[7] = y + (3 * l);
        
        xpoints[8] = x;
        ypoints[8] = y + (2 * l);
        
        xpoints[9] = x;
        ypoints[9] = y + l;
        
        heading = Math.atan2((double)(target.getY() - ypoints[6]),(double)(target.getX() - xpoints[6]));
        velocity = v;
    }
    
    public void move(){
        int dx = (int)(velocity * Math.sin(heading));
        int dy = (int)(velocity * Math.cos(heading));
        translate(dy, dx);
        this.invalidate();
        heading = Math.atan2((double)(target.getY() - ypoints[6]),(double)(target.getX() - xpoints[6]));
    }
    
    public Point getHead(){
        return new Point(xpoints[8], ypoints[8]);
    }
    
    public double getHeading(){
        return heading;
    }
    
    public Point getTarget(){
        return target;
    }
    
    public boolean atTarget(){
        return contains(target.getX(), target.getY());
    }
    
    public Point getTail(){
        return new Point(xpoints[1], ypoints[1]);
    }
}
