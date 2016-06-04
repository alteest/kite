package com.prospero.kite.screen.old3d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.prospero.kite.Kite;
import com.prospero.kite.model.GO;
import com.prospero.kite.model.Planet;
import com.prospero.kite.model.SpaceSystem;
import com.prospero.kite.model.Station;
import com.prospero.kite.model.StationType;
import com.prospero.kite.screen.SpaceSystemBackgroundActor;
import com.prospero.kite.screen.menu.StationTypeSelectionMenu;

public class PlanetScreen3d extends ObjectScreen3d {

	private Stage menuStage = new Stage();
	private InputMultiplexer inputController;
	
	public PlanetScreen3d(final Kite game, final Planet planet) {
		super(game, planet);

		SpaceSystemBackgroundActor background = new SpaceSystemBackgroundActor();
        background.setPosition(0, 0);	
		
        stage = new Stage();
        font = new BitmapFont();
        label = new Label(" ", new Label.LabelStyle(font, Color.WHITE));
        stage.addActor(background);
        stage.addActor(label);
        stringBuilder = new StringBuilder();

/*        float r = planet.getRadius();
        float dist = 2 * r;
        float d = r / 3;
        float degree = (float) Math.toDegrees(Math.asin(d / dist));
        cam = new DegreePerspectiveCamera(2 * degree, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ((DegreePerspectiveCamera) cam).setInitValues(new Vector3(0f, -dist, r), 45f);
        cam.lookAt(0f, 0f, r);
        cam.near = 0.2f * Kite.multi;
        cam.far = 20 * dist;
        cam.update();
        
        camController = new SpaceSystemCameraInputController(cam, 5 * dist / 6, 8000 * dist / 6);
        camController.lookAt2 = new Vector2(0f, r);
        camController.scrollFactor = camController.scrollFactor * 50;
        camController.rotateAngle = 90;

        inputController = new InputMultiplexer(this, camController);*/

		modelBatch = new ModelBatch();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (selectedObj != null && selectedObj.getClass() == Station.class) {
			menuStage.clear();
			StationTypeSelectionMenu menu = new StationTypeSelectionMenu("", game.getSkin(), this);
			menuStage.addActor(menu);
	        Gdx.input.setInputProcessor(menuStage);
			menuStage.draw();
		} else {
	        Gdx.input.setInputProcessor(inputController);
		}
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		selectedObj = getObject(screenX, screenY);
		return selectedObj != null;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (selectedObj != null) {
			if (selectedObj instanceof Station) {
				Station station = (Station) selectedObj;
				if (station.getStationType() != StationType.Unknown) {
					game.setScreen(new StationScreen3d(game, station));
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean keyDown(int keycode) {
        if((keycode == Keys.BACK) || (keycode == Keys.ESCAPE)) {
        	game.setScreen(new SpaceSystemScreen3d(game, (SpaceSystem) object.getParent()));
        	return true;
         }
         return false;
	}

    public GO getObject (int screenX, int screenY) {
        Ray ray = cam.getPickRay(screenX, screenY);
        return ((Planet) object).getObject(ray);
    }
}
