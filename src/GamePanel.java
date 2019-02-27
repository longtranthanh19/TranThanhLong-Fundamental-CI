import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    BufferedImage playerImage;
    int playerX;
    int playerY;
    BufferedImage backgroundImage;
    int backgroundX;
    int backgroundY;

    public GamePanel() {
        playerImage = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        backgroundImage = SpriteUtils.loadImage ("assets/images/background/0.png");
        playerX = 100;
        playerY = 100;
        backgroundX = 0;
        backgroundY = 600-3109;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backgroundImage, backgroundX, backgroundY, null);
        g.drawImage(playerImage, playerX, playerY, null);

    }
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
        if (backgroundY < 0) {
            backgroundY += 5;
        }

        int playerSpeed = 3;
        if (GameWindow.isUpPress && playerY - playerSpeed >= 0) {
            playerY -= playerSpeed;
        }
        if (GameWindow.isDownPress && playerY + playerSpeed <= 560 - playerImage.getHeight()) {
            playerY += playerSpeed;
        }
        if (GameWindow.isLeftPress && playerX - playerSpeed >= 0) {
            playerX -= playerSpeed;
        }
        if (GameWindow.isRightPress && playerX + playerSpeed <= backgroundImage.getWidth() - playerImage.getWidth()) {
            playerX += playerSpeed;
        }

    }
}
