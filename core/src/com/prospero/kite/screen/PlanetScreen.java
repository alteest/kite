package com.prospero.kite.screen;

import com.prospero.kite.Kite;
import com.prospero.kite.model.Planet;

public class PlanetScreen extends ObjectScreen {

	private final Planet planet;
	
	public PlanetScreen(final Kite game, final Planet planet) {
		super(game);
		this.planet = planet;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
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
}
