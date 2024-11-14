import javax.swing.*;
import java.awt.*;

public class BoatDrawing extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //  корпус корабля
        g.setColor(Color.BLACK);
        int[] xPoints = {100, 300, 260, 140};
        int[] yPoints = {250, 250, 300, 300};
        g.fillPolygon(xPoints, yPoints, 4);

        //  левая часть паруса
        g.setColor(Color.GRAY);
        int[] xLeftSail = {250, 150, 200};
        int[] yLeftSail = {250, 250, 100};
        g.fillPolygon(xLeftSail, yLeftSail, 3);

        // правая часть паруса
        g.setColor(Color.BLACK);
        int[] xRightSail = {250, 200, 200};
        int[] yRightSail = {250, 250, 100};
        g.fillPolygon(xRightSail, yRightSail, 3);

        // окна
        g.setColor(Color.WHITE);
        g.fillOval(150, 260, 30, 30);
        g.fillOval(190, 260, 30, 30);
        g.fillOval(230, 260, 30, 30);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Boat");
        BoatDrawing boat = new BoatDrawing();
        frame.add(boat);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
