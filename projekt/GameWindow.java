import java.awt.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameWindow extends BasicWindow
{
    private JButton Spin = new JButton("Spin!");
    private JButton Back = new JButton("Go Back");

    private JTextField BetAmount = new JTextField();

    private JLabel Balance = new JLabel("Current Balance: 1000");
    private JLabel Bet = new JLabel("Bet: ");
    private JLabel One = new JLabel();
    private JLabel Two = new JLabel();
    private JLabel Three = new JLabel();
    private JLabel Four = new JLabel();

    private Wheel wheel = new Wheel();
    private RouletteLayout mate = new RouletteLayout();

    private int bet = 0;
    public boolean goBack = false;

    private void SetWagered()
    {
        String array[] = {"Red", "Black", "Even", "Odd", "1 to 18", "19 to 36", "1st 12", "2nd 12", "3rd 12", "0"};
        String p = "";

        for(int i = 1; i < 11; i++)
            p += "<html>" + array[i - 1] + ": " + Integer.toString(mate.t[i]) + "<br/>";
        p += "<html> 1: " + Integer.toString(mate.t[11]) + "<br/>";
        p += "<html> 2: " + Integer.toString(mate.t[12]) + "<br/>";

        One.setText(p);
        p = "";

        for(int i = 13; i < 25; i++)
            p += "<html>" + Integer.toString(i - 10) + ": " + Integer.toString(mate.t[i]) + "<br/>";

        Two.setText(p);
        p = "";

        for(int i = 25; i < 37; i++)
             p += "<html>" + Integer.toString(i - 10) + ": " + Integer.toString(mate.t[i]) + "<br/>";

        Three.setText(p);
        p = "";

        for(int i = 37; i < 47; i++)
           p += "<html>" + Integer.toString(i - 10) + ": " + Integer.toString(mate.t[i]) + "<br/>";

        Four.setText(p);
    }
    class CheckBalance extends Thread
    {
        Thread t;
        public void run()
        {
            try
            {
               while(true)
               {
                    String s = "Current Balance: ";
                    s += Integer.toString(mate.balance);
                    Balance.setText(s);
                    Thread.sleep(50);
                    SetWagered();
                }
            }
            catch (InterruptedException e) {}
        }
        public void start()
        {
            if(t == null)
            {
               t = new Thread (this);
               t.start ();
            }
        }
    }
    class BackListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            goBack = true;
            frame.dispose();
        }
    }
    class SpinListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            wheel.spin();

            mate.check(wheel.number, wheel.colour);
            if(!mate.spin) return;

            layout.putConstraint(SpringLayout.WEST, wheel.Ball, wheel.x(), SpringLayout.WEST, inter);
            layout.putConstraint(SpringLayout.NORTH, wheel.Ball, wheel.y(), SpringLayout.NORTH, inter);
            frame.revalidate();
            frame.repaint();

            inter.add(wheel.Image);
            layout.putConstraint(SpringLayout.WEST, wheel.Image, 50, SpringLayout.WEST, inter);
            layout.putConstraint(SpringLayout.NORTH, wheel.Image, 10, SpringLayout.NORTH, inter);
            frame.revalidate();
            frame.repaint();
        }
    }
    
    public GameWindow()
    {
        CheckBalance x = new CheckBalance();
        x.start();

        inter.add(wheel.Ball);
        layout.putConstraint(SpringLayout.WEST, wheel.Ball, 246, SpringLayout.WEST, inter);
        layout.putConstraint(SpringLayout.NORTH, wheel.Ball, 100, SpringLayout.NORTH, inter);

        inter.add(wheel.Image);
        Back.addActionListener(new BackListener());
        layout.putConstraint(SpringLayout.WEST, wheel.Image, 50, SpringLayout.WEST, inter);
        layout.putConstraint(SpringLayout.NORTH, wheel.Image, 10, SpringLayout.NORTH, inter);

        inter.add(Spin);
        Spin.addActionListener(new SpinListener());
        Spin.setPreferredSize(new Dimension(150, 50));
        layout.putConstraint(SpringLayout.WEST, Spin, 128, SpringLayout.WEST, wheel.Image);
        layout.putConstraint(SpringLayout.NORTH, Spin, 420, SpringLayout.NORTH, wheel.Image);

        inter.add(Balance);
        Balance.setPreferredSize(new Dimension(150, 50));
        layout.putConstraint(SpringLayout.WEST, Balance, 125, SpringLayout.WEST, Spin);
        layout.putConstraint(SpringLayout.NORTH, Balance, 60, SpringLayout.NORTH, Spin);

        inter.add(mate.panel);
        layout.putConstraint(SpringLayout.WEST, mate.panel, 450, SpringLayout.WEST, wheel.Image);
        layout.putConstraint(SpringLayout.NORTH, mate.panel, 15, SpringLayout.NORTH, wheel.Image);
        mate.panel.setPreferredSize(new Dimension(200, 450));

        inter.add(One);
        layout.putConstraint(SpringLayout.WEST, One, 30, SpringLayout.WEST, inter);
        layout.putConstraint(SpringLayout.NORTH, One, 50, SpringLayout.NORTH, Balance);

        inter.add(Two);
        layout.putConstraint(SpringLayout.WEST, Two, 200, SpringLayout.WEST, One);
        layout.putConstraint(SpringLayout.NORTH, Two, 50, SpringLayout.NORTH, Balance);

        inter.add(Three);
        layout.putConstraint(SpringLayout.WEST, Three, 200, SpringLayout.WEST, Two);
        layout.putConstraint(SpringLayout.NORTH, Three, 50, SpringLayout.NORTH, Balance);

        inter.add(Four);
        layout.putConstraint(SpringLayout.WEST, Four, 200, SpringLayout.WEST, Three);
        layout.putConstraint(SpringLayout.NORTH, Four, 50, SpringLayout.NORTH, Balance);

        inter.add(Back);

        frame.setPreferredSize(new Dimension(800, 770));
        show();
    }
}
