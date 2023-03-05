import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.awt.geom.AffineTransform;
import java.awt.event.*;

public class KochSnowflake extends JPanel implements MouseWheelListener {

    private static String axiom = "F++F++F";
    private static String productionRule = "F-F++F-F";
    private int order = 1;
    private double zoomFactor = 1;

    private final int startLength = 14348907; // 3^15

    private HashMap<String, Line2D> lines = new HashMap<String, Line2D>();

    public KochSnowflake() {
        addMouseWheelListener(this);
    }

    public void setOrder(int newOrder) {
        order = newOrder;
    }

    private void drawSnowflake(Graphics2D g, int angle, String str, int length, int ord) {
        if (angle < 360 || angle > -360)
            angle = angle % 360;
        for (char c : str.toCharArray()) {
            switch (c) {
                case 'F':
                    if (ord > 1) {
                        drawSnowflake(g, angle, productionRule, length / 3, ord - 1);
                        break;
                    }

                    Line2D line;
                    String key = angle + "," + length;
                    if (!lines.containsKey(key)) {
                        double newX = length * Math.cos(Math.toRadians(angle));
                        double newY = length * Math.sin(Math.toRadians(angle));
                        line = new Line2D.Double(0, 0, newX, newY);
                        lines.put(key, line);
                    } else
                        line = lines.get(key);

                    g.draw(line);
                    g.translate(line.getX2(), line.getY2());

                    break;
                case '+':
                    angle += 60;
                    break;
                case '-':
                    angle -= 60;
                    break;

                default:
                    break;
            }

        }

    }

    /**
     * Gets the preferred size of the Canvas.
     * 
     * @return The preferred size of the Canvas.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 900);
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0)// zoom in
            zoomFactor *= 1.1;
        else // zoom out
            zoomFactor /= 1.1;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(new BasicStroke(1));
        g2.setColor(new Color(0, 0, 0));
        g2.translate(100, 200);
        g2.scale(0.00001 * zoomFactor, 0.00001 * zoomFactor);

        drawSnowflake(g2, 0, axiom, startLength, order);
    }

}
