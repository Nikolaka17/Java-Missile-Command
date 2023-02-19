import java.awt.Polygon;
import java.awt.Point;

public class City extends Polygon{
    
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

    public Point getHead(){
        return new Point(xpoints[6], ypoints[6]);
    }
}
