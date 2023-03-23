import java.awt.Polygon;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Class that represents a missile for the missile command game
 * @see MissileCommand
 * @see MCWindow
 */
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
    
    /**
     * Constructor to create a player missile
     * @param x The x value of the missiles top left point
     * @param y The y value of the missiles top left point
     * @param l The length of each segment of the missile
     * @param v The velocity of the missile
     * @param t The target for the missile to shoot to
     */
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

    /**
     * Constructor to create an enemy missile
     * @param x The x value of the missiles top left point
     * @param y The y value of the missiles top left point
     * @param l The length of each segment of the missile
     * @param v The velocity of the missile
     * @param t The target for the missile to shoot to
     * @param e A boolean representing if the missile is an enemy missile
     */
    public Missile(int x, int y, int l, int v, Point t, boolean e){
        this(x, y, l, v, t);
        enemy = e;
    }
    
    /**
     * Moves the missile towards the target
     */
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
        distance = (int) Math.abs(target.getY() - ypoints[6]);
    }

    /**
     * Adds a spot to the missiles trail
     * @param x The x value of the spot
     * @param y The y value of the spot
     */
    private void add(int x, int y){
        spots.add(new Rectangle(x, y, 5, 5));
    }
    
    /**
     * Returns the location of the front of the missile
     * @return A point with the x and y value of the head of the missile
     */
    public Point getHead(){
        return new Point(xpoints[6], ypoints[6]);
    }
    
    /**
     * Returns the direction of the missile
     * @return The direction of the missile pointing from its current location to the target
     */
    public double getHeading(){
        return heading;
    }
    
    /**
     * Returns the location of the target of the missile
     * @return A point with the x and y value of the target of the missile
     */
    public Point getTarget(){
        return target;
    }
    
    /**
     * Checks if the missile is at or close enough to the target to explode
     * @return A boolean representing if the missile is at its target
     */
    public boolean atTarget(){
        return contains(target.getX(), target.getY()) || (distance <= Math.abs(dy));
    }
    
    /**
     * Returns the location of the back of the missile
     * @return A point with the x and y value of the tail of the missile
     */
    public Point getTail(){
        return new Point(xpoints[1], ypoints[1]);
    }

    /**
     * Gets the x value of the center of the missile
     * @return The center x value of the missile
     */
    public int getCenterX(){
        return xpoints[2] - ((xpoints[2] - xpoints[0])/2);
    }

    /**
     * Gets the y value of the center of the missile
     * @return The center y value of the missile
     */
    public int getCenterY(){
        return ypoints[7] - ((ypoints[7] - ypoints[0])/2);
    }

    /**
     * Checks if the missile is an enemy or player missile
     * @return A boolean representing if the missile is an enemy missile
     */
    public boolean isEnemy(){
        return enemy;
    }
}
