package com.prospero.kite.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.MathUtils;
import com.prospero.kite.model3d.ModelFactory;

public class Planet extends Sphere {

	private float distance = 0;
	ModelInstance instance2 = null;
	
	public Planet(String name, String texture, float distance, float position, float r) {
		super(name, texture, distance * MathUtils.sin(position), 0f, distance * MathUtils.cos(position), r);
		this.distance = distance;
		loadModel();
	}

	@Override
	protected void loadModel() {
		super.loadModel();
        instance2 = new ModelInstance(ModelFactory.getCircle(distance), 0, 0, 0);
        instance2.materials.get(0).set(new ColorAttribute(ColorAttribute.Diffuse, Color.WHITE));
	}

	@Override
	public void render(ModelBatch modelBatch, Environment environment) {
		modelBatch.render(instance, shader);
		modelBatch.render(instance2);
	}
}
