package game.items;

import game.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {
    private Handler handler;
    private ArrayList<Item> items;

    public ItemManager(Handler handler){
        this.handler = handler;
        items = new ArrayList<Item>();
    }

    public void tick(){
        Iterator<Item> it = items.iterator();
        while(it.hasNext()){
            Item i = it.next();
            i.tick();
            if(i.getCount() == Item.PICKED_UP){ // neu so luong bang so am thi xoa no khoi list
                it.remove();

            }
        }
    }
    public void render(Graphics g){
        for(Item i:items){
            i.render(g);
        }

    }



    public void addItem(Item i){
        i.setHandler(handler);
        items.add(i);
    }
    // get and set
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
