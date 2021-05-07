package game.entities.statics;

import java.awt.Graphics;
import game.Handler;
import game.gfx.Assets;
import game.items.Item;
import game.tiles.Tile;

public class Tree extends StaticEntity {
    private static final int[] BONUS = {100, 150};

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        health = 1;
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
    }
    
    @Override
    public void tick() {
    }
    
    @Override
    public void die() {
       // handler.getWorld().getItemManager().addItem(Item.woodItem);

        handler.getWorld().getEntityManager().getPlayer().setScore(BONUS[level]);
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.wood, (int) (x-handler.getGame().getGameCamera().getxOffset()),
                (int) (y-handler.getGame().getGameCamera().getyOffset()), width, height, null);
    }
    
    @Override
    public boolean isFood() {
        return true;
    }
}
