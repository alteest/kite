package com.prospero.kite.screen;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;

public class SpaceSystemCamera extends PerspectiveCamera {

	private final Vector3 tmpVec = new Vector3();
	
	public SpaceSystemCamera(float fieldOfViewY, float viewportWidth, float viewportHeight) {
		super(fieldOfViewY, viewportWidth, viewportHeight);
	}

	@Override
	public void rotateAround (Vector3 point, Vector3 axis, float angle) {
		tmpVec.set(point);
		tmpVec.sub(position);
		translate(tmpVec);
		rotate(axis, angle);
		tmpVec.rotate(axis, angle);
		translate(-tmpVec.x, -tmpVec.y, -tmpVec.z);
	}
	
}
