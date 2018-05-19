/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author TH3_HACK3R
 */
public class Sounds {

    public static void collisionToWallSound() {

        try {
            AudioStream as;
            InputStream in = new FileInputStream("Wood2.wav");
            as = new AudioStream(in);
            AudioPlayer.player.start(as);
        } catch (IOException ex) {
            Logger.getLogger(ball.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void collisionToStickSound() {

        try {
            AudioStream as;
            InputStream in = new FileInputStream("Randomize38.wav");
            as = new AudioStream(in);
            AudioPlayer.player.start(as);
        } catch (IOException ex) {
            Logger.getLogger(ball.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void collisionToBrickSound() {

        try {
            AudioStream as;
            InputStream in = new FileInputStream("Explosion68.wav");
            as = new AudioStream(in);
            AudioPlayer.player.start(as);
        } catch (IOException ex) {
            Logger.getLogger(ball.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
