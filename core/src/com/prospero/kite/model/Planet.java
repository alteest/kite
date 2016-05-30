package com.prospero.kite.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.prospero.kite.model3d.ModelFactory;

public class Planet extends Sphere {

	private float distance = 0;
	ModelInstance orbit = null;
	protected Array<Station> stations = new Array<Station>();
	
	public Planet(String name, String texture, float distance, float position, float r) {
		super(name, texture, distance * MathUtils.sin(position), 0f, distance * MathUtils.cos(position), r);
		this.distance = distance;
		loadModel();
		
		addStation(new Station("Station1", r + 1f, 90f, 0.2f));
	}

	public void addStation(Station station) {
		station.setParent(this);
		stations.add(station);
	}
	
	@Override
	protected void loadModel() {
		super.loadModel();
        orbit = new ModelInstance(ModelFactory.getCircle(distance), 0, 0, 0);
        orbit.materials.get(0).set(new ColorAttribute(ColorAttribute.Diffuse, Color.WHITE));
	}

	@Override
	public void render(ModelBatch modelBatch, Environment environment, GO parent) {
		//modelBatch.render(instance, shader);
		if (this.equals(parent)) {
			instance.transform.setToTranslation(0, 0, 0);
			modelBatch.render(instance);
			for (Station station : stations) {
				station.render(modelBatch, environment, parent);
			}
		} else {
			instance.transform.setToTranslation(x, y, z);
			modelBatch.render(instance);
			modelBatch.render(orbit);
		}
	}
}
