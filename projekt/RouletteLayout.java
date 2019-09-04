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

import org.w3c.dom.events.EventTarget;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class RouletteLayout extends JPanel
{
    public JPanel panel = new JPanel();

    private JButton Red = new JButton("Red");       // 1
    private JButton Black = new JButton("Black");   // 2
    private JButton Even = new JButton("Even");     // 3
    private JButton Odd = new JButton("Odd");       // 4
    private JButton OtE = new JButton("1 to 18");   // 5
    private JButton NtT = new JButton("19 to 36");  // 6
    private JButton first = new JButton("1st 12");  // 7
    private JButton second = new JButton("2nd 12"); // 8
    private JButton third = new JButton("3rd 12");  // 9
    private JButton zero = new JButton("0");        // 10

    private JTextField BetAmount = new JTextField();
    private JTextField Number = new JTextField();

    private JLabel Bet = new JLabel("           Bet");
    private JLabel Numbers = new JLabel(" Number 1-36");

    public int balance = 1000;
    private int money = -1;
    private int number = 100;
    public int t[] = new int[70];

    public boolean spin = true;

    private Errors Mistake = new Errors();

    private void zeruj()
    {
        money = -1;
        number = 100;
        BetAmount.setText("");
        Number.setText("");
    }

    class Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            if(evt.getActionCommand() == "Red") number = 1;
            if(evt.getActionCommand() == "Black") number = 2;
            if(evt.getActionCommand() == "Even") number = 3;
            if(evt.getActionCommand() == "Odd") number = 4;
            if(evt.getActionCommand() == "1 to 18") number = 5;
            if(evt.getActionCommand() == "19 to 36") number = 6;
            if(evt.getActionCommand() == "1st 12") number = 7;
            if(evt.getActionCommand() == "2nd 12") number = 8;
            if(evt.getActionCommand() == "3rd 12") number = 9;
            if(evt.getActionCommand() == "0") number = 10;
            if(evt.getActionCommand() == "BetAmount")
            {
                if(number == 100)
                {
                    Mistake.SelectBet();
                    BetAmount.setText("");
                    return;
                }
                else
                try
                {
                    money = Integer.parseInt(BetAmount.getText());
                    if(money < 0) throw new Exception("");
                    if(money > balance) throw new Exception("too much");
                }
                catch(Exception e)
                {
                    if(e.getMessage() == "too much") Mistake.TooMuch();
                    else Mistake.BadInput();
                    zeruj();
                }
            }
            if(number != 100 && money != -1) add(number, money);
        }
    }

    class FListener implements FocusListener
    {
        private int b = 0;

        public void focusGained(FocusEvent e) {}

        public void focusLost(FocusEvent e)
        {
            try
            {
                b = Integer.parseInt(Number.getText());
                if(b > 36 || b < 1) throw new Exception("BadRange");
            }
            catch(IllegalArgumentException evt)
            {
                if(Number.getText().equals("")) return;
                Mistake.BadInput();
                zeruj();
                return;
            }
            catch(Exception evt)
            {
                Mistake.InvalidRange();
                System.out.println(evt);
                zeruj();
                return;
            }
            number = b + 10;
        }
    }

    private void add(int i, Integer m)
    {
        t[i] = m;
        balance -= m;
        zeruj();
    }

    public void check(int num, String c)
    {
        int NumbersAmount = 0;
        boolean HitNumber = false;
        boolean Wagered = false;
        spin = true;
        for(int i = 1; i < 47; i++)
            if(t[i] != 0) Wagered = true;

        for(int i = 10; i < 47; i++)
        {
            if(t[i] != 0)
            {
                NumbersAmount++;
                if(i - 10 == num) HitNumber = true;
            }
            if(NumbersAmount > 5)
            {
                Mistake.FiveNumbers();
                for(int j = 1; j < 47; j++)
                {
                    balance += t[j];
                    t[j] = 0;
                }
                spin = false;
                return;
            }
        }

        if(NumbersAmount == 1 && HitNumber) balance += 35 * t[num + 10];
        if(NumbersAmount == 2 && HitNumber) balance += 17 * t[num + 10];
        if(NumbersAmount == 3 && HitNumber) balance += 11 * t[num + 10];
        if(NumbersAmount == 4 && HitNumber) balance += 8 * t[num + 10];
        if(NumbersAmount == 5 && HitNumber) balance += 5 * t[num + 10];
        if(c == "Red") t[2] = 0;
        else t[1] = 0;
        if(num % 2 == 0) t[4] = 0;
        else t[3] = 0;
        if(num <= 18 && num > 0) t[6] = 0;
        else t[5] = 0;
        if(num >= 1 && num <= 12)
        {
            t[8] = 0;
            t[9] = 0;
        }
        if(num >= 13 && num <= 24)
        {
            t[7] = 0;
            t[9] = 0;
        }
        if(num >= 25 && num <= 36)
        {
            t[8] = 0;
            t[7] = 0;
        }
        balance += 2 * (t[1] + t[2] + t[3] + t[4] + t[5] + t[6]);
        balance += 3 * (t[7] + t[8] + t[9]);

        for(int i = 1; i < 47; i++) t[i] = 0;

        if(!Wagered)
        {
            Mistake.NothingWagered();
            spin = false;
        }
    }

    public RouletteLayout()
    {
        panel.setBackground(Color.gray);
        panel.setLayout(new GridLayout(7, 2));

        panel.add(Red);
        Red.addActionListener(new Listener());

        panel.add(Black);
        Black.addActionListener(new Listener());

        panel.add(Even);
        Even.addActionListener(new Listener());

        panel.add(Odd);
        Odd.addActionListener(new Listener());

        panel.add(OtE);
        OtE.addActionListener(new Listener());

        panel.add(NtT);
        NtT.addActionListener(new Listener());

        panel.add(first);
        first.addActionListener(new Listener());

        panel.add(second);
        second.addActionListener(new Listener());

        panel.add(third);
        third.addActionListener(new Listener());

        panel.add(zero);
        zero.addActionListener(new Listener());

        panel.add(BetAmount);
        BetAmount.addActionListener(new Listener());
        BetAmount.setActionCommand("BetAmount");

        panel.add(Number);
        Number.setActionCommand("Number");
        Number.addFocusListener(new FListener());

        panel.add(Bet);
        panel.add(Numbers);
    }
}
