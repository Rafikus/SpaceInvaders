package com.spaceInvaders.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Rafikus on 29.03.2017.
 */
public class Player extends GameObject
{
    private BufferedImage img;
    private Handler handler;
    private Game game;
    private long timer = System.currentTimeMillis();
    private float cooldown = 0f, fireRate = 2f;

    public Player(int x, int y, ID id, int scale, Handler handler, Game game)
    {
        super(x, y, id, scale);
        this.handler = handler;
        try {
            img = ImageIO.read(new File("./SPRITES/Player.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void tick() {
        x += velX;
        y += velY;

        if(shoot)
        {
            if(cooldown < 0)
            {
                handler.addObject(new Bullet(x + 4 * scale, y + 1 * scale, ID.PlayerBullet, scale, -3, handler, game));
                cooldown = fireRate;
            }
        }
        if(System.currentTimeMillis() - timer > 1000)
        {
            cooldown -= .1f;
        }
        x = Game.clamp(x, 70, Game.WIDTH - 100);
    }

    public void render(Graphics g) {
        g.drawImage(img, x, y, img.getWidth() * scale, img.getHeight() * scale, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, img.getWidth() * scale, img.getHeight() * scale);
    }
}
