package com.prospero.kite.screen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;

public class SpaceSystemCameraInputController extends CameraInputController {
	private final float minDistance;
	private final float maxDistance;

	public SpaceSystemCameraInputController (final Camera camera, final float minDistance, final float maxDistance) {
		super(camera);
		this.minDistance = minDistance;
		this.maxDistance = maxDistance;
	}

	@Override
	public boolean zoom (float amount) {
		Vector3 newPos = camera.position.cpy().add(new Vector3().set(camera.direction).scl(amount));
		float distance = newPos.dst(0f, 0f, 0f);
		if ((distance > minDistance) && (distance < maxDistance)) {
			return super.zoom(amount);
		}
		return true;
	}

	@Override
	protected boolean process(float deltaX, float deltaY, int button) {
		if (button == rotateButton) {
			deltaY = 0f;
			//float degree = Math.toDegrees(Math.atan2(target.y - y, target.x - x));
		}
	    return super.process(deltaX, deltaY, button);
	}
}
