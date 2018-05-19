package programming;

import java.applet.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;
import sun.audio.*;    //import the sun.audio package
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class ball extends shape {

    public static int XSpeed = 5;
    public static int YSpeed = 5;
    int r = constants.r;

    Color color;
    int lives = 3;
    public static boolean gameOver = false;
    Image ball1, ball2;

    public ball(int x, int y, Color col) {
        super(x, y);
        this.color = col;

    }

    public void draw(Graphics g) {
        ball1 = new ImageIcon("ball1.png").getImage();
        ball2 = new ImageIcon("ball2.png").getImage();

        if (this.color == Color.BLUE) {
            g.drawImage(ball1, x, y, null);
        } else if (this.color == Color.GREEN) {
            g.drawImage(ball2, x, y, null);
        }

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, r, r);
    }

}
