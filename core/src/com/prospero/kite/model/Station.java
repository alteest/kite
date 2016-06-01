package com.prospero.kite.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.MathUtils;
import com.prospero.kite.model3d.ModelFactory;

public class Station extends GO {

	ModelInstance orbit = null;
	protected final float distance;
	protected final float position;
	protected StationType type = StationType.Unknown;
	
	public Station(String name, float distance, float position, float r) {
		super(name, distance * MathUtils.sin(MathUtils.degreesToRadians * position), 0f, distance * MathUtils.cos(MathUtils.degreesToRadians * position), r);
		this.distance = distance;
		this.position = position;
		initModel();
	}

	protected void initModel() {
        instance = new ModelInstance(ModelFactory.getSphereLines(r), x, y, z);
        calculateCoordinates();

        orbit = new ModelInstance(ModelFactory.getCircle(distance), 0, 0, 0);
        orbit.materials.get(0).set(new ColorAttribute(ColorAttribute.Diffuse, Color.WHITE));
	}

	@Override
	public void render(ModelBatch modelBatch, Environment environment, GO parent) {
		if (this.equals(parent)) {
			instance.transform.setToTranslation(0, 0, 0);
			modelBatch.render(instance);
		} else {
			instance.transform.setToTranslation(x, y, z);
			modelBatch.render(instance);
			modelBatch.render(orbit);
		}
	}

	public StationType getStationType() {
		return type;
	}
}
