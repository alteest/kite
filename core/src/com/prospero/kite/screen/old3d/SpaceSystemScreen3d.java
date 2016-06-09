package com.prospero.kite.screen.old3d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
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
import com.prospero.kite.screen.MainMenuScreen;
import com.prospero.kite.screen.Background;

public class SpaceSystemScreen3d extends ObjectScreen3d {

	public SpaceSystemScreen3d(final Kite game, SpaceSystem spaceSystem) {
		super(game, spaceSystem);

		Background background = new Background("");
        background.setPosition(0, 0);	
		
        stage = new Stage();
        font = new BitmapFont();
        label = new Label(" ", new Label.LabelStyle(font, Color.WHITE));
        stage.addActor(background);
        stage.addActor(label);
        stringBuilder = new StringBuilder();
        
        //stage = new Stage(new ScreenViewport());
        //stage.addActor(background);

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(6f * Kite.multi, 8f * Kite.multi, 6f * Kite.multi);
        cam.lookAt(0, 0, 0);
        cam.near = 1f * Kite.multi;
        cam.far = 300f * Kite.multi;
        cam.update();
         
        camController = new SpaceSystemCameraInputController(cam, 5f * Kite.multi, 25f * Kite.multi);
        camController.lookAt3 = new Vector3(0f, 0f, 0f);
        camController.scrollFactor = camController.scrollFactor * Kite.multi;
        Gdx.input.setInputProcessor(new InputMultiplexer(this, camController));

		modelBatch = new ModelBatch();
		//environment = new Environment();
		//environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		//environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		//Gdx.input.setCatchBackKey(true);
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
        if(keycode == Keys.BACK || keycode == Keys.ESCAPE){
        	game.setScreen(new MainMenuScreen(game));
        	return true;
            //if (shouldReallyQuit)
            //  Gdx.app.exit();
         }
         return false;
	}


	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		selectedObj = getObject(screenX, screenY);
		return selectedObj != null;
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (selectedObj != null) {
			if (selectedObj.getClass() == Planet.class) {
				//game.setScreen(new PlanetScreen(game, (Planet) selectedObj));
				game.setScreen(new StaticPlanetScreen3d(game, (Planet) selectedObj));
			}
		}
		return false;
	}


	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

    public GO getObject (int screenX, int screenY) {
        Ray ray = cam.getPickRay(screenX, screenY);
        //return ((SpaceSystem) object).getObject(ray);
        return null;
    }	
}
