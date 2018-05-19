package programming;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

class constants {

    public static int frameW = 605;
    public static int frameH = 600;

    public static int stickW = 100;
    public static int stickH = 20;

    public static int brickW = 70;
    public static int brickH = 30;

    public static int r = 15;

    public static int XSpeed = 8;
    public static int YSpeed = 8;

}

class GameBoard extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

    private JButton Single;
    private JButton Multi;
    private JButton Board;
    private JButton Credits;
    private JButton Exit;
    private Image Img;
    private Image Img1;
    private Image Img2;
    private Image Img3;
    private Image Img4;
    private Image Img5;
    public static String difficult;

    private Rectangle r1 = new Rectangle(243, 50, 315, 113);
    private Rectangle r2 = new Rectangle(243, 200, 315, 113);
    private Rectangle r3 = new Rectangle(243, 350, 315, 113);
    private Rectangle r4 = new Rectangle(243, 500, 315, 113);
    private Rectangle r5 = new Rectangle(243, 650, 315, 113);

    private ArrayList<Score> S = new ArrayList<Score>();
    private int x = constants.r - 5;
    private int y = constants.brickH + 10;
    public static brick[][] brickLvl = new brick[11][11];
    public static brick[][] brickLv2 = new brick[9][8];

    public void createLVLEasy() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                brickLvl[i][j] = new brick(x, y);
                x = x + constants.brickW + constants.r + 1;
            }
            y += constants.brickH + 10;
            x = constants.r - 5;
        }
    }

    public void createLVLMedium() {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0 || i == 3) {
                    brickLvl[i][j] = new brick(x, y);
                    x = x + constants.brickW + constants.r + 1;
                }
            }
            y += constants.brickH + 10;
            x = constants.r - 5;
        }
    }

    public GameBoard() {

        intialize();
        createLVLEasy();
        Score.open();
    }

    public void intialize() {
        this.setTitle("Welcome To Our Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(800, 800));
        this.setResizable(false);
        Img = new ImageIcon("Game.jpg").getImage();
        Img1 = new ImageIcon("btnsingleplayer.png").getImage();
        Img2 = new ImageIcon("btnmultiplayer.png").getImage();
        Img3 = new ImageIcon("btnhighscore.png").getImage();
        Img4 = new ImageIcon("btncredits.png").getImage();
        Img5 = new ImageIcon("btnexit.png").getImage();
        Container c = this.getContentPane();
        c.setLayout(null);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //   this.addMouseMotionListener(this);
/*
        //Initializing The Single Button
        Single = new JButton("Single Player");
        Single.setSize(200, 50);
        Single.setLocation(320, 300);
        Single.addActionListener(this);
        c.add(Single);
        // Initializing The Multi Button
        Multi = new JButton("Multi Player");
        Multi.setSize(200, 50);
        Multi.setLocation(320, 360);
        Multi.addActionListener(this);
        c.add(Multi);
        // Initializing The Board Button
        Board = new JButton("High Score");
        Board.setSize(200, 50);
        Board.setLocation(320, 420);
        Board.addActionListener(this);
        c.add(Board);
        // Initializing The Credits Button
        Credits = new JButton("Credits");
        Credits.setSize(200, 50);
        Credits.setLocation(320, 480);
        Credits.addActionListener(this);
        c.add(Credits);
        // Initializing The Exit Button 
        Exit = new JButton("Exit");
        Exit.setSize(200, 50);
        Exit.setLocation(320, 540);
        Exit.addActionListener(this);
        c.add(Exit);
         */
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(Img, 0, 0, 800, 800, null);
        g.drawImage(Img1, 243, 50, null);
        g.drawImage(Img2, 243, 200, null);
        g.drawImage(Img3, 243, 350, null);
        g.drawImage(Img4, 243, 500, null);
        g.drawImage(Img5, 243, 650, null);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(Single)) {
            Difficulty Df = new Difficulty();
            Df.setVisible(true);

            //SinglePlayer sp = new SinglePlayer();
            //sp.setVisible(true);
            this.dispose();

        } else if (evt.getSource() == Multi) {
            MultiPlayer Mp = new MultiPlayer();
            Mp.setVisible(true);
            this.dispose();
        } else if (evt.getSource() == Board) {
            ScoreBoard Sb = new ScoreBoard();
            Sb.setVisible(true);

        } else if (evt.getSource() == Credits) {
            credits Cr = new credits();
            Cr.setVisible(true);

        } else if (evt.getSource() == Exit) {
            this.dispose();
        }
    }

    public void btnsound() {

        try {
            AudioStream as;
            InputStream in = new FileInputStream("button.wav");
            as = new AudioStream(in);
            AudioPlayer.player.start(as);
        } catch (IOException ex) {
            Logger.getLogger(ball.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*   try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("E:/Programming/button.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch(Exception ex) {
        System.out.println("Error with playing sound.");
        ex.printStackTrace();
    }
         */
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (r1.contains(e.getX(), e.getY())) {
            Difficulty Df = new Difficulty();
            Df.setVisible(true);

            //SinglePlayer sp = new SinglePlayer();
            //sp.setVisible(true);
            this.dispose();

        } else if (r2.contains(e.getX(), e.getY())) {
            MultiPlayer Mp = new MultiPlayer();
            Mp.setVisible(true);
            this.dispose();
        } else if (r3.contains(e.getX(), e.getY())) {
            ScoreBoard Sb = new ScoreBoard();
            Sb.setVisible(true);

        } else if (r4.contains(e.getX(), e.getY())) {
            credits Cr = new credits();
            Cr.setVisible(true);

        } else if (r5.contains(e.getX(), e.getY())) {
            this.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
//          if (r1.contains(e.getX(), e.getY())) {
//            Difficulty Df=new Difficulty ();
//            Df.setVisible(true);
//            
//            //SinglePlayer sp = new SinglePlayer();
//            //sp.setVisible(true);
//
//            this.dispose();
//
//        } else if (r2.contains(e.getX(), e.getY())) {
//            MultiPlayer Mp = new MultiPlayer();
//            Mp.setVisible(true);
//            this.dispose();
//        } else if (r3.contains(e.getX(), e.getY())) {
//            ScoreBoard Sb = new ScoreBoard();
//            Sb.setVisible(true);
//            
//        } else if (r4.contains(e.getX(), e.getY())) {
//            credits Cr = new credits();
//            Cr.setVisible(true);
//            
//
//        } else if (r5.contains(e.getX(), e.getY())) {
//            this.dispose();
//        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if ((e.getX() >= 247 && e.getX() <= 243 + 300) && (e.getY() == 55 || e.getY() == 50 + 100)) {
            btnsound();

        } else if ((e.getX() >= 247 && e.getX() <= 243 + 300) && (e.getY() == 205 || e.getY() == 200 + 100)) {
            btnsound();

        } else if ((e.getX() >= 247 && e.getX() <= 243 + 300) && (e.getY() == 355 || e.getY() == 350 + 100)) {
            btnsound();

        } else if ((e.getX() >= 247 && e.getX() <= 243 + 300) && (e.getY() == 505 || e.getY() == 500 + 100)) {
            btnsound();

        } else if ((e.getX() >= 247 && e.getX() <= 243 + 300) && (e.getY() == 655 || e.getY() == 650 + 100)) {
            btnsound();

        } else if ((e.getX() == 247 || e.getX() == 243 + 300) && (e.getY() >= 55 && e.getY() <= 50 + 100)) {
            btnsound();

        } else if ((e.getX() == 247 || e.getX() == 243 + 300) && (e.getY() >= 205 && e.getY() <= 200 + 100)) {
            btnsound();

        } else if ((e.getX() == 247 || e.getX() == 243 + 300) && (e.getY() >= 355 && e.getY() <= 350 + 100)) {
            btnsound();

        } else if ((e.getX() == 247 || e.getX() == 243 + 300) && (e.getY() >= 505 && e.getY() <= 500 + 100)) {
            btnsound();

        } else if ((e.getX() == 247 || e.getX() == 243 + 300) && (e.getY() >= 655 && e.getY() <= 650 + 100)) {
            btnsound();

        }

    }
}

class credits extends JFrame {

    private JLabel lbl1;
    private JLabel lbl2;
    private JLabel lbl3;
    private JLabel lbl4;
    private JLabel lbl5;
    private JLabel lbl6;
    private JLabel lbl7;

    public credits() {

        this.setTitle("Credits");

        this.setResizable(false);
        this.setSize(800, 800);
        Container c = this.getContentPane();
        c.setLayout(null);
        //initializing The First Label
        lbl1 = new JLabel("RokoShit");
        lbl1.setSize(150, 100);
        lbl1.setLocation(350, 100);
        lbl1.setFont(new java.awt.Font("Script MT Bold", 1, 30));
        c.add(lbl1);
        //initializing the Second Label
        lbl2 = new JLabel("Made By:");
        lbl2.setSize(150, 100);
        lbl2.setLocation(0, 350);
        lbl2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 25));
        c.add(lbl2);
        //initializing The Third Label
        lbl3 = new JLabel("Abdallah Osama");
        lbl3.setSize(150, 150);
        lbl3.setLocation(350, 400);
        lbl3.setFont(new java.awt.Font("Script MT Bold", 1, 20));
        c.add(lbl3);
        //initializing The Fourth Label
        lbl4 = new JLabel("Khaled Kord");
        lbl4.setSize(150, 150);
        lbl4.setLocation(350, 450);
        lbl4.setFont(new java.awt.Font("Script MT Bold", 1, 20));
        c.add(lbl4);
        // initializing the Fifth Label 
        lbl5 = new JLabel("Kyrillos Nagi");
        lbl5.setSize(150, 150);
        lbl5.setLocation(350, 500);
        lbl5.setFont(new java.awt.Font("Script MT Bold", 1, 20));
        c.add(lbl5);
        // initializing The Sixth Label
        lbl6 = new JLabel("Mohamed Magdi");
        lbl6.setSize(150, 150);
        lbl6.setLocation(350, 550);
        lbl6.setFont(new java.awt.Font("Script MT Bold", 2, 20));
        c.add(lbl6);
        //initialzing The Seventh Label
        lbl7 = new JLabel("Mohamed Osama");
        lbl7.setSize(150, 150);
        lbl7.setLocation(350, 600);
        lbl7.setFont(new java.awt.Font("Script MT Bold", 2, 20));
        c.add(lbl7);

    }

}

public class Programming {

    public static void main(String[] args) {
        GameBoard GB = new GameBoard();
        GB.setVisible(true);
    }

}
