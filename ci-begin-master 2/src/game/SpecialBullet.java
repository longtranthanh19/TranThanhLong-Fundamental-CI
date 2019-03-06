package game;

import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpecialBullet {
    BufferedImage image;
    Vector2D position;
    Vector2D velocity;

    public  SpecialBullet() {
        image = SpriteUtils.loadImage("assets/images/player-bullets/a/2.png");
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
