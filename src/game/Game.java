package game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
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
import game.worlds.ScoreBoard;

public class Game implements Runnable {
    
    private Display display;
    private int width, height;
    private String title;
    private Player player;
    private EntityManager entityManager;
    private ArrayList<Entity> entity;
    private int stage=0;
    private boolean die_player;
    private int level = 0;
    private boolean running = false;

    
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;
    
    //States
    public State gameState;
    public MenuState menuState;

    
    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
    //Handler
    private Handler handler;
    private Gameover gameover;
    private Gamewin gamewin;


    //Game camera
    private GameCamera gameCamera;
    private int[] BONUS_SCORE= {500,100,1500};
    
    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }
    
    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();
    }
    private void initMap(String path) {
        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);
        player = new Player(handler, 0, 0);
        gameState = new GameState(handler, player,path);
        menuState = new MenuState(handler);
        if(stage==0) {
        	menuState.setActive(true);
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
    	path[0]="res/world/world1.txt";
    	path[1]="res/world/world2.txt";
    	path[2]="res/world/world3.txt";
    	init();
    	while(true) {
            initMap(path[stage]);
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
                    running = false;
                    die_player = false;                   
                    last = System.currentTimeMillis();
                    gameOver();
                    stop();

                }else if(handler.getWorld().getNumberOfKey()==0) {                	
                    if(player.getCollisionBounds(0f, 0f).intersects(handler.getWorld().bounds)){
                    	Player.score+= BONUS_SCORE[level];
                    	ScoreBoard.tick();
                        keyManager.refresh();
                        stage++;
                        running = false;                        
                        last = System.currentTimeMillis();
                        if(stage == 3) {
                            gameWin();
                            stop();
                        }
                        //display.close();
                        break;
                    }

                }
                 
            }
    	} 
    }
    
    public void gameWin() {
    	stage=0;
    	display.close();
    	try {
            gamewin=new Gamewin(handler);           
		} catch (IOException e) {
			e.printStackTrace();
		}
    	player.setCore();
    }
    public void gameOver() {
    	stage=0;
    	display.close();
    	try {
			gameover=new Gameover(handler);			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	player.setCore();
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
    
    public int getStage() {
	return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }  

    public synchronized void start() {
        if(running)
            return;
        running = true;        
        thread = new Thread(this);
        thread.start();

    }
    
    public synchronized void stop() {
        thread.stop();
    }


}
