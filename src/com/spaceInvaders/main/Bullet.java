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
            img = ImageIO.read(new File(".\\SPRITES\\Bullet.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        this.velY = velY;
        System.out.println("SHOOT!");
    }

    private void die()
    {
        if(this.y < 0 || this.y > game.HEIGHT)
        {
            handler.removeObject(this);
            System.out.println("I dieded");
        }
    }

    private void collision()
    {
        for(int i = 0; i < handler.objects.size(); i++)
        {
            GameObject tempObject = handler.objects.get(i);
            if(getBounds().intersects(tempObject.getBounds()) && tempObject.getId() == ID.Enemy)
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
