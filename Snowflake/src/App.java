import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.imageio.*;

public class App {
    public static void main(String[] args) throws Exception {

        // Set up the main GUI frame
        JFrame frame = new JFrame("Koch Snowflake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        KochSnowflake koch = new KochSnowflake();

        JSlider slider = new JSlider();
        slider.setValue(1);
        slider.setMinimum(1);
        slider.setMaximum(12);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
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

        frame.add(koch, BorderLayout.NORTH);
        frame.add(slider, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
