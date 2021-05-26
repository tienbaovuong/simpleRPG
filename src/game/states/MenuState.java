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
    private boolean isActive=false;
    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        
        uiManager.addObject(new UIImageButton(336, 300, 128, 64, Assets.btn_start, () -> {
            //dispose();
        	if(isActive)
        	c = new ChooseState(handler);
            
        }));
        
        uiManager.addObject(new UIImageButton(336, 364, 128, 64, Assets.how_to_play_button, () ->{
        	if(isActive) {
            try {   
		h=new HowToPlayState(handler);
		//dispose();
            } catch (IOException e) {
		e.printStackTrace();
            }
        }}));
        
        uiManager.addObject(new UIImageButton(336, 428, 128, 64, Assets.quit_button, () ->{
        	if(isActive)
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
        new Score(0,0, 0);
        long highestScore = Score.getHighestScoreFromFile();
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.drawString("HighScore: "+ highestScore, 275, 250);
    }

    public UIManager getUiManager() {
        return uiManager;
    }

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
    
    
    
}

