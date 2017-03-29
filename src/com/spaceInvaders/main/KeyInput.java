package com.spaceInvaders.main;

import javafx.scene.input.KeyCode;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Rafikus on 29.03.2017.
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler)
    {
        this.handler = handler;
    }
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        for(int i = 0; i < handler.objects.size(); i++)
        {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getId() == ID.Player)
            {
                if(key == KeyEvent.VK_A)
                {
                    tempObject.setVelX(-5);
                }
                if(key == KeyEvent.VK_D)
                {
                    tempObject.setVelX(5);
                }
                if(key == KeyEvent.VK_SPACE)
                {
                    tempObject.setShoot(true);
                }
            }
        }
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        for(int i = 0; i < handler.objects.size(); i++)
        {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getId() == ID.Player)
            {
                if(key == KeyEvent.VK_A)
                {
                    tempObject.setVelX(0);
                }
                if(key == KeyEvent.VK_D)
                {
                    tempObject.setVelX(0);
                }
                if(key == KeyEvent.VK_SPACE)
                {
                    tempObject.setShoot(false);
                }
            }
        }
    }
}
