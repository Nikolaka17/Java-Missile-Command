import java.awt.geom.Ellipse2D;

public class Explosion extends Ellipse2D.Double{
    private int iterNum = 0;
    
    public Explosion(int x, int y, int r){
        this.x = x + r;
        this.y = y + r;
        this.height = r + r;
        this.width = r + r;
    }

    public boolean grow(){
        height += 10;
        width += 10;
        iterNum++;
        return iterNum < 10;
    }

    @Override
    public double getX(){
        return x + (height / 2);
    }

    @Override 
    public double getY(){
        return y + (height / 2);
    }
}
