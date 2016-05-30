package com.prospero.kite.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.prospero.kite.Kite;
import com.prospero.kite.model.Planet;
import com.prospero.kite.model.SpaceSystem;

public class PlanetScreen extends ObjectScreen {

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

        float dist = 2 * planet.getRadius();
        float d = planet.getRadius() / 3;
        cam = new PerspectiveCamera(9.6f, planet.getRadius(), 2 * d);
        cam.position.set(planet.getRadius() - d, dist, 0f);
        cam.lookAt(0f, planet.getRadius(), 0f);
        cam.near = 0.1f;
        cam.far = 300f;
        cam.update();
         
        camController = new SpaceSystemCameraInputController(cam, 3 * dist / 4, 50 * dist / 4);
        //camController.scrollFactor = -planet.getRadius() / 200f;
        camController.scrollFactor = -planet.getRadius() / 20f;
        camController.rotateAngle = -90;
        Gdx.input.setInputProcessor(new InputMultiplexer(this, camController));

		modelBatch = new ModelBatch();
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
	public boolean keyDown(int keycode) {
        if((keycode == Keys.BACK) || (keycode == Keys.ESCAPE)) {
        	game.setScreen(new SpaceSystemScreen(game, (SpaceSystem) object.getParent()));
        	return true;
            //if (shouldReallyQuit)
            //  Gdx.app.exit();
         }
         return false;
	}
}
