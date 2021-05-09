package game.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import game.Handler;
import game.entities.Entity;
import game.gfx.Animation;
import game.gfx.Assets;
import game.items.Item;

public class Player extends Creature {
    //Attack timer
    private long lastAttackTimer, attackCooldown = 300, attackTimer = attackCooldown;
    //Bullet
    private ArrayList<Bullet> bullets;
    private static int bullet_number = 30;
    //Score
    public static long score = 0;
    private static int player_health=3;
    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
   
        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 20;
        bounds.height = 30;
  
        //Animation
        animDown = new Animation(200, Assets.player_down);
        animUp = new Animation(200, Assets.player_up);
        animLeft = new Animation(200, Assets.player_left);
        animRight = new Animation(200, Assets.player_right);
        currentImage = animDown;
        //Bullets
        bullets = new ArrayList<Bullet>();
        
        
    }
    // hồi máu khi máu thấp hơn mặc định
    public int healing(int x){
        if(player_health<DEFAULT_HEALTH[level]){
            return player_health+x;
        }
        return player_health;
    }

    @Override
    public void tick() {
        //Animation
        currentImage.tick();
        //Movement
        getInput();
        move();
        //Attack 
        attack();
        checkAttacks();
        //Bullet 
        Iterator<Bullet> it = bullets.iterator();
        while(it.hasNext()){
            Bullet b = it.next();
            b.tick();
            if(!b.isActive()){
                it.remove();
                bullet_number--;
            }

        }
        handler.getGame().getGameCamera().centerOnEntity(this);
        
    }
    
    public void checkAttacks() {
        Rectangle cb = getCollisionBounds(0,0);
        Rectangle ar = new Rectangle();
        int arSize = 4;
        ar.width = arSize;
        ar.height = arSize;
        
        if(currentImage == animUp){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        } else if(currentImage == animDown) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        }else if(currentImage == animLeft) {
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize;
        }else if(currentImage == animRight) {
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else
            return;
      
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0, 0).intersects(ar)){
                if(e.isFood()){
                    e.hurt(1);
                    return;
                }else{
                	this.setX(handler.getWorld().getSpawnX());
                	this.setY(handler.getWorld().getSpawnY());
                    this.hurt(1);
                    return;
                }   
            }
        }

    }
    
    public void attack() {

            if(handler.getKeyManager().space) {
                attackTimer += System.currentTimeMillis() - lastAttackTimer;
                lastAttackTimer = System.currentTimeMillis();
                if(attackTimer < attackCooldown)
                    return;
                if(isUp()){
                    bullets.add(new Bullet(handler,this, x + width / 4, y));

                } else if(isDown()) {
                    bullets.add(new Bullet(handler,this, x + width /4, y + height));

                }else if(isLeft()) {
                    bullets.add(new Bullet(handler,this, x - width / 2, y + height / 4));

                }else if(isRight()) {
                    bullets.add(new Bullet(handler,this, x + width, y + height / 4));
                }
            }
            attackTimer = 0;


    }
    public void hurt(int amt) {
        player_health -= amt;
        if(player_health <= 0) {
            active = false;
            die();
        }
    }
    @Override
    public void die() {
    	handler.getGame().setDie_player(true);
        
    }
    
    private void getInput() {
        xMove = 0;
        yMove = 0;
        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x-handler.getGame().getGameCamera().getxOffset()),
                (int) (y-handler.getGame().getGameCamera().getyOffset()), width, height, null);
        bullets.forEach((b) -> {
            b.render(g);
        });
    }
    
    private BufferedImage getCurrentAnimationFrame() {
        if(xMove < 0){
            currentImage = animLeft;
        } else if (xMove > 0) {
            currentImage = animRight;
        } else if (yMove < 0) {
            currentImage = animUp;
        } else if (yMove > 0) {
            currentImage = animDown;
        }
        return currentImage.getCurrentFrame();
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score += score;
    }
    public void setcore() {
    	this.score = 0;
    	this.player_health=3;
    	this.bullet_number=30;
    }

    public int getBullet_number() {
        return bullet_number;
    }

    public void setBullet_number(int bullet_number) {
        this.bullet_number += bullet_number;
    }
	public int getPlayer_health() {
		return player_health;
	}
	public  void setPlayer_health(int player_health) {
		this.player_health = player_health;
	}
    
}