package com.prospero.kite.screen.old3d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.prospero.kite.Kite;
import com.prospero.kite.model.Planet;
import com.prospero.kite.screen.SpaceSystemBackgroundActor;

public class StaticPlanetScreen3d extends ObjectScreen3d {

	private CameraInputController camController2;
	private Stage menuStage = new Stage();
	private InputMultiplexer inputController;
	private ModelInstance planetInstance;

	private AssetManager assetManager;
	private boolean loading;
	
	public StaticPlanetScreen3d(final Kite game, final Planet planet) {
		super(game, planet);

		SpaceSystemBackgroundActor background = new SpaceSystemBackgroundActor();
        background.setPosition(0, 0);	
		
        stage = new Stage();
        font = new BitmapFont();
        label = new Label(" ", new Label.LabelStyle(font, Color.WHITE));
        stage.addActor(background);
        stage.addActor(label);
        stringBuilder = new StringBuilder();

        /*float r = planet.getRadius();
        float dist = 2 * r;
        float d = r / 3;
        float degree = (float) Math.toDegrees(Math.asin(d / dist));
        cam = new DegreePerspectiveCamera(2 * degree, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ((DegreePerspectiveCamera) cam).setInitValues(new Vector3(0f, -dist, r), 45f);
        cam.lookAt(0f, 0f, r);
        cam.near = 0.2f * Kite.multi;
        cam.far = 20 * dist;
        cam.update();*/
        
        /*camController = new SpaceSystemCameraInputController(cam, 5 * dist / 6, 8000 * dist / 6);
        camController.lookAt2 = new Vector2(0f, r);
        camController.scrollFactor = camController.scrollFactor * 50;
        camController.rotateAngle = 90;*/

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(3f, 4f, 3f);
        cam.lookAt(0, 0, 0);
        cam.near = 0.1f;
        cam.far = 300000f;
        cam.update();
         
        camController2 = new CameraInputController(cam);
        camController2.scrollFactor = camController2.scrollFactor;
        Gdx.input.setInputProcessor(new InputMultiplexer(this, camController2));
        
        inputController = new InputMultiplexer(this, camController2);
        Gdx.input.setInputProcessor(inputController);

		modelBatch = new ModelBatch();

		String modelName = "models/AlienPlanet/AlienPlanet.g3dj";
		//String modelName = "models/Naboo/naboo.g3db";
	    assetManager = new AssetManager();
	    assetManager.load(modelName, Model.class);
	    assetManager.finishLoadingAsset(modelName);
		assetManager.finishLoading();

	    model = assetManager.get(modelName);

	    loading = true;
	    
		//ModelLoader loader = new ObjLoader();
        //model = loader.loadModel(Gdx.files.internal("models/Naboo/naboo.obj"));
        planetInstance = new ModelInstance(model, 0f, 0f, 0f);
		
	}

	private void doneLoading() {
	    Model test = assetManager.get("models/Naboo/naboo.obj", Model.class);
	    planetInstance = new ModelInstance(test);
	    loading = false;
	}
	
	@Override
	public void render(float delta) {
	    //if (loading && assetManager.update()) {
	    //   doneLoading();
	    //}
	    
		camController2.update();
        
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        /*if (stringBuilder != null) {
        	stringBuilder.setLength(0);
        	stringBuilder.append(" FPS: ").append(Gdx.graphics.getFramesPerSecond());
        	label.setText(stringBuilder);
        }
        if (stage != null) {
        	stage.draw();
        }*/

        if (modelBatch != null && planetInstance != null) {
        	modelBatch.begin(cam);
        	modelBatch.render(planetInstance);
        	//object.render(modelBatch, environment, object);
        	modelBatch.end();
        }
	}		
	
}
