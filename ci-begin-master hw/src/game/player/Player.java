package game.player;

import game.GameObject;
import game.GameWindow;
import game.Settings;
import game.physics.BoxCollider;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject {
    int hp;

    public Player() {
        renderer = new Renderer("assets/images/players/straight");
        hitBox = new BoxCollider(this, 32, 48);
        position.set(300, 500);
        hp = 10;

    }

    static Font font = new Font("Verdana"
            , Font.BOLD, 32);
    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setFont(font);
        g.setColor(Color.GREEN);
        g.drawString(hp + ""
                , (int) position.x
                , (int) position.y);
    }

    @Override
    public void run() {
        super.run();
        move();
        limit();
        fire();
    }

    // TODO: remove fireCount
    int fireCount;
    private void fire() {
        fireCount++;
        if(GameWindow.isFirePress && fireCount > 20) {
            for (int i = 0; i < 6; i++) {
                PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);
                bullet.position.set(position.x, position.y);
                bullet.velocity.setAngle(-Math.PI / 3 - i * (Math.PI / 15));
            }
            fireCount = 0;
        }
    }

    private void limit() {
        if(position.x < Settings.PLAYER_WIDTH / 2) {
            position.set(Settings.PLAYER_WIDTH / 2, position.y);
        }
        if(position.x > Settings.BACKGROUND_WIDTH - (Settings.PLAYER_WIDTH / 2)) {
            position.set(
                    Settings.BACKGROUND_WIDTH - (Settings.PLAYER_WIDTH / 2),
                    position.y
            );
        }
        if(position.y < Settings.PLAYER_HEIGHT / 2) {
            position.set(position.x, Settings.PLAYER_HEIGHT / 2);
        }
        if(position.y > Settings.GAME_HEIGHT - (Settings.PLAYER_HEIGHT / 2) ) {
            position.set(
                    position.x,
                    Settings.GAME_HEIGHT - (Settings.PLAYER_HEIGHT / 2)
            );
        }
    }

    private void move() {
        int playerSpeed = 3;
        int vx = 0;
        int vy = 0;
        if(GameWindow.isUpPress) {
            vy -= playerSpeed;
        }
        if(GameWindow.isDownPress) {
            vy += playerSpeed;
        }
        if(GameWindow.isLeftPress) {
            vx -= playerSpeed;
        }
        if(GameWindow.isRightPress) {
            vx += playerSpeed;
        }
        velocity.set(vx, vy);
        velocity.setLength(playerSpeed);
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if(hp <= 0) {
            hp = 0;
            this.deactive();
        }
    }
}