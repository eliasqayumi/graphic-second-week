package ball;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Shadow extends JPanel {
    private double xSlide;
    private double ySlide;
    private double degree;

    public Shadow(double xSlide, double ySlide, double degree) {
        this.xSlide = xSlide;
        this.ySlide = ySlide;
        this.degree = degree;
    }

    public void doDrawing(Graphics2D g2d) {
        double hRadius = getHeight() * 0.05;
        g2d.draw(new Line2D.Double(xSlide + hRadius, ySlide + hRadius,
                xSlide + hRadius, ySlide + hRadius - degree));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing((Graphics2D) g);
    }
}
