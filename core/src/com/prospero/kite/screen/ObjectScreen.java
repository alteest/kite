package com.prospero.kite.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.prospero.kite.Kite;
import com.prospero.kite.model.GO;

public abstract class ObjectScreen implements Screen, InputProcessor {

	protected final Kite game;
	GO object = null;
	public GO selectedObj = null;
	
	protected SpriteBatch batch = null;
	protected Stage stage = new Stage();
	protected StringBuilder stringBuilder;
	protected Label label;
	
	public ObjectScreen(final Kite game) {
		this.game = game;
		//Gdx.input.setCatchBackKey(true);
		batch = new SpriteBatch();
		stage.addActor(new SpaceSystemBackgroundActor());

		stringBuilder = new StringBuilder();
        label = new Label(" ", game.getSkin());
        stage.addActor(label);
		//Gdx.input.setCatchBackKey(true);
		Gdx.input.setInputProcessor(this);
	}

	public ObjectScreen(final Kite game, GO obj) {
		this(game);
		this.object = obj;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (stringBuilder != null) {
        	stringBuilder.setLength(0);
        	stringBuilder.append(" FPS: ").append(Gdx.graphics.getFramesPerSecond());
        	if (selectedObj != null) {
        		stringBuilder.append(" : ").append(selectedObj.getName());
        	}
        	label.setText(stringBuilder);
        }
        if (stage != null) {
        	stage.draw();
        }
        
        ShapeRenderer renderer = new ShapeRenderer();
        renderer.begin(ShapeType.Line);
        object.draw(renderer, object);
        renderer.end();
        
        batch.begin();
        object.draw(batch, object);
        batch.end();
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
		stage.dispose();
		batch.dispose();
		game.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
}
