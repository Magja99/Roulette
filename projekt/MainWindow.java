import java.awt.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.*;
import javax.swing.*;

public class MainWindow extends BasicWindow
{
    private JButton Game = new JButton("Play Roulette");
    private JButton Exit = new JButton("Exit");
    private Component Title = new JLabel("The Gorgeous Roulette!");

    class GameListener extends Thread implements ActionListener
    {
        private GameWindow game;
        private Thread t;

        public void actionPerformed(ActionEvent e)
        {
            frame.setVisible(false);
            game = new GameWindow();
            start();
        }

        public void run()
        {
            try
            {
               while(true)
               {
                  if(game.goBack)
                  {
                      game.goBack = false;
                      frame.setVisible(true);
                      t = null;
                      break;
                  }
                  Thread.sleep(50);
               }
            }
            catch (InterruptedException e) {}
        }

        public void start ()
        {
            if(t == null)
            {
               t = new Thread (this);
               t.start();
            }
        }
    }
    class ExitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            frame.dispose();
            System.exit(0);
        }
    }
    public MainWindow()
    {
        inter.add(Title);
        layout.putConstraint(SpringLayout.WEST, Title, 75, SpringLayout.WEST, inter);
        layout.putConstraint(SpringLayout.NORTH, Title, 10, SpringLayout.NORTH, inter);

        inter.add(Game);
        layout.putConstraint(SpringLayout.WEST, Game, 75, SpringLayout.WEST, inter);
        layout.putConstraint(SpringLayout.NORTH, Game, 30, SpringLayout.NORTH, Title);

        inter.add(Exit);
        layout.putConstraint(SpringLayout.WEST, Exit, 75, SpringLayout.WEST, inter);
        layout.putConstraint(SpringLayout.NORTH, Exit, 70, SpringLayout.NORTH, Game);

        frame.setPreferredSize(new Dimension(300, 225));
        Game.setPreferredSize(new Dimension(150, 50));
        Exit.setPreferredSize(new Dimension(150, 50));
        Game.addActionListener(new GameListener());
        Exit.addActionListener(new ExitListener());
        show();
    }
}
