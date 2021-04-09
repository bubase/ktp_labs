// двумерный класс точки
public class Point2d {
    // координата X
    private double xCoord;
    // координата Y
    private double yCoord;

    // конструктор инициализации
    public Point2d ( double x,  double y) {
        xCoord = x;
        yCoord = y;
    }
    // конструктор по умолчанию
    public Point2d() {
        //Вызовите конструктор с двумя параметрами и определите источник.
        this(0, 0);
    }

    // Возвращение координаты Y
    public double getY () {
        return yCoord;
    }

    // Установка значения координаты X
    public void setX( double val) {
        xCoord = val;
    }

    // Установка значения координаты Y
    public void  setY ( double val) {
        yCoord = val;
    }
}
