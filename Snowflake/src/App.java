import java.awt.*;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.event.ComponentEvent;
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

        KochSnowflake kochPanel = new KochSnowflake();
        JPanel appPanel = new JPanel();
        JPanel sliderPanel = new JPanel();

        JSlider slider = new JSlider(0);
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
                    kochPanel.setOrder(value);
                    kochPanel.repaint();
                }
            }

        });

        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        sliderPanel.add(slider, BorderLayout.CENTER);

        kochPanel.setPreferredSize(new Dimension(800, 800));

        appPanel.setLayout(new BoxLayout(appPanel, BoxLayout.Y_AXIS));
        appPanel.add(kochPanel, BorderLayout.CENTER);
        appPanel.add(sliderPanel, BorderLayout.EAST);
        appPanel.setBorder(padding);

        frame.add(appPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(300, 300));

        frame.addComponentListener(new ComponentListener() {

            private void resizeApp(JFrame frame, KochSnowflake kochPanel) {
                int newWidth = frame.getWidth();
                int newHeight = frame.getHeight();
                int smallestDimension = newWidth > newHeight ? newHeight : newWidth;

                kochPanel.resize(kochPanel.getWidth(), kochPanel.getHeight(), smallestDimension);
            }

            @Override
            public void componentResized(ComponentEvent e) {
                resizeApp(frame, kochPanel);
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                resizeApp(frame, kochPanel);
            }

            @Override
            public void componentShown(ComponentEvent e) {
                resizeApp(frame, kochPanel);
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });

    }

}
