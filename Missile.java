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
        System.out.println("Flag");
        System.out.println(dx + " " + dy);
        translate(dx, dy);
        this.invalidate();
        heading = Math.atan2((double)(target.getY() - ypoints[6]),(double)(target.getX() - xpoints[6]));
    }
    
    public Point getHead(){
        return new Point(xpoints[7], ypoints[7]);
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
