import java.awt.geom.Ellipse2D;

public class Explosion extends Ellipse2D.Double{
    private int iterNum = 0;
    
    public Explosion(int x, int y, double r){
        this.x = x - (r/2);
        this.y = y - (r/2);
        this.width = r;
        this.height = r;
    }

    public boolean grow(){
        height += 10;
        width += 10;
        x -= 10;
        y -= 10;
        iterNum++;
        return iterNum > 5;
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
