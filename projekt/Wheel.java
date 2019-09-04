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

public class Wheel
{
    public JLabel Image;
    public JLabel Ball;

    public int number = 0;
    private int index;
    public String colour;

    private int X[] = {246, 263, 280, 299, 313, 324, 335, 343, 351, 357, 353, 350, 343, 335, 324, 310, 293, 275, 255, 237, 217, 199, 185, 168, 154, 143, 140, 137,
        134, 135, 140, 148, 161, 176, 192, 210, 228};
    private int Y[] = {100, 103, 108, 113, 124, 138, 151, 167, 183, 202, 220, 240, 255, 273, 289, 300, 309, 316, 319, 319, 315, 308, 298, 289, 275, 257, 239, 221,
        202, 183, 165, 148, 133, 120, 110, 105, 101};
    private int N[] = {0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 6, 21, 13, 36, 11, 30, 8, 23, 10, 5, 24, 16, 33, 1, 20, 14, 31, 9, 22,
            18, 29, 7, 28, 12, 35, 3, 26};

    public Wheel()
    {
        Image = new JLabel(new ImageIcon("wheel.png"));
        Ball = new JLabel(new ImageIcon("new_ball.png"));
    }

    public void spin()
    {
        Random r = new Random();
        number = r.nextInt((36 - 0) + 1);
        if(number == 0) colour = "Green";
        else
		{
            for(int i = 1; i <= 36; i++)
            {
                if(number == N[i])
                {
                    if(i % 2 == 1) colour = "Red";
                    else colour = "Black";
                    index = i;
                    return;
                }
            }
        }
    }

    public int x()
    {
        return X[index];
    }

    public int y()
    {
        return Y[index];
    }
}
