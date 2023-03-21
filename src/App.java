import java.awt.*;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.imageio.*;

/**
 * The App class creates the GUI for the Koch Snowflake program.
 * 
 * The GUI displays the fractal with a JSlider component that allows the user
 * to adjust the order of the fractal.
 * 
 * The KochSnowflake class handles the generation and drawing of the fractal,
 * and the ComponentListener is used to dynamically resize the fractal when the
 * window size changes.
 * 
 * @author David Brockbank
 * @version 1.0, March 2023
 */
public class App {
    /**
     * The main method sets up and displays the main GUI frame for the Koch
     * Snowflake program.
     * The frame contains a panel that displays the Koch Snowflake and a slider for
     * adjusting the order of the fractal.
     * The method also includes a component listener to resize the fractal panel
     * when the frame is resized.
     *
     * @param args command line arguments
     * @throws Exception if there is an error loading the icon image
     */
    public static void main(String[] args) throws Exception {

        // Set up the main GUI frame
        JFrame frame = new JFrame("Koch Snowflake");
        Image image = ImageIO.read(new File("icon.png"));
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

            /**
             * Resize the fractal panel when the frame is resized
             *
             * @param frame     the main frame
             * @param kochPanel the fractal panel
             */
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
