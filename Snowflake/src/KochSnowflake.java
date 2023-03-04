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

    private final int startLength = 59049; // 3^10

    private HashMap<Integer, Double[]> trigValues = new HashMap<Integer, Double[]>();
    // memoisation of trig functions of angles (key)
    // [0] is cosine
    // [1] is sine

    public KochSnowflake() {
        addMouseWheelListener(this);
    }

    public void setOrder(int newOrder) {
        order = newOrder;
    }

    private void drawSnowflake(Graphics2D g, int angle, String str, int length, int ord) {
        for (char c : str.toCharArray()) {
            switch (c) {
                case 'F':
                    if (ord > 1) {
                        drawSnowflake(g, angle, productionRule, length / 3, ord - 1);
                        break;
                    }
                    Double[] trig = new Double[2];
                    if (!trigValues.containsKey(angle)) {
                        trig[0] = Math.cos(Math.toRadians(angle));
                        trig[1] = Math.sin(Math.toRadians(angle));
                        trigValues.put(angle, trig);
                    } else
                        trig = trigValues.get(angle);

                    double newX = length * trig[0];// cosine(angle)
                    double newY = length * trig[1];// sin(angle)
                    Line2D line = new Line2D.Double(0, 0, newX, newY);

                    g.draw(line);
                    g.translate(newX, newY);

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
        System.out.println(e.getWheelRotation());
        if (e.getWheelRotation() < 0) {// zoom in
            // mousePoint = e.getPoint();
            zoomFactor *= 1.1;
        } else { // zoom out
            // mousePoint = e.getPoint();
            zoomFactor /= 1.1;
        }
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(new BasicStroke(1));
        g2.setColor(new Color(0, 0, 0));
        g2.translate(100, 200);
        g2.scale(0.01 * zoomFactor, 0.01 * zoomFactor);

        drawSnowflake(g2, 0, axiom, startLength, order);

    }

}
