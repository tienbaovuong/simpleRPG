package game.states;


import game.Handler;
import game.gfx.Assets;
import game.ui.UIImageButton;
import game.ui.UIManager;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

public class PauseState extends State{
    private UIManager uiManager;
    public PauseState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        
        uiManager.addObject(new UIImageButton(336, 300, 128, 64, Assets.btn_start, () -> {
        	if(isActive) {
        	State.setState(handler.getGame().gameState);
            handler.getGame().gameState.setActive(true);         
            this.isActive=false;
        	}}));             
        
        uiManager.addObject(new UIImageButton(336, 364, 128, 64, Assets.quit_button, () ->{
        	if(isActive) {
        		handler.getGame().menuState=new MenuState(handler);
        		State.setState(handler.getGame().menuState);
                handler.getGame().menuState.setActive(true);
                this.isActive=false;
        	}}));
       
    }	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		uiManager.tick();
	}
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		handler.getGame().gameState.render(g);
		g.setColor(Color.GRAY);
		g.fillRect(336,300, 128, 128);
		uiManager.render(g);
	}


}