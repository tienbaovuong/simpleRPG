package game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


import game.display.Display;
import game.entities.Entity;
import game.entities.EntityManager;
import game.entities.Player;
import game.gfx.Assets;
import game.gfx.GameCamera;
import game.input.KeyManager;
import game.input.MouseManager;
import game.states.GameState;
import game.states.Gameover;
import game.states.Gamewin;
import game.states.MenuState;
import game.states.State;
import javazoom.jl.decoder.JavaLayerException;

public class Game implements Runnable {
    
    private Display display;
    private int width, height;
    private String title;
    private Player player;
    private EntityManager entityManager;
    private ArrayList<Entity> entity;
    //private int ck=0;//kiểm tra số entity
    //private boolean chec=true;//khi stop thì dùng luôn.
    private int q=0;
    private boolean die_player;
    private int level = 0;
    private boolean running = false;
    //private boolean choi_lai;
    
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;
    
    //States
    public State currentState;
    public State gameState;
    public MenuState menuState;
    public State howToPlayState;
    public State highScoreState;
    public State quitState;
    
    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
    //Handler
    private Handler handler;
    private Gameover gameover;
    private Gamewin gamewin;


    //gamecamera
    private GameCamera gameCamera;
    
    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }
    
    private void init(String path) {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();
        
        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);
        player = new Player(handler, 0, 0);
        gameState = new GameState(handler, player,path);
        menuState = new MenuState(handler);
        
        if(q==0) {
        	State.setState(menuState);
        }
        
        else {
        	State.setState(gameState);
        }
        
        
    }
    
    
    private void tick() {
        keyManager.tick();
        State.getState().tick();
        entityManager = handler.getWorld().getEntityManager();
        entity = entityManager.getEntities();
    }
    
    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw Here
        State.getState().render(g);        
        //End Drawing!
        bs.show();
        g.dispose();
    }
    
    @Override
    public void run() {
    	long last = 0;
    	String[] path=new String[3];
    	path[2]="res/world/world1.txt";
    	path[1]="res/world/world2.txt";
    	path[0]="res/world/world3.txt";
    	
    	while(true) {
            init(path[q]);
            int fps = 60;
            double timePerTick = 1000000000 / fps;
            double delta = 0;
            long now;
            long lastTime = System.nanoTime();
            long timer = 0;
            int ticks = 0;
            running=true;
            
            while(running) {
                now = System.nanoTime();
                delta += (now - lastTime) / timePerTick;
                timer += now - lastTime;
                lastTime = now;
                
                if(delta >= 1) {
                    tick();
                    render();
                    ticks++;
                    delta--; 
                }
                
                if(die_player){
                    q++;
                    running = false;
                    die_player = false;
                    System.out.println(q);
                    last = System.currentTimeMillis();
                    //System.out.println("Tong diem den vong " + q + ": " + player.getScore());
                    gameOver();
                    stop();
                    //Launcher laun = new Launcher();
                    //q = 0;
                    //player.setcore();
                }else if(handler.getWorld().getNumberOfKey()==0) {
                    if( player.getX() == handler.getWorld().getExitX() && player.getY() == handler.getWorld().getExitY()){
                        keyManager.refresh();
                        q++;
                        running = false;
                        System.out.println(q);
                        last = System.currentTimeMillis();
                        //System.out.println("Tong diem den vong  " + q + ": " + player.getScore() );
                        if(q == 3) {
                            gameWin();
                            stop();
                            //Launcher lau = new Launcher();
                            //try {
                            //    Thread.sleep(600);
                            //} catch (InterruptedException e) {
                            //    e.printStackTrace();
                            //}
                            //q = 0;
                            //player.setcore();
                            //running = false;

                        }

                        //System.out.println("--------");                      
                        display.close();
                        break;
                    }

                }
                 
            }
    	} 
    }
    
    public void gameWin() {
    	q=0;
    	display.close();
    	try {
            gamewin=new Gamewin(handler);
            try{
                thread.sleep(600);
            }
            catch(Exception e){
                e.printStackTrace();
             }
		} catch (IOException e) {
			e.printStackTrace();
		}
    	player.setcore();
    }
    public void gameOver() {
    	q=0;
    	display.close();
    	try {
			gameover=new Gameover(handler);
			try{

                thread.sleep(300);
                }
            catch(Exception e){
                 e.printStackTrace();
                }
		} catch (IOException e) {
			e.printStackTrace();
		}
    	player.setcore();
    }
    public GameCamera getGameCamera() {
        return gameCamera;
    }


    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        this.getPlayer().setPlayer_health(this.getPlayer().DEFAULT_HEALTH[level]);
        this.getPlayer().initBullet_number(this.getPlayer().BULLET_NUMBER[level]);
    }

    public Display getDisplay() {
        return display;
    }

    public Player getPlayer() {
        return player;
    }
    
    public void setDie_player(boolean die_player){
        this.die_player=die_player;
    }
    
    public int getQ() {
	return q;
    }

    public void setQ(int q) {
        this.q = q;
    }  

    public synchronized void start() {
        if(running)
            return;
        running = true;        
        thread = new Thread(this);
        thread.start();

    }
    
    public synchronized void stop() {
        /*if(!running)
            return;
        running = false;*/
        thread.stop();
    }
    
    public boolean isRunning() {
	return running;
    }


}
