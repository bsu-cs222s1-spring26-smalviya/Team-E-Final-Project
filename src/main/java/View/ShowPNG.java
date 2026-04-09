package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ShowPNG {
    public void showPng(String fileName) throws IOException {
            BufferedImage image = ImageIO.read(new File(fileName));

            JFrame frame = new JFrame("Show PNG picture");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JLabel label = new JLabel(new ImageIcon(image));
            frame.getContentPane().add(label, BorderLayout.CENTER);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
