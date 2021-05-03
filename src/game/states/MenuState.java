package game.states;

import java.awt.*;
import java.io.IOException;

import game.Handler;
import game.entities.Entity;
import game.gfx.Assets;
import game.ui.UIImageButton;
import game.ui.UIManager;
import game.worlds.Score;

public class MenuState extends State {

    private UIManager uiManager;
    private ChooseState c;
    private HowToPlayState h;
    
    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        
        uiManager.addObject(new UIImageButton(336, 300, 128, 64, Assets.btn_start, () -> {
            c = new ChooseState(handler);
        }));
        
        uiManager.addObject(new UIImageButton(336, 364, 128, 64, Assets.how_to_play_button, () ->{
            try {
		h=new HowToPlayState(handler);
            } catch (IOException e) {
		e.printStackTrace();
            }
        }));
        
        uiManager.addObject(new UIImageButton(336, 428, 128, 64, Assets.quit_button, () ->{
            System.exit(0);
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();        
       
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.backgroundMenu,0,0,800,608,null);
        g.drawImage(Assets.logoMenu,225,112,371,76,null);

        uiManager.render(g);
        new Score(0,0);
        long highestScore = Score.getHighestScoreFromFile();
        g.setColor(Color.red);
        g.drawString("Highest Score: "+ highestScore, 350, 520);
    }

    public UIManager getUiManager() {
        return uiManager;
    }
    
    
    
}

