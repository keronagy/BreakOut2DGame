package programming;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Difficulty extends JFrame implements ActionListener, MouseListener {

    private JButton EasyMode;
    private JButton MediumMode;
    private JButton HardMode;
    private Image Img;
    private Image Img1;
    private Image Img2;
    private Image Img3;
    private Rectangle r1 = new Rectangle(243, 200, 315, 113);
    private Rectangle r2 = new Rectangle(243, 350, 315, 113);
    private Rectangle r3 = new Rectangle(243, 500, 315, 113);

    public Difficulty() {
        initcomponents();
    }

    public void initcomponents() {
        this.setTitle("Difficuulty");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Img = new ImageIcon("Game.jpg").getImage();
        Img1 = new ImageIcon("easy.png").getImage();
        Img2 = new ImageIcon("medium.png").getImage();
        Img3 = new ImageIcon("hard.png").getImage();
        this.setSize(800, 800);
        Container c = this.getContentPane();
        c.setLayout(null);
        this.addMouseListener(this);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(Img, 0, 0, null);
        g.drawImage(Img1, 243, 200, null);
        g.drawImage(Img2, 243, 350, null);
        g.drawImage(Img3, 243, 500, null);

    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(EasyMode)) {
            GameBoard.difficult = "Easy";

            SinglePlayer sp = new SinglePlayer();

            this.dispose();
        }
        if (evt.getSource().equals(MediumMode)) {
            GameBoard.difficult = "Medium";

            SinglePlayer MM = new SinglePlayer();

            MM.setVisible(true);
            this.dispose();
        }
        if (evt.getSource().equals(HardMode)) {
            GameBoard.difficult = "Hard";
            SinglePlayer HM = new SinglePlayer();
            HM.setVisible(true);
            this.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (r1.contains(e.getX(), e.getY())) {
            GameBoard.difficult = "Easy";

            SinglePlayer sp = new SinglePlayer();
            sp.setVisible(true);
            this.dispose();
        }
        if (r2.contains(e.getX(), e.getY())) {
            GameBoard.difficult = "Medium";

            SinglePlayer MM = new SinglePlayer();
            MM.setVisible(true);
            this.dispose();
        }
        if (r3.contains(e.getX(), e.getY())) {
            GameBoard.difficult = "Hard";
            SinglePlayer HM = new SinglePlayer();

            HM.setVisible(true);
            this.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
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
}
