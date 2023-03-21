import java.awt.Polygon;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Missile extends Polygon{
    private Point target;
    private double heading;
    private int velocity;
    public ArrayList<Rectangle> spots;
    private boolean enemy;
    private int iter;
    private int distance;
    private int dy;
    private int dx;
    
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
        distance = (int)((target.getX() - xpoints[6]) * Math.cos(heading));
        dx = (int)(velocity * Math.sin(heading));
        dy = (int)(velocity * Math.cos(heading));
        velocity = v;
        spots = new ArrayList<Rectangle>();
        enemy = false;
        iter = 0;
    }

    public Missile(int x, int y, int l, int v, Point t, boolean e){
        this(x, y, l, v, t);
        enemy = e;
    }
    
    public void move(){
        dx = (int)(velocity * Math.sin(heading));
        dy = (int)(velocity * Math.cos(heading));
        iter++;
        if(iter % 5 == 0){
            if(enemy){
                add((int)getTail().getX(), (int)getTail().getY());
            }else{
                add((int)getHead().getX(), (int)getHead().getY());
            }
        }
        translate(dy, dx);
        this.invalidate();
        heading = Math.atan2((double)(target.getY() - ypoints[6]),(double)(target.getX() - xpoints[6]));
        distance = (int)((target.getX() - xpoints[6]) * Math.cos(heading));
    }

    public void add(int x, int y){
        spots.add(new Rectangle(x, y, 5, 5));
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
    
    public boolean atTarget(){
        return contains(target.getX(), target.getY()) || (Math.abs(distance) <= Math.abs(dy) && Math.abs(distance) <= Math.abs(dx));
    }
    
    public Point getTail(){
        return new Point(xpoints[1], ypoints[1]);
    }

    public int getCenterX(){
        return xpoints[2] - ((xpoints[2] - xpoints[0])/2);
    }

    public int getCenterY(){
        return ypoints[7] - ((ypoints[7] - ypoints[0])/2);
    }

    public boolean isEnemy(){
        return enemy;
    }
}
