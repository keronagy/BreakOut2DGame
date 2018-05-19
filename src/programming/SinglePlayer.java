package programming;

import java.applet.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;
import sun.audio.*;    //import the sun.audio package
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class SinglePlayer extends JFrame implements ActionListener, KeyListener {

    GameBoard g = new GameBoard();
    private int XSpeed;
    private int YSpeed;
    private JLabel ScoreLbl;
    private JLabel lifeLbl;
    private int score;
    static stick s;
    ball b;
    static brick[][] brickLvl;
    private int e =0;

    Image game;
    public static Timer time;

    public SinglePlayer() {

        if (GameBoard.difficult.equals("Easy")) {
            time = new Timer(50, this);
            time.start();
        } else if (GameBoard.difficult.equals("Medium")) {
            time = new Timer(30, this);
            time.start();
        } else if (GameBoard.difficult.equals("Hard")) {
            time = new Timer(20, this);
            time.start();
        }
        this.addKeyListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(constants.frameW, constants.frameH);

        InitialComps();
    }

    public void InitialComps() {
        s = new stick(300, constants.frameH - constants.stickH - 10);
        b = new ball(300 + constants.stickW / 2, constants.frameH - 2 * constants.stickH, Color.BLUE);
        brickLvl = new brick[4][7];
        score = 0;

        this.setVisible(true);
        this.setResizable(false);
        Container c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.cyan);
        lifeLbl = new JLabel("Life:  " + b.lives);
        lifeLbl.setBounds(20, 0, 65, 15);
        c.add(lifeLbl);
        ScoreLbl = new JLabel("Score:  " + score);
        ScoreLbl.setBounds(constants.frameW - 80, 0, 65, 15);
        c.add(ScoreLbl);

    }

    public void ChangeScore() {
        ScoreLbl.setText("Score:  " + s.getScore());
    }

    public void ChangeLife() {
        lifeLbl.setText("Life: " + b.lives);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        s.draw(g);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                if (GameBoard.brickLvl[i][j] != null) {
                    GameBoard.brickLvl[i][j].draw(g);
                }

            }

        }

        b.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        collisionToWall(b);
        collisionToStick(s, b);
        collisionToBrick(GameBoard.brickLvl, b, s);
        ChangeScore();
        ChangeLife();
        

        repaint();
        checkWin();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            s.moveLeft();
            if (s.CollisionTowall()) {
                s.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            s.moveRight();
            if (s.CollisionTowall()) {
                s.moveLeft();
            }
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            GameBoard s = new GameBoard();
            s.setVisible(true);
            SinglePlayer.time.stop();
            this.dispose();

        }
        if (e.getKeyCode() == KeyEvent.VK_P) {

            SinglePlayer.time.stop();

        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

            SinglePlayer.time.start();

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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

            SinglePlayer.time.stop();

        }

    }

    public void resetBall(ball b) {

        b.lives--;

        if (b.lives > 0) {
            b.x = s.x + constants.stickW / 2;
            b.y = constants.frameH - 2 * constants.stickH;
        } else {
            SingleGameOver(s);
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

                        if (b[i][j].health <= 0) {
                            b[i][j] = null;
                            e++;
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

                        if (b[i][j].health <= 0) {
                            b[i][j] = null;
                            e++;
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

                        if (b[i][j].health <= 0) {
                            b[i][j] = null;
                            e++;
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

                        if (b[i][j].health <= 0) {
                            b[i][j] = null;
                            e++;
                        }
                        Sounds.collisionToBrickSound();
                        s.setScore(s.getScore() + 5);

                    }

                }
            }
        }

    }
    private void checkWin() {
        
        
        if (e==28){
           time.stop();
int i = 0;
        String name = JOptionPane.showInputDialog(null, "What's your name?");
        try {
            if (!(name.equals(""))) {
                Score hs = Score.open();
                ArrayList<HighScore> ar = Score.Scores;
                hs.addScore(new HighScore(name, s.getScore()));
                i = ar.get(0).getScore();

            }
        } catch (Exception ex) {
        }

        if (i == s.getScore()) {
            int yesNo = JOptionPane.showConfirmDialog(null, "GAME OVER!!\nCONGRATULATION you just recorded the highest score: " + s.getScore() + "\nDo you want to play again?", "", JOptionPane.YES_NO_OPTION);

            if (yesNo == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
            if (yesNo == JOptionPane.YES_OPTION) {
                Difficulty sp = new Difficulty();
                sp.setVisible(true);

            }
        } else {
            int yesNo = JOptionPane.showConfirmDialog(null, "GAME OVER!!\nYour score: " + s.getScore() + "\nDo you want to play again?", "", JOptionPane.YES_NO_OPTION);

            if (yesNo == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
            if (yesNo == JOptionPane.YES_OPTION) {
                Difficulty sp = new Difficulty();
                sp.setVisible(true);

            }
        }
        this.dispose();
        e=0;

    }
    }

    public void SingleGameOver(stick s) {

        int i = 0;
        String name = JOptionPane.showInputDialog(null, "What's your name?");
        try {
            if (!(name.equals(""))) {
                Score hs = Score.open();
                ArrayList<HighScore> ar = Score.Scores;
                hs.addScore(new HighScore(name, s.getScore()));
                i = ar.get(0).getScore();

            }
        } catch (Exception ex) {
        }

        if (i == s.getScore()) {
            int yesNo = JOptionPane.showConfirmDialog(null, "GAME OVER!!\nCONGRATULATION you just recorded the highest score: " + s.getScore() + "\nDo you want to play again?", "", JOptionPane.YES_NO_OPTION);

            if (yesNo == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
            if (yesNo == JOptionPane.YES_OPTION) {
                Difficulty sp = new Difficulty();
                sp.setVisible(true);

            }
        } else {
            int yesNo = JOptionPane.showConfirmDialog(null, "GAME OVER!!\nYour score: " + s.getScore() + "\nDo you want to play again?", "", JOptionPane.YES_NO_OPTION);

            if (yesNo == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
            if (yesNo == JOptionPane.YES_OPTION) {
                Difficulty sp = new Difficulty();
                sp.setVisible(true);

            }
        }
        this.dispose();

    }

}
