package game.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import game.Handler;
import game.entities.monsters.Monster3;

public class EntityManager {
    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private ArrayList<Entity> subEntities;
    private Comparator<Entity> renderSorter = new Comparator<Entity>(){
        @Override
        public int compare(Entity a, Entity b) {
            if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
                return -1;
            return 1;
        }
    };

    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        subEntities = new ArrayList<Entity>();
        addEntity(player);
    }
    public void tick() {
    	subEntities.clear();    	
        Iterator<Entity> it = entities.iterator();
        while(it.hasNext()){
            Entity e = it.next();
            e.tick();
            if(!e.isActive())
                it.remove();
        }
        if(subEntities.isEmpty()==false)
        	entities.addAll(subEntities);
        entities.sort(renderSorter);
    }
    
    public void render(Graphics g) {
        for(Entity e: entities) {
            e.render(g);
        }
    }
    
    public void addEntity(Entity e) {
        entities.add(e);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
	public ArrayList<Entity> getSubEntities() {
		return subEntities;
	}
	public void setSubEntities(ArrayList<Entity> subEntities) {
		this.subEntities = subEntities;
	}
    
    
}
