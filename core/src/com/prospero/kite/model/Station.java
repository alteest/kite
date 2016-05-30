package com.prospero.kite.model;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.MathUtils;
import com.prospero.kite.model3d.ModelFactory;

public class Station extends GO {

	public Station(String name, float distance, float position, float r) {
		super(name, distance * MathUtils.sin(position), 0f, distance * MathUtils.cos(position), r);
		initModel();
	}

	protected void initModel() {
        instance = new ModelInstance(ModelFactory.getSphereLines(r), x, y, z);
        calculateCoordinates();
	}
}
