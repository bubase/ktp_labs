import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator 
{
    static final int MAX_ITERATIONS = 2000;

    public String toString()
    {
        return "Tricorn";
    }

    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2.5;
        range.y = -1;
        range.height = 2;
        range.width = 1.5;
    }    

    public int numIterations(double x, double y)
    {
        double zx = x;
        double zy = y;

        int iteration = 0;

        while(zx * zx + zy * zy < 4 && iteration < MAX_ITERATIONS)
        {
            double xtemp = zx * zx - zy * zy + x;
            zy = -2 * zx * zy + y;
            zx = xtemp;

            iteration++;
        }

        if(iteration == MAX_ITERATIONS)
            return -1;
        return iteration;
    }
}
