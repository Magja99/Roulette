import java.awt.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.*;
import javax.swing.*;
import java.awt.Graphics;
import javax.swing.SpringLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BasicWindow
{
    public Container inter;
    public JFrame frame = new JFrame();
    public SpringLayout layout = new SpringLayout();
    public BasicWindow()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inter = frame.getContentPane();
        inter.setLayout(layout);
    }
    public void show()
    {
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (frame.getWidth() / 2), middle.y - (frame.getHeight() / 2));
        frame.setLocation(newLocation);
        frame.setVisible(true);
    }
}