package com.prospero.kite.model;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

public class Star extends Sphere {
	
	public Star(String name, String texture, final int r) {
		super(name, texture, 0f, 0f, r);
	}
	
	@Override
	public void render(ModelBatch modelBatch, Environment environment, GO parent) {
		//modelBatch.render(instance, shader);
		modelBatch.render(instance);
	}
}
