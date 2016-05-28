package com.prospero.kite.model;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

public class Star extends Sphere {
	
	public Star(String name, String texture, float r) {
		super(name, texture, 0f, 0f, 0f, r);
		loadModel();
	}
	
	@Override
	public void render(ModelBatch modelBatch, Environment environment) {
		//modelBatch.render(instance, shader);
		modelBatch.render(instance);
	}
}
