import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.Line2D;

public class KochSnowflake extends JPanel {

    private static String axiom = "F++F++F";
    private static String productionRule = "F-F++F-F";
    private int order = 1;
    private final int startLength = 243;

    public KochSnowflake() {
        // '+': () => { ctx.rotate((Math.PI/180) * 60) },
        // '-': () => { ctx.rotate((Math.PI/180) * -60) },
        // 'F': () => {
        // ctx.beginPath()
        // ctx.moveTo(0,0)
        // ctx.lineTo(0, 28/(koch.iterations + 1))
        // ctx.stroke()
        // ctx.translate(0, 28/(koch.iterations + 1))}
        // }
    }

    public void setOrder(int newOrder) {
        order = newOrder;
    }

    private void drawSnowflake(Graphics2D g, int angle, String str, int length, int ord) {
        for (char c : str.toCharArray()) {
            switch (c) {
                case 'F':
                    double newX = length * Math.cos(Math.toRadians(angle));
                    double newY = length * Math.sin(Math.toRadians(angle));
                    if (ord != 0) {
                        drawSnowflake(g, angle, productionRule, length / 3, ord - 1);
                        break;
                    }
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
        return new Dimension(1000, 800);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(new BasicStroke(2));
        g2.setColor(new Color(0, 0, 0));
        g2.translate(100, 200);
        drawSnowflake(g2, 0, axiom, startLength, order);
    }

}
