/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

class stick extends shape {

    int SW = constants.stickW;
    int SH = constants.stickH;

    Image stick1, stick2;
    private int score = 0;

    public stick(int x, int y) {
        super(x, y);

    }

    public void draw(Graphics g) {
        stick1 = new ImageIcon("stick1.jpg").getImage();
        g.drawImage(stick1, x, y, null);
    }

    public void draw2(Graphics g) {
        stick2 = new ImageIcon("stick2.jpg").getImage();
        g.drawImage(stick2, x, y, null);
    }

    public void move(int XSpeed) {
        x = x + XSpeed;
    }

    public void moveLeft() {
        x = x - 10;
    }

    public void moveRight() {
        x = x + 10;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, SW, SH);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Rectangle getMidRect() {
        return new Rectangle(x + (constants.stickW / 2) - ((constants.stickW / 2) / 2), y, constants.stickW / 2, constants.stickH / 2);
    }

    public Rectangle getLeftRect() {
        return new Rectangle(x, y, ((constants.stickW / 2) / 2), constants.stickH);
    }

    public Rectangle getRightRect() {
        return new Rectangle(x + constants.stickW - ((constants.stickW / 2) / 2), y, ((constants.stickW / 2) / 2), constants.stickH);
    }

    public boolean CollisionTowall() {
        if (x < 0) {
            return true;
        } else if (x + constants.stickW > constants.frameW) {
            return true;
        }
        return false;
    }

}
