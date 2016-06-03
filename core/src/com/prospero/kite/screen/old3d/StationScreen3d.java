package com.prospero.kite.screen.old3d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.prospero.kite.Kite;
import com.prospero.kite.model.Planet;
import com.prospero.kite.model.Station;
import com.prospero.kite.screen.menu.StationParametersWindow;

public class StationScreen3d extends ObjectScreen3d {

	public StationScreen3d(final Kite game, Station station) {
		super(game, station);

        stage = new Stage();
        stage.addActor(new StationParametersWindow(game.getSkin(), station));
		
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(6f, 8f, 3f);
        cam.lookAt(0, 0, -3f);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
        
        camController = new SpaceSystemCameraInputController(cam, 1f, 300f);
        camController.rotateAngle = 90;

        Gdx.input.setInputProcessor(new InputMultiplexer(this, camController));

		modelBatch = new ModelBatch();
	}

	@Override
	public boolean keyDown(int keycode) {
        if((keycode == Keys.BACK) || (keycode == Keys.ESCAPE)) {
        	if (object.getParent() != null) {
        		game.setScreen(new PlanetScreen3d(game, (Planet) object.getParent()));
        		return true;
        	}
         }
         return false;
	}

	@Override
	public void render(float delta) {
		super.render(delta);
	}
	
}
