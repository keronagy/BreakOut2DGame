/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import static programming.SinglePlayer.brickLvl;

/**
 *
 * @author TH3_HACK3R
 */
public class MultiPlayer extends JFrame implements ActionListener, KeyListener {

    private int XSpeed;
    private int YSpeed;
    private int YSpeeed;
    private int XSpeeed;
    public static JLabel blueScoreLbl;
    public static JLabel greenScoreLbl;
    private int score;
    public static boolean LEFT = false;
    public static boolean RIGHT = false;
    public static boolean A = false;
    public static boolean D = false;
    public static Timer time;
    private int v =0;

    GameBoard g = new GameBoard();
    stick s, s2;
    ball b, b2;
    static brick[][] brickLvl;
    int mx;

    int x = 25, y = 50;
    Image game;

    public MultiPlayer() {
        XSpeed = 0;
        YSpeed = 0;
        score = 0;
        GameBoard.difficult = "Easy";

        time = new Timer(20, this);
        time.start();
        this.addKeyListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(constants.frameW, constants.frameH);

        InitialComps();
    }

    public void InitialComps() {
        s = new stick(300, constants.frameH - constants.stickH - 10);
        b = new ball(300 + constants.stickW / 2, constants.frameH - 2 * constants.stickH, Color.BLUE);
        s2 = new stick(100, constants.frameH - constants.stickH - 10);
        b2 = new ball(100, constants.frameH - constants.stickH - 10, Color.GREEN);
        brickLvl = new brick[4][7];
        this.setVisible(true);
        this.setResizable(false);
        Container c = this.getContentPane();
        

        c.setLayout(null);
        c.setBackground(Color.cyan);
        greenScoreLbl = new JLabel("Score:  " + score);
        greenScoreLbl.setBounds(constants.frameW - 80, 0, 65, 15);  //s2
        greenScoreLbl.setForeground(Color.green);
        c.add(greenScoreLbl);

        blueScoreLbl = new JLabel("Score:  " + score);
        blueScoreLbl.setBounds(20, 0, 65, 15);  // s
        blueScoreLbl.setForeground(Color.blue);
        c.add(blueScoreLbl);
    }
    
    public void StartGame(){
        MultiPlayer.time.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        s.draw(g);

        s2.draw2(g);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                if (GameBoard.brickLvl[i][j] != null) {
                    GameBoard.brickLvl[i][j].draw(g);
                }

            }

        }
        b.draw(g);
        b2.draw(g);
    }

    public void ChangeScoreBlue() {
        blueScoreLbl.setText("Score:  " + s.getScore());
    }

    public void ChangeScoreGreen() {
        greenScoreLbl.setText("Score:  " + s2.getScore());
    }

    public void collisionToWall(ball b) {

        b.x = b.x + XSpeed;
        b.y = b.y + YSpeed;

        if (b.x <= 0) {
            XSpeed = constants.XSpeed;
            if (b.y < constants.frameH) {
                Sounds.collisionToWallSound();
            }
        } else if (b.x + b.r > constants.frameW) {
            XSpeed = -constants.XSpeed;
            if (b.y < constants.frameH) {
                Sounds.collisionToWallSound();
            }
        }
        if (b.y <= 25) {
            YSpeed = constants.YSpeed;
            Sounds.collisionToWallSound();

        }

        if (b.y > constants.frameH) {
            resetBall(b);

        }

    }

    public void resetBall(ball b) {

        b.lives--;

        if (b.lives > 0) {
            b.x = s.x + constants.stickW / 2;
            b.y = constants.frameH - 2 * constants.stickH;
        }

    }

    public void MultiGameOver(ball b1, ball b2, stick s, stick s2) {

        if (s.getScore() > s2.getScore()) {
            int yesNo = JOptionPane.showConfirmDialog(null, "CONGRATULATION BLUE STICK WINS \nGreen Stick score: " + s2.getScore() + "\nBlue Stick Score: " + s.getScore() + " \nDo you want to play again?", "", JOptionPane.YES_NO_OPTION);
            if (yesNo == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
            if (yesNo == JOptionPane.YES_OPTION) {
               GameBoard mp = new GameBoard();
                mp.setVisible(true);
                
                this.dispose();
                this.score = 0;
                s.setScore(0);
                s2.setScore(0);
                b1.lives = 3;
                b2.lives = 3;
                

            }
        } else if (s2.getScore() > s.getScore()) {
            int yesNo = JOptionPane.showConfirmDialog(null, "CONGRATULATION GREEN STICK WINS \nGreen Stick score: " + s2.getScore() + "\nBlue Stick Score: " + s.getScore() + "\nDo you want to play again?", "", JOptionPane.YES_NO_OPTION);
            if (yesNo == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
            if (yesNo == JOptionPane.YES_OPTION) {
                GameBoard mp = new GameBoard();
                mp.setVisible(true);
                this.score = 0;
                s.setScore(0);
                s2.setScore(0);
                
                this.dispose();
                b1.lives = 3;
                b2.lives = 3;
               

            }

        } else {
            int yesNo = JOptionPane.showConfirmDialog(null, "NO ONE WINS IT'S DRAW\n both scores: " + s.getScore() + " \nDo you want to play again?", "", JOptionPane.YES_NO_OPTION);
            if (yesNo == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
            if (yesNo == JOptionPane.YES_OPTION) {
                GameBoard mp = new GameBoard();
                mp.setVisible(true);
                
                this.dispose();
                this.score = 0;
                s.setScore(0);
                s2.setScore(0);
                b1.lives = 3;
                b2.lives = 3;
                
            }

        }

    }

    public void collisionToStick(stick s, ball b) {
        // this.collisionToWall();
        if (b.getBounds().intersects(s.getMidRect()) == true) {
            YSpeed = -constants.YSpeed;
            Sounds.collisionToStickSound();

        }
        if (b.getBounds().intersects(s.getRightRect()) == true) {
            YSpeed = -constants.YSpeed;
            XSpeed = constants.XSpeed;
            Sounds.collisionToStickSound();
        }
        if (b.getBounds().intersects(s.getLeftRect()) == true) {
            YSpeed = -constants.YSpeed;
            XSpeed = -constants.XSpeed;
            Sounds.collisionToStickSound();
        }
    }

    public void collisionToBrick(brick[][] b, ball b1, stick s) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                if (b[i][j] != null) {

                    if (b1.getBounds().intersects(b[i][j].getTop()) == true && b[i][j].health == 0) {
                        if (YSpeed != -constants.YSpeed) {
                            YSpeed = -constants.YSpeed;
                        } else {
                            YSpeed = constants.YSpeed;
                        }

                        switch (GameBoard.difficult) {
                            case "Easy":
                                b[i][j].health -= 3;
                                break;

                            case "Medium":
                                b[i][j].health -= 2;
                                break;

                            case "Hard":
                                b[i][j].health -= 1;
                                break;
                        }

                        if (b[i][j].health == 0) {
                            b[i][j] = null;
                            v++;
                        }
                        Sounds.collisionToBrickSound();

                        s.setScore(s.getScore() + 5);

                    } else if (b1.getBounds().intersects(b[i][j].getBottom()) == true) {
                        if (YSpeed != constants.YSpeed) {
                            YSpeed = constants.YSpeed;
                        } else {
                            YSpeed = -constants.YSpeed;
                        }

                        switch (GameBoard.difficult) {
                            case "Easy":
                                b[i][j].health -= 3;
                                break;

                            case "Medium":
                                b[i][j].health -= 2;
                                break;

                            case "Hard":
                                b[i][j].health -= 1;
                                break;
                        }

                        if (b[i][j].health == 0) {
                            b[i][j] = null;v++;
                        }
                        Sounds.collisionToBrickSound();
                        s.setScore(s.getScore() + 5);

                    } else if (b1.getBounds().intersects(b[i][j].getRight()) == true) {
                        if (XSpeed != constants.XSpeed) {
                            XSpeed = constants.XSpeed;
                        } else {
                            XSpeed = -constants.XSpeed;
                        }

                        switch (GameBoard.difficult) {
                            case "Easy":
                                b[i][j].health -= 3;
                                break;

                            case "Medium":
                                b[i][j].health -= 2;
                                break;

                            case "Hard":
                                b[i][j].health -= 1;
                                break;
                        }

                        if (b[i][j].health == 0) {
                            b[i][j] = null;v++;
                        }
                        Sounds.collisionToBrickSound();
                        s.setScore(s.getScore() + 5);

                    } else if (b1.getBounds().intersects(b[i][j].getLeft()) == true) {
                        if (XSpeed != -constants.XSpeed) {
                            XSpeed = -constants.XSpeed;
                        } else {
                            XSpeed = constants.XSpeed;
                        }

                        switch (GameBoard.difficult) {
                            case "Easy":
                                b[i][j].health -= 3;
                                break;

                            case "Medium":
                                b[i][j].health -= 2;
                                break;

                            case "Hard":
                                b[i][j].health -= 1;
                                break;
                        }

                        if (b[i][j].health == 0) {
                            b[i][j] = null;v++;
                        }
                        Sounds.collisionToBrickSound();
                        s.setScore(s.getScore() + 5);

                    }

                }
            }
        }
    }

    public void collisionToWall2(ball b) {

        b.x = b.x + XSpeeed;
        b.y = b.y + YSpeeed;

        if (b.x <= 0) {
            XSpeeed = constants.XSpeed;
            if (b.y < constants.frameH) {
                Sounds.collisionToWallSound();
            }
        } else if (b.x + b.r > constants.frameW) {
            XSpeeed = -constants.XSpeed;
            if (b.y < constants.frameH) {
                Sounds.collisionToWallSound();
            }
        }
        if (b.y <= 25) {
            YSpeeed = constants.YSpeed;
            Sounds.collisionToWallSound();

        }

        if (b.y > constants.frameH) {
            resetBall2(b);

        }

    }

    public void collisionToStick2(stick s, ball b) {
        // this.collisionToWall();
        if (b.getBounds().intersects(s.getMidRect()) == true) {
            YSpeeed = -constants.YSpeed;
            Sounds.collisionToStickSound();

        }
        if (b.getBounds().intersects(s.getRightRect()) == true) {
            YSpeeed = -constants.YSpeed;
            XSpeeed = constants.XSpeed;
            Sounds.collisionToStickSound();
        }
        if (b.getBounds().intersects(s.getLeftRect()) == true) {
            YSpeeed = -constants.YSpeed;
            XSpeeed = -constants.XSpeed;
            Sounds.collisionToStickSound();
        }
    }

    public void collisionToBrick2(brick[][] b, ball b1, stick s) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                if (b[i][j] != null) {

                    if (b1.getBounds().intersects(b[i][j].getTop()) == true && b[i][j].health == 0) {
                        if (YSpeeed != -constants.YSpeed) {
                            YSpeeed = -constants.YSpeed;
                        } else {
                            YSpeeed = constants.YSpeed;
                        }

                        switch (GameBoard.difficult) {
                            case "Easy":
                                b[i][j].health -= 3;
                                break;

                            case "Medium":
                                b[i][j].health -= 2;
                                break;

                            case "Hard":
                                b[i][j].health -= 1;
                                break;
                        }

                        if (b[i][j].health == 0) {
                            b[i][j] = null;v++;
                        }
                        Sounds.collisionToBrickSound();

                        s.setScore(s.getScore() + 5);

                    } else if (b1.getBounds().intersects(b[i][j].getBottom()) == true) {
                        if (YSpeeed != constants.YSpeed) {
                            YSpeeed = constants.YSpeed;
                        } else {
                            YSpeeed = -constants.YSpeed;
                        }

                        switch (GameBoard.difficult) {
                            case "Easy":
                                b[i][j].health -= 3;
                                break;

                            case "Medium":
                                b[i][j].health -= 2;
                                break;

                            case "Hard":
                                b[i][j].health -= 1;
                                break;
                        }

                        if (b[i][j].health == 0) {
                            b[i][j] = null;v++;
                        }
                        Sounds.collisionToBrickSound();
                        s.setScore(s.getScore() + 5);

                    } else if (b1.getBounds().intersects(b[i][j].getRight()) == true) {
                        if (XSpeeed != constants.XSpeed) {
                            XSpeeed = constants.XSpeed;
                        } else {
                            XSpeeed = -constants.XSpeed;
                        }

                        switch (GameBoard.difficult) {
                            case "Easy":
                                b[i][j].health -= 3;
                                break;

                            case "Medium":
                                b[i][j].health -= 2;
                                break;

                            case "Hard":
                                b[i][j].health -= 1;
                                break;
                        }

                        if (b[i][j].health == 0) {
                            b[i][j] = null;v++;
                        }
                        Sounds.collisionToBrickSound();
                        s.setScore(s.getScore() + 5);

                    } else if (b1.getBounds().intersects(b[i][j].getLeft()) == true) {
                        if (XSpeeed != -constants.XSpeed) {
                            XSpeeed = -constants.XSpeed;
                        } else {
                            XSpeeed = constants.XSpeed;
                        }

                        switch (GameBoard.difficult) {
                            case "Easy":
                                b[i][j].health -= 3;
                                break;

                            case "Medium":
                                b[i][j].health -= 2;
                                break;

                            case "Hard":
                                b[i][j].health -= 1;
                                break;
                        }

                        if (b[i][j].health == 0) {
                            b[i][j] = null;v++;
                        }
                        Sounds.collisionToBrickSound();
                        s.setScore(s.getScore() + 5);

                    }

                }
            }
        }
    }

    public void resetBall2(ball b) {

        b.lives--;

        if (b.lives > 0) {
            b.x = s2.x + constants.stickW / 2;
            b.y = constants.frameH - 2 * constants.stickH;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

          if ((b.lives <= 0 && b2.lives <= 0)||v == 28) {
            this.MultiGameOver(b, b2, s, s2);
            time.stop();}

        collisionToWall(b);
        collisionToStick(s, b);
        collisionToBrick(GameBoard.brickLvl, b, s);
        ChangeScoreBlue();
        collisionToWall2(b2);
        collisionToStick2(s2, b2);
        collisionToBrick2(GameBoard.brickLvl, b2, s2);
        ChangeScoreGreen();
        movingLeft();
        movingRight();
        movingA();
        movingD();
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
//            s2.moveLeft();
//            if (s2.CollisionTowall()) {
//                s2.moveRight();
//            }

            A = true;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
//            s2.moveRight();
//            if (s2.CollisionTowall()) {
//                s2.moveLeft();
//            }
            D = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//            s.moveLeft();
//            if (s.CollisionTowall()) {
//                s.moveRight();
//            }

            LEFT = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            s.moveRight();
//            if (s.CollisionTowall()) {
//                s.moveLeft();
//            }
            RIGHT = true;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_HOME) {
            g.setVisible(true);
            System.exit(0);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            LEFT = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            RIGHT = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_D) {
            D = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
            A = false;
        }
    }

    public void movingLeft() {
        if (LEFT == true) {
            s.moveLeft();
            if (s.CollisionTowall()) {
                s.moveRight();
            }
        }
    }

    public void movingRight() {

        if (RIGHT == true) {
            s.moveRight();
            if (s.CollisionTowall()) {
                s.moveLeft();
            }
        }
    }

    public void movingA() {
        if (A == true) {
            s2.moveLeft();
            if (s2.CollisionTowall()) {
                s2.moveRight();
            }
        }
    }

    public void movingD() {
        if (D == true) {
            s2.moveRight();
            if (s2.CollisionTowall()) {
                s2.moveLeft();
            }
        }
    }

}
