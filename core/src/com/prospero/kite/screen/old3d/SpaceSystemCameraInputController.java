package com.prospero.kite.screen.old3d;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class SpaceSystemCameraInputController extends CameraInputController {
	private final float minDistance;
	private final float maxDistance;
	
	public Vector3 lookAt3 = null;
	public Vector2 lookAt2 = null;

	public SpaceSystemCameraInputController (final Camera camera, final float minDistance, final float maxDistance) {
		super(camera);
		this.minDistance = minDistance;
		this.maxDistance = maxDistance;
	}

	@Override
	public boolean zoom(float amount) {
		Vector3 newPos = camera.position.cpy().add(new Vector3().set(camera.direction).scl(amount));
		float distance = 0f;
		if (lookAt3 != null) {
			distance = newPos.dst(lookAt3);
		} else if (lookAt2 != null) {
			distance = newPos.dst(lookAt2.x, 0, lookAt2.y);
		} else {
			return false;
		}
		if ((distance > minDistance) && (distance < maxDistance)) {
			return super.zoom(amount);
		}
		return true;
	}

	@Override
	protected boolean process(float deltaX, float deltaY, int button) {
		if (button == rotateButton) {
			deltaY = 0f;
		}
	    return super.process(deltaX, deltaY, button);
	}
}
