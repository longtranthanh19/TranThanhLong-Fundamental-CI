package game;

import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class GamePanel extends JPanel {
    Player player;
    Background background;
    ArrayList<Enemy> enemies;

    public GamePanel() {
        // backgroundCreate
        background = new Background();

        // playerCreate
        player = new Player();

        // enemyCreate
        enemies = new ArrayList<>();
    }

    @Override
    public void paint(Graphics g) {
        //backgroundDraw
        background.render(g);

        //playerDraw
        player.render(g);

        //enemyDraw
        for (int i = 0; i < enemies.size(); i++){
            Enemy enemy = enemies.get(i);
            enemy.render(g);
        }
    }

    public void gameLoop() {
        long lastLoop = 0;
        long delay = 1000 / 60;
        while(true) {
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastLoop > delay) {
                runAll(); // logic game
                renderAll(); // render anh cua game
                lastLoop = currentTime;
            }
        }
    }

    private void renderAll() {
        repaint(); // goi lai ham paint
    }

    private void runAll() {
        background.run();
        summonEnemies();
        enermiesRun();
        player.run();
        }



    private void enermiesRun() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.run();
        }
    }
    // TODO: remove summonCount

    int summonCount;
    int wayCount;
    int enemyCount;
    Random random = new Random();
    int enemyX = 100 + random.nextInt(200);
    private void summonEnemies() {
        wayCount++;
        if(wayCount > 120) {
            summonCount++;
            if(summonCount > 15) {
                Enemy enemy = new Enemy();
                enemy.position.set(enemyX, - 100);
                enemy.velocity.setAngle(Math.PI/9);
                enemies.add(enemy);
                summonCount = 0;
                enemyCount++;
                if(enemyCount > 4) {
                    wayCount = 0;
                    enemyCount = 0;
                    enemyX = 100 + random.nextInt(200);
                }
            }
        }
    }
}
