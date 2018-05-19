package programming;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

class brick extends shape {

    int BW = constants.brickW;
    int BH = constants.brickH;
    public int health = 3;
    Image brick;

    public brick(int x, int y) {
        super(x, y);
    }

    public void draw(Graphics g) {
        brick = new ImageIcon("brick.jpg").getImage();
        g.drawImage(brick, x, y, null);

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, BW, BH);
    }

    public Rectangle getTop() {
        return new Rectangle(x, y, BW, BH / 2);
    }

    public Rectangle getBottom() {
        return new Rectangle(x, y + (BH / 2), BW, BH / 2);

    }

    public Rectangle getLeft() {
        return new Rectangle(x, y + 1, 1, BH - 2);

    }

    public Rectangle getRight() {
        return new Rectangle(x + BW - 1, y + 1, 1, BH - 2);

    }
}
