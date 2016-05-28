package com.prospero.kite.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.prospero.kite.Kite;
import com.prospero.kite.model.GO;
import com.prospero.kite.model.SpaceSystem;

public class SpaceSystemScreen extends ObjectScreen {

	private SpaceSystem spaceSystem;

	private Stage stage;
	private Label label;
	private BitmapFont font;
	private StringBuilder stringBuilder;

	public PerspectiveCamera cam;
    public CameraInputController camController;
    public Shader shader;
    public RenderContext renderContext;
    public Model model;
    public Renderable renderable;
    Texture texture;

	protected ModelBatch modelBatch;
	protected Environment environment;
	private GO selectedObj = null;
	
	public SpaceSystemScreen(final Kite game) {
		super(game);

		SpaceSystemBackgroundActor background = new SpaceSystemBackgroundActor();
        background.setPosition(0, 0);	
		
        stage = new Stage();
        font = new BitmapFont();
        label = new Label(" ", new Label.LabelStyle(font, Color.WHITE));
        stage.addActor(background);
        stage.addActor(label);
        stringBuilder = new StringBuilder();
        
        //stage = new Stage(new ScreenViewport());
        //stage.addActor(background);

        //cam = new SpaceSystemCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(6f, 8f, 6f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
         
        //camController = new CameraInputController(cam);
        camController = new SpaceSystemCameraInputController(cam);
        //InputMultiplexer im = new InputMultiplexer();
        //im.addProcessor(camController);
        Gdx.input.setInputProcessor(new InputMultiplexer(this, camController));

		modelBatch = new ModelBatch();
		//environment = new Environment();
		//environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		//environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		//Gdx.input.setCatchBackKey(true);
		
		spaceSystem = new SpaceSystem();
		spaceSystem.generate();
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
        camController.update();
        
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stringBuilder.setLength(0);
        stringBuilder.append(" FPS: ").append(Gdx.graphics.getFramesPerSecond());
        if (selectedObj != null) {
        	stringBuilder.append(" Selected: ").append(selectedObj.getName());
        }
        label.setText(stringBuilder);
        stage.draw();        

        modelBatch.begin(cam);
		spaceSystem.render(modelBatch, environment);
        modelBatch.end();
        
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
		modelBatch.dispose();
		super.dispose();
	}


	@Override
	public boolean keyDown(int keycode) {
        if(keycode == Keys.BACK){
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
		//if (selectedObj != null) {
		//	if (selectedObj.getClass() == Planet.class) {
		//		game.setScreen(new PlanetScreen(game, (Planet) selectedObj));
		//	}
		//}
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
		System.out.print("scrolled");
		// TODO Auto-generated method stub
		return false;
	}

    public GO getObject (int screenX, int screenY) {
        Ray ray = cam.getPickRay(screenX, screenY);
        return spaceSystem.getObject(ray);
    }	
}
