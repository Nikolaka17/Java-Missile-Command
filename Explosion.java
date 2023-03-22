import java.awt.geom.Ellipse2D;

/**
 * A class that represents an explosion for the missile command
 */
public class Explosion extends Ellipse2D.Double{
    private int iterNum = 0;
    private int growSize;
    
    /**
     * Constructor to create a default explosion
     * @param x The x value of the center of the explosion
     * @param y The y value of the center of the explosion
     * @param d The diameter of the explosion
     */
    public Explosion(int x, int y, double d){
        this.x = x - (d/2);
        this.y = y - (d/2);
        this.width = d;
        this.height = d;
        this.growSize = 10;
    }

    /**
     * Constructor to create an explosion with a custom growing size
     * @param x The x value of the center of the explosion
     * @param y The y value of the center of the explosion
     * @param d The diameter of the explosion
     * @param g The ammount of times the explosion can grow
     */
    public Explosion(int x, int y, double d, int g){
        this.x = x - (d/2);
        this.y = y - (d/2);
        this.width = d;
        this.height = d;
        this.growSize = g;
    }

    /**
     * Method to make the explosion grow in size
     * @return A boolean representing if the explosion has reached its max size
     */
    public boolean grow(){
        height += 10;
        width += 10;
        x -= 10;
        y -= 10;
        iterNum++;
        return iterNum > growSize;
    }

    /**
     * Method to get the center x value of the explosion
     * @return The center x value of the explosion
     */
    @Override
    public double getX(){
        return x + (height / 2);
    }

    /**
     * Method to get the center y value of the explosion
     * @return The center y value of the explosion
     */
    @Override 
    public double getY(){
        return y + (height / 2);
    }
}
