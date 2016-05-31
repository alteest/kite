package com.prospero.kite.screen;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class DegreePerspectiveCamera extends PerspectiveCamera {

	private final Vector3 tmpVec = new Vector3();
	private Vector2 start = null;
	private float degree = 0;
	
	public DegreePerspectiveCamera(float fieldOfViewY, float viewportWidth, float viewportHeight) {
		super(fieldOfViewY, viewportWidth, viewportHeight);
	}

	public void setInitValues(Vector3 start, float degree) {
		this.start = new Vector2(start.x, start.z);
		this.position.set(start);
		this.degree = degree; 
	}
	
	@Override
	public void rotateAround (Vector3 point, Vector3 axis, float angle) {
		if (start == null) {
			super.rotateAround(point, axis, angle);
		}
		if (angle == 0f) {
			return;
		}
		Vector3 newPos = position.cpy();
		tmpVec.set(point);
		tmpVec.sub(position);
		newPos.add(tmpVec);
		tmpVec.rotate(axis, angle);
		newPos.add(-tmpVec.x, -tmpVec.y, -tmpVec.z);
		float d = start.angle(new Vector2(newPos.x, newPos.z));
		if (d < degree && d > -degree) {
			rotate(axis, angle);
			position.set(newPos);
		}
	}
}
