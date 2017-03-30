package com.spaceInvaders.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Rafikus on 29.03.2017.
 */
public class Bullet extends GameObject {

    private BufferedImage img;
    private Handler handler;
    private Game game;

    public Bullet(int x, int y, ID id, int scale, int velY, Handler handler, Game game)
    {
        super(x, y, id, scale);
        this.handler = handler;
        this.game = game;
        try {
            img = ImageIO.read(new File("./SPRITES/Bullet.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        this.velY = velY;
    }

    private void die()
    {
        if(this.y < 0 || this.y > game.HEIGHT)
        {
            handler.removeObject(this);
            System.out.println("I died");
        }
    }

    private void collision()
    {
        for(int i = 0; i < handler.objects.size(); i++)
        {
            GameObject tempObject = handler.objects.get(i);
            if(this.id == ID.PlayerBullet && tempObject.getId() == ID.Enemy && getBounds().intersects(tempObject.getBounds()))
            {
                handler.removeObject(tempObject);
                handler.removeObject(this);
            }
            if(this.id == ID.EnemyBullet && tempObject.getId() == ID.Player && getBounds().intersects(tempObject.getBounds()))
            {
                handler.removeObject(tempObject);
                handler.removeObject(this);
            }
        }
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        die();
        collision();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, x, y, img.getWidth() * scale, img.getHeight() * scale, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, img.getWidth() * scale, img.getHeight() * scale);
    }
}
