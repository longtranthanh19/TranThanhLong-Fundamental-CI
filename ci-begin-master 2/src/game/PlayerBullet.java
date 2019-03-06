package game;

import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class PlayerBullet {
    BufferedImage image;
    Vector2D position;
    Vector2D velocity;

    public  PlayerBullet() {
        image = SpriteUtils.loadImage("assets/images/player-bullets/a/0.png");
        position = new Vector2D();
        velocity = new Vector2D(0,-5);
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) position.x, (int) position.y, null);
    }

    public void run() {
        position.add(velocity.x , velocity.y);
    }
}

