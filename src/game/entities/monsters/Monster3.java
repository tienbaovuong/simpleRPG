package game.entities.monsters;

import game.Handler;
import game.entities.Bullet;
import game.gfx.Animation;
import game.gfx.Assets;
import game.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Monster3 extends Monster{
    private long lastAttackTimer, attackCooldown = 1000, attackTimer = attackCooldown;
    private long lastCreateTimer, createCooldown = 20000, createTimer = attackCooldown;

    private static final int[] BONUS = {200, 400,500};

    private float xPlayer, yPlayer;
    private final float xZ, yZ;

    private ArrayList<Bullet> bullets;
    private ArrayList<Monster2> monster2s;

    public Monster3(Handler handler, float x, float y) {
        super(handler, x, y);
        xZ=x;
        yZ=y;
        speed = ZOMBIE_SPEED[level];
        xMove = 0;
        yMove = 0;

        //Animation
        animDown = new Animation(200, Assets.boss_down);
        animUp = new Animation(200, Assets.boss_up);
        animLeft = new Animation(200, Assets.boss_left);
        animRight = new Animation(200, Assets.boss_right);

        currentImage = animDown;

        bullets = new ArrayList<Bullet>();
    }

    @Override
    public void tick() {
        //Animation
        currentImage.tick();
        move();
        attack();
        Iterator<Bullet> it = bullets.iterator();
        while(it.hasNext()){
            Bullet b = it.next();
            b.setSpeed(b.BULLET_SPEED[level]);
            b.tick();
            if(!b.isActive())
                it.remove();
        }
    }

    @Override
    public void attack() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
            return;

        float xx=x+width/4;
        float yy=y+height/4;
        bullets.add(new Bullet(handler,this, xx, yy,1));
        bullets.add(new Bullet(handler,this, xx, yy,2));
        bullets.add(new Bullet(handler,this, xx, yy,3));
        bullets.add(new Bullet(handler,this, xx, yy,4));
        attackTimer = 0;
    }

    public void create() {
        createTimer += System.currentTimeMillis() - lastCreateTimer;
        lastCreateTimer = System.currentTimeMillis();
        if(createTimer < createCooldown)
            return;

        float xx=x+width/4;
        float yy=y+height/4;

        monster2s.add(new Monster2(handler,xx,yy));
        createTimer = 0;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(currentImage.getCurrentFrame(), (int) (x-handler.getGame().getGameCamera().getxOffset()),
                (int) (y-handler.getGame().getGameCamera().getyOffset()), width, height, null);
        bullets.forEach((b) -> {
            b.render(g);
        });
    }


    @Override
    public void move() {
        xPlayer=handler.getWorld().getEntityManager().getPlayer().getX();
        yPlayer=handler.getWorld().getEntityManager().getPlayer().getY();
        if(Math.abs(yPlayer-y)>500  || Math.abs(xPlayer-x)>500 ) {
            return;
        }
        if(Math.abs(yPlayer-y)>Math.abs(xPlayer-x)) {
            if(x==xPlayer) {
                if(y>yPlayer)
                    yMove=-speed;
                else
                    yMove=+speed;
                moveY();
            }

            else  {
                if(x>xPlayer)
                    xMove=-speed;
                else
                    xMove=+speed;
                moveX();
            }
        }
        else {
            if(y==yPlayer) {
                if(x>xPlayer)
                    xMove=-speed;
                else
                    xMove=+speed;
                moveX();
            }

            else  {
                if(y>yPlayer)
                    yMove=-speed;
                else
                    yMove=+speed;
                moveY();
            }
        }
    }

    @Override
    public void moveX(){
        if(xMove > 0){ //Tiếp tục di chuyển sang trái nếu không chạm vào tile
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
                currentImage=animRight;
            } else{ //Di chuyển đến sát bound của tile và biến mất
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
                xMove = -xMove;
                moveY();
            }
        } else if (xMove < 0) { //Moving right
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
                currentImage = animLeft;
            } else {
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
                xMove = -xMove;
                moveY();
            }
        }
    }

    @Override
    public void moveY(){
        if(yMove < 0) {
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)  &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
                currentImage=animUp;
            } else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
                yMove = -yMove;
                moveX();
            }
        } else if(yMove > 0){
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)  &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
                currentImage=animDown;
            } else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height;
                yMove = -yMove;
                moveX();
            }
        }
    }
}
