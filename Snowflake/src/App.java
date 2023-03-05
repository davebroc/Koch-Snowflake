import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.imageio.*;

public class App {
    public static void main(String[] args) throws Exception {

        // Set up the main GUI frame
        JFrame frame = new JFrame("Koch Snowflake");
        Image image = ImageIO.read(new File("./src/icon.png"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(image);

        KochSnowflake koch = new KochSnowflake();
        JPanel contentPanel = new JPanel();

        JSlider slider = new JSlider(1);
        slider.setValue(1);
        slider.setMinimum(1);
        slider.setMaximum(10);
        slider.setMajorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int value = source.getValue();
                    koch.setOrder(value);
                    koch.repaint();
                }
            }

        });

        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        contentPanel.setBorder(padding);
        contentPanel.add(koch);
        contentPanel.add(slider);

        frame.add(contentPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
