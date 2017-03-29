package com.spaceInvaders.main;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Rafikus on 29.03.2017.
 */
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 720, HEIGHT = WIDTH /12 * 9;
    private int scale = 3;
    private Thread thread;
    private boolean running = false;

    private Handler handler;

    public Game()
    {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        new Window(WIDTH, HEIGHT, "SpaceInvaders", this);

        handler.addObject(new Player(WIDTH/2 - 3 * scale, HEIGHT - HEIGHT/8, ID.Player, scale, handler, this));
        for(int i = 1; i < 8; i++)
        {
            for (int j = 1; j < 3; j++)
            {
            handler.addObject(new Enemy(scale*i*20, scale*j*10, ID.Enemy, scale, EnemyID.Small));
            handler.addObject(new Enemy(scale*i*20, 70 + scale*j*10, ID.Enemy, scale, EnemyID.Medium));
            handler.addObject(new Enemy(scale*i*20, 140 + scale*j*10, ID.Enemy, scale, EnemyID.Large));
            }
        }
    }

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop()
    {
        try{
            thread.join();
            running = false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run()
    {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1)
            {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: "+ frames);
                frames = 0;
            }
        }
        stop();
    }

    public static int clamp(int var, int min, int max)
    {
        if(var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }

    private void tick()
    {
        handler.tick();
    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0, WIDTH, HEIGHT);
        handler.render(g);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args)
    {
        new Game();
    }
}
