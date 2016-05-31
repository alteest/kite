package com.prospero.kite.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.prospero.kite.model.StationType;

public class StationTypeSelectionMenuClickListener extends ClickListener {

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Gdx.input.vibrate(20);
        return true;
    };
    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
    	event.getListenerActor();
    	System.out.println("test");
    	//Button buttn = (Button) event.getTarget();
    	//StationType.valueOf(buttn.getName());
    };
	
}
