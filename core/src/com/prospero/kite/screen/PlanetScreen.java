package com.prospero.kite.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.prospero.kite.Kite;
import com.prospero.kite.model.GO;
import com.prospero.kite.model.Planet;
import com.prospero.kite.model.SpaceSystem;
import com.prospero.kite.model.Station;
import com.prospero.kite.screen.menu.StationTypeSelectionMenu;

public class PlanetScreen extends ObjectScreen {

	private Stage menuStage = new Stage();
	private InputMultiplexer inputController;
	
	public PlanetScreen(final Kite game, final Planet planet) {
		super(game, planet);

		SpaceSystemBackgroundActor background = new SpaceSystemBackgroundActor();
        background.setPosition(0, 0);	
		
        stage = new Stage();
        font = new BitmapFont();
        label = new Label(" ", new Label.LabelStyle(font, Color.WHITE));
        stage.addActor(background);
        stage.addActor(label);
        stringBuilder = new StringBuilder();

        float r = planet.getRadius();
        float dist = 2 * r;
        float d = r / 3;
        float degree = (float) Math.toDegrees(Math.asin(d / dist));
        cam = new DegreePerspectiveCamera(2 * degree, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ((DegreePerspectiveCamera) cam).setInitValues(new Vector3(0f, -2 * dist, r), 45f);
        cam.lookAt(0f, 0f, r);
        cam.near = 0.2f * Kite.multi;
        cam.far = 20 * dist;
        cam.update();
        
        camController = new SpaceSystemCameraInputController(cam, 3 * dist / 4, 50 * dist / 4);
        camController.scrollFactor = -r / 20f;
        camController.rotateAngle = 90;

        inputController = new InputMultiplexer(this, camController);

		modelBatch = new ModelBatch();
		
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (selectedObj != null) {
			menuStage.clear();
			StationTypeSelectionMenu menu = new StationTypeSelectionMenu("", game.getSkin(), (Station) selectedObj);
			menuStage.addActor(menu);
	        Gdx.input.setInputProcessor(menuStage);
			menuStage.draw();
		} else {
	        Gdx.input.setInputProcessor(new InputMultiplexer(this, camController));
		}
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		selectedObj = getObject(screenX, screenY);
		return selectedObj != null;
	}
	
	@Override
	public boolean keyDown(int keycode) {
        if((keycode == Keys.BACK) || (keycode == Keys.ESCAPE)) {
        	game.setScreen(new SpaceSystemScreen(game, (SpaceSystem) object.getParent()));
        	return true;
         }
         return false;
	}

    public GO getObject (int screenX, int screenY) {
        Ray ray = cam.getPickRay(screenX, screenY);
        return ((Planet) object).getObject(ray);
    }
}
