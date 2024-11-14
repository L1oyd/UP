// Абстрактный класс "Фигура"
abstract class Shape {
    protected double x, y; // Координаты фигуры
    protected double angle; // Угол поворота

    public Shape(double x, double y) {
        this.x = x;
        this.y = y;
        this.angle = 0;
    }

    // Метод для перемещения фигуры
    public void move(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    // Метод для поворота фигуры на угол
    public void rotate(double angle) {
        this.angle = (this.angle + angle) % 360;
    }

    // Абстрактный метод для изменения размера фигуры
    public abstract void resize(double factor);

    // Метод для вывода информации о фигуре
    public void printInfo() {
        System.out.println("Position: (" + x + ", " + y + ")");
        System.out.println("Rotation: " + angle + " degrees");
    }
}

// Класс "Круг"
class Circle extends Shape {
    private double radius;

    public Circle(double x, double y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void resize(double factor) {
        this.radius *= factor;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Circle with radius: " + radius);
    }
}

// Класс "Квадрат"
class Square extends Shape {
    private double side;

    public Square(double x, double y, double side) {
        super(x, y);
        this.side = side;
    }

    @Override
    public void resize(double factor) {
        this.side *= factor;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Square with side length: " + side);
    }
}

// Класс "Прямоугольник"
class Rectangle extends Shape {
    private double width, height;

    public Rectangle(double x, double y, double width, double height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void resize(double factor) {
        this.width *= factor;
        this.height *= factor;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Rectangle with width: " + width + " and height: " + height);
    }
}

// Основной класс
public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(0, 0, 5);
        Square square = new Square(10, 10, 4);
        Rectangle rectangle = new Rectangle(5, 5, 6, 3);

        // Работа с фигурами
        circle.printInfo();
        circle.move(3, 4);
        circle.rotate(45);
        circle.resize(2);
        circle.printInfo();

        square.printInfo();
        square.move(-2, 5);
        square.rotate(90);
        square.resize(0.5);
        square.printInfo();

        rectangle.printInfo();
        rectangle.move(1, -1);
        rectangle.rotate(30);
        rectangle.resize(1.5);
        rectangle.printInfo();
    }
}
