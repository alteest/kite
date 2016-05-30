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
        
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(0f, planet.getRadius(), 10f);
        cam.lookAt(0f, planet.getRadius(), 0f);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
         
        camController = new SpaceSystemCameraInputController(cam, 3 * planet.getRadius() / 4, 5 * planet.getRadius() / 4);
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
        if(keycode == Keys.BACK){
        	game.setScreen(new SpaceSystemScreen(game, (SpaceSystem) object.getParent()));
        	return true;
            //if (shouldReallyQuit)
            //  Gdx.app.exit();
         }
         return false;
	}
}
