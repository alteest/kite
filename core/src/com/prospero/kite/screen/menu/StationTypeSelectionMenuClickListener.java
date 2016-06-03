package com.prospero.kite.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.prospero.kite.model.Planet;
import com.prospero.kite.model.Station;
import com.prospero.kite.model.StationType;
import com.prospero.kite.model3d.StationFactory;
import com.prospero.kite.screen.old3d.PlanetScreen3d;

public class StationTypeSelectionMenuClickListener extends ClickListener {

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Gdx.input.vibrate(20);
        return true;
    };
    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
    	StationType type = (StationType) event.getListenerActor().getUserObject();
    	StationTypeSelectionMenu menu = (StationTypeSelectionMenu) event.getListenerActor().getParent();
    	PlanetScreen3d screen = (PlanetScreen3d) menu.screen;
    	Planet planet = (Planet) screen.selectedObj.getParent();
    	planet.replaceStation((Station) screen.selectedObj, StationFactory.getStation(type, (Station) screen.selectedObj));
    	screen.selectedObj = null;
    };
	
}
