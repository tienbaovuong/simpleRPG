package game.tiles;

import game.gfx.Assets;

public class RockTile extends Tile {

    public RockTile(int id) {
        super(Assets.bridge, id);
        solid = false;
    }
    
}
