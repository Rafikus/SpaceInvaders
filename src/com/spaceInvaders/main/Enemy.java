package com.spaceInvaders.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Rafikus on 29.03.2017.
 */
public class Enemy extends GameObject {

    private EnemyID enemyID;
    private BufferedImage img_0, img_1, tmp;
    private long timer = System.currentTimeMillis();
    private boolean change = false;

    public Enemy(int x, int y, ID id, int scale, EnemyID enemyID)
    {
        super(x, y, id, scale);
        this.enemyID = enemyID;
        try {
            img_0 = ImageIO.read(new File(".\\SPRITES\\Enemy"+enemyID+"_0.png"));
            img_1 = ImageIO.read(new File(".\\SPRITES\\Enemy"+enemyID+"_1.png"));
            tmp = img_0;
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g)
    {
        if(System.currentTimeMillis() - timer > 1000)
        {
            timer += 1000;
            if(change)
            {
                tmp = img_0;
                change = !change;
            }
            else
            {
                tmp = img_1;
                change = !change;
            }
        }
        g.drawImage(tmp, x, y, tmp.getWidth() * scale, tmp.getHeight() * scale, null);


    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, tmp.getWidth() * scale, tmp.getHeight() * scale);
    }
}
