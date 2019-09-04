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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Errors extends BasicWindow
{
    private JLabel ErrorMessege = new JLabel("", SwingConstants.CENTER);
    private JButton OK = new JButton("OK");

    public Errors()
    {
        frame.setTitle("ERROR");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        class OKListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
            }
        }

        inter.add(ErrorMessege);
        layout.putConstraint(SpringLayout.WEST, ErrorMessege, 50, SpringLayout.WEST, inter);
        layout.putConstraint(SpringLayout.NORTH, ErrorMessege, 20, SpringLayout.NORTH, inter);

        inter.add(OK);
        layout.putConstraint(SpringLayout.WEST, OK, 50, SpringLayout.WEST, ErrorMessege);
        layout.putConstraint(SpringLayout.NORTH, OK, 40, SpringLayout.NORTH, ErrorMessege);
        OK.addActionListener(new OKListener());

        frame.setPreferredSize(new Dimension(300, 125));
        ErrorMessege.setPreferredSize(new Dimension(200, 20));
        OK.setPreferredSize(new Dimension(100, 30));
    }

    public void BadInput()
    {
        ErrorMessege.setText("Bad Input!");
        show();
    }

    public void InvalidRange()
    {
        ErrorMessege.setText("Invalid Range!");
        show();
    }

    public void SelectBet()
    {
        ErrorMessege.setText("First select bet!");
        show();
    }

    public void FiveNumbers()
    {
        ErrorMessege.setText("Selected more than 5 numbers!");
        show();
    }

    public void NothingWagered()
    {
        ErrorMessege.setText("Nothing wagered!");
        show();
    }
    public void TooMuch()
    {
        ErrorMessege.setText("You don't have that much!");
        show();
    }
}
