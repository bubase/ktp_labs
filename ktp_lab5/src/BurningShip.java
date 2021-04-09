import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator 
{
    static final int MAX_ITERATIONS = 2000;

    public String toString()
    {
        return "Burning Ship";
    }

    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -2.5;
        range.height = 3;
        range.width = 4;
    }

    public int numIterations(double x, double y)
    {
        double zx = x;
        double zy = y;

        int iteration = 0;
        
        while(zx * zx + zy * zy < 4 && iteration < MAX_ITERATIONS)
        {
            double xtemp = zx * zx - zy * zy + x;
            zy = Math.abs(2 * zx * zy) + y;
            zx = xtemp;

            iteration++;
        }

        if(iteration == MAX_ITERATIONS)
            return -1;
        return iteration;
    }
}
