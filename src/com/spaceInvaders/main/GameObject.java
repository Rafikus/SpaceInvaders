package com.spaceInvaders.main;

import java.awt.*;

/**
 * Created by Rafikus on 29.03.2017.
 */
public abstract class GameObject {

    protected int x, y, velX, velY;
    protected int scale;
    protected ID id;
    protected boolean shoot;

    public GameObject(int x, int y, ID id, int scale)
    {
        this.x = x;
        this.y = y;
        this.id = id;
        this.scale = scale;
    }
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public void setId(ID id)
    {
        this.id = id;
    }
    public void setVelX(int velX)
    {
        this.velX = velX;
    }
    public void setVelY(int velY)
    {
        this.velY = velY;
    }
    public void setShoot(boolean b) {this.shoot = b;}
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public ID getId()
    {
        return id;
    }
    public int getVelX()
    {
        return velX;
    }
    public int getVelY()
    {
        return velY;
    }
    public boolean getShoot() {return shoot;}


}
