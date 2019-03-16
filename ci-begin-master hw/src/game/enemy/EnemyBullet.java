package game.enemy;

import game.GameObject;
import game.Settings;
import game.physics.BoxCollider;
import game.player.Player;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class EnemyBullet extends GameObject {
    int damage;
    public EnemyBullet() {
        BufferedImage image = SpriteUtils.loadImage("assets/images/enemies/bullets/green.png");
        hitBox = new BoxCollider(this, 16, 16);
        renderer = new Renderer(image);
        velocity.set(0, 5);
        damage = 1;
    }

    @Override
    public void run() {
        super.run();
        deactiveIfNeeded();
        checkIntersects();
    }

    private void checkIntersects() {
        Player player = GameObject.findIntersects(Player.class
                , this);
        if(player != null) {
            this.deactive();
            player.takeDamage(damage);
        }
    }

    private void deactiveIfNeeded() {
        if(this.position.y > Settings.GAME_HEIGHT + 50) {
            this.deactive();
        }
    }
}