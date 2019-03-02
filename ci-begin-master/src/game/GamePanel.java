package game;

import game.GameWindow;
import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    BufferedImage playerImage;
    Vector2D playerPosition;
    BufferedImage backgroundImage;
    Vector2D backgroundPosition;
    BufferedImage bulletImage;
    ArrayList<Vector2D> bulletPositions ;
    BufferedImage enemyImage;
    ArrayList<Vector2D> enemyPositions;
    Vector2D enemyPosition;


    public GamePanel() {
        playerImage = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        backgroundImage = SpriteUtils.loadImage ("assets/images/background/0.png");
        playerPosition = new Vector2D(100,100);
        backgroundPosition = new Vector2D(0,600-3109);
        bulletImage = SpriteUtils.loadImage("assets/images/player-bullets/a/1.png");
        bulletPositions = new ArrayList<>();
        bulletPositions.add(new Vector2D(300,450));
        enemyImage = SpriteUtils.loadImage("assets/images/enemies/level0/black/0.png");
        enemyPositions = new ArrayList<>();

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backgroundImage,
                (int) backgroundPosition.x,
                (int) backgroundPosition.y, null);

        g.drawImage(playerImage,
                (int) playerPosition.x,
                (int) playerPosition.y, null);

        for (int i = 0; i < bulletPositions.size(); i++) {
            Vector2D bulletPosition = bulletPositions.get(i);
            g.drawImage(
                    bulletImage,
                    (int) bulletPosition.x,
                    (int) bulletPosition.y,
                    null
            );
        }
        for (int i = 0; i < enemyPositions.size(); i++) {
            Vector2D enemyPosition = enemyPositions.get(i);
            g.drawImage(
                    enemyImage,
                    (int) enemyPosition.x,
                    (int) enemyPosition.y,
                    null
            );
        }
    }


    private void bulletRun() {
        for (int i = 0; i < bulletPositions.size (); i++){
            Vector2D bulletPosition = bulletPositions.get(i);
            bulletPosition.add(0, -3);
        }
    };
    public void gameLoop(){
        long lastLoop = 0;
        long delay = 1000/60;
        while(true){
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastLoop > delay){
                System.out.println("run + render");
                runAll(); // Logic Game
                renderAll(); // Render Game
                lastLoop = currentTime;
            }
        }
    }


    private void renderAll() {
        repaint(); // Goi Lai Ham Paint
    }


    private void runAll(){
        bulletRun();
        backgroundMove();
        playerMove();
        playerFire();
        playerLimit();
        enemyAppear();
        enemyDown();


    }

    //TODO : remove
    int frameCount;
    private void playerFire() {
        frameCount++;
        if(GameWindow.isFirePress && frameCount > 20){
            Vector2D bulletPosition = playerPosition.clone();
            bulletPositions.add(bulletPosition);
            frameCount = 0;
        }
    }

    private void playerMove() {
        int playerSpeed = 3;
        int vx = 0;
        int vy = 0;

        if (GameWindow.isUpPress) {
            vy-= playerSpeed;
        }
        if (GameWindow.isDownPress) {
            vy += playerSpeed;
        }
        if (GameWindow.isLeftPress) {
            vx -= playerSpeed;
        }
        if (GameWindow.isRightPress) {
            vx+= playerSpeed;
        }
        playerPosition.add(vx,vy);
    };

    private void backgroundMove() {
        backgroundPosition.add(0, 10);
        if (backgroundPosition.y > 0) {
            backgroundPosition.set(backgroundPosition.x, 0);

        }
    };

    private void playerLimit() {

            if ( playerPosition.x < 0) {
                playerPosition.set(0, playerPosition.y);
            }
            if (playerPosition.x > backgroundImage.getWidth() - playerImage.getWidth()){
                playerPosition.set(
                        backgroundImage.getWidth() - playerImage.getWidth(),
                        playerPosition.y
                );

                if(playerPosition.y < 0) {
                    playerPosition.set(playerPosition.x, 0);
                }

                if(playerPosition.y > 600 - playerImage.getHeight()){
                    playerPosition.set(playerPosition.x,
                            600 - playerImage.getHeight());
                }

            }
        }
    private void enemyAppear() {
        double enemyPositionX = Math.random() * 380;
        frameCount ++;
        if (frameCount == 60) {
            enemyPosition = new Vector2D(enemyPositionX, 300);
            enemyPositions.add(enemyPosition);
            frameCount = 0;
        }
    }

    private void enemyDown() {
        for (int i = 0; i < enemyPositions.size(); i++) {
            Vector2D enemyPosition = enemyPositions.get(i);
            enemyPosition.add(0, +3);
        }
    }
    }

