import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator
{
    static final int MAX_ITERATIONS = 2000;

    public String toString()
    {
        return "Mandelbrot Set";
    }


    /** Sets the initial values for Mandelbrot set */
    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -1.5;
        range.height = 3;
        range.width = 3;
    }

    /** Fractal function that actually counts the num of iterations to check if (x,y) in Mandelbrot's set */
    public int numIterations(double x, double y)
    {
        double zx = x;
        double zy = y;
        int iterations = 0;

        while(iterations < MAX_ITERATIONS && zx * zx + zy * zy < 4)
        {
            double xtemp = zx * zx - zy * zy + x;
            zy = 2 * zx * zy + y;
            zx = xtemp;
            iterations++;
        }

        if(iterations == MAX_ITERATIONS)
            return -1;
        return iterations;
    }
}