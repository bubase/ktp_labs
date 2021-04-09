import java.util.Scanner;

public class Lab1
{
    public static void main(String[] args)
    {
        double x = 0;
        double y = 0;
        double z = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите x,y,z точки 1: ");
        x = scanner.nextDouble();
        y = scanner.nextDouble();
        z = scanner.nextDouble();
        Point3d point1 = new Point3d(x, y, z);

        System.out.print("Введите x,y,z точки 2: ");
        x = scanner.nextDouble();
        y = scanner.nextDouble();
        z = scanner.nextDouble();
        Point3d point2 = new Point3d(x, y, z);

        System.out.print("Введите x,y,z точки 3: ");
        x = scanner.nextDouble();
        y = scanner.nextDouble();
        z = scanner.nextDouble();
        Point3d point3 = new Point3d(x, y, z);

        scanner.close();

        System.out.println(String.format("Площадь треугольника: %f", computeArea(point1, point2, point3)));
    }

    // Метод для вычисления площади по формуле Герона
    public static double computeArea(Point3d p1, Point3d p2, Point3d p3)
    {
        if(p1.equals(p2) || p1.equals(p3) || p2.equals(p3))
        {
            System.out.println("Некоторые из точек совпадают, проверьте правильность координат");
            return 0;
        }

        double a = 0;   // длина одной стороны
        double b = 0;   // длина второй стороны
        double c = 0;   // длина третьей стороны
        double p = 0;   // полупериметр

        a = p1.distanceTo(p2);
        b = p1.distanceTo(p3);
        c = p2.distanceTo(p3);
        p = (a + b + c) / 2;

        return Math.sqrt(p * (p-a) * (p-b) * (p-c));
    }
}
