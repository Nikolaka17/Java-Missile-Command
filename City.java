import java.awt.Polygon;
import java.awt.Point;

/**
 * A class representing the cities for the missile command game
 */
public class City extends Polygon{
    
    /**
     * Constructor to create a city
     * @param x The x value of the top left of the city
     * @param y The y value of the top left of the city
     * @param l The length of each segment in the city
     */
    public City(int x, int y, int l){
        npoints = 18;
        ypoints = new int[npoints];
        xpoints = new int[npoints];
        
        int[][] offsets = new int[][]{{0, 8}, {0, 6}, {2, 6}, {2, 2}, {4, 2}, {4, 0}, {6, 0}, {6, 2}, {8, 2}, {8, 4}, {10, 4}, {10, 1}, {12, 1}, {12, 0}, {14, 0}, {14, 6}, {16, 6}, {16, 8}};
        
        for(int i = 0; i < npoints; i++){
            xpoints[i] = x + (l * offsets[i][0]);
            ypoints[i] = y + (l * offsets[i][1]);
        }
    }

    /**
     * Returns the top center of the city
     * @return A point with the x and y values of the top center point of the city
     */
    public Point getHead(){
        return new Point(xpoints[6], ypoints[6]);
    }
}
